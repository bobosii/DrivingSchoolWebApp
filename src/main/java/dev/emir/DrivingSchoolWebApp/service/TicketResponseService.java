package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.TicketResponse;
import dev.emir.DrivingSchoolWebApp.repository.TicketResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketResponseService {

    private final TicketResponseRepository ticketResponseRepository;

    @Autowired
    public TicketResponseService(TicketResponseRepository ticketResponseRepository) {
        this.ticketResponseRepository = ticketResponseRepository;
    }

    public List<TicketResponse> getAllTicketResponses() {
        return ticketResponseRepository.findAll();
    }

    public Optional<TicketResponse> getTicketResponseById(Long id) {
        return ticketResponseRepository.findById(id);
    }

    public List<TicketResponse> getTicketResponsesByTicketId(Long ticketId) {
        return ticketResponseRepository.findByTicketId(ticketId);
    }

    public List<TicketResponse> getTicketResponsesByResponderId(Long responderId) {
        return ticketResponseRepository.findByResponderId(responderId);
    }

    public TicketResponse createTicketResponse(TicketResponse ticketResponse) {
        return ticketResponseRepository.save(ticketResponse);
    }

    public TicketResponse updateTicketResponse(TicketResponse ticketResponse) {
        return ticketResponseRepository.save(ticketResponse);
    }

    public void deleteTicketResponse(Long id) {
        ticketResponseRepository.deleteById(id);
    }
} 