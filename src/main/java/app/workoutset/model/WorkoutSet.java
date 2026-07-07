package app.workoutset.model;

import app.exercise.model.Exercise;
import app.workout.model.Workout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private double weight;
    private int reps;
    private int durationSeconds;
    private double distance;

    private int restSeconds;

    @Enumerated(EnumType.STRING)
    private SetType setType;

    private boolean completed;

    private LocalDateTime completedAt;
}
