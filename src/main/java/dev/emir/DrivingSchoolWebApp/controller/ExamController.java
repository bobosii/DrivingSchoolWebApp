package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.Exam;
import dev.emir.DrivingSchoolWebApp.service.ExamService;
import dev.emir.DrivingSchoolWebApp.enums.ExamType;
import dev.emir.DrivingSchoolWebApp.enums.ExamStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        return examService.getExamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Exam>> getExamsByType(@PathVariable ExamType type) {
        return ResponseEntity.ok(examService.getExamsByType(type));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Exam>> getExamsByStatus(@PathVariable ExamStatus status) {
        return ResponseEntity.ok(examService.getExamsByStatus(status));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Exam>> getExamsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(examService.getExamsByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.createExam(exam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        return examService.getExamById(id)
                .map(existingExam -> {
                    exam.setId(id);
                    return ResponseEntity.ok(examService.updateExam(exam));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        return examService.getExamById(id)
                .map(exam -> {
                    examService.deleteExam(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 