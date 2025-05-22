package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.SimulatorSession;
import dev.emir.DrivingSchoolWebApp.repository.SimulatorSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SimulatorSessionService {

    private final SimulatorSessionRepository simulatorSessionRepository;

    @Autowired
    public SimulatorSessionService(SimulatorSessionRepository simulatorSessionRepository) {
        this.simulatorSessionRepository = simulatorSessionRepository;
    }

    public List<SimulatorSession> getAllSimulatorSessions() {
        return simulatorSessionRepository.findAll();
    }

    public Optional<SimulatorSession> getSimulatorSessionById(Long id) {
        return simulatorSessionRepository.findById(id);
    }

    public List<SimulatorSession> getSimulatorSessionsByInstructorId(Long instructorId) {
        return simulatorSessionRepository.findByInstructorId(instructorId);
    }

    public List<SimulatorSession> getSimulatorSessionsByStudentId(Long studentId) {
        return simulatorSessionRepository.findByStudentId(studentId);
    }

    public List<SimulatorSession> getSimulatorSessionsByDateRange(LocalDateTime start, LocalDateTime end) {
        return simulatorSessionRepository.findByStartTimeBetween(start, end);
    }

    public SimulatorSession createSimulatorSession(SimulatorSession simulatorSession) {
        return simulatorSessionRepository.save(simulatorSession);
    }

    public SimulatorSession updateSimulatorSession(SimulatorSession simulatorSession) {
        return simulatorSessionRepository.save(simulatorSession);
    }

    public void deleteSimulatorSession(Long id) {
        simulatorSessionRepository.deleteById(id);
    }
} 