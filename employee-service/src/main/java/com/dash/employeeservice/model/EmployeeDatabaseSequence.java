package com.dash.employeeservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee_database_sequences")
@Data
@NoArgsConstructor
public class EmployeeDatabaseSequence {

    @Id
    private String id;
    private long seq;
}
