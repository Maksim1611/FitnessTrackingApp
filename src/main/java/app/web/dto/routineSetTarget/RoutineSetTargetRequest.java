package app.web.dto.routineSetTarget;

import app.workoutset.model.SetType;
import jakarta.validation.constraints.NotNull;

public record RoutineSetTargetRequest(
        Integer setNumber,
        Double targetWeight,
        Integer targetRepsMin,
        Integer targetRepsMax,
        Integer targetDurationSeconds,
        @NotNull SetType setType
) {}
