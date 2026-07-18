package app.web;

import app.security.AuthenticationMetadata;
import app.user.service.UserService;
import app.utils.DtoMapper;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(DtoMapper.toUserResponse(userService.getUserById(principal.getId())));
    }

    @PostMapping(value = "/profile/avatar", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> uploadAvatar(@RequestParam("file") org.springframework.web.multipart.MultipartFile file,
                                                     @AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(userService.updateAvatar(principal.getId(), file));
    }

}
