package app.web.dto.social;

import app.social.model.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        NotificationType type,
        UUID actorId,
        String actorUsername,
        String actorImageUrl,
        UUID referenceId,
        boolean read,
        LocalDateTime createdAt
) {
}
