package app.web.dto.stats;

import app.exercise.model.MuscleGroup;

public record MuscleGroupVolumeResponse(
        MuscleGroup muscleGroup,
        double totalVolume,
        int totalSets
) {
}
