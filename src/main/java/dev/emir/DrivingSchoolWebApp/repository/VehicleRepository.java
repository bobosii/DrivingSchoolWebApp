package dev.emir.DrivingSchoolWebApp.repository;

import dev.emir.DrivingSchoolWebApp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findByIsAvailable(Boolean isAvailable);
    List<Vehicle> findByBrand(String brand);
    List<Vehicle> findByModel(String model);
    List<Vehicle> findByYearOfManufacture(Integer yearOfManufacture);
} 