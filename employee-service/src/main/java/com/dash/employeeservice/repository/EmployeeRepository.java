package com.dash.employeeservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dash.employeeservice.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, Long> {
    
    Optional<EmployeeModel> findByEmpId(long employeeId);
}
