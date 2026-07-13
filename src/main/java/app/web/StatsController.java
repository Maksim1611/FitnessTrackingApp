package app.web;

import app.security.AuthenticationMetadata;
import app.stats.service.StatsService;
import app.web.dto.stats.ExerciseProgressPointResponse;
import app.web.dto.stats.ProgressionSuggestionResponse;
import app.web.dto.stats.WeeklyVolumeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/exercises/{exerciseId}/progress")
    public ResponseEntity<List<ExerciseProgressPointResponse>> getExerciseProgress(@PathVariable UUID exerciseId,
                                                                                   @AuthenticationPrincipal AuthenticationMetadata principal) {
        List<ExerciseProgressPointResponse> exerciseProgress = statsService.getExerciseProgress(exerciseId, principal.getId());
        return ResponseEntity.ok(exerciseProgress);
    }

    @GetMapping("/volume")
    public ResponseEntity<List<WeeklyVolumeResponse>> getWeeklyVolume(@RequestParam(defaultValue = "8") int weeks,
                                                                      @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(statsService.getWeeklyVolume(weeks, principal.getId()));
    }

    @GetMapping("/exercises/{id}/suggestion")
    public ResponseEntity<ProgressionSuggestionResponse> getProgressionSuggestion(@PathVariable UUID id,
                                                                                  @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(statsService.getProgressionSuggestion(id, principal.getId()));
    }
}
