package app.social.model;

import app.user.model.User;
import app.workout.model.Workout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "workout_like", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "workout_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutLike {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private LocalDateTime createdAt;
}
