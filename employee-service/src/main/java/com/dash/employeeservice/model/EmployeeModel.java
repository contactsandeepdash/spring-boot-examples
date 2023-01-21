package com.dash.employeeservice.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "employee_details")
@Data
public class EmployeeModel {

    @Transient
    public static final String SEQUENCE_NAME = "employee_sequence";

    @Id
    private long id;

    private long empId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public void Employee() {
        Objects.requireNonNull(empId);
        // Objects.requireNonNull(email);

        if (age < 18) {
            minor = true;
            throw new IllegalArgumentException("You cannot hire a minor as employee");
        }
    }

    boolean minor;

    public boolean isMinor() {
        return minor;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

}
