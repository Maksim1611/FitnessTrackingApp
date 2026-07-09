package app.web;

import app.security.AuthenticationMetadata;
import app.web.dto.workout.WorkoutRequest;
import app.web.dto.workout.WorkoutResponse;
import app.web.dto.workoutSet.AddWorkoutSetRequest;
import app.web.dto.workoutSet.UpdateWorkoutSetRequest;
import app.web.dto.workoutSet.WorkoutSetResponse;
import app.workout.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponse> startWorkout(@RequestBody WorkoutRequest request,
                                                          @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.startWorkout(request, principal.getId()));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getWorkoutHistory(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.getWorkoutHistory(principal.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(@PathVariable UUID id,
                                                           @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.getWorkoutById(id, principal.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID id,
                                               @AuthenticationPrincipal AuthenticationMetadata principal) {
        workoutService.deleteWorkout(id, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<WorkoutResponse> finishWorkout(@PathVariable UUID id,
                                                          @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.finishWorkout(id, principal.getId()));
    }

    @PostMapping("/{id}/sets")
    public ResponseEntity<WorkoutSetResponse> addSet(@PathVariable UUID id,
                                                       @RequestBody AddWorkoutSetRequest request,
                                                       @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.addSet(id, request, principal.getId()));
    }

    @PatchMapping("/{id}/sets/{setId}")
    public ResponseEntity<WorkoutSetResponse> updateSet(@PathVariable UUID id, @PathVariable UUID setId,
                                                         @RequestBody UpdateWorkoutSetRequest request,
                                                         @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(workoutService.updateSet(id, setId, request, principal.getId()));
    }

    @DeleteMapping("/{id}/sets/{setId}")
    public ResponseEntity<Void> deleteSet(@PathVariable UUID id, @PathVariable UUID setId,
                                           @AuthenticationPrincipal AuthenticationMetadata principal) {
        workoutService.deleteSet(id, setId, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
