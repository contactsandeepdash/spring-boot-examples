package com.dash.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(value = "/getEmployeeList")
    public ResponseEntity<String> getEmployeeList() {
        return new ResponseEntity<>(
                "get List of employees",
                HttpStatus.OK);
    }
}
