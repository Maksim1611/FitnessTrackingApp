package app.web.dto.routineSetTarget;

import app.workoutset.model.SetType;
import lombok.Data;

import java.util.UUID;

public record RoutineSetTargetResponse(
        UUID id,
        Integer setNumber,
        Double targetWeight,
        Integer targetRepsMin,
        Integer targetRepsMax,
        Integer targetDurationSeconds,
        SetType setType
) {}
