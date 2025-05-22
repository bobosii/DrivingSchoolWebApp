package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.SupportTicket;
import dev.emir.DrivingSchoolWebApp.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;

    @Autowired
    public SupportTicketService(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    public List<SupportTicket> getAllSupportTickets() {
        return supportTicketRepository.findAll();
    }

    public Optional<SupportTicket> getSupportTicketById(Long id) {
        return supportTicketRepository.findById(id);
    }

    public List<SupportTicket> getSupportTicketsByStudentId(Long studentId) {
        return supportTicketRepository.findByStudentId(studentId);
    }

    public List<SupportTicket> getSupportTicketsByStatus(String status) {
        return supportTicketRepository.findByStatus(status);
    }

    public List<SupportTicket> getSupportTicketsByPriority(String priority) {
        return supportTicketRepository.findByPriority(priority);
    }

    public List<SupportTicket> getSupportTicketsByAssignedTo(String assignedTo) {
        return supportTicketRepository.findByAssignedTo(assignedTo);
    }

    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        return supportTicketRepository.save(supportTicket);
    }

    public SupportTicket updateSupportTicket(SupportTicket supportTicket) {
        return supportTicketRepository.save(supportTicket);
    }

    public void deleteSupportTicket(Long id) {
        supportTicketRepository.deleteById(id);
    }
} 