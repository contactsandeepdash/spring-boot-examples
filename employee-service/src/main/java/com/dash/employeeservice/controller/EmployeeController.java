package com.dash.employeeservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dash.employeeservice.exception.ResourceNotFoundException;
import com.dash.employeeservice.model.EmployeeModel;
import com.dash.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/getEmployeeList")
    public ResponseEntity<String> getEmployeeList() {
        return ResponseEntity.ok("get List of employees");
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeModel> saveEmployee(@Validated @RequestBody EmployeeModel employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable(value = "id") Long id,
            @Validated @RequestBody EmployeeModel employeeDetails) throws ResourceNotFoundException {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @GetMapping("/findAllEmployees")
    public List<EmployeeModel> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/employees/{id}")
    public Map<EmployeeModel, String> deleteEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return employeeService.deleteEmployee(id);
    }

}
