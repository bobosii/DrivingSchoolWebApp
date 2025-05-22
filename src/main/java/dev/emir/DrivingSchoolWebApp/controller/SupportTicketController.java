package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.SupportTicket;
import dev.emir.DrivingSchoolWebApp.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-tickets")
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    @Autowired
    public SupportTicketController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @GetMapping
    public ResponseEntity<List<SupportTicket>> getAllSupportTickets() {
        return ResponseEntity.ok(supportTicketService.getAllSupportTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTicket> getSupportTicketById(@PathVariable Long id) {
        return supportTicketService.getSupportTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SupportTicket>> getSupportTicketsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(supportTicketService.getSupportTicketsByStudentId(studentId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SupportTicket>> getSupportTicketsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(supportTicketService.getSupportTicketsByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<SupportTicket>> getSupportTicketsByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(supportTicketService.getSupportTicketsByPriority(priority));
    }

    @GetMapping("/assigned-to/{assignedTo}")
    public ResponseEntity<List<SupportTicket>> getSupportTicketsByAssignedTo(@PathVariable String assignedTo) {
        return ResponseEntity.ok(supportTicketService.getSupportTicketsByAssignedTo(assignedTo));
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createSupportTicket(@RequestBody SupportTicket supportTicket) {
        return ResponseEntity.ok(supportTicketService.createSupportTicket(supportTicket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicket> updateSupportTicket(@PathVariable Long id, @RequestBody SupportTicket supportTicket) {
        return supportTicketService.getSupportTicketById(id)
                .map(existingTicket -> {
                    supportTicket.setId(id);
                    return ResponseEntity.ok(supportTicketService.updateSupportTicket(supportTicket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportTicket(@PathVariable Long id) {
        return supportTicketService.getSupportTicketById(id)
                .map(ticket -> {
                    supportTicketService.deleteSupportTicket(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 