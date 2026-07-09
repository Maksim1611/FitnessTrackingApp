package app.user.service;

import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.security.AuthenticationMetadata;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.repository.UserRepository;
import app.utils.DtoMapper;
import app.web.dto.auth.RegisterRequest;
import app.web.dto.user.EditProfileRequest;
import app.web.dto.user.UserResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .role(UserRole.USER)
                .username(request.username())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .followers(0)
                .following(0)
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
}
