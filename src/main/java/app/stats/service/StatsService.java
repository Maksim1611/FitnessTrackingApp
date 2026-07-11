package app.stats.service;

import app.web.dto.stats.ExerciseProgressPointResponse;
import app.web.dto.stats.WeeklyVolumeResponse;
import app.workoutset.model.WorkoutSet;
import app.workoutset.repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatsService {

    private final WorkoutSetRepository workoutSetRepository;

    public StatsService(WorkoutSetRepository workoutSetRepository) {
        this.workoutSetRepository = workoutSetRepository;
    }

    public List<ExerciseProgressPointResponse> getExerciseProgress(UUID exerciseId, UUID userId) {
        List<WorkoutSet> completedSets = workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId);

        Map<UUID, List<WorkoutSet>> setsByWorkout = new LinkedHashMap<>();

        for (WorkoutSet set : completedSets) {
            setsByWorkout.computeIfAbsent(set.getWorkout().getId(), k -> new ArrayList<>()).add(set);
        }

        List<ExerciseProgressPointResponse> points = new ArrayList<>();

        for (List<WorkoutSet> sessionSets : setsByWorkout.values()) {
            WorkoutSet topSet = sessionSets.getFirst();
            boolean sessionHadPr = false;

            for (WorkoutSet set : sessionSets) {
                if (weightOf(set) > weightOf(topSet)) {
                    topSet = set;
                }
                if (set.isPersonalRecord()) {
                    sessionHadPr = true;
                }
            }

            points.add(new ExerciseProgressPointResponse(
                    topSet.getWorkout().getId(),
                    topSet.getWorkout().getStartedAt(),
                    topSet.getWeight(),
                    topSet.getReps(),
                    sessionHadPr
            ));
        }

        return points;
    }

    private double weightOf(WorkoutSet set) {
        return set.getWeight() != null ? set.getWeight() : 0.0;
    }

    public List<WeeklyVolumeResponse> getWeeklyVolume(int weeks, UUID userId) {
        LocalDate firstWeekStart = LocalDate.now().with(DayOfWeek.MONDAY).minusWeeks(weeks - 1);

        LocalDateTime since = firstWeekStart.atStartOfDay();

        List<WorkoutSet> completedSets = workoutSetRepository.findCompletedSetsSince(userId, since);

        Map<LocalDate, List<WorkoutSet>> setsByWorkout = new TreeMap<>();

        for (WorkoutSet set : completedSets) {
            LocalDate weekStart = set.getWorkout().getStartedAt().toLocalDate().with(DayOfWeek.MONDAY);
            setsByWorkout.computeIfAbsent(weekStart, k -> new ArrayList<>()).add(set);
        }

        List<WeeklyVolumeResponse> result = new ArrayList<>();
        for (Map.Entry<LocalDate, List<WorkoutSet>> entry : setsByWorkout.entrySet()) {
            double totalVolume = 0.0;
            for (WorkoutSet set : entry.getValue()) {
                if (set.getWeight() != null && set.getReps() != null) {
                    totalVolume += set.getWeight() * set.getReps();
                }
            }

            result.add(new WeeklyVolumeResponse(entry.getKey(), totalVolume, entry.getValue().size()));
        }

        return result;
    }

}
