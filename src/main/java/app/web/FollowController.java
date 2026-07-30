package app.web;

import app.follow.service.FollowService;
import app.security.AuthenticationMetadata;
import app.user.service.UserService;
import app.web.dto.user.PublicProfileResponse;
import app.web.dto.user.UserSearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<Void> followUser(@PathVariable UUID id,
                                           @AuthenticationPrincipal AuthenticationMetadata principal) {
        followService.followUser(id, principal.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/follow")
    public ResponseEntity<Void> unfollowUser(@PathVariable UUID id,
                                             @AuthenticationPrincipal AuthenticationMetadata principal) {
        followService.unfollowUser(id, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/suggested")
    public ResponseEntity<List<UserSearchResponse>> getSuggestedUsers(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(userService.getSuggestedUsers(principal.getId(), 6));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(@RequestParam String username) {
        List<UserSearchResponse> userSearchResponses = userService.searchUsers(username);
        return ResponseEntity.ok(userSearchResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicProfileResponse> getPublicProfile(@PathVariable UUID id,
                                                                  @AuthenticationPrincipal AuthenticationMetadata principal) {
        PublicProfileResponse profile = userService.getPublicProfile(id, principal.getId());
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/follow-requests")
    public ResponseEntity<List<UserSearchResponse>> getFollowRequests(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(followService.getFollowRequests(principal.getId()));
    }

    @GetMapping("/follow-requests/count")
    public ResponseEntity<Long> getFollowRequestCount(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(followService.getFollowRequestCount(principal.getId()));
    }

    @PostMapping("/follow-requests/{requesterId}/accept")
    public ResponseEntity<Void> acceptFollowRequest(@PathVariable UUID requesterId,
                                                    @AuthenticationPrincipal AuthenticationMetadata principal) {
        followService.acceptFollowRequest(requesterId, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/follow-requests/{requesterId}")
    public ResponseEntity<Void> denyFollowRequest(@PathVariable UUID requesterId,
                                                  @AuthenticationPrincipal AuthenticationMetadata principal) {
        followService.denyFollowRequest(requesterId, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
