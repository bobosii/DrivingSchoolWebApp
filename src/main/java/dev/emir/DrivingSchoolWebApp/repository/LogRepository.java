package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Log;
import dev.emir.DrivingSchoolWebApp.enums.LogType;
import dev.emir.DrivingSchoolWebApp.enums.LogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByUserId(Long userId);
    List<Log> findByType(LogType type);
    List<Log> findByStatus(LogStatus status);
} 