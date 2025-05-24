package dev.emir.DrivingSchoolWebApp.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return switch (role) {
            case ADMIN -> 1;
            case EMPLOYEE -> 2;
            case INSTRUCTOR -> 3;
            case STUDENT -> 4;
        };
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.EMPLOYEE;
            case 3 -> Role.INSTRUCTOR;
            case 4 -> Role.STUDENT;
            default -> throw new IllegalArgumentException("Unknown role id: " + dbData);
        };
    }
} 