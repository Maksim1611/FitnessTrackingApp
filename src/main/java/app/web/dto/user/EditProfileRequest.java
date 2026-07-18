package app.web.dto.user;

import app.user.model.Sex;

import java.time.LocalDate;

public record EditProfileRequest(
        String username,
        String name,
        String email,
        String password,
        String imageUrl,
        LocalDate birthDate,
        Sex sex,
        String bio
) {
}
