package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.Vehicle;
import dev.emir.DrivingSchoolWebApp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return vehicleService.getVehicleByLicensePlate(licensePlate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available/{isAvailable}")
    public ResponseEntity<List<Vehicle>> getVehiclesByAvailability(@PathVariable Boolean isAvailable) {
        return ResponseEntity.ok(vehicleService.getVehiclesByAvailability(isAvailable));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Vehicle>> getVehiclesByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(vehicleService.getVehiclesByBrand(brand));
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Vehicle>> getVehiclesByModel(@PathVariable String model) {
        return ResponseEntity.ok(vehicleService.getVehiclesByModel(model));
    }

    @GetMapping("/year/{yearOfManufacture}")
    public ResponseEntity<List<Vehicle>> getVehiclesByYearOfManufacture(@PathVariable Integer yearOfManufacture) {
        return ResponseEntity.ok(vehicleService.getVehiclesByYearOfManufacture(yearOfManufacture));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.getVehicleById(id)
                .map(existingVehicle -> {
                    vehicle.setId(id);
                    return ResponseEntity.ok(vehicleService.updateVehicle(vehicle));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> {
                    vehicleService.deleteVehicle(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 