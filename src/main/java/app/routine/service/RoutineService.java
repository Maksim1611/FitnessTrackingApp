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
import app.user.model.User;
import app.user.repository.UserRepository;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.routine.RoutineRequest;
import app.web.dto.routine.RoutineResponse;
import app.web.dto.routine.UpdateRoutineRequest;
import app.web.dto.routineExercise.RoutineExerciseRequest;
import app.web.dto.routineSetTarget.RoutineSetTargetRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static app.exercise.model.Equipment.BODYWEIGHT;
import static app.exercise.model.ExerciseType.*;

@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final RoutineSetTargetRepository routineSetTargetRepository;

    public RoutineService(RoutineRepository routineRepository, UserService userService, ExerciseService exerciseService, RoutineExerciseRepository routineExerciseRepository, RoutineSetTargetRepository routineSetTargetRepository) {
        this.routineRepository = routineRepository;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.routineExerciseRepository = routineExerciseRepository;
        this.routineSetTargetRepository = routineSetTargetRepository;
    }

    public RoutineResponse createRoutine(RoutineRequest request, UUID userId) {
        User user = userService.getUserById(userId);

        Routine routine = Routine.builder()
                .name(request.name())
                .notes(request.notes())
                .user(user)
                .folderOrder(request.folderOrder())
                .build();

        Routine savedRoutine = routineRepository.save(routine);

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

                routineSetTargetRepository.save(routineSetTarget);
            }
        }

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

    public void deleteRoutine(UUID id, UUID userId) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Routine not found"));
        if (!routine.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this routine");
        }
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
}
