package app.web.dto.auth;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}
