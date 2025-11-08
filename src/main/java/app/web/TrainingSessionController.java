package app.web;

import app.model.TrainingSession;
import app.service.TrainingSessionService;
import app.web.dto.TrainingSessionRequest;
import app.web.dto.TrainingSessionResponse;
import app.web.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingSessionController {
    private final TrainingSessionService trainingSessionService;

    @Autowired
    public TrainingSessionController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @PostMapping
    public ResponseEntity<TrainingSessionResponse> bookSession(@RequestBody TrainingSessionRequest request){
        TrainingSession trainingSession = trainingSessionService.bookSession(request);
        TrainingSessionResponse response = DtoMapper.fromTrainingSession(trainingSession);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingSessionResponse> cancelSession(@PathVariable UUID id){
        TrainingSession trainingSession = trainingSessionService.cancelSession(id);
        TrainingSessionResponse response = DtoMapper.fromTrainingSession(trainingSession);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TrainingSessionResponse>> getAllTrainingSessionsByUserId(@RequestParam UUID userId){
        List<TrainingSessionResponse> responses =
                trainingSessionService.getAllTrainingSessionsByUserId(userId)
                .stream()
                .map(DtoMapper::fromTrainingSession)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingSessionResponse> getTrainingSessionById(@PathVariable UUID id){
        TrainingSession trainingSession = trainingSessionService.getTrainingSessionById(id);
        TrainingSessionResponse response = DtoMapper.fromTrainingSession(trainingSession);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
