package app.web.dto.social;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        UUID userId,
        String username,
        String userImageUrl,
        String text,
        LocalDateTime createdAt
) {
}
