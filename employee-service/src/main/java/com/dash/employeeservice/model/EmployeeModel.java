package com.dash.employeeservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "employee_details")
@Data
public class EmployeeModel {

    @Transient
    public static final String SEQUENCE_NAME = "employee_sequence";

    @Id
    long id;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    String fullName;
    String email;
    
    @NumberFormat
    int age;
    boolean minor;

}
