package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByStudentId(Long studentId);
    List<Document> findByInstructorId(Long instructorId);
    List<Document> findByType(Document.DocumentType type);
    List<Document> findByStatus(Document.DocumentStatus status);
} 