package app.web.dto.badge;

import app.badge.model.BadgeCategory;
import app.badge.model.BadgeTier;
import app.exercise.model.MuscleGroup;

import java.time.LocalDateTime;
import java.util.UUID;

public record BadgeResponse(
        UUID id,
        BadgeCategory category,
        BadgeTier tier,
        MuscleGroup muscleGroup,
        LocalDateTime earnedAt
) {
}
