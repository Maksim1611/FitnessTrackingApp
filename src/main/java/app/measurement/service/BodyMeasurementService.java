package app.measurement.service;

import app.exception.ResourceNotFoundException;
import app.measurement.model.BodyMeasurement;
import app.measurement.repository.BodyMeasurementRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.measurement.BodyMeasurementRequest;
import app.web.dto.measurement.BodyMeasurementResponse;
import app.web.dto.measurement.UpdateBodyMeasurementRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BodyMeasurementService {

    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final UserService userService;

    public BodyMeasurementService(BodyMeasurementRepository bodyMeasurementRepository, UserService userService) {
        this.bodyMeasurementRepository = bodyMeasurementRepository;
        this.userService = userService;
    }

    public BodyMeasurementResponse createBodyMeasurement(BodyMeasurementRequest request, UUID userId) {
        User user = userService.getUserById(userId);

        BodyMeasurement bodyMeasurement = BodyMeasurement.builder()
                .user(user)
                .recordedAt(request.recordedAt() != null ? request.recordedAt() : LocalDateTime.now())
                .weight(request.weight())
                .bodyFatPercentage(request.bodyFatPercentage())
                .neck(request.neck())
                .shoulders(request.shoulders())
                .chest(request.chest())
                .waist(request.waist())
                .hips(request.hips())
                .biceps(request.biceps())
                .thighs(request.thighs())
                .calves(request.calves())
                .notes(request.notes())
                .photoUrl(request.photoUrl())
                .build();

        BodyMeasurement savedMeasurement = bodyMeasurementRepository.save(bodyMeasurement);

        return DtoMapper.toBodyMeasurementResponse(savedMeasurement);
    }

    public List<BodyMeasurementResponse> getMeasurementHistory(UUID userId) {
        User user = userService.getUserById(userId);

        return bodyMeasurementRepository.findAllByUserOrderByRecordedAtDesc(user).
                stream().
                map(DtoMapper::toBodyMeasurementResponse).
                toList();
    }

    public BodyMeasurementResponse getMeasurementById(UUID measurementId, UUID userId) {
        BodyMeasurement bodyMeasurement = getOwnedMeasurement(measurementId, userId);
        return DtoMapper.toBodyMeasurementResponse(bodyMeasurement);
    }

    private BodyMeasurement getOwnedMeasurement(UUID measurementId, UUID userId) {
        BodyMeasurement measurement = bodyMeasurementRepository.findById(measurementId).
                orElseThrow(() -> new ResourceNotFoundException("Measurement not found"));

        if (!measurement.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You don't have access to this body measurement");
        }

        return measurement;
    }

    public BodyMeasurementResponse updateMeasurement(UUID measurementId, UpdateBodyMeasurementRequest request, UUID userId) {
        BodyMeasurement bodyMeasurement = getOwnedMeasurement(measurementId, userId);

        if (request.recordedAt() != null) {
            bodyMeasurement.setRecordedAt(request.recordedAt());
        }
        if (request.weight() != null) {
            bodyMeasurement.setWeight(request.weight());
        }
        if (request.bodyFatPercentage() != null) {
            bodyMeasurement.setBodyFatPercentage(request.bodyFatPercentage());
        }
        if (request.neck() != null) {
            bodyMeasurement.setNeck(request.neck());
        }
        if (request.shoulders() != null) {
            bodyMeasurement.setShoulders(request.shoulders());
        }
        if (request.chest() != null) {
            bodyMeasurement.setChest(request.chest());
        }
        if (request.waist() != null) {
            bodyMeasurement.setWaist(request.waist());
        }
        if (request.hips() != null) {
            bodyMeasurement.setHips(request.hips());
        }
        if (request.biceps() != null) {
            bodyMeasurement.setBiceps(request.biceps());
        }
        if (request.thighs() != null) {
            bodyMeasurement.setThighs(request.thighs());
        }
        if (request.calves() != null) {
            bodyMeasurement.setCalves(request.calves());
        }
        if (request.notes() != null) {
            bodyMeasurement.setNotes(request.notes());
        }
        if (request.photoUrl() != null) {
            bodyMeasurement.setPhotoUrl(request.photoUrl());
        }

        BodyMeasurement savedMeasurement = bodyMeasurementRepository.save(bodyMeasurement);

        return DtoMapper.toBodyMeasurementResponse(savedMeasurement);
    }

    public void deleteMeasurement(UUID measurementId, UUID userId) {
        BodyMeasurement bodyMeasurement = getOwnedMeasurement(measurementId, userId);
        bodyMeasurementRepository.delete(bodyMeasurement);
    }
}
