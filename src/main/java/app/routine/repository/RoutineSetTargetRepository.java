package app.routine.repository;

import app.routine.model.RoutineSetTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoutineSetTargetRepository extends JpaRepository<RoutineSetTarget, UUID> {
}
