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
@Tag(name = " Employee API", description = "APIs for managing employees")
public class Controller {

    private final EmployeeService employeeService;


    @GetMapping("/")
    @Operation(summary = "Get all employees", description = "retrieves a list of all employees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return createResponse(HttpStatus.NO_CONTENT, "Empty Employee List");
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/")
    @Operation(summary = "add employee", description = "create/register a new employee in the system")
    public ResponseEntity<Message> createEmployee(@RequestBody RegisterEmployee registerEmployee) {
        Employee createdEmployee = mapRegisterEmployeeToEntity(registerEmployee);
        employeeService.createEmployee(createdEmployee);

        String successMessage = "Employee " + createdEmployee.getName() + " created successfully";
        return createResponse(HttpStatus.CREATED, successMessage);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id", description = "retrieves an employee using his/her id")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Long id
    ) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(()-> new RuntimeException("Employee Not Found "));

        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }


    @PutMapping("/{id}")
    @Operation(summary = "update employee", description = "update the existing employee using his/her id")
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



    @DeleteMapping("/{id}")
    @Operation(summary = "deleting employee", description = "removing an employees from database using his/her id")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return createResponse(HttpStatus.NO_CONTENT, "Employee deleted successfully.");
        } catch (RuntimeException e) {
            return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }





















//    // Utility method to create a Message response
//    private ResponseEntity<Message> createResponse(HttpStatus status, String messageText) {
//        Message message = new Message(status, messageText);
//        return ResponseEntity.status(status).body(message);
//    }
//
//    // Utility method to map RegisterEmployee DTO to Employee entity
//    private Employee mapRegisterEmployeeToEntity(RegisterEmployee registerEmployee) {
//        Employee employee = new Employee();
//        employee.setFirstName(registerEmployee.getFirstName());
//        employee.setLastName(registerEmployee.getLastName());
//        employee.setEmail(registerEmployee.getEmail());
//        Department department = departmentRepo.findById(registerEmployee.getDepartmentId())
//                .orElseThrow(() -> new RuntimeException("Department Not Found"));
//        employee.setDepartment(department);
//        employee.setSalary(registerEmployee.getSalary());
//        return employee;
//    }
}

