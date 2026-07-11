package app.web;

import app.exercise.model.Equipment;
import app.exercise.model.ExerciseType;
import app.exercise.model.MuscleGroup;
import app.exercise.service.ExerciseService;
import app.security.AuthenticationMetadata;
import app.web.dto.exercise.ExerciseRequest;
import app.web.dto.exercise.ExerciseResponse;
import app.web.dto.exercise.UpdateExerciseRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> create(@Valid @RequestBody ExerciseRequest exerciseRequest, @AuthenticationPrincipal AuthenticationMetadata principal) {

        ExerciseResponse response = exerciseService.createExercise(exerciseRequest, principal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getMyExercises(@AuthenticationPrincipal AuthenticationMetadata principal,
                                                                 @RequestParam(required = false)MuscleGroup muscleGroup,
                                                                 @RequestParam(required = false)Equipment equipment,
                                                                 @RequestParam(required = false) ExerciseType exerciseType)  {
        List<ExerciseResponse> exerciseResponses = exerciseService.searchExercises(principal.getId(), muscleGroup, equipment, exerciseType);
        return ResponseEntity.status(HttpStatus.OK).body(exerciseResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getExercise(@PathVariable UUID id,
                                                        @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(exerciseService.getExerciseByIdAndUserId(id, principal.getId()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseResponse> updateExercise(@PathVariable UUID id,
                                                           @AuthenticationPrincipal AuthenticationMetadata principal,
                                                           @RequestBody UpdateExerciseRequest request) {
        ExerciseResponse exerciseResponse = exerciseService.updateExercise(id, principal.getId(), request);
        return ResponseEntity.ok(exerciseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable UUID id, @AuthenticationPrincipal AuthenticationMetadata principal) {
        exerciseService.deleteExercise(id, principal.getId());
        return ResponseEntity.noContent().build();
    }

}
