package app.web.dto.workoutSet;

import app.workoutset.model.SetType;

public record UpdateWorkoutSetRequest(
        Double weight,
        Integer reps,
        Integer durationSeconds,
        Double rpe,
        Integer restSeconds,
        SetType setType,
        Boolean completed
) {
}
