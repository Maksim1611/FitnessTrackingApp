package app.exercise.model;

import lombok.Getter;

@Getter
public enum ExerciseType {

    WEIGHT_REPS("Weight Reps"),
    REPS_ONLY("Reps Only"),
    WEIGHTED_BODYWEIGHT("Weighted Bodyweight"),
    ASSISTED_BODYWEIGHT("Assisted Bodyweight"),
    DURATION("Duration"),
    WEIGHT_DURATION("Weight & Duration"),
    DISTANCE("Distance");

    private final String displayName;

    ExerciseType(String displayName) {
        this.displayName = displayName;
    }
}
