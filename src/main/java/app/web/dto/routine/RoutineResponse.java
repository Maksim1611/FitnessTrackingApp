package app.web.dto.routine;

import app.web.dto.routineExercise.RoutineExerciseResponse;

import java.util.List;
import java.util.UUID;

public record RoutineResponse(
        UUID id,
        String name,
        String notes,
        Integer folderOrder,
        List<RoutineExerciseResponse> exercises
){
}
