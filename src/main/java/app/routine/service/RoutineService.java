package app.routine.service;

import app.exception.ResourceNotFoundException;
import app.exercise.model.Exercise;
import app.exercise.model.ExerciseType;
import app.exercise.service.ExerciseService;
import app.routine.model.Routine;
import app.routine.model.RoutineExercise;
import app.routine.model.RoutineSetTarget;
import app.routine.repository.RoutineExerciseRepository;
import app.routine.repository.RoutineRepository;
import app.routine.repository.RoutineSetTargetRepository;
import app.web.dto.routineExercise.RoutineExerciseResponse;
import app.web.dto.routineExercise.UpdateRoutineExerciseRequest;
import app.workout.model.Workout;
import app.workout.repository.WorkoutRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.routine.RoutineRequest;
import app.web.dto.routine.RoutineResponse;
import app.web.dto.routine.UpdateRoutineRequest;
import app.web.dto.routineExercise.RoutineExerciseRequest;
import app.web.dto.routineSetTarget.RoutineSetTargetRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final RoutineSetTargetRepository routineSetTargetRepository;
    private final WorkoutRepository workoutRepository;

    public RoutineService(RoutineRepository routineRepository, UserService userService, ExerciseService exerciseService, RoutineExerciseRepository routineExerciseRepository, RoutineSetTargetRepository routineSetTargetRepository, WorkoutRepository workoutRepository) {
        this.routineRepository = routineRepository;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.routineExerciseRepository = routineExerciseRepository;
        this.routineSetTargetRepository = routineSetTargetRepository;
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public RoutineResponse createRoutine(RoutineRequest request, UUID userId) {
        User user = userService.getUserById(userId);

        // Validate the whole request before persisting anything, so a bad set
        // can't leave a half-created routine behind.
        for (RoutineExerciseRequest exerciseRequest : request.exercises()) {
            Exercise requestedExercise = exerciseService.getExerciseById(exerciseRequest.exerciseId());
            for (RoutineSetTargetRequest setRequest : exerciseRequest.sets()) {
                validateSetTarget(setRequest, requestedExercise.getExerciseType());
            }
        }

        Routine routine = Routine.builder()
                .name(request.name())
                .notes(request.notes())
                .user(user)
                .folderOrder(request.folderOrder())
                .build();

        Routine savedRoutine = routineRepository.save(routine);
        List<RoutineExercise> savedExercises = new ArrayList<>();

        for (RoutineExerciseRequest exerciseRequest : request.exercises()) {
            Exercise exercise = exerciseService.getExerciseById(exerciseRequest.exerciseId());

            RoutineExercise routineExercise = RoutineExercise.builder()
                    .routine(savedRoutine)
                    .exercise(exercise)
                    .exerciseOrder(exerciseRequest.exerciseOrder())
                    .exerciseNote(exerciseRequest.exerciseNote())
                    .supersetGroupId(exerciseRequest.supersetGroupId())
                    .build();

            RoutineExercise savedRoutineExercise = routineExerciseRepository.save(routineExercise);
            List<RoutineSetTarget> savedSets = new ArrayList<>();

            for (RoutineSetTargetRequest setRequest : exerciseRequest.sets()) {
                validateSetTarget(setRequest, exercise.getExerciseType());

                RoutineSetTarget routineSetTarget = RoutineSetTarget.builder().
                        routineExercise(savedRoutineExercise).
                        setNumber(setRequest.setNumber()).
                        targetWeight(setRequest.targetWeight()).
                        targetRepsMin(setRequest.targetRepsMin()).
                        targetRepsMax(setRequest.targetRepsMax()).
                        targetDurationSeconds(setRequest.targetDurationSeconds()).
                        setType(setRequest.setType()).
                        build();

                savedSets.add(routineSetTargetRepository.save(routineSetTarget));
            }

            savedRoutineExercise.setTargetSets(savedSets);
            savedExercises.add(savedRoutineExercise);
        }

        savedRoutine.setExercises(savedExercises);

        return DtoMapper.mapToRoutineResponse(savedRoutine);
    }

    private void validateSetTarget(RoutineSetTargetRequest set, ExerciseType type) {
        switch (type) {
            case WEIGHT_REPS -> {
                if (set.targetWeight() == null)
                    throw new IllegalArgumentException("Weight is required for WEIGHT_REPS exercise");
                if (set.targetRepsMin() == null)
                    throw new IllegalArgumentException("Reps are required for WEIGHT_REPS exercise");
            }
            case BODYWEIGHT -> {
                if (set.targetRepsMin() == null)
                    throw new IllegalArgumentException("Reps are required for BODYWEIGHT exercise");
            }
            case DURATION -> {
                if (set.targetDurationSeconds() == null)
                    throw new IllegalArgumentException("Duration is required for DURATION exercise");
            }
            case WEIGHT_DURATION -> {
                if (set.targetWeight() == null)
                    throw new IllegalArgumentException("Weight is required for WEIGHT_DURATION exercise");
                if (set.targetDurationSeconds() == null)
                    throw new IllegalArgumentException("Duration is required for WEIGHT_DURATION exercise");
            }
        }
    }

    public List<RoutineResponse> getRoutinesByUserId(UUID userId) {
        return routineRepository.findAllByUser(userService.getUserById(userId))
                .stream()
                .map(DtoMapper::mapToRoutineResponse)
                .toList();
    }

    public RoutineResponse getRoutineByUserId(UUID id, UUID userId) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Routine not found"));

        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        return DtoMapper.mapToRoutineResponse(routine);
    }

    @Transactional
    public void deleteRoutine(UUID id, UUID userId) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Routine not found"));
        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        List<Workout> workouts = workoutRepository.findAllByRoutine(routine);
        workouts.forEach(workout -> workout.setRoutine(null));
        workoutRepository.saveAll(workouts);

        routineRepository.delete(routine);
    }

    public RoutineResponse updateRoutine(UUID id, UpdateRoutineRequest request, UUID currentUserId) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found"));

        if (!routine.getUser().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        if (request.name() != null && !request.name().isBlank()) {
            routine.setName(request.name());
        }
        if (request.notes() != null) {
            routine.setNotes(request.notes());
        }
        if (request.folderOrder() != null) {
            routine.setFolderOrder(request.folderOrder());
        }

        return DtoMapper.mapToRoutineResponse(routineRepository.save(routine));
    }

    @Transactional
    public RoutineExerciseResponse addExerciseToRoutine(UUID routineId, RoutineExerciseRequest request, UUID userId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new ResourceNotFoundException("Routine not found"));

        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        Exercise exercise = exerciseService.getExerciseById(request.exerciseId());

        // Validate before persisting so a bad set can't leave an orphaned routine exercise.
        for (RoutineSetTargetRequest setRequest : request.sets()) {
            validateSetTarget(setRequest, exercise.getExerciseType());
        }

        RoutineExercise routineExercise = RoutineExercise.builder()
                .routine(routine)
                .exercise(exercise)
                .exerciseOrder(request.exerciseOrder())
                .supersetGroupId(request.supersetGroupId())
                .exerciseNote(request.exerciseNote())
                .build();

        RoutineExercise savedRoutineExercise = routineExerciseRepository.save(routineExercise);

        List<RoutineSetTarget> savedSets = new ArrayList<>();

        for (RoutineSetTargetRequest setRequest : request.sets()) {
            validateSetTarget(setRequest, exercise.getExerciseType());

            RoutineSetTarget routineSetTarget = RoutineSetTarget.builder()
                    .routineExercise(savedRoutineExercise)
                    .setNumber(setRequest.setNumber())
                    .targetWeight(setRequest.targetWeight())
                    .targetRepsMin(setRequest.targetRepsMin())
                    .targetDurationSeconds(setRequest.targetDurationSeconds())
                    .setType(setRequest.setType())
                    .targetRepsMax(setRequest.targetRepsMax())
                    .build();

            savedSets.add(routineSetTargetRepository.save(routineSetTarget));
        }

        savedRoutineExercise.setTargetSets(savedSets);

        return DtoMapper.mapToRoutineExerciseResponse(savedRoutineExercise);
    }

    public Routine getRoutineById(UUID routineId) {
        return routineRepository.findById(routineId).orElseThrow(() -> new ResourceNotFoundException("Routine not found"));
    }

    public void removeExerciseFromRoutine(UUID routineId, UUID routineExerciseId, UUID userId) {
        Routine routine = getRoutineById(routineId);

        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        RoutineExercise routineExercise = routineExerciseRepository.findById(routineExerciseId).
                orElseThrow(() -> new ResourceNotFoundException("Exercise not found in this routine"));

        if (!routineExercise.getRoutine().getId().equals(routineId)) {
            throw new ResourceNotFoundException("Exercise not found in this routine");
        }

        routineExerciseRepository.delete(routineExercise);
    }

    public RoutineExercise getRoutineExerciseById(UUID routineExerciseId) {
        return routineExerciseRepository.findById(routineExerciseId).orElseThrow(() -> new ResourceNotFoundException("Exercise not found in this routine"));
    }

    @Transactional
    public RoutineExerciseResponse updateExerciseInRoutine(UUID routineId, UUID routineExerciseId,
                                                           UpdateRoutineExerciseRequest request, UUID userId) {
        Routine routine = this.getRoutineById(routineId);

        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }

        RoutineExercise routineExercise = this.getRoutineExerciseById(routineExerciseId);

        if (!routineExercise.getRoutine().getId().equals(routineId)) {
            throw new ResourceNotFoundException("Exercise not found in this routine");
        }

        if (request.exerciseOrder() != null) {
            routineExercise.setExerciseOrder(request.exerciseOrder());
        }
        if (request.exerciseNote() != null) {
            routineExercise.setExerciseNote(request.exerciseNote());
        }
        if (request.supersetGroupId() != null) {
            routineExercise.setSupersetGroupId(request.supersetGroupId());
        }

        if (request.sets() != null) {
            routineExercise.getTargetSets().clear();

            for (RoutineSetTargetRequest setRequest : request.sets()) {
                validateSetTarget(setRequest, routineExercise.getExercise().getExerciseType());

                RoutineSetTarget routineSetTarget = RoutineSetTarget.builder()
                        .routineExercise(routineExercise)
                        .setNumber(setRequest.setNumber())
                        .targetWeight(setRequest.targetWeight())
                        .targetRepsMin(setRequest.targetRepsMin())
                        .targetRepsMax(setRequest.targetRepsMax())
                        .targetDurationSeconds(setRequest.targetDurationSeconds())
                        .setType(setRequest.setType())
                        .build();

                routineExercise.getTargetSets().add(routineSetTarget);
            }
        }

        RoutineExercise savedRoutineExercise = routineExerciseRepository.save(routineExercise);

        return DtoMapper.mapToRoutineExerciseResponse(savedRoutineExercise);
    }
}
