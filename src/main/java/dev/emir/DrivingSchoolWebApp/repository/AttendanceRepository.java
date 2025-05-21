package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByInstructorId(Long instructorId);
    List<Attendance> findByCourseId(Long courseId);
    List<Attendance> findByStatus(Attendance.AttendanceStatus status);
} 