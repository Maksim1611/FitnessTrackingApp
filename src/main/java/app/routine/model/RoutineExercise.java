package app.routine.model;

import app.exercise.model.Exercise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int exerciseOrder;
    private String exerciseNote;
    private int supersetGroupId;

    @OneToMany(mappedBy = "routineExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineSetTarget> targetSets;

}
