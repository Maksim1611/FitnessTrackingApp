package app.web.dto.measurement;

import java.time.LocalDateTime;

public record UpdateBodyMeasurementRequest(
        LocalDateTime recordedAt,
        Double weight,
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
