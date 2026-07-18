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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public FollowService(FollowRepository followRepository, UserService userService, NotificationService notificationService) {
        this.followRepository = followRepository;
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

        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .createdAt(LocalDateTime.now())
                .build();

        followRepository.save(follow);

        notificationService.notify(followed, follower, NotificationType.FOLLOW, null);
    }

    public void unfollowUser(UUID targetUserId, UUID currentUserId) {
        if (targetUserId.equals(currentUserId)) {
            throw new IllegalArgumentException("You can't unfollow yourself");
        }

        User follower = userService.getUserById(currentUserId);
        User followed = userService.getUserById(targetUserId);

        Follow follow = followRepository.findByFollowerAndFollowed(follower, followed)
                .orElseThrow(() -> new ResourceNotFoundException("You are not following this user"));

        followRepository.delete(follow);
    }

    public long getFollowerCount(UUID userId) {
        return followRepository.countByFollowed(userService.getUserById(userId));
    }

    public long getFollowedCount(UUID userId) {
        return followRepository.countByFollower(userService.getUserById(userId));
    }

}
