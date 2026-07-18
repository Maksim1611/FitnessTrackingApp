package app.feed.service;

import app.social.repository.WorkoutCommentRepository;
import app.social.repository.WorkoutLikeRepository;
import app.web.dto.feed.FeedItemResponse;
import app.workout.model.Workout;
import app.workout.repository.WorkoutRepository;
import app.workoutset.model.WorkoutSet;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FeedService {

    private static final int MAX_PAGE_SIZE = 50;

    private final WorkoutRepository workoutRepository;
    private final WorkoutLikeRepository likeRepository;
    private final WorkoutCommentRepository commentRepository;

    public FeedService(WorkoutRepository workoutRepository, WorkoutLikeRepository likeRepository,
                       WorkoutCommentRepository commentRepository) {
        this.workoutRepository = workoutRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public List<FeedItemResponse> getFeed(UUID userId, int page, int size) {
        int cappedSize = Math.min(size, MAX_PAGE_SIZE);

        List<Workout> workouts = workoutRepository.findFeedForUser(userId, PageRequest.of(page, cappedSize));

        List<FeedItemResponse> feed = new ArrayList<>();
        for (Workout workout : workouts) {
            int totalSets = 0;
            double totalVolume = 0.0;
            int prCount = 0;

            for (WorkoutSet set : workout.getWorkoutSets()) {
                if (!set.isCompleted()) {
                    continue;
                }
                totalSets++;
                if (set.getWeight() != null && set.getReps() != null) {
                    totalVolume += set.getWeight() * set.getReps();
                }
                if (set.isPersonalRecord()) {
                    prCount++;
                }
            }

            feed.add(new FeedItemResponse(
                    workout.getId(),
                    workout.getUser().getId(),
                    workout.getUser().getUsername(),
                    workout.getUser().getName(),
                    workout.getUser().getImageUrl(),
                    workout.getName(),
                    workout.getStartedAt(),
                    workout.getFinishedAt(),
                    totalSets,
                    totalVolume,
                    prCount,
                    likeRepository.countByWorkoutId(workout.getId()),
                    commentRepository.countByWorkoutId(workout.getId()),
                    likeRepository.existsByUserIdAndWorkoutId(userId, workout.getId())
            ));
        }

        return feed;
    }
}