package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.Notification;
import dev.emir.DrivingSchoolWebApp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Notification>> getNotificationsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(notificationService.getNotificationsByStudentId(studentId));
    }

    @GetMapping("/student/{studentId}/read/{read}")
    public ResponseEntity<List<Notification>> getNotificationsByStudentIdAndRead(
            @PathVariable Long studentId, @PathVariable boolean read) {
        return ResponseEntity.ok(notificationService.getNotificationsByStudentIdAndRead(studentId, read));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Notification>> getNotificationsByType(@PathVariable String type) {
        return ResponseEntity.ok(notificationService.getNotificationsByType(type));
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        return notificationService.getNotificationById(id)
                .map(existingNotification -> {
                    notification.setId(id);
                    return ResponseEntity.ok(notificationService.updateNotification(notification));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(notification -> {
                    notificationService.deleteNotification(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 