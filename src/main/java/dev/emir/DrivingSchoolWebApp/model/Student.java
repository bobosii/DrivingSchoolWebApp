package dev.emir.DrivingSchoolWebApp.model;

import dev.emir.DrivingSchoolWebApp.enums.LicenceClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "identity_number", nullable = false, unique = true)
    private String identityNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "licence_class", nullable = false)
    private LicenceClass licenceClass;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @OneToMany(mappedBy = "student")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<SimulatorSession> simulatorSessions = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Document> documents = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<SupportTicket> supportTickets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "student_course_session",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_session_id")
    )
    private List<CourseSession> courseSessions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "student_exam",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams = new ArrayList<>();

    @Column(name = "medical_certificate_expiry")
    private LocalDateTime medicalCertificateExpiry;

    @Column(name = "theory_exam_passed")
    private Boolean theoryExamPassed = false;

    @Column(name = "practical_exam_passed")
    private Boolean practicalExamPassed = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 