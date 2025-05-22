package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.Feedback;
import dev.emir.DrivingSchoolWebApp.service.FeedbackService;
import dev.emir.DrivingSchoolWebApp.enums.FeedbackType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Feedback>> getFeedbackByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByStudentId(studentId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Feedback>> getFeedbackByType(@PathVariable FeedbackType type) {
        return ResponseEntity.ok(feedbackService.getFeedbackByType(type));
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return feedbackService.getFeedbackById(id)
                .map(existingFeedback -> {
                    feedback.setId(id);
                    return ResponseEntity.ok(feedbackService.updateFeedback(feedback));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(feedback -> {
                    feedbackService.deleteFeedback(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 