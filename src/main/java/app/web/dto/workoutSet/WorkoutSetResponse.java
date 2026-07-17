package app.web.dto.workoutSet;

import app.exercise.model.ExerciseType;
import app.workoutset.model.SetType;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkoutSetResponse(
        UUID id,
        UUID exerciseId,
        String exerciseName,
        ExerciseType exerciseType,
        Integer setNumber,
        Double weight,
        Integer reps,
        Integer durationSeconds,
        Double rpe,
        Integer restSeconds,
        Integer supersetGroupId,
        Integer exerciseOrder,
        SetType setType,
        boolean completed,
        LocalDateTime completedAt,
        boolean isPersonalRecord
) {
}
