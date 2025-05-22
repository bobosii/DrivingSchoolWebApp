package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.SimulatorSession;
import dev.emir.DrivingSchoolWebApp.service.SimulatorSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/simulator-sessions")
public class SimulatorSessionController {

    private final SimulatorSessionService simulatorSessionService;

    @Autowired
    public SimulatorSessionController(SimulatorSessionService simulatorSessionService) {
        this.simulatorSessionService = simulatorSessionService;
    }

    @GetMapping
    public ResponseEntity<List<SimulatorSession>> getAllSimulatorSessions() {
        return ResponseEntity.ok(simulatorSessionService.getAllSimulatorSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimulatorSession> getSimulatorSessionById(@PathVariable Long id) {
        return simulatorSessionService.getSimulatorSessionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SimulatorSession>> getSimulatorSessionsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(simulatorSessionService.getSimulatorSessionsByStudentId(studentId));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<SimulatorSession>> getSimulatorSessionsByInstructorId(@PathVariable Long instructorId) {
        return ResponseEntity.ok(simulatorSessionService.getSimulatorSessionsByInstructorId(instructorId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<SimulatorSession>> getSimulatorSessionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(simulatorSessionService.getSimulatorSessionsByDateRange(start, end));
    }

    @PostMapping
    public ResponseEntity<SimulatorSession> createSimulatorSession(@RequestBody SimulatorSession simulatorSession) {
        return ResponseEntity.ok(simulatorSessionService.createSimulatorSession(simulatorSession));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimulatorSession> updateSimulatorSession(@PathVariable Long id, @RequestBody SimulatorSession simulatorSession) {
        return simulatorSessionService.getSimulatorSessionById(id)
                .map(existingSession -> {
                    simulatorSession.setId(id);
                    return ResponseEntity.ok(simulatorSessionService.updateSimulatorSession(simulatorSession));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimulatorSession(@PathVariable Long id) {
        return simulatorSessionService.getSimulatorSessionById(id)
                .map(session -> {
                    simulatorSessionService.deleteSimulatorSession(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 