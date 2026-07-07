package app.exercise.service;

import app.exception.ResourceNotFoundException;
import app.exercise.model.Exercise;
import app.exercise.repository.ExerciseRepository;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.exercise.ExerciseRequest;
import app.web.dto.exercise.ExerciseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserService userService;

    public ExerciseService(ExerciseRepository exerciseRepository, UserService userService) {
        this.exerciseRepository = exerciseRepository;
        this.userService = userService;
    }

    public ExerciseResponse createExercise(ExerciseRequest exerciseRequest, UUID userId) {
        User user = userService.getUserById(userId);

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
        return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));
    }

}
