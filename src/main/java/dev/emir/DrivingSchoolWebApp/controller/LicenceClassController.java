package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.LicenceClass;
import dev.emir.DrivingSchoolWebApp.service.LicenceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licence-classes")
public class LicenceClassController {

    private final LicenceClassService licenceClassService;

    @Autowired
    public LicenceClassController(LicenceClassService licenceClassService) {
        this.licenceClassService = licenceClassService;
    }

    @GetMapping
    public ResponseEntity<List<LicenceClass>> getAllLicenceClasses() {
        return ResponseEntity.ok(licenceClassService.getAllLicenceClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenceClass> getLicenceClassById(@PathVariable Long id) {
        return licenceClassService.getLicenceClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<LicenceClass> getLicenceClassByCode(@PathVariable String code) {
        return licenceClassService.getLicenceClassByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LicenceClass> createLicenceClass(@RequestBody LicenceClass licenceClass) {
        return ResponseEntity.ok(licenceClassService.createLicenceClass(licenceClass));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicenceClass> updateLicenceClass(@PathVariable Long id, @RequestBody LicenceClass licenceClass) {
        return licenceClassService.getLicenceClassById(id)
                .map(existingLicenceClass -> {
                    licenceClass.setId(id);
                    return ResponseEntity.ok(licenceClassService.updateLicenceClass(licenceClass));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicenceClass(@PathVariable Long id) {
        return licenceClassService.getLicenceClassById(id)
                .map(licenceClass -> {
                    licenceClassService.deleteLicenceClass(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 