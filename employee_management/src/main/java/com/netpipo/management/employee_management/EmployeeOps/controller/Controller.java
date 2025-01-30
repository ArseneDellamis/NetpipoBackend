package com.netpipo.management.employee_management.EmployeeOps.controller;

import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import com.netpipo.management.employee_management.EmployeeOps.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netpipo.management.employee_management.EmployeeOps.controller.Utilities.createResponse;
import static com.netpipo.management.employee_management.EmployeeOps.controller.Utilities.mapRegisterEmployeeToEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/netpipo/api/employees")
public class Controller {

    private final EmployeeService employeeService;

    //    get all employee url: http://localhost:8080/netpipo/api/employees/
    @GetMapping("/")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return createResponse(HttpStatus.NO_CONTENT, "Empty Employee List");
        }
        return ResponseEntity.ok(employees);
    }

    //    create an employee url: http://localhost:8080/netpipo/api/employees/
    @PostMapping("/")
    public ResponseEntity<Message> createEmployee(@RequestBody RegisterEmployee registerEmployee) {
        Employee createdEmployee = mapRegisterEmployeeToEntity(registerEmployee);
        employeeService.createEmployee(createdEmployee);

        String successMessage = "Employee " + createdEmployee.getName() + " created successfully";
        return createResponse(HttpStatus.CREATED, successMessage);
    }

    //    get employee details by id url: http://localhost:8080/netpipo/api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Long id
    ) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(()-> new RuntimeException("Employee Not Found "));

        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }


    //    update an employee url: http://localhost:8080/netpipo/api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
                                            @RequestBody RegisterEmployee updatedEmployeeDto) {
        try {
            Employee updatedEmployee = mapRegisterEmployeeToEntity(updatedEmployeeDto);
            Employee updated = employeeService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    //    delete an employee url: http://localhost:8080/netpipo/api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return createResponse(HttpStatus.NO_CONTENT, "Employee deleted successfully.");
        } catch (RuntimeException e) {
            return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}

