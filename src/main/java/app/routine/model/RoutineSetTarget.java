package app.routine.model;

import app.workoutset.model.SetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutineSetTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "routine_exercise_id")
    private RoutineExercise routineExercise;

    private int setNumber;
    private double targetWeight;
    private int targetDurationSeconds;

    @Enumerated(EnumType.STRING)
    private SetType setType;

}
