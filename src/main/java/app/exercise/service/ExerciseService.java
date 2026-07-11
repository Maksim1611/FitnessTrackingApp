package app.exercise.service;

import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.exercise.model.Equipment;
import app.exercise.model.Exercise;
import app.exercise.model.ExerciseType;
import app.exercise.model.MuscleGroup;
import app.exercise.repository.ExerciseRepository;
import app.routine.repository.RoutineExerciseRepository;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.exercise.ExerciseRequest;
import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.exercise.UpdateExerciseRequest;
import app.workoutset.repository.WorkoutSetRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserService userService;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final WorkoutSetRepository workoutSetRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, UserService userService, RoutineExerciseRepository routineExerciseRepository, WorkoutSetRepository workoutSetRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userService = userService;
        this.routineExerciseRepository = routineExerciseRepository;
        this.workoutSetRepository = workoutSetRepository;
    }

    public ExerciseResponse createExercise(ExerciseRequest exerciseRequest, UUID userId) {
        User user = userService.getUserById(userId);

        if (exerciseRepository.existsByNameAndCreatedBy(exerciseRequest.name(), user)) {
            throw new DuplicateResourceException("You already have an exercise named '" + exerciseRequest.name() + "'");
        }

        Exercise exercise = Exercise.builder()
                .name(exerciseRequest.name())
                .exerciseType(exerciseRequest.exerciseType())
                .equipment(exerciseRequest.equipment())
                .primaryMuscleGroup(exerciseRequest.primaryMuscleGroup())
                .otherMuscleGroups(exerciseRequest.otherMuscles())
                .imageUrl(exerciseRequest.imageUrl())
                .createdBy(user)
                .build();

        Exercise savedExercise = exerciseRepository.save(exercise);

        return DtoMapper.exerciseToResponse(savedExercise);
    }

    public List<ExerciseResponse> getExercisesByUserId(UUID id) {
        return exerciseRepository.findByCreatedById(id)
                .stream().map(DtoMapper::exerciseToResponse).collect(Collectors.toList());
    }

    public Exercise getExerciseById(UUID id) {
        return exerciseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Exercise not found"));
    }

    public ExerciseResponse getExerciseByIdAndUserId(UUID id, UUID userId) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Exercise not found"));

        if (!userId.equals(exercise.getCreatedBy().getId())) {
            throw new AccessDeniedException("You don't have access to this exercise");
        }

        return DtoMapper.exerciseToResponse(exercise);
    }

    public ExerciseResponse updateExercise(UUID exerciseId, UUID userId, UpdateExerciseRequest request) {
        Exercise exercise = getExerciseById(exerciseId);

        if (!exercise.getCreatedBy().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this exercise");
        }

        if (request.name() != null && !request.name().isBlank() && !exercise.getName().equals(request.name())) {
            if (exerciseRepository.existsByNameAndCreatedBy(request.name(), exercise.getCreatedBy())) {
                throw new DuplicateResourceException("You already have an exercise named '" + request.name() + "'");
            }
            exercise.setName(request.name());
        }
        if (request.exerciseType() != null) {
            exercise.setExerciseType(request.exerciseType());
        }
        if (request.imageUrl() != null) {
            exercise.setImageUrl(request.imageUrl());
        }
        if (request.primaryMuscleGroup() != null) {
            exercise.setPrimaryMuscleGroup(request.primaryMuscleGroup());
        }
        if (request.equipment() != null) {
            exercise.setEquipment(request.equipment());
        }
        if (request.otherMuscles() != null) {
            exercise.setOtherMuscleGroups(request.otherMuscles());
        }

        Exercise savedExercise = exerciseRepository.save(exercise);

        return DtoMapper.exerciseToResponse(savedExercise);
    }

    public void deleteExercise(UUID exerciseId, UUID userId) {
        Exercise exercise = getExerciseById(exerciseId);

        if (!exercise.getCreatedBy().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this exercise");
        }
        if (routineExerciseRepository.existsByExercise_Id(exerciseId) || workoutSetRepository.existsByExercise_Id(exerciseId)) {
            throw new IllegalArgumentException("This exercise is used in a routine or workout and can't be deleted");
        }

        exerciseRepository.delete(exercise);
    }

    public List<ExerciseResponse> searchExercises(UUID userId, MuscleGroup muscleGroup, Equipment equipment, ExerciseType exerciseType) {
        return exerciseRepository.searchExercises(userId, muscleGroup, equipment, exerciseType)
                .stream().map(DtoMapper::exerciseToResponse).collect(Collectors.toList());
    }

}
