package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Feedback;
import dev.emir.DrivingSchoolWebApp.enums.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByStudentId(Long studentId);
    List<Feedback> findByInstructorId(Long instructorId);
    List<Feedback> findByCourseId(Long courseId);
    List<Feedback> findByType(FeedbackType type);
} 