package app.web.dto.routine;

public record UpdateRoutineRequest(
        String name,
        String notes,
        Integer folderOrder
) {
}
