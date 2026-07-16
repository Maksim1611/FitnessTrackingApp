package app.workoutset.repository;

import app.workout.model.Workout;
import app.workoutset.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, UUID> {
    List<WorkoutSet> findAllByWorkoutAndExercise_Id(Workout workout, UUID exerciseId);
    boolean existsByExercise_Id(UUID exerciseId);

    @Query("SELECT MAX(ws.weight) FROM WorkoutSet ws WHERE ws.exercise.id = :exerciseId AND ws.workout.user.id = :userId AND ws.completed = true AND ws.id <> :excludeId")
    Double findMaxWeight(@Param("exerciseId") UUID exerciseId, @Param("userId") UUID userId, @Param("excludeId") UUID excludeId);

    @Query("SELECT MAX(ws.reps) FROM WorkoutSet ws WHERE ws.exercise.id = :exerciseId AND ws.workout.user.id = :userId AND ws.completed = true AND ws.id <> :excludeId")
    Integer findMaxReps(@Param("exerciseId") UUID exerciseId, @Param("userId") UUID userId, @Param("excludeId") UUID excludeId);

    @Query("SELECT MAX(ws.durationSeconds) FROM WorkoutSet ws WHERE ws.exercise.id = :exerciseId AND ws.workout.user.id = :userId AND ws.completed = true AND ws.id <> :excludeId")
    Integer findMaxDuration(@Param("exerciseId") UUID exerciseId, @Param("userId") UUID userId, @Param("excludeId") UUID excludeId);

    @Query("SELECT ws FROM WorkoutSet ws WHERE ws.exercise.id = :exerciseId AND ws.workout.user.id = :userId AND ws.completed = true ORDER BY ws.workout.startedAt ASC")
    List<WorkoutSet> findCompletedSetsForExercise(@Param("exerciseId") UUID exerciseId, @Param("userId") UUID userId);

    @Query("SELECT ws FROM WorkoutSet ws WHERE ws.workout.user.id = :userId AND ws.completed = true AND ws.workout.startedAt >= :since")
    List<WorkoutSet> findCompletedSetsSince(@Param("userId") UUID userId, @Param("since") LocalDateTime since);

    @Query("SELECT ws FROM WorkoutSet ws WHERE ws.workout.user.id = :userId AND ws.completed = true")
    List<WorkoutSet> findAllCompletedByUser(@Param("userId") UUID userId);

    @Query("SELECT MIN(ws.weight) FROM WorkoutSet ws WHERE ws.exercise.id = :exerciseId AND ws.workout.user.id = :userId AND ws.completed = true AND ws.id <> :excludeId")
    Double findMinWeight(@Param("exerciseId") UUID exerciseId, @Param("userId") UUID userId, @Param("excludeId") UUID excludeId);

}
