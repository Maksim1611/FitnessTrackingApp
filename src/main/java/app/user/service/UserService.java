package app.user.service;

import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.follow.repository.FollowRepository;
import app.follow.repository.FollowRequestRepository;
import app.security.AuthenticationMetadata;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.repository.UserRepository;
import app.utils.DtoMapper;
import app.web.dto.auth.RegisterRequest;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.PublicProfileResponse;
import app.web.dto.user.UserResponse;
import app.web.dto.user.UserSearchResponse;
import app.workout.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static final java.util.List<String> INTERNAL_MARKERS =
            List.of("test", "demo", "qa", "sample", "dummy");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowRepository followRepository;
    private final WorkoutRepository workoutRepository;
    private final app.upload.FileStorageService fileStorageService;

    private final FollowRequestRepository followRequestRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FollowRepository followRepository,
                       WorkoutRepository workoutRepository, app.upload.FileStorageService fileStorageService, FollowRequestRepository followRequestRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.followRepository = followRepository;
        this.workoutRepository = workoutRepository;
        this.fileStorageService = fileStorageService;
        this.followRequestRepository = followRequestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user with email: " + email));

        return AuthenticationMetadata.builder()
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .password(user.getPassword())
                .id(user.getId())
                .build();
    }

    @Transactional
    public void registerUser(@Valid RegisterRequest request) {
        Optional<User> optional = userRepository.findByEmail(request.email());

        if (optional.isPresent()) {
            throw new DuplicateResourceException("Email already in use");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("Username already in use");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .role(UserRole.USER)
                .username(request.username())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .workouts(new ArrayList<>())
                .imageUrl("")
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponse updateProfile(UUID userId, EditProfileRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (request.email() != null && !request.email().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.email())) {
                throw new DuplicateResourceException("Email is already in use");
            }
        }

        if (request.username() != null && !request.username().equals(user.getUsername())) {
            if (userRepository.existsByUsername(request.username())) {
                throw new DuplicateResourceException("Username is already in use");
            }
        }

        DtoMapper.updateUserFromRequest(user,request);

        if (request.password() != null && !request.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        user.setUpdatedOn(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        return DtoMapper.toUserResponse(savedUser);
    }

    public List<UserSearchResponse> searchUsers(String username) {
        return userRepository.findTop20ByUsernameContainingIgnoreCase(username)
                .stream()
                .map(user -> new UserSearchResponse(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getImageUrl()
                )).toList();
    }

    public UserResponse updateAvatar(UUID userId, org.springframework.web.multipart.MultipartFile file) {
        String url = fileStorageService.storeImage(file, "avatars", userId.toString());

        User user = getUserById(userId);
        user.setImageUrl(url);
        user.setUpdatedOn(LocalDateTime.now());
        return DtoMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserSearchResponse> getSuggestedUsers(UUID currentUserId, int limit) {
        User current = getUserById(currentUserId);
        var followedIds = followRepository.findAllByFollower(current).stream()
                .map(f -> f.getFollowed().getId())
                .collect(java.util.stream.Collectors.toSet());

        return userRepository.findAll().stream()
                .filter(u -> !u.getId().equals(currentUserId)
                        && !followedIds.contains(u.getId())
                        && !looksInternal(u))
                .sorted((a, b) -> Long.compare(followRepository.countByFollowed(b), followRepository.countByFollowed(a)))
                .limit(limit)
                .map(u -> new UserSearchResponse(u.getId(), u.getName(), u.getUsername(), u.getImageUrl()))
                .toList();
    }

    public PublicProfileResponse getPublicProfile(UUID targetUserId, UUID currentUserId) {
        User targetUser = this.getUserById(targetUserId);
        User currentUser = this.getUserById(currentUserId);

        boolean isFollowing = !targetUserId.equals(currentUserId) && followRepository.existsByFollowerAndFollowed(currentUser, targetUser);

        boolean requested = !targetUserId.equals(currentUserId)
                && followRequestRepository.existsByRequesterAndTarget(currentUser, targetUser);

        return new PublicProfileResponse(
                targetUser.getId(),
                targetUser.getName(),
                targetUser.getUsername(),
                targetUser.getImageUrl(),
                targetUser.getBio(),
                followRepository.countByFollowed(targetUser),
                followRepository.countByFollower(targetUser),
                workoutRepository.countByUserAndFinishedAtIsNotNull(targetUser),
                isFollowing,
                targetUser.isPrivateProfile(),
                requested
                );
    }

    private boolean looksInternal(User u) {
        String handle = (u.getUsername() == null ? "" : u.getUsername()).toLowerCase();
        return INTERNAL_MARKERS.stream().anyMatch(handle::contains);
    }

    public boolean canViewWorkouts(UUID targetUserId, UUID viewerId) {
        if (targetUserId.equals(viewerId)) return true;
        User target = getUserById(targetUserId);
        if (!target.isPrivateProfile()) return true;
        return followRepository.existsByFollowerAndFollowed(getUserById(viewerId), target);
    }


}
