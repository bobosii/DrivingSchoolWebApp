package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.LicenceClass;
import dev.emir.DrivingSchoolWebApp.repository.LicenceClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceClassService {

    private final LicenceClassRepository licenceClassRepository;

    @Autowired
    public LicenceClassService(LicenceClassRepository licenceClassRepository) {
        this.licenceClassRepository = licenceClassRepository;
    }

    public List<LicenceClass> getAllLicenceClasses() {
        return licenceClassRepository.findAll();
    }

    public Optional<LicenceClass> getLicenceClassById(Long id) {
        return licenceClassRepository.findById(id);
    }

    public Optional<LicenceClass> getLicenceClassByCode(String code) {
        return licenceClassRepository.findByCode(code);
    }

    public LicenceClass createLicenceClass(LicenceClass licenceClass) {
        return licenceClassRepository.save(licenceClass);
    }

    public LicenceClass updateLicenceClass(LicenceClass licenceClass) {
        return licenceClassRepository.save(licenceClass);
    }

    public void deleteLicenceClass(Long id) {
        licenceClassRepository.deleteById(id);
    }
} 