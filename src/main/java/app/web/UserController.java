package app.web;

import app.security.AuthenticationMetadata;
import app.user.service.UserService;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody EditProfileRequest request, @AuthenticationPrincipal AuthenticationMetadata principal) {
        UserResponse userResponse = userService.updateProfile(principal.getId(), request);
        return ResponseEntity.ok(userResponse);
    }

}
