package app.scheduler;

import app.model.Status;
import app.model.TrainingSession;
import app.repository.TrainingSessionRepository;
import app.service.TrainingSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class TrainingStatusScheduler {
    private final TrainingSessionService trainingSessionService;

    @Autowired
    public TrainingStatusScheduler(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }


    @Scheduled(cron = "0 40 20 * * *")
    public void updateTrainingSessionStatus(){//всички тренировки със статус booked
        List<TrainingSession> trainingSessions = trainingSessionService.getAllTrainingSessionsWithStatusBooked();
        for(int i = 0; i < trainingSessions.size(); i++){
            if(LocalDateTime.now().isAfter(trainingSessions.get(i).getEndTime())){
                trainingSessions.get(i).setStatus(Status.COMPLETED);
                trainingSessionService.updateTraining(trainingSessions.get(i));
            }
        }
    }
}
