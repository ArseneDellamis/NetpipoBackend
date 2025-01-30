package com.netpipo.management.employee_management.EmployeeOps.controller;

import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utilities {
    // Utility method to create a Message response
    public static ResponseEntity<Message> createResponse(HttpStatus status, String messageText) {
        Message message = new Message(status, messageText);
        return ResponseEntity.status(status).body(message);
    }

    // Utility method to map RegisterEmployee DTO to Employee entity
    public static Employee mapRegisterEmployeeToEntity(RegisterEmployee registerEmployee) {
        Employee employee = new Employee();
        employee.setName(registerEmployee.getName());
        employee.setEmail(registerEmployee.getEmail());
        employee.setPosition(registerEmployee.getPosition());
        employee.setSalary(registerEmployee.getSalary());
        return employee;
    }
}
