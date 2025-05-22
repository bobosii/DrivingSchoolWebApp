package dev.emir.DrivingSchoolWebApp.service;

import dev.emir.DrivingSchoolWebApp.model.Course;
import dev.emir.DrivingSchoolWebApp.repository.CourseRepository;
import dev.emir.DrivingSchoolWebApp.enums.CourseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByStatus(CourseStatus status) {
        return courseRepository.findByStatus(status);
    }

    public List<Course> getCoursesByLicenceClassId(Long licenceClassId) {
        return courseRepository.findByLicenceClassId(licenceClassId);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
} 