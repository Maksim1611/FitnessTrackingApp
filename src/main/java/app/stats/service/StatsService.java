package app.stats.service;

import app.exercise.model.MuscleGroup;
import app.web.dto.stats.ExerciseProgressPointResponse;
import app.web.dto.stats.MuscleGroupVolumeResponse;
import app.web.dto.stats.ProgressionSuggestionResponse;
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

    public ProgressionSuggestionResponse getProgressionSuggestion(UUID exerciseId, UUID userId) {
        List<WorkoutSet> completedSets = workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId);

        if (completedSets.isEmpty()) {
            return new ProgressionSuggestionResponse(null, null, null,
                    "Not enough history yet — complete a session with this exercise first");
        }

        UUID lastWorkoutId = completedSets.getLast().getWorkout().getId();
        List<WorkoutSet> lastSession = new ArrayList<>();
        for (WorkoutSet set : completedSets) {
            if (set.getWorkout().getId().equals(lastWorkoutId)) {
                lastSession.add(set);
            }
        }

        WorkoutSet topSet = lastSession.getFirst();
        for (WorkoutSet set : lastSession) {
            if (weightOf(set) > weightOf(topSet)) {
                topSet = set;
            }
        }

        return switch (topSet.getExercise().getExerciseType()) {
            case WEIGHT_REPS, WEIGHTED_BODYWEIGHT -> suggestForWeightReps(topSet);
            case REPS_ONLY, BODYWEIGHT -> new ProgressionSuggestionResponse(null,
                    topSet.getReps() != null ? topSet.getReps() + 1 : null, null,
                    "Last session: " + topSet.getReps() + " reps — try one more");
            case DURATION, WEIGHT_DURATION -> new ProgressionSuggestionResponse(topSet.getWeight(),
                    null, topSet.getDurationSeconds() != null ? topSet.getDurationSeconds() + 10 : null,
                    "Last session: " + topSet.getDurationSeconds() + "s — try 10 more seconds");
            default -> new ProgressionSuggestionResponse(null, null, null,
                    "Suggestions aren't supported for this exercise type yet");
        };
    }

    private ProgressionSuggestionResponse suggestForWeightReps(WorkoutSet topSet) {
        double weight = weightOf(topSet);
        int reps = topSet.getReps() != null ? topSet.getReps() : 0;
        Double rpe = topSet.getRpe();

        String lastSession = String.format("Last session: %.1fkg x %d%s", weight, reps,
                rpe != null ? " @ RPE " + rpe : "");

        if (rpe != null && rpe > 9) {
            if (reps < 5) {
                double deloaded = Math.round((weight * 0.95) / 2.5) * 2.5;
                return new ProgressionSuggestionResponse(deloaded, reps, null,
                        lastSession + " — that was a grind, back off a little and rebuild");
            }
            return new ProgressionSuggestionResponse(weight, reps, null,
                    lastSession + " — right at your limit, consolidate before adding");
        }

        if (rpe != null && rpe >= 8) {
            return new ProgressionSuggestionResponse(weight, reps + 1, null,
                    lastSession + " — solid effort, add a rep at this weight");
        }

        if (reps >= 8) {
            return new ProgressionSuggestionResponse(weight + 2.5, reps, null,
                    lastSession + " — you're ready to move up");
        }

        return new ProgressionSuggestionResponse(weight, reps + 1, null,
                lastSession + " — build to 8 reps before adding weight");
    }


    public List<MuscleGroupVolumeResponse> getMuscleGroupVolume(int weeks, UUID userId) {
        LocalDate firstWeekStart = LocalDate.now().with(DayOfWeek.MONDAY).minusWeeks(weeks - 1);

        LocalDateTime since = firstWeekStart.atStartOfDay();

        List<WorkoutSet> completedSets = workoutSetRepository.findCompletedSetsSince(userId, since);

        Map<MuscleGroup, List<WorkoutSet>> setsByMuscleGroup = new TreeMap<>();

        for (WorkoutSet set : completedSets) {
            MuscleGroup muscleGroup = set.getExercise().getPrimaryMuscleGroup();
            setsByMuscleGroup.computeIfAbsent(muscleGroup, k -> new ArrayList<>()).add(set);
        }

        List<MuscleGroupVolumeResponse> result = new ArrayList<>();
        for (Map.Entry<MuscleGroup, List<WorkoutSet>> entry : setsByMuscleGroup.entrySet()) {
            double totalVolume = 0.0;
            for (WorkoutSet set : entry.getValue()) {
                if (set.getWeight() != null && set.getReps() != null) {
                    totalVolume += set.getWeight() * set.getReps();
                }
            }

            result.add(new MuscleGroupVolumeResponse(entry.getKey(), totalVolume, entry.getValue().size()));
        }

        return result;
    }
}
