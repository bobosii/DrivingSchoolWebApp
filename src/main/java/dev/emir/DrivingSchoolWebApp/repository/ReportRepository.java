package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStudentId(Long studentId);
    List<Report> findByInstructorId(Long instructorId);
    List<Report> findByCourseId(Long courseId);
    List<Report> findByType(Report.ReportType type);
} 