package app.measurement.repository;

import app.measurement.model.BodyMeasurement;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, UUID> {

    List<BodyMeasurement> findAllByUserOrderByRecordedAtDesc(User user);

    List<BodyMeasurement> findAllByUser(User user);
}
