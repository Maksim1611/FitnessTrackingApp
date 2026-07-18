package app.social.service;

import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.social.model.NotificationType;
import app.social.model.WorkoutComment;
import app.social.model.WorkoutLike;
import app.social.repository.WorkoutCommentRepository;
import app.social.repository.WorkoutLikeRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.social.CommentResponse;
import app.workout.model.Workout;
import app.workout.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutSocialService {

    private final WorkoutLikeRepository likeRepository;
    private final WorkoutCommentRepository commentRepository;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public WorkoutSocialService(WorkoutLikeRepository likeRepository, WorkoutCommentRepository commentRepository,
                                WorkoutRepository workoutRepository, UserService userService,
                                NotificationService notificationService) {
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.workoutRepository = workoutRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    private Workout getWorkout(UUID workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
    }

    @Transactional
    public void likeWorkout(UUID workoutId, UUID userId) {
        User user = userService.getUserById(userId);
        Workout workout = getWorkout(workoutId);

        if (likeRepository.existsByUserAndWorkout(user, workout)) {
            throw new DuplicateResourceException("Already liked");
        }

        likeRepository.save(WorkoutLike.builder()
                .user(user)
                .workout(workout)
                .createdAt(LocalDateTime.now())
                .build());

        notificationService.notify(workout.getUser(), user, NotificationType.LIKE, workoutId);
    }

    @Transactional
    public void unlikeWorkout(UUID workoutId, UUID userId) {
        User user = userService.getUserById(userId);
        Workout workout = getWorkout(workoutId);

        WorkoutLike like = likeRepository.findByUserAndWorkout(user, workout)
                .orElseThrow(() -> new ResourceNotFoundException("You haven't liked this workout"));

        likeRepository.delete(like);
    }

    public List<CommentResponse> getComments(UUID workoutId) {
        getWorkout(workoutId); // 404 for unknown workouts
        return commentRepository.findAllByWorkoutIdOrderByCreatedAtAsc(workoutId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public CommentResponse addComment(UUID workoutId, UUID userId, String text) {
        User user = userService.getUserById(userId);
        Workout workout = getWorkout(workoutId);

        WorkoutComment comment = commentRepository.save(WorkoutComment.builder()
                .user(user)
                .workout(workout)
                .text(text)
                .createdAt(LocalDateTime.now())
                .build());

        notificationService.notify(workout.getUser(), user, NotificationType.COMMENT, workoutId);

        return toResponse(comment);
    }

    @Transactional
    public void deleteComment(UUID workoutId, UUID commentId, UUID userId) {
        WorkoutComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getWorkout().getId().equals(workoutId)) {
            throw new ResourceNotFoundException("Comment not found");
        }

        boolean isAuthor = comment.getUser().getId().equals(userId);
        boolean isWorkoutOwner = comment.getWorkout().getUser().getId().equals(userId);
        if (!isAuthor && !isWorkoutOwner) {
            throw new AccessDeniedException("You can't delete this comment");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse toResponse(WorkoutComment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getUser().getImageUrl(),
                comment.getText(),
                comment.getCreatedAt()
        );
    }
}
