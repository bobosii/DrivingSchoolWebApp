package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.SimulatorSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SimulatorSessionRepository extends JpaRepository<SimulatorSession, Long> {
    List<SimulatorSession> findByInstructorId(Long instructorId);
    List<SimulatorSession> findByStudentId(Long studentId);
    List<SimulatorSession> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
} 