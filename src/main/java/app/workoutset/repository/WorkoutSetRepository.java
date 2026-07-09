package app.workoutset.repository;

import app.workout.model.Workout;
import app.workoutset.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, UUID> {
    List<WorkoutSet> findAllByWorkoutAndExercise_Id(Workout workout, UUID exerciseId);
}
