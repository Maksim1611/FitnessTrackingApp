package app.utils;

import app.user.model.User;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static void updateUserFromRequest(User user, EditProfileRequest request) {
        if (request.name() != null && !request.name().isEmpty()) {
            user.setName(request.name());
        }
        if (request.email() != null && !request.email().isEmpty()) {
            user.setEmail(request.email());
        }
        if (request.imageUrl() != null && !request.imageUrl().isEmpty()) {
            user.setImageUrl(request.imageUrl());
        }
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getImageUrl(),
                user.getCreatedOn()
        );
    }
}
