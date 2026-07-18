package app.social.service;

import app.social.model.Notification;
import app.social.model.NotificationType;
import app.social.repository.NotificationRepository;
import app.user.model.User;
import app.web.dto.social.NotificationResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void notify(User recipient, User actor, NotificationType type, UUID referenceId) {
        if (recipient.getId().equals(actor.getId())) {
            return; // no self-notifications
        }
        notificationRepository.save(Notification.builder()
                .user(recipient)
                .actor(actor)
                .type(type)
                .referenceId(referenceId)
                .read(false)
                .createdAt(LocalDateTime.now())
                .build());
    }

    public List<NotificationResponse> getMyNotifications(UUID userId) {
        return notificationRepository.findTop50ByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(n -> new NotificationResponse(
                        n.getId(),
                        n.getType(),
                        n.getActor() != null ? n.getActor().getId() : null,
                        n.getActor() != null ? n.getActor().getUsername() : null,
                        n.getActor() != null ? n.getActor().getImageUrl() : null,
                        n.getReferenceId(),
                        n.isRead(),
                        n.getCreatedAt()
                ))
                .toList();
    }

    public long getUnreadCount(UUID userId) {
        return notificationRepository.countByUserIdAndReadFalse(userId);
    }

    @Transactional
    public void markAllRead(UUID userId) {
        List<Notification> unread = notificationRepository.findAllByUserIdAndReadFalse(userId);
        unread.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(unread);
    }
}
