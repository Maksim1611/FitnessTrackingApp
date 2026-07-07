package app.utils;

import app.exercise.model.Exercise;
import app.routine.model.Routine;
import app.user.model.User;
import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.routine.RoutineResponse;
import app.web.dto.routineExercise.RoutineExerciseResponse;
import app.web.dto.routineSetTarget.RoutineSetTargetResponse;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DtoMapper {

    public static void updateUserFromRequest(User user, EditProfileRequest request) {
        if (request.name() != null && !request.name().isEmpty()) {
            user.setName(request.name());
        }
        if (request.email() != null && !request.email().isEmpty()) {
            user.setEmail(request.email());
        }
        if (request.imageUrl() != null && !request.imageUrl().isEmpty()) {
            user.setImageUrl(request.imageUrl());
        }
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getImageUrl(),
                user.getCreatedOn()
        );
    }

    public static ExerciseResponse exerciseToResponse(Exercise exercise) {
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

    public static RoutineResponse mapToRoutineResponse(Routine routine) {
        List<RoutineExerciseResponse> exerciseResponses = routine.getExercises()
                .stream()
                .map(re -> new RoutineExerciseResponse(
                        re.getId(),
                        re.getExerciseOrder(),
                        re.getExerciseNote(),
                        re.getSupersetGroupId(),
                        DtoMapper.exerciseToResponse(re.getExercise()),
                        re.getTargetSets().stream()
                                .map(s -> new RoutineSetTargetResponse(
                                        s.getId(),
                                        s.getSetNumber(),
                                        s.getTargetWeight(),
                                        s.getTargetRepsMin(),
                                        s.getTargetRepsMax(),
                                        s.getTargetDurationSeconds(),
                                        s.getSetType()
                                ))
                                .toList()
                ))
                .toList();

        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getNotes(),
                routine.getFolderOrder(),
                exerciseResponses
        );
    }
}
