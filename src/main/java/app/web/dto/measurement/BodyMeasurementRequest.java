package app.web.dto.measurement;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BodyMeasurementRequest(
        LocalDateTime recordedAt,
        @NotNull Double weight,
        Double bodyFatPercentage,
        Double neck,
        Double shoulders,
        Double chest,
        Double waist,
        Double hips,
        Double biceps,
        Double thighs,
        Double calves,
        String notes,
        String photoUrl
) {
}
