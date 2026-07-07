package app.exercise.model;

import lombok.Getter;

@Getter
public enum MuscleGroup {

    CHEST("Chest"),
    BACK("Back"),
    QUADS("Quads"),
    HAMSTRINGS("Hamstrings"),
    GLUTES("Glutes"),
    SHOULDERS("Shoulders"),
    BICEPS("Biceps"),
    TRICEPS("Triceps"),
    CORE("Core"),
    ABDOMINAL("Abdominals"),
    CALVES("Calves"),
    FULL_BODY("Full Body"),
    NECK("Neck"),
    FOREARMS("Forearms"),
    LATS("Lats"),
    UPPER_BACK("Upper Back"),
    LOWER_BACK("Lower Back"),
    TRAPS("Traps"),
    CARDIO("Cardio"),
    QUADRICEPS("Quadriceps"),
    OTHER("Other");

    private final String displayName;

    MuscleGroup(String displayName) {
        this.displayName = displayName;
    }
}
