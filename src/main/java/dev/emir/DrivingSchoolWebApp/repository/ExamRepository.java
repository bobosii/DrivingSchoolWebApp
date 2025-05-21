package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Exam;
import dev.emir.DrivingSchoolWebApp.enums.ExamType;
import dev.emir.DrivingSchoolWebApp.enums.ExamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByStudentId(Long studentId);
    List<Exam> findByInstructorId(Long instructorId);
    List<Exam> findByType(ExamType type);
    List<Exam> findByStatus(ExamStatus status);
} 