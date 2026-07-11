package app.web;

import app.jwt.JwtUtil;
import app.refreshToken.model.RefreshToken;
import app.refreshToken.repository.RefreshTokenRepository;
import app.security.AuthenticationMetadata;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.user.service.UserService;
import app.web.dto.auth.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return ResponseEntity.status(409).body("Email already in use");
        }
        userService.registerUser(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }

        User user = userRepository.findByEmail(request.email()).orElseThrow();
        AuthenticationMetadata authenticationMetadata = AuthenticationMetadata.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        String accessToken = jwtUtil.generateAccessToken(authenticationMetadata);
        String refreshTokenStr = jwtUtil.generateRefreshToken(authenticationMetadata);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenStr);
        refreshToken.setUser(user);
        refreshToken.setExpiredAt(LocalDateTime.now().plusDays(7));
        refreshTokenRepository.save(refreshToken);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshTokenStr));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        Optional<RefreshToken> storedTokenOpt = refreshTokenRepository.findByToken(request.refreshToken());

        if (storedTokenOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }

        RefreshToken storedToken = storedTokenOpt.get();

        if (storedToken.isRevoked() || storedToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }

        if (!jwtUtil.isTokenValid(storedToken.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
        }
        User user = storedToken.getUser();

        storedToken.setRevoked(true);
        refreshTokenRepository.save(storedToken);

        AuthenticationMetadata authMetadata = AuthenticationMetadata.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        String newAccessToken = jwtUtil.generateAccessToken(authMetadata);
        String newRefreshTokenStr = jwtUtil.generateRefreshToken(authMetadata);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(newRefreshTokenStr)
                .expiredAt(LocalDateTime.now().plusDays(7))
                .user(user)
                .build();

        refreshTokenRepository.save(refreshToken);

        return ResponseEntity.ok(new AuthResponse(newAccessToken,  newRefreshTokenStr));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshRequest request) {
        refreshTokenRepository.findByToken(request.refreshToken()).ifPresent(refreshToken -> {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        });
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}
