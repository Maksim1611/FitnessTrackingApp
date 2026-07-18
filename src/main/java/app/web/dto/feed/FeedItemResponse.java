package app.web.dto.feed;

import java.time.LocalDateTime;
import java.util.UUID;

public record FeedItemResponse(
        UUID workoutId,
        UUID userId,
        String username,
        String name,
        String userImageUrl,
        String workoutName,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        int totalSets,
        double totalVolume,
        int prCount,
        long likeCount,
        long commentCount,
        boolean likedByMe
) {
}
