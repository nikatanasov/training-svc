package app.repository;

import app.model.Status;
import app.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, UUID> {
    Optional<TrainingSession> findById(UUID id);
    List<TrainingSession> findAllByUserId(UUID userId);
    List<TrainingSession> findAllByStatus(Status  status);
}
