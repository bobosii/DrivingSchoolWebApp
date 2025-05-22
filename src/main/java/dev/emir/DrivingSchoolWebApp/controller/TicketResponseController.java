package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.TicketResponse;
import dev.emir.DrivingSchoolWebApp.service.TicketResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-responses")
public class TicketResponseController {

    private final TicketResponseService ticketResponseService;

    @Autowired
    public TicketResponseController(TicketResponseService ticketResponseService) {
        this.ticketResponseService = ticketResponseService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTicketResponses() {
        return ResponseEntity.ok(ticketResponseService.getAllTicketResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketResponseById(@PathVariable Long id) {
        return ticketResponseService.getTicketResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<TicketResponse>> getTicketResponsesByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketResponseService.getTicketResponsesByTicketId(ticketId));
    }

    @GetMapping("/responder/{responderId}")
    public ResponseEntity<List<TicketResponse>> getTicketResponsesByResponderId(@PathVariable Long responderId) {
        return ResponseEntity.ok(ticketResponseService.getTicketResponsesByResponderId(responderId));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicketResponse(@RequestBody TicketResponse ticketResponse) {
        return ResponseEntity.ok(ticketResponseService.createTicketResponse(ticketResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicketResponse(@PathVariable Long id, @RequestBody TicketResponse ticketResponse) {
        return ticketResponseService.getTicketResponseById(id)
                .map(existingResponse -> {
                    ticketResponse.setId(id);
                    return ResponseEntity.ok(ticketResponseService.updateTicketResponse(ticketResponse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketResponse(@PathVariable Long id) {
        return ticketResponseService.getTicketResponseById(id)
                .map(response -> {
                    ticketResponseService.deleteTicketResponse(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 