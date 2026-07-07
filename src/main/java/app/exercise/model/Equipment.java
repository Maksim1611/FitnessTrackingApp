package app.exercise.model;

import lombok.Getter;

@Getter
public enum Equipment {

    BARBELL("Barbell"),
    DUMBBELL("Dumbbell"),
    MACHINE("Machine"),
    CABLE("Cable"),
    BODYWEIGHT("BodyWeight"),
    RESISTANCE_BAND("Resistance Band"),
    KETTLEBELL("Kettlebell"),
    PLATE("Plate"),
    OTHER("Other");

    private final String displayName;

    Equipment(String displayName) {
        this.displayName = displayName;
    }
}
