package app.workout.service;

import app.exception.ResourceNotFoundException;
import app.exercise.model.Exercise;
import app.exercise.model.ExerciseType;
import app.exercise.service.ExerciseService;
import app.routine.model.Routine;
import app.routine.model.RoutineExercise;
import app.routine.model.RoutineSetTarget;
import app.routine.repository.RoutineRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.workout.WorkoutRequest;
import app.web.dto.workout.WorkoutResponse;
import app.web.dto.workoutSet.AddWorkoutSetRequest;
import app.web.dto.workoutSet.UpdateWorkoutSetRequest;
import app.web.dto.workoutSet.WorkoutSetResponse;
import app.workout.model.Workout;
import app.workout.repository.WorkoutRepository;
import app.workoutset.model.WorkoutSet;
import app.workoutset.repository.WorkoutSetRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final RoutineRepository routineRepository;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutSetRepository workoutSetRepository,
                           UserService userService, ExerciseService exerciseService, RoutineRepository routineRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutSetRepository = workoutSetRepository;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.routineRepository = routineRepository;
    }

    public WorkoutResponse startWorkout(WorkoutRequest request, UUID userId) {
        User user = userService.getUserById(userId);
        Routine routine = null;

        if (request.routineId() != null) {
            routine = routineRepository.findById(request.routineId())
                    .orElseThrow(() -> new ResourceNotFoundException("Routine not found"));

            if (!routine.getUser().getId().equals(userId)) {
                throw new AccessDeniedException("You don't have access to this routine");
            }
        }

        Workout workout = Workout.builder()
                .user(user)
                .routine(routine)
                .name(request.name() != null ? request.name() : (routine != null ? routine.getName() : "Workout"))
                .startedAt(LocalDateTime.now())
                .build();

        Workout savedWorkout = workoutRepository.save(workout);
        List<WorkoutSet> savedSets = new ArrayList<>();

        if (routine != null) {
            for (RoutineExercise routineExercise : routine.getExercises()) {
                for (RoutineSetTarget target : routineExercise.getTargetSets()) {
                    WorkoutSet workoutSet = WorkoutSet.builder()
                            .workout(savedWorkout)
                            .exercise(routineExercise.getExercise())
                            .setNumber(target.getSetNumber())
                            .weight(target.getTargetWeight())
                            .reps(target.getTargetRepsMin())
                            .durationSeconds(target.getTargetDurationSeconds())
                            .exerciseOrder(routineExercise.getExerciseOrder())
                            .supersetGroupId(routineExercise.getSupersetGroupId())
                            .setType(target.getSetType())
                            .completed(false)
                            .build();

                    savedSets.add(workoutSetRepository.save(workoutSet));
                }
            }
        }

        savedWorkout.setWorkoutSets(savedSets);

        return DtoMapper.toWorkoutResponse(savedWorkout);
    }

    public List<WorkoutResponse> getWorkoutHistory(UUID userId) {
        User user = userService.getUserById(userId);

        return workoutRepository.findAllByUserOrderByStartedAtDesc(user)
                .stream()
                .map(DtoMapper::toWorkoutResponse)
                .toList();
    }

    public WorkoutResponse getWorkoutById(UUID workoutId, UUID userId) {
        return DtoMapper.toWorkoutResponse(getOwnedWorkout(workoutId, userId));
    }

    public void deleteWorkout(UUID workoutId, UUID userId) {
        Workout workout = getOwnedWorkout(workoutId, userId);
        workoutRepository.delete(workout);
    }

    public WorkoutResponse finishWorkout(UUID workoutId, UUID userId) {
        Workout workout = getOwnedWorkout(workoutId, userId);

        if (workout.getFinishedAt() != null) {
            throw new IllegalArgumentException("Workout is already finished");
        }

        workout.setFinishedAt(LocalDateTime.now());
        return DtoMapper.toWorkoutResponse(workoutRepository.save(workout));
    }

    public WorkoutSetResponse addSet(UUID workoutId, AddWorkoutSetRequest request, UUID userId) {
        Workout workout = getOwnedWorkout(workoutId, userId);

        if (workout.getFinishedAt() != null) {
            throw new IllegalArgumentException("Cannot add a set to a finished workout");
        }

        Exercise exercise = exerciseService.getExerciseById(request.exerciseId());

        Integer setNumber = request.setNumber() != null
                ? request.setNumber()
                : workoutSetRepository.findAllByWorkoutAndExercise_Id(workout, exercise.getId()).size() + 1;

        WorkoutSet workoutSet = WorkoutSet.builder()
                .workout(workout)
                .exercise(exercise)
                .setNumber(setNumber)
                .weight(request.weight())
                .reps(request.reps())
                .durationSeconds(request.durationSeconds())
                .rpe(request.rpe())
                .restSeconds(request.restSeconds())
                .supersetGroupId(request.supersetGroupId())
                .exerciseOrder(request.exerciseOrder())
                .setType(request.setType())
                .completed(false)
                .build();

        return DtoMapper.toWorkoutSetResponse(workoutSetRepository.save(workoutSet));
    }

    public WorkoutSetResponse updateSet(UUID workoutId, UUID setId, UpdateWorkoutSetRequest request, UUID userId) {
        Workout workout = getOwnedWorkout(workoutId, userId);
        WorkoutSet workoutSet = getOwnedSet(workout, setId);

        if (request.weight() != null) {
            workoutSet.setWeight(request.weight());
        }
        if (request.reps() != null) {
            workoutSet.setReps(request.reps());
        }
        if (request.durationSeconds() != null) {
            workoutSet.setDurationSeconds(request.durationSeconds());
        }
        if (request.rpe() != null) {
            workoutSet.setRpe(request.rpe());
        }
        if (request.restSeconds() != null) {
            workoutSet.setRestSeconds(request.restSeconds());
        }
        if (request.setType() != null) {
            workoutSet.setSetType(request.setType());
        }
        if (request.completed() != null) {
            workoutSet.setCompleted(request.completed());
            workoutSet.setCompletedAt(request.completed() ? LocalDateTime.now() : null);
        }

        evaluatePersonalRecord(workoutSet, userId);

        return DtoMapper.toWorkoutSetResponse(workoutSetRepository.save(workoutSet));
    }

    private void evaluatePersonalRecord(WorkoutSet workoutSet, UUID userId) {
        if (!workoutSet.isCompleted()) {
            workoutSet.setPersonalRecord(false);
            return;
        }

        UUID exerciseId = workoutSet.getExercise().getId();
        ExerciseType exerciseType = workoutSet.getExercise().getExerciseType();

        boolean isPersonalRecord = switch (exerciseType) {
            case WEIGHT_REPS, WEIGHTED_BODYWEIGHT -> {
                Double maxWeight = workoutSetRepository.findMaxWeight(exerciseId, userId, workoutSet.getId());
                yield workoutSet.getWeight() != null && (maxWeight == null || workoutSet.getWeight() > maxWeight);
            }
            case REPS_ONLY, BODYWEIGHT -> {
                Integer maxReps = workoutSetRepository.findMaxReps(exerciseId, userId, workoutSet.getId());
                yield workoutSet.getReps() != null && (maxReps == null || workoutSet.getReps() > maxReps);
            }
            case DURATION, WEIGHT_DURATION -> {
                Integer maxDuration = workoutSetRepository.findMaxDuration(exerciseId, userId, workoutSet.getId());
                yield workoutSet.getDurationSeconds() != null && (maxDuration == null || workoutSet.getDurationSeconds() > maxDuration);
            }
            default -> false;
        };

        workoutSet.setPersonalRecord(isPersonalRecord);
    }

    public void deleteSet(UUID workoutId, UUID setId, UUID userId) {
        Workout workout = getOwnedWorkout(workoutId, userId);
        WorkoutSet workoutSet = getOwnedSet(workout, setId);
        workoutSetRepository.delete(workoutSet);
    }

    private Workout getOwnedWorkout(UUID workoutId, UUID userId) {
        Workout workout = getWorkoutEntity(workoutId);

        if (!workout.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this workout");
        }

        return workout;
    }

    private Workout getWorkoutEntity(UUID workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
    }

    private WorkoutSet getOwnedSet(Workout workout, UUID setId) {
        return workout.getWorkoutSets().stream()
                .filter(set -> set.getId().equals(setId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Set not found in this workout"));
    }
}
