package app.web.mapper;

import app.model.TrainingSession;
import app.web.dto.TrainingSessionResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {
    public static TrainingSessionResponse fromTrainingSession(TrainingSession trainingSession) {
        return TrainingSessionResponse.builder()
                .id(trainingSession.getId())
                .userId(trainingSession.getUserId())
                .courtName(trainingSession.getCourtName())
                .status(trainingSession.getStatus())
                .startTime(trainingSession.getStartTime())
                .endTime(trainingSession.getEndTime())
                .build();
    }
}
