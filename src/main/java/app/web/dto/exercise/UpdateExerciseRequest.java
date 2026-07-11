package app.web.dto.exercise;

import app.exercise.model.Equipment;
import app.exercise.model.ExerciseType;
import app.exercise.model.MuscleGroup;

import java.util.Set;

public record UpdateExerciseRequest(
        String name,
        ExerciseType exerciseType,
        Equipment equipment,
        MuscleGroup primaryMuscleGroup,
        Set<MuscleGroup> otherMuscles,
        String imageUrl
) {
}
