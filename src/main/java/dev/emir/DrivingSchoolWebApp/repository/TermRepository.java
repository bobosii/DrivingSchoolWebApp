package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
    List<Term> findByCourseId(Long courseId);
    List<Term> findByInstructorId(Long instructorId);
    List<Term> findByStudentId(Long studentId);
    List<Term> findByStatus(Term.TermStatus status);
} 