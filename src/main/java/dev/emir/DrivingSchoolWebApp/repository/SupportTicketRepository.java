package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByStudentId(Long studentId);
    List<SupportTicket> findByStatus(String status);
    List<SupportTicket> findByPriority(String priority);
    List<SupportTicket> findByAssignedTo(String assignedTo);
} 