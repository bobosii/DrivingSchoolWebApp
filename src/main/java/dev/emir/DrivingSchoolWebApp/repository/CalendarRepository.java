package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByStudentId(Long studentId);
    List<Calendar> findByInstructorId(Long instructorId);
    List<Calendar> findByCourseId(Long courseId);
    List<Calendar> findByStatus(Calendar.CalendarStatus status);
} 