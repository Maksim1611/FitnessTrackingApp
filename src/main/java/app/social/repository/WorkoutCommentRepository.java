package app.social.repository;

import app.social.model.WorkoutComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutCommentRepository extends JpaRepository<WorkoutComment, UUID> {

    List<WorkoutComment> findAllByWorkoutIdOrderByCreatedAtAsc(UUID workoutId);

    long countByWorkoutId(UUID workoutId);
}
