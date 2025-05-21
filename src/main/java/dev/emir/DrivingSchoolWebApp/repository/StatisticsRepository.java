package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Statistics;
import dev.emir.DrivingSchoolWebApp.enums.StatisticsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    List<Statistics> findByType(StatisticsType type);
    List<Statistics> findByPeriodStartBetween(LocalDateTime start, LocalDateTime end);
    List<Statistics> findByTypeAndPeriodStartBetween(StatisticsType type, LocalDateTime start, LocalDateTime end);
} 