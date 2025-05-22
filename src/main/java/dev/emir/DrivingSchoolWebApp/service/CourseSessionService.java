package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.CourseSession;
import dev.emir.DrivingSchoolWebApp.model.StudentCourseSession;
import dev.emir.DrivingSchoolWebApp.repository.CourseSessionRepository;
import dev.emir.DrivingSchoolWebApp.repository.StudentCourseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseSessionService {

    private final CourseSessionRepository courseSessionRepository;
    private final StudentCourseSessionRepository studentCourseSessionRepository;

    @Autowired
    public CourseSessionService(CourseSessionRepository courseSessionRepository,
                              StudentCourseSessionRepository studentCourseSessionRepository) {
        this.courseSessionRepository = courseSessionRepository;
        this.studentCourseSessionRepository = studentCourseSessionRepository;
    }

    public List<CourseSession> getAllCourseSessions() {
        return courseSessionRepository.findAll();
    }

    public Optional<CourseSession> getCourseSessionById(Long id) {
        return courseSessionRepository.findById(id);
    }

    public List<CourseSession> getCourseSessionsByCourseId(Long courseId) {
        return courseSessionRepository.findByCourseId(courseId);
    }

    public List<CourseSession> getCourseSessionsByInstructorId(Long instructorId) {
        return courseSessionRepository.findByInstructorId(instructorId);
    }

    public List<CourseSession> getCourseSessionsByClassroomId(Long classroomId) {
        return courseSessionRepository.findByClassroomId(classroomId);
    }

    public List<CourseSession> getCourseSessionsByStudentId(Long studentId) {
        return studentCourseSessionRepository.findByStudentId(studentId)
                .stream()
                .map(StudentCourseSession::getCourseSession)
                .collect(Collectors.toList());
    }

    public CourseSession createCourseSession(CourseSession courseSession) {
        return courseSessionRepository.save(courseSession);
    }

    public CourseSession updateCourseSession(CourseSession courseSession) {
        return courseSessionRepository.save(courseSession);
    }

    public void deleteCourseSession(Long id) {
        courseSessionRepository.deleteById(id);
    }
} 