package app.badge.service;

import app.badge.model.BadgeCategory;
import app.badge.model.BadgeTier;
import app.badge.model.UserBadge;
import app.badge.repository.UserBadgeRepository;
import app.exercise.model.MuscleGroup;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.badge.BadgeResponse;
import app.workout.repository.WorkoutRepository;
import app.workoutset.model.WorkoutSet;
import app.workoutset.repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BadgeService {

    private final UserBadgeRepository userBadgeRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final UserService userService;

    public BadgeService(UserBadgeRepository userBadgeRepository, WorkoutRepository workoutRepository,
                        WorkoutSetRepository workoutSetRepository, UserService userService) {
        this.userBadgeRepository = userBadgeRepository;
        this.workoutRepository = workoutRepository;
        this.workoutSetRepository = workoutSetRepository;
        this.userService = userService;
    }

    public List<BadgeResponse> getMyBadges(UUID userId) {
        User user = userService.getUserById(userId);

        List<UserBadge> badges = userBadgeRepository.findAllByUserOrderByEarnedAtDesc(user);

        List<BadgeResponse> result = new ArrayList<>();
        for (UserBadge badge : badges) {
            result.add(new BadgeResponse(badge.getId(), badge.getCategory(), badge.getTier(),
                    badge.getMuscleGroup(), badge.getEarnedAt()));
        }
        return result;
    }

    public void evaluateAndAward(UUID userId) {
        User user = userService.getUserById(userId);

        long finishedWorkouts = workoutRepository.countByUserAndFinishedAtIsNotNull(user);

        List<WorkoutSet> allCompletedSets = workoutSetRepository.findAllCompletedByUser(userId);

        long prCount = 0;
        double totalVolume = 0.0;
        long cardioSeconds = 0;

        Map<MuscleGroup, Double> volumeByMuscle = new HashMap<>();

        for (WorkoutSet set : allCompletedSets) {
            if (set.isPersonalRecord()) {
                prCount++;
            }
            if (set.getDurationSeconds() != null) {
                cardioSeconds = cardioSeconds + set.getDurationSeconds();
            }

            if (set.getWeight() != null && set.getReps() != null) {
                double setVolume = set.getWeight() * set.getReps();
                totalVolume = totalVolume + setVolume;

                MuscleGroup muscle = set.getExercise().getPrimaryMuscleGroup();
                if (muscle != null) {
                    Double currentVolume = volumeByMuscle.get(muscle);
                    if (currentVolume == null) {
                        currentVolume = 0.0;
                    }
                    volumeByMuscle.put(muscle, currentVolume + setVolume);
                }
            }
        }

        awardForMetric(user, BadgeCategory.WORKOUT_COUNT, null, finishedWorkouts);
        awardForMetric(user, BadgeCategory.PR_COUNT, null, prCount);
        awardForMetric(user, BadgeCategory.TOTAL_VOLUME, null, (long) totalVolume);
        awardForMetric(user, BadgeCategory.CARDIO_TIME, null, cardioSeconds);

        for (Map.Entry<MuscleGroup, Double> entry : volumeByMuscle.entrySet()) {
            awardForMetric(user, BadgeCategory.MUSCLE_VOLUME, entry.getKey(), (long) (double) entry.getValue());
        }
    }

    private void awardForMetric(User user, BadgeCategory category, MuscleGroup muscleGroup, long metric) {
        for (BadgeTier tier : BadgeTier.values()) {
            long threshold = thresholdFor(category, tier);

            if (metric < threshold) {
                continue;
            }

            boolean alreadyEarned = userBadgeRepository.existsByUserAndCategoryAndTierAndMuscleGroup(user, category, tier, muscleGroup);
            if (alreadyEarned) {
                continue;
            }

            UserBadge badge = UserBadge.builder()
                    .user(user)
                    .category(category)
                    .tier(tier)
                    .muscleGroup(muscleGroup)
                    .earnedAt(LocalDateTime.now())
                    .build();

            userBadgeRepository.save(badge);
        }
    }

    private long thresholdFor(BadgeCategory category, BadgeTier tier) {
        if (category == BadgeCategory.WORKOUT_COUNT) {
            if (tier == BadgeTier.BRONZE) return 10;
            if (tier == BadgeTier.SILVER) return 50;
            if (tier == BadgeTier.GOLD) return 100;
            if (tier == BadgeTier.DIAMOND) return 250;
            return 500;
        }
        if (category == BadgeCategory.PR_COUNT) {
            if (tier == BadgeTier.BRONZE) return 1;
            if (tier == BadgeTier.SILVER) return 10;
            if (tier == BadgeTier.GOLD) return 25;
            if (tier == BadgeTier.DIAMOND) return 50;
            return 100;
        }
        if (category == BadgeCategory.TOTAL_VOLUME) {
            if (tier == BadgeTier.BRONZE) return 100_000;
            if (tier == BadgeTier.SILVER) return 500_000;
            if (tier == BadgeTier.GOLD) return 2_000_000;
            if (tier == BadgeTier.DIAMOND) return 10_000_000;
            return 50_000_000;
        }
        if (category == BadgeCategory.CARDIO_TIME) {
            if (tier == BadgeTier.BRONZE) return 3_600;
            if (tier == BadgeTier.SILVER) return 36_000;
            if (tier == BadgeTier.GOLD) return 180_000;
            if (tier == BadgeTier.DIAMOND) return 540_000;
            return 1_800_000;
        }
        if (tier == BadgeTier.BRONZE) return 10_000;
        if (tier == BadgeTier.SILVER) return 50_000;
        if (tier == BadgeTier.GOLD) return 250_000;
        if (tier == BadgeTier.DIAMOND) return 1_000_000;
        return 5_000_000;
    }
}