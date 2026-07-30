package app.follow.service;

import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.follow.model.Follow;
import app.follow.repository.FollowRepository;
import app.social.model.NotificationType;
import app.social.service.NotificationService;
import app.user.model.User;
import app.user.service.UserService;
import org.springframework.stereotype.Service;
import app.follow.model.FollowRequest;
import app.follow.repository.FollowRequestRepository;
import app.web.dto.user.UserSearchResponse;
import java.util.List;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    private final FollowRequestRepository followRequestRepository;

    public FollowService(FollowRepository followRepository, FollowRequestRepository followRequestRepository,
                         UserService userService, NotificationService notificationService) {
        this.followRepository = followRepository;
        this.followRequestRepository = followRequestRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void followUser(UUID targetUserId, UUID currentUserId) {
        if (targetUserId.equals(currentUserId)) {
            throw new IllegalArgumentException("You can't follow yourself");
        }
        User follower = userService.getUserById(currentUserId);
        User followed = userService.getUserById(targetUserId);

        if (followRepository.existsByFollowerAndFollowed(follower, followed)) {
            throw new DuplicateResourceException("You are already following this user");
        }

        if (followed.isPrivateProfile()) {
            if (followRequestRepository.existsByRequesterAndTarget(follower, followed)) {
                throw new DuplicateResourceException("Follow request already sent");
            }
            followRequestRepository.save(FollowRequest.builder()
                    .requester(follower).target(followed).createdAt(LocalDateTime.now()).build());
            notificationService.notify(followed, follower, NotificationType.FOLLOW_REQUEST, null);
            return;
        }

        followRepository.save(Follow.builder()
                .follower(follower).followed(followed).createdAt(LocalDateTime.now()).build());
        notificationService.notify(followed, follower, NotificationType.FOLLOW, null);
    }

    public void unfollowUser(UUID targetUserId, UUID currentUserId) {
        if (targetUserId.equals(currentUserId)) {
            throw new IllegalArgumentException("You can't unfollow yourself");
        }
        User follower = userService.getUserById(currentUserId);
        User followed = userService.getUserById(targetUserId);

        followRequestRepository.findByRequesterAndTarget(follower, followed)
                .ifPresent(followRequestRepository::delete);
        followRepository.findByFollowerAndFollowed(follower, followed)
                .ifPresent(followRepository::delete);
    }

    public long getFollowerCount(UUID userId) {
        return followRepository.countByFollowed(userService.getUserById(userId));
    }

    public long getFollowedCount(UUID userId) {
        return followRepository.countByFollower(userService.getUserById(userId));
    }

    public List<UserSearchResponse> getFollowRequests(UUID currentUserId) {
        User me = userService.getUserById(currentUserId);
        return followRequestRepository.findAllByTargetOrderByCreatedAtDesc(me).stream()
                .map(r -> new UserSearchResponse(r.getRequester().getId(), r.getRequester().getName(),
                        r.getRequester().getUsername(), r.getRequester().getImageUrl()))
                .toList();
    }

    public long getFollowRequestCount(UUID currentUserId) {
        return followRequestRepository.countByTarget(userService.getUserById(currentUserId));
    }

    public void acceptFollowRequest(UUID requesterId, UUID currentUserId) {
        User target = userService.getUserById(currentUserId);
        User requester = userService.getUserById(requesterId);
        FollowRequest request = followRequestRepository.findByRequesterAndTarget(requester, target)
                .orElseThrow(() -> new ResourceNotFoundException("No such follow request"));
        followRequestRepository.delete(request);
        if (!followRepository.existsByFollowerAndFollowed(requester, target)) {
            followRepository.save(Follow.builder()
                    .follower(requester).followed(target).createdAt(LocalDateTime.now()).build());
        }
        notificationService.notify(requester, target, NotificationType.FOLLOW_ACCEPTED, null);
    }

    public void denyFollowRequest(UUID requesterId, UUID currentUserId) {
        User target = userService.getUserById(currentUserId);
        User requester = userService.getUserById(requesterId);
        followRequestRepository.findByRequesterAndTarget(requester, target)
                .ifPresent(followRequestRepository::delete);
    }

}
