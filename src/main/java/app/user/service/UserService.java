package app.user.service;

import app.exception.ResourceNotFoundException;
import app.security.AuthenticationMetadata;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.repository.UserRepository;
import app.web.dto.auth.RegisterRequest;
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
                .role(user.getRole())
                .password(user.getPassword())
                .id(user.getId())
                .build();
    }

    @Transactional
    public void registerUser(@Valid RegisterRequest request) {
        Optional<User> optional = userRepository.findByEmail(request.email());

        if (optional.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .role(UserRole.USER)
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
}
