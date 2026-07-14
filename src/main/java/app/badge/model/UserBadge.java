package app.badge.model;

import app.exercise.model.MuscleGroup;
import app.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BadgeCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BadgeTier tier;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group", length = 30)
    private MuscleGroup muscleGroup;

    @Column(nullable = false)
    private LocalDateTime earnedAt;
}
