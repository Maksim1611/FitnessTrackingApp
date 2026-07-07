package app.exercise.model;

import app.routine.model.RoutineExercise;
import app.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "created_by_user_id"}))
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;

    @Enumerated(EnumType.STRING)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    private MuscleGroup primaryMuscleGroup;

    @ElementCollection(targetClass = MuscleGroup.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group")
    @CollectionTable(name = "exercise_other_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    private Set<MuscleGroup> otherMuscleGroups;

    @OneToMany(mappedBy = "exercise")
    private List<RoutineExercise> routineExercises;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;
}
