package app.badge.repository;

import app.badge.model.BadgeCategory;
import app.badge.model.BadgeTier;
import app.badge.model.UserBadge;
import app.exercise.model.MuscleGroup;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, UUID> {

    List<UserBadge> findByUserOrderByEarnedAtDesc(User user);

    boolean existsByUserAndCategoryAndTierAndMuscleGroup(User user, BadgeCategory category, BadgeTier tier, MuscleGroup muscleGroup);

    List<UserBadge> findAllByUserOrderByEarnedAtDesc(User user);
}
