package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Vehicle;
import dev.emir.DrivingSchoolWebApp.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlateNumber(String plateNumber);
    List<Vehicle> findByVehicleType(VehicleType type);
    List<Vehicle> findByIsAvailable(Boolean isAvailable);
    boolean existsByPlateNumber(String plateNumber);
} 