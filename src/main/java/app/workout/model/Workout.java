package app.workout.model;

import app.routine.model.Routine;
import app.user.model.User;
import app.workoutset.model.WorkoutSet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @Column
    private String name;

    @OneToMany(mappedBy = "workout")
   private List<WorkoutSet> workoutSets;
}
