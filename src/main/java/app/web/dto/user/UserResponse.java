package app.web.dto.user;

import app.user.model.Sex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String username,
        String email,
        String imageUrl,
        LocalDate birthDate,
        Sex sex,
        String bio,
        LocalDateTime createdAt
) {}
