package app.web.dto.routine;

import app.web.dto.routineExercise.RoutineExerciseRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RoutineRequest(
        @NotBlank String name,
        String notes,
        Integer folderOrder,
        List<RoutineExerciseRequest> exercises
) {
}
