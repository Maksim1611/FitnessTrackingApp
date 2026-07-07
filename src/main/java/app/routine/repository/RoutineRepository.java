package app.routine.repository;

import app.routine.model.Routine;
import app.user.model.User;
import app.web.dto.routine.RoutineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, UUID> {
    List<Routine> findAllByUser(User user);

    Routine findByUserAndId(User user, UUID id);

    void deleteByIdAndUser(UUID id, User user);
}
