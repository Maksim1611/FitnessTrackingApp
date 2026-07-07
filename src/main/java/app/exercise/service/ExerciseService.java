package app.exercise.service;

import app.exercise.model.Exercise;
import app.exercise.repository.ExerciseRepository;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.user.service.UserService;
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

        return mapToResponse(savedExercise);
    }

    public List<ExerciseResponse> getExercisesByUserId(UUID id) {
        return exerciseRepository.findByCreatedById(id)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private ExerciseResponse mapToResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getExerciseType(),
                exercise.getEquipment(),
                exercise.getPrimaryMuscleGroup(),
                exercise.getOtherMuscleGroups(),
                exercise.getImageUrl()
        );
    }
}
