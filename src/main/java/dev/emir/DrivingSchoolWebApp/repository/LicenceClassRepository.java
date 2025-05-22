package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.LicenceClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenceClassRepository extends JpaRepository<LicenceClass, Long> {
    Optional<LicenceClass> findByCode(String code);
} 