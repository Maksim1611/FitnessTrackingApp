package app.web.dto.social;

public record WorkoutSocialResponse(
        long likeCount,
        long commentCount,
        boolean likedByMe
) {
}
