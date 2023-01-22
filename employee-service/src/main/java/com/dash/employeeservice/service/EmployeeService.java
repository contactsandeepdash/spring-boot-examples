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
        
        if (employee.getAge() < 18) {
            employee.setMinor(true);
            throw new IllegalArgumentException("You cannot hire a minor as employee");
        }

        employee.setId(sequenceGeneratorService.generateSequence(EmployeeModel.SEQUENCE_NAME));

        List<EmployeeModel> existingEmployeeList = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(),
                employee.getLastName());

        employee.setFullName(employee.getFirstName() + " " + employee.getLastName());

        employee.setMinor(false);

        if (existingEmployeeList.isEmpty()) {
            employee.setEmail(
                    employee.getFirstName().toLowerCase() + "." + employee.getLastName().toLowerCase() + "@dash.com");
        } else {
            employee.setEmail(employee.getFirstName().toLowerCase() + "." + employee.getLastName().toLowerCase()
                    + (existingEmployeeList.size() + 1)
                    + "@dash.com");
        }

        EmployeeModel employeeModel = employeeRepository.save(employee);
        return new ResponseEntity<>(employeeModel, HttpStatus.CREATED);
    }

    public ResponseEntity<EmployeeModel> updateEmployee(Long id,
            @Validated @RequestBody EmployeeModel employeeDetails) throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + id));

        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final EmployeeModel updatedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    }

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + id));
        return ResponseEntity.ok().body(employee);
    }

    public Map<EmployeeModel, String> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employee id :: " + id));

        employeeRepository.delete(employee);
        Map<EmployeeModel, String> response = new HashMap<>();
        response.put(employee, "Deleted -> " + Boolean.TRUE);
        return response;
    }
}
