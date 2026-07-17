package app.utils;

import app.exercise.model.Exercise;
import app.measurement.model.BodyMeasurement;
import app.routine.model.Routine;
import app.routine.model.RoutineExercise;
import app.user.model.User;
import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.measurement.BodyMeasurementResponse;
import app.web.dto.routine.RoutineResponse;
import app.web.dto.routineExercise.RoutineExerciseResponse;
import app.web.dto.routineSetTarget.RoutineSetTargetResponse;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import app.web.dto.workout.WorkoutResponse;
import app.web.dto.workoutSet.WorkoutSetResponse;
import app.workout.model.Workout;
import app.workoutset.model.WorkoutSet;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DtoMapper {

    public static void updateUserFromRequest(User user, EditProfileRequest request) {
        if (request.name() != null && !request.name().isEmpty()) {
            user.setName(request.name());
        }
        if (request.username() != null && !request.username().isEmpty()) {
            user.setUsername(request.username());
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
                exercise.getImageUrl(),
                exercise.getCreatedBy() != null
        );
    }

    public static RoutineResponse mapToRoutineResponse(Routine routine) {
        List<RoutineExerciseResponse> exerciseResponses = routine.getExercises()
                .stream()
                .map(DtoMapper::mapToRoutineExerciseResponse)
                .toList();

        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getNotes(),
                routine.getFolderOrder(),
                exerciseResponses
        );
    }

    public static WorkoutSetResponse toWorkoutSetResponse(WorkoutSet set) {
        return new WorkoutSetResponse(
                set.getId(),
                set.getExercise().getId(),
                set.getExercise().getName(),
                set.getExercise().getExerciseType(), 
                set.getSetNumber(),
                set.getWeight(),
                set.getReps(),
                set.getDurationSeconds(),
                set.getRpe(),
                set.getRestSeconds(),
                set.getSupersetGroupId(),
                set.getExerciseOrder(),
                set.getSetType(),
                set.isCompleted(),
                set.getCompletedAt(),
                set.isPersonalRecord()
        );
    }

    public static WorkoutResponse toWorkoutResponse(Workout workout) {
        List<WorkoutSetResponse> sets = workout.getWorkoutSets() == null
                ? List.of()
                : workout.getWorkoutSets().stream().map(DtoMapper::toWorkoutSetResponse).toList();

        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                workout.getRoutine() == null ? null : workout.getRoutine().getId(),
                workout.getStartedAt(),
                workout.getFinishedAt(),
                sets
        );
    }

    public static RoutineExerciseResponse mapToRoutineExerciseResponse(RoutineExercise routineExercise) {
        List<RoutineSetTargetResponse> setTargetResponses = routineExercise.getTargetSets().stream()
                .map(s -> new RoutineSetTargetResponse(
                        s.getId(),
                        s.getSetNumber(),
                        s.getTargetWeight(),
                        s.getTargetRepsMin(),
                        s.getTargetRepsMax(),
                        s.getTargetDurationSeconds(),
                        s.getSetType()
                )).toList();

        return new RoutineExerciseResponse(
                routineExercise.getId(),
                routineExercise.getExerciseOrder(),
                routineExercise.getExerciseNote(),
                routineExercise.getSupersetGroupId(),
                DtoMapper.exerciseToResponse(routineExercise.getExercise()),
                setTargetResponses
        );
    }

    public static BodyMeasurementResponse toBodyMeasurementResponse(BodyMeasurement measurement) {
        return new BodyMeasurementResponse(
                measurement.getId(),
                measurement.getRecordedAt(),
                measurement.getWeight(),
                measurement.getBodyFatPercentage(),
                measurement.getNeck(),
                measurement.getShoulders(),
                measurement.getChest(),
                measurement.getWaist(),
                measurement.getHips(),
                measurement.getBiceps(),
                measurement.getThighs(),
                measurement.getCalves(),
                measurement.getNotes(),
                measurement.getPhotoUrl()
        );
    }

}
