package dev.emir.DrivingSchoolWebApp.config;

import dev.emir.DrivingSchoolWebApp.enums.Role;
import dev.emir.DrivingSchoolWebApp.model.User;
import dev.emir.DrivingSchoolWebApp.model.RoleEntity;
import dev.emir.DrivingSchoolWebApp.repository.UserRepository;
import dev.emir.DrivingSchoolWebApp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create roles if they don't exist
        RoleEntity adminRole = createRoleIfNotExists("ADMIN");
        RoleEntity employeeRole = createRoleIfNotExists("EMPLOYEE");
        RoleEntity instructorRole = createRoleIfNotExists("INSTRUCTOR");
        RoleEntity studentRole = createRoleIfNotExists("STUDENT");

        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@drivingschool.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.ADMIN);
            admin.setIsActive(true);
            admin.setPhoneNumber("5551234567");
            userRepository.save(admin);
            System.out.println("admin created");
        }

        // Create employee user if not exists
        if (!userRepository.existsByUsername("employee")) {
            User employee = new User();
            employee.setUsername("employee");
            employee.setEmail("employee@drivingschool.com");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setFirstName("Sarah");
            employee.setLastName("Johnson");
            employee.setRole(Role.EMPLOYEE);
            employee.setIsActive(true);
            employee.setPhoneNumber("5559876543");
            userRepository.save(employee);
            System.out.println("employee created");
        }

        // Create instructor user if not exists
        if (!userRepository.existsByUsername("instructor")) {
            User instructor = new User();
            instructor.setUsername("instructor");
            instructor.setEmail("instructor@drivingschool.com");
            instructor.setPassword(passwordEncoder.encode("instructor123"));
            instructor.setFirstName("John");
            instructor.setLastName("Doe");
            instructor.setRole(Role.INSTRUCTOR);
            instructor.setIsActive(true);
            instructor.setPhoneNumber("5552345678");
            userRepository.save(instructor);
            System.out.println("instructor created");
        }

        // Create student user if not exists
        if (!userRepository.existsByUsername("student")) {
            User student = new User();
            student.setUsername("student");
            student.setEmail("student@drivingschool.com");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setFirstName("Jane");
            student.setLastName("Smith");
            student.setRole(Role.STUDENT);
            student.setIsActive(true);
            student.setPhoneNumber("5553456789");
            userRepository.save(student);
            System.out.println("student created");
        }

        // Print all user credentials
        System.out.println("\n=== User Credentials ===");
        System.out.println("\nAdmin User:");
        System.out.println("Username: admin");
        System.out.println("Password: admin123");
        System.out.println("Email: admin@drivingschool.com");
        
        System.out.println("\nEmployee User:");
        System.out.println("Username: employee");
        System.out.println("Password: employee123");
        System.out.println("Email: employee@drivingschool.com");
        
        System.out.println("\nInstructor User:");
        System.out.println("Username: instructor");
        System.out.println("Password: instructor123");
        System.out.println("Email: instructor@drivingschool.com");
        
        System.out.println("\nStudent User:");
        System.out.println("Username: student");
        System.out.println("Password: student123");
        System.out.println("Email: student@drivingschool.com");
        System.out.println("\n=====================");
    }

    private RoleEntity createRoleIfNotExists(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            RoleEntity role = new RoleEntity();
            role.setName(roleName);
            return roleRepository.save(role);
        }
        return roleRepository.findByName(roleName);
    }
} 