package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.StudentCourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseSessionRepository extends JpaRepository<StudentCourseSession, Long> {
    List<StudentCourseSession> findByStudentId(Long studentId);
    List<StudentCourseSession> findByCourseSessionId(Long courseSessionId);
    boolean existsByStudentIdAndCourseSessionId(Long studentId, Long courseSessionId);
} 