package app.web.dto.routineExercise;

import app.web.dto.routineSetTarget.RoutineSetTargetRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RoutineExerciseRequest(
        @NotNull UUID exerciseId,
        @NotNull Integer exerciseOrder,
        String exerciseNote,
        Integer supersetGroupId,
        List<RoutineSetTargetRequest> sets
) {
}
