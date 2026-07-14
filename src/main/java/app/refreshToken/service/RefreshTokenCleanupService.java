package app.refreshToken.service;

import app.refreshToken.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefreshTokenCleanupService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenCleanupService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void cleanup() {
        int deleted = refreshTokenRepository.deleteExpiredAndRevoke(LocalDateTime.now());
        System.out.println("Refresh token cleanup: removed " + deleted + " rows");
    }
}
