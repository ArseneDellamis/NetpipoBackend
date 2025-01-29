package com.netpipo.management.employee_management.controller;

import com.netpipo.management.employee_management.manage.Department;
import com.netpipo.management.employee_management.manage.Employee;
import com.netpipo.management.employee_management.service.DepartmentService;
import com.netpipo.management.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netpipo/api")
public class Controller {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public Controller(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/employees/all")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return createResponse(HttpStatus.NO_CONTENT, "Empty Employee List");
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employees/add")
    public ResponseEntity<Message> createEmployee(@RequestBody RegisterEmployee registerEmployee) {
        Employee createdEmployee = mapRegisterEmployeeToEntity(registerEmployee);
        employeeService.createEmployee(createdEmployee);

        String successMessage = "Employee " + createdEmployee.getFirstName() + " " + createdEmployee.getLastName() + " created successfully";
        return createResponse(HttpStatus.CREATED, successMessage);
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable long id
    ) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(()-> new RuntimeException("Employee Not Found "));

        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }

    @GetMapping("/employees/search")
    public ResponseEntity<?> searchEmployees(@RequestParam String name){
//        User the service method to search
        List<Employee> employees =
                employeeService.searchEmployeesByName(name);

        if (employees.isEmpty()) {
            return createResponse(
                    HttpStatus.NO_CONTENT,
                    "No Employees Found"
            );
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(employees);
    }

    @GetMapping("/employees/department/{name}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable String name) {
      try {
          List<Employee> employees = employeeService
                  .getEmployeesByDepartment(name);
          if (employees.isEmpty()) {
              return createResponse(HttpStatus.NO_CONTENT, "No employees found in this department.");
          }
          return ResponseEntity.ok(employees);
      } catch (RuntimeException e) {
          return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
      }
    }



    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmployee) {
        try {
            Employee updated = employeeService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        try {
            employeeService.deleteEmployee(id);
            return createResponse(HttpStatus.NO_CONTENT, "Employee deleted successfully.");
        } catch (RuntimeException e) {
            return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }





















    // Utility method to create a Message response
    private ResponseEntity<Message> createResponse(HttpStatus status, String messageText) {
        Message message = new Message(status, messageText);
        return ResponseEntity.status(status).body(message);
    }

    // Utility method to map RegisterEmployee DTO to Employee entity
    private Employee mapRegisterEmployeeToEntity(RegisterEmployee registerEmployee) {
        Employee employee = new Employee();
        employee.setFirstName(registerEmployee.getFirstName());
        employee.setLastName(registerEmployee.getLastName());
        employee.setEmail(registerEmployee.getEmail());
        employee.setDepartment(departmentService.getDepartmentByName(registerEmployee.getDepartment()));
        employee.setSalary(registerEmployee.getSalary());
        return employee;
    }
}

