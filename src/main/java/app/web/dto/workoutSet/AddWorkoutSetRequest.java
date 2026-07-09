package app.web.dto.workoutSet;

import app.workoutset.model.SetType;

import java.util.UUID;

public record AddWorkoutSetRequest(
        UUID exerciseId,
        Integer setNumber,
        Double weight,
        Integer reps,
        Integer durationSeconds,
        Double rpe,
        Integer restSeconds,
        Integer supersetGroupId,
        Integer exerciseOrder,
        SetType setType
) {
}
