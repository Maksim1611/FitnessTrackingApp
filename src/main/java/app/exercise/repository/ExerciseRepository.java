package app.exercise.repository;

import app.exercise.model.Equipment;
import app.exercise.model.Exercise;
import app.exercise.model.ExerciseType;
import app.exercise.model.MuscleGroup;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    Optional<Exercise> findByName(String name);

    List<Exercise> findByCreatedById(UUID createdById);

    boolean existsByNameAndCreatedBy(String name, User createdBy);

    @Query("SELECT e FROM Exercise e WHERE (e.createdBy IS NULL OR e.createdBy.id = :userId) " +
            "AND (:muscleGroup IS NULL OR e.primaryMuscleGroup = :muscleGroup) " +
            "AND (:equipment IS NULL OR e.equipment = :equipment) " +
            "AND (:exerciseType IS NULL OR e.exerciseType = :exerciseType) " +
            "ORDER BY e.name")
    List<Exercise> searchExercises(@Param("userId") UUID userId,
                                   @Param("muscleGroup") MuscleGroup muscleGroup,
                                   @Param("equipment") Equipment equipment,
                                   @Param("exerciseType") ExerciseType exerciseType);
}
