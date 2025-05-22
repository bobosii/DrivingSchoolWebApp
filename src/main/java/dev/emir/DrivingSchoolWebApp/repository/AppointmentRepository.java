package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Appointment;
import dev.emir.DrivingSchoolWebApp.enums.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStudentId(Long studentId);
    List<Appointment> findByInstructorId(Long instructorId);
    List<Appointment> findByAppointmentType(AppointmentType appointmentType);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
} 