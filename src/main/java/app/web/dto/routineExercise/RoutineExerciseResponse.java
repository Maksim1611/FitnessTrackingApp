package app.web.dto.routineExercise;

import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.routineSetTarget.RoutineSetTargetResponse;

import java.util.List;
import java.util.UUID;

public record RoutineExerciseResponse(
        UUID id,
        Integer exerciseOrder,
        String exerciseNote,
        Integer supersetGroupId,
        ExerciseResponse exercise,
        List<RoutineSetTargetResponse> sets
) {}
