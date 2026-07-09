package app.web.dto.workout;

import java.util.UUID;

public record WorkoutRequest(
        String name,
        UUID routineId
) {
}
