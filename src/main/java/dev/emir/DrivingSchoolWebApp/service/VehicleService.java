package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.Vehicle;
import dev.emir.DrivingSchoolWebApp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    public List<Vehicle> getVehiclesByAvailability(Boolean isAvailable) {
        return vehicleRepository.findByIsAvailable(isAvailable);
    }

    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    public List<Vehicle> getVehiclesByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> getVehiclesByYearOfManufacture(Integer yearOfManufacture) {
        return vehicleRepository.findByYearOfManufacture(yearOfManufacture);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
} 