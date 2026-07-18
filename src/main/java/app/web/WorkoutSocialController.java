package app.web;

import app.security.AuthenticationMetadata;
import app.social.service.WorkoutSocialService;
import app.web.dto.social.CommentRequest;
import app.web.dto.social.CommentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/workout/{workoutId}")
public class WorkoutSocialController {

    private final WorkoutSocialService socialService;

    public WorkoutSocialController(WorkoutSocialService socialService) {
        this.socialService = socialService;
    }

    @PostMapping("/like")
    public ResponseEntity<Void> like(@PathVariable UUID workoutId, @AuthenticationPrincipal AuthenticationMetadata principal) {
        socialService.likeWorkout(workoutId, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/like")
    public ResponseEntity<Void> unlike(@PathVariable UUID workoutId, @AuthenticationPrincipal AuthenticationMetadata principal) {
        socialService.unlikeWorkout(workoutId, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable UUID workoutId) {
        return ResponseEntity.ok(socialService.getComments(workoutId));
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable UUID workoutId,
                                                      @Valid @RequestBody CommentRequest request,
                                                      @AuthenticationPrincipal AuthenticationMetadata principal) {
        CommentResponse comment = socialService.addComment(workoutId, principal.getId(), request.text());
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID workoutId, @PathVariable UUID commentId,
                                              @AuthenticationPrincipal AuthenticationMetadata principal) {
        socialService.deleteComment(workoutId, commentId, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
