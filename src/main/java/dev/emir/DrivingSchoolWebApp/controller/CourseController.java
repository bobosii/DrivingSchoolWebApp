package dev.emir.DrivingSchoolWebApp.controller;

import dev.emir.DrivingSchoolWebApp.model.Course;
import dev.emir.DrivingSchoolWebApp.service.CourseService;
import dev.emir.DrivingSchoolWebApp.enums.CourseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Course>> getCoursesByStatus(@PathVariable CourseStatus status) {
        return ResponseEntity.ok(courseService.getCoursesByStatus(status));
    }

    @GetMapping("/licence-class/{licenceClassId}")
    public ResponseEntity<List<Course>> getCoursesByLicenceClassId(@PathVariable Long licenceClassId) {
        return ResponseEntity.ok(courseService.getCoursesByLicenceClassId(licenceClassId));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.getCourseById(id)
                .map(existingCourse -> {
                    course.setId(id);
                    return ResponseEntity.ok(courseService.updateCourse(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(course -> {
                    courseService.deleteCourse(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 