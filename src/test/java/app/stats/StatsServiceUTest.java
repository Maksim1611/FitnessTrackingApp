package app.stats;

import app.exercise.model.Exercise;
import app.exercise.model.ExerciseType;
import app.stats.service.StatsService;
import app.web.dto.stats.ProgressionSuggestionResponse;
import app.workout.model.Workout;
import app.workoutset.model.WorkoutSet;
import app.workoutset.repository.WorkoutSetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsServiceUTest {

    @Mock
    private WorkoutSetRepository workoutSetRepository;

    @InjectMocks
    private StatsService statsService;

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsEmptyCompletedSets_thenReturnEmptyProgressionResponse() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(Collections.emptyList());

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertNull(response.suggestedReps());
        assertNull(response.suggestedWeight());
        assertNull(response.suggestedDurationSeconds());
        assertTrue(response.reason().contains("Not enough history"));
    }

    private WorkoutSet buildSet(Workout workout, ExerciseType type, Double weight, Integer reps, Double rpe) {
        return WorkoutSet.builder()
                .id(UUID.randomUUID())
                .workout(workout)
                .exercise(Exercise.builder().id(UUID.randomUUID()).exerciseType(type).build())
                .weight(weight)
                .reps(reps)
                .rpe(rpe)
                .completed(true)
                .build();
    }

    private WorkoutSet buildDurationSet(Workout workout, int durationSeconds) {
        return WorkoutSet.builder()
                .id(UUID.randomUUID())
                .workout(workout)
                .exercise(Exercise.builder().id(UUID.randomUUID()).exerciseType(ExerciseType.DURATION).build())
                .weight(null)
                .reps(null)
                .rpe(null)
                .completed(true)
                .durationSeconds(durationSeconds)
                .build();
    }

    private Workout buildWorkout() {
        return Workout.builder().id(UUID.randomUUID()).startedAt(LocalDateTime.now()).build();
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSet_thenReturnRPE7() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 80.0, 10, 7.0);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(10, response.suggestedReps());
        assertEquals(82.5, response.suggestedWeight());

    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSet_thenReturnRepsTo9() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 80.0, 8, 8.5);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(9, response.suggestedReps());
        assertEquals(80.0, response.suggestedWeight());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSet_thenNothingChanges() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 100.0, 6, 9.5);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(6, response.suggestedReps());
        assertEquals(100.0, response.suggestedWeight());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSet_thenDeloadTo132Kg() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 140.0, 3, 9.5);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(132.5, response.suggestedWeight());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSetWithRPENull_thenExceptWeightUpTwoAndHalfKg() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 80.0, 10, null);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(82.5 , response.suggestedWeight());
        assertEquals(10, response.suggestedReps());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutOneSetWithRPENull_thenExceptWeightSame1RepMore() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.WEIGHT_REPS, 80.0, 6, null);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(80 , response.suggestedWeight());
        assertEquals(7, response.suggestedReps());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneWorkoutTwoSetsWithNullRPE_thenReturnBasedOnHeavierSet() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet set1 = buildSet(workout, ExerciseType.WEIGHT_REPS, 80.0, 10, null);
        WorkoutSet set2 = buildSet(workout, ExerciseType.WEIGHT_REPS, 85.0, 6, null);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(set1, set2));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(85.0 , response.suggestedWeight());
        assertEquals(7, response.suggestedReps());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsTwoWorkoutTwoSetsWithDifferentDates_thenReturnBasedOnNewerOne() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout1 = buildWorkout();
        workout1.setStartedAt(LocalDateTime.now().minusDays(5));

        Workout workout2 = buildWorkout();
        workout2.setStartedAt(LocalDateTime.now().minusDays(6));

        WorkoutSet set1 = buildSet(workout1, ExerciseType.WEIGHT_REPS, 60.0, 10, null);
        WorkoutSet set2 = buildSet(workout2, ExerciseType.WEIGHT_REPS, 100.0, 10, null);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(set2, set1));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(62.5 , response.suggestedWeight());
        assertEquals(10, response.suggestedReps());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneSetWithTypeDuration_returnsDuration70() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();
        Workout workout = buildWorkout();

        WorkoutSet workoutSet = buildDurationSet(workout, 60);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertEquals(70, response.suggestedDurationSeconds());
        assertNull(response.suggestedReps());
        assertNull(response.suggestedWeight());
    }

    @Test
    void whenGetProgressionSuggestion_andRepositoryReturnsOneSetWithBodyWeightType_thenReturns13WeightNull() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();
        WorkoutSet workoutSet = buildSet(workout, ExerciseType.BODYWEIGHT, null, 12, null);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertNull(response.suggestedWeight());
        assertEquals(13, response.suggestedReps());
    }

    @Test
    void whenGetProgressionSuggestion_andExerciseTypeIsUnsupported_thenReturnsNotSupportedReason() {
        UUID userId = UUID.randomUUID();
        UUID exerciseId = UUID.randomUUID();

        Workout workout = buildWorkout();

        WorkoutSet workoutSet = buildSet(workout, ExerciseType.ASSISTED_BODYWEIGHT, null, 15, 7.6);

        when(workoutSetRepository.findCompletedSetsForExercise(exerciseId, userId)).thenReturn(List.of(workoutSet));

        ProgressionSuggestionResponse response = statsService.getProgressionSuggestion(exerciseId, userId);

        assertNull(response.suggestedWeight());
        assertNull(response.suggestedReps());
        assertNull(response.suggestedDurationSeconds());
        assertTrue(response.reason().contains("aren't supported"));    }
}
