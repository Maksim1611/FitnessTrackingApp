package app.workoutset.model;

import lombok.Getter;

@Getter
public enum SetType {

    WARMUP("Warm-up"),
    NORMAL("Normal"),
    FAILURE("Failure"),
    WORKING("Working"),
    DROPSET("Drop Set");

    private final String displayName;

    SetType(String displayName) {
        this.displayName = displayName;
    }
}
