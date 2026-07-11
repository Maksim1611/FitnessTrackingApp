package app.web.dto.stats;

import java.time.LocalDate;

public record WeeklyVolumeResponse(
        LocalDate weekStart,
        double totalVolume,
        int totalSets
) {
}
