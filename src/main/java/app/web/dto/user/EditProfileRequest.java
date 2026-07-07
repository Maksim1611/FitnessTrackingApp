package app.web.dto.user;

public record EditProfileRequest(
        String username,
        String name,
        String email,
        String password,
        String imageUrl
) {
}
