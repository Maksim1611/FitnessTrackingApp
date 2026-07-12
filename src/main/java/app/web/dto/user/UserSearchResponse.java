package app.web.dto.user;

import java.util.UUID;

public record UserSearchResponse(
        UUID id,
        String name,
        String username,
        String imageUrl
) {
}
