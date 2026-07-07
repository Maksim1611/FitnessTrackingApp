package app.web;

import app.routine.service.RoutineService;
import app.security.AuthenticationMetadata;
import app.web.dto.routine.RoutineRequest;
import app.web.dto.routine.RoutineResponse;
import app.web.dto.routine.UpdateRoutineRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/routine")
public class RoutineController {

    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(@RequestBody RoutineRequest routineRequest,
                                                         @AuthenticationPrincipal AuthenticationMetadata principal) {
        RoutineResponse routine = routineService.createRoutine(routineRequest, principal.getId());
        return ResponseEntity.ok(routine);
    }

    @GetMapping
    public ResponseEntity<List<RoutineResponse>> getAllRoutines(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(routineService.getRoutinesByUserId(principal.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> getRoutineById(@PathVariable UUID id, @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(routineService.getRoutineByUserId(id, principal.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable UUID id,  @AuthenticationPrincipal AuthenticationMetadata principal) {
        routineService.deleteRoutine(id, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoutineResponse> updateRoutine(@PathVariable UUID id, @RequestBody UpdateRoutineRequest request, @AuthenticationPrincipal AuthenticationMetadata principal) {
        RoutineResponse routineResponse = routineService.updateRoutine(id, request, principal.getId());
        return ResponseEntity.ok(routineResponse);
    }
}
