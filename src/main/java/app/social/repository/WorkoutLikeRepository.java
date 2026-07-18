package app.social.repository;

import app.social.model.WorkoutLike;
import app.user.model.User;
import app.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutLikeRepository extends JpaRepository<WorkoutLike, UUID> {

    boolean existsByUserAndWorkout(User user, Workout workout);

    Optional<WorkoutLike> findByUserAndWorkout(User user, Workout workout);

    long countByWorkout(Workout workout);

    boolean existsByUserIdAndWorkoutId(UUID userId, UUID workoutId);

    long countByWorkoutId(UUID workoutId);
}
