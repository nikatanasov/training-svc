package app.service;

import app.model.Status;
import app.model.TrainingSession;
import app.repository.TrainingSessionRepository;
import app.web.dto.TrainingSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public TrainingSession bookSession(TrainingSessionRequest request) {
        TrainingSession trainingSession = TrainingSession.builder()
                .userId(request.getUserId())
                .courtName(request.getCourtName())
                .status(Status.BOOKED)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();
        return trainingSessionRepository.save(trainingSession);
    }

    public TrainingSession cancelSession(UUID id) {
        Optional<TrainingSession> optionalTrainingSession = trainingSessionRepository.findById(id);

        if(optionalTrainingSession.isEmpty()){
            throw new IllegalStateException("There is no training session with this id");
        }

        TrainingSession trainingSession = optionalTrainingSession.get();
        trainingSession.setStatus(Status.CANCELLED);
        return trainingSessionRepository.save(trainingSession);
    }


    public List<TrainingSession> getAllTrainingSessionsByUserId(UUID userId) {
        return trainingSessionRepository.findAllByUserId(userId);
    }

    public TrainingSession getTrainingSessionById(UUID id) {
        return trainingSessionRepository.findById(id).orElseThrow(() -> new IllegalStateException("There is no training session with this id "+id+"!"));
    }

    public void updateTraining(TrainingSession trainingSession) {
        trainingSessionRepository.save(trainingSession);
    }

    public List<TrainingSession> getAllTrainingSessionsWithStatusBooked() {
        return trainingSessionRepository.findAllByStatus(Status.BOOKED);
    }
}
