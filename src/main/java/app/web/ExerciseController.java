package app.web;

import app.exercise.service.ExerciseService;
import app.security.AuthenticationMetadata;
import app.web.dto.exercise.ExerciseRequest;
import app.web.dto.exercise.ExerciseResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<ExerciseResponse>> getAllExercisesById(@PathVariable UUID id)  {
        return ResponseEntity.status(HttpStatus.OK).body(exerciseService.getExercisesByUserId(id));
    }
}
