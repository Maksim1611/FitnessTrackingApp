package app.web.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String username,
        String email,
        String imageUrl,
        LocalDateTime createdAt
) {}
