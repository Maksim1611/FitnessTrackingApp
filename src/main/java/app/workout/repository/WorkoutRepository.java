package app.workout.repository;

import app.routine.model.Routine;
import app.user.model.User;
import app.workout.model.Workout;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    List<Workout> findAllByUserOrderByStartedAtDesc(User user);

    List<Workout> findAllByRoutine(Routine routine);

    @Query("SELECT w FROM Workout w WHERE w.finishedAt IS NOT NULL AND w.user IN (SELECT f.followed FROM Follow f WHERE f.follower.id = :userId) ORDER BY w.finishedAt DESC")
    List<Workout> findFeedForUser(@Param("userId") UUID userId, Pageable pageable);

    long countByUserAndFinishedAtIsNotNull(User user);
}
