package app.web;

import app.feed.service.FeedService;
import app.security.AuthenticationMetadata;
import app.web.dto.feed.FeedItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public ResponseEntity<List<FeedItemResponse>> getFeed(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size,
                                                          @AuthenticationPrincipal AuthenticationMetadata principal) {

        List<FeedItemResponse> feed = feedService.getFeed(principal.getId(), page, size);
        return ResponseEntity.ok(feed);
    }

}
