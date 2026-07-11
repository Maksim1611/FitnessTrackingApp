package app.web;

import app.measurement.service.BodyMeasurementService;
import app.security.AuthenticationMetadata;
import app.web.dto.measurement.BodyMeasurementRequest;
import app.web.dto.measurement.BodyMeasurementResponse;
import app.web.dto.measurement.UpdateBodyMeasurementRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/measurements")
public class BodyMeasurementController {

    private final BodyMeasurementService bodyMeasurementService;

    public BodyMeasurementController(BodyMeasurementService bodyMeasurementService) {
        this.bodyMeasurementService = bodyMeasurementService;
    }

    @PostMapping
    public ResponseEntity<BodyMeasurementResponse> createMeasurement(@RequestBody @Valid BodyMeasurementRequest request,
                                                                     @AuthenticationPrincipal AuthenticationMetadata principal) {
        BodyMeasurementResponse bodyMeasurement = bodyMeasurementService.createBodyMeasurement(request, principal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyMeasurement);
    }

    @GetMapping
    public ResponseEntity<List<BodyMeasurementResponse>> getMeasurementHistory(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(bodyMeasurementService.getMeasurementHistory(principal.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyMeasurementResponse> getMeasurementById(@PathVariable UUID id,
                                                                      @AuthenticationPrincipal AuthenticationMetadata principal) {
        BodyMeasurementResponse bodyMeasurement = bodyMeasurementService.getMeasurementById(id, principal.getId());
        return ResponseEntity.status(HttpStatus.OK).body(bodyMeasurement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable UUID id,
                                                  @AuthenticationPrincipal AuthenticationMetadata principal) {
        bodyMeasurementService.deleteMeasurement(id, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BodyMeasurementResponse> updateMeasurement(@PathVariable UUID id,
                                                                     @AuthenticationPrincipal AuthenticationMetadata principal,
                                                                     @RequestBody UpdateBodyMeasurementRequest request) {
        BodyMeasurementResponse response = bodyMeasurementService.updateMeasurement(id, request, principal.getId());
        return ResponseEntity.ok(response);
    }

}
