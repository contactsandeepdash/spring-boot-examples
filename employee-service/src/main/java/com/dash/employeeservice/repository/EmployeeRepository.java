package com.dash.employeeservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dash.employeeservice.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, Long> {
    
    List<EmployeeModel> findByFirstNameAndLastName(String firstName, String lastName);
}
