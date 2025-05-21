package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserEmail(String email);
    Optional<Student> findByIdentityNumber(String identityNumber);
    boolean existsByIdentityNumber(String identityNumber);
    Student findByUserId(Long userId);
} 