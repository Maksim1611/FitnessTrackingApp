package app.web;

import app.badge.service.BadgeService;
import app.security.AuthenticationMetadata;
import app.web.dto.badge.BadgeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping
    public ResponseEntity<List<BadgeResponse>> getMyBadges(@AuthenticationPrincipal AuthenticationMetadata principal) {
        return ResponseEntity.ok(badgeService.getMyBadges(principal.getId()));
    }

}
