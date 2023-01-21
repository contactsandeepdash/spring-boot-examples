package com.dash.employeeservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dash.employeeservice.exception.ResourceNotFoundException;
import com.dash.employeeservice.model.EmployeeModel;
import com.dash.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public ResponseEntity<EmployeeModel> saveEmployee(@Validated @RequestBody EmployeeModel employee) {
        employee.setId(sequenceGeneratorService.generateSequence(EmployeeModel.SEQUENCE_NAME));
        EmployeeModel employeeModel = employeeRepository.save(employee);
        return new ResponseEntity<>(employeeModel, HttpStatus.CREATED);
    }

    public ResponseEntity<EmployeeModel> updateEmployee(Long employeeId,
            @Validated @RequestBody EmployeeModel employeeDetails) throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findByEmpId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final EmployeeModel updatedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    }

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findByEmpId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    public Map<EmployeeModel, String> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findByEmpId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<EmployeeModel, String> response = new HashMap<>();
        response.put(employee, "Deleted -> " + Boolean.TRUE);
        return response;
    }
}
