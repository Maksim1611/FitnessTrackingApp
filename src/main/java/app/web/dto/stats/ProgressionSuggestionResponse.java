package app.web.dto.stats;

public record ProgressionSuggestionResponse(
        Double suggestedWeight,
        Integer suggestedReps,
        Integer suggestedDurationSeconds,
        String reason
) {
}
