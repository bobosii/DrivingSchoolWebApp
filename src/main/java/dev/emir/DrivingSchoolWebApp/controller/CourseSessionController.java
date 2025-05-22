package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.CourseSession;
import dev.emir.DrivingSchoolWebApp.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-sessions")
public class CourseSessionController {

    private final CourseSessionService courseSessionService;

    @Autowired
    public CourseSessionController(CourseSessionService courseSessionService) {
        this.courseSessionService = courseSessionService;
    }

    @GetMapping
    public ResponseEntity<List<CourseSession>> getAllCourseSessions() {
        return ResponseEntity.ok(courseSessionService.getAllCourseSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseSession> getCourseSessionById(@PathVariable Long id) {
        return courseSessionService.getCourseSessionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseSessionService.getCourseSessionsByCourseId(courseId));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByInstructorId(@PathVariable Long instructorId) {
        return ResponseEntity.ok(courseSessionService.getCourseSessionsByInstructorId(instructorId));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByClassroomId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(courseSessionService.getCourseSessionsByClassroomId(classroomId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(courseSessionService.getCourseSessionsByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<CourseSession> createCourseSession(@RequestBody CourseSession courseSession) {
        return ResponseEntity.ok(courseSessionService.createCourseSession(courseSession));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseSession> updateCourseSession(@PathVariable Long id, @RequestBody CourseSession courseSession) {
        return courseSessionService.getCourseSessionById(id)
                .map(existingSession -> {
                    courseSession.setId(id);
                    return ResponseEntity.ok(courseSessionService.updateCourseSession(courseSession));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSession(@PathVariable Long id) {
        return courseSessionService.getCourseSessionById(id)
                .map(session -> {
                    courseSessionService.deleteCourseSession(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 