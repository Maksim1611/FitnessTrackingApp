package app.web.dto.workout;

import app.web.dto.workoutSet.WorkoutSetResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WorkoutResponse(
        UUID id,
        String name,
        UUID routineId,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        List<WorkoutSetResponse> sets
) {
}
