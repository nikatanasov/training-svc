package app.web.dto;

import app.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TrainingSessionResponse {
    private UUID id;

    private UUID userId;

    private String courtName;

    private Status status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
