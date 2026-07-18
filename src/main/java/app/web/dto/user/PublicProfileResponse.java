package app.web.dto.user;

import java.util.UUID;

public record PublicProfileResponse(
        UUID id,
        String name,
        String username,
        String imageUrl,
        long followerCount,
        long followingCount,
        long workoutCount,
        boolean isFollowing
) {
}
