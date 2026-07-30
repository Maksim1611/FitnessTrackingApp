package app.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String name,
        @NotBlank
        @Size(min = 3, max = 20, message = "Username must be 3–20 characters")
        @Pattern(
                regexp = "^[a-zA-Z][a-zA-Z0-9_]*$",
                message = "Username must start with a letter and use only letters, numbers or underscores"
        )
        String username,
        @Email @NotBlank String email,
        @Size(min = 8) String password

) {}
