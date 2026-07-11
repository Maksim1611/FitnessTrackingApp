package app.web.dto.routineExercise;

import app.web.dto.routineSetTarget.RoutineSetTargetRequest;

import java.util.List;

public record UpdateRoutineExerciseRequest(
        Integer exerciseOrder,
        String exerciseNote,
        Integer supersetGroupId,
        List<RoutineSetTargetRequest> sets
) {
}
