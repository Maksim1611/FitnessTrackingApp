package app.web.dto.exercise;

import app.exercise.model.Equipment;
import app.exercise.model.ExerciseType;
import app.exercise.model.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record ExerciseResponse(

        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Exercise type is required")
        ExerciseType exerciseType,

        @NotNull(message = "Equipment is required")
        Equipment equipment,

        @NotNull(message = "Primary muscle group is required")
        MuscleGroup primaryMuscleGroup,

        Set<MuscleGroup> otherMuscles,

        String imageUrl
) {
}
