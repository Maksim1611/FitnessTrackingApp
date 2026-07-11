package app.web.dto.stats;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExerciseProgressPointResponse(
        UUID workoutId,
        LocalDateTime workoutDate,
        Double topWeight,
        Integer repsAtTopWeight,
        boolean personalRecord
) {
}
