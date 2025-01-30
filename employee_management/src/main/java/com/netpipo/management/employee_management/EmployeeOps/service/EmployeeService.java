package com.netpipo.management.employee_management.EmployeeOps.service;

import com.netpipo.management.employee_management.EmployeeOps.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepo;


    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employeeToUpdate = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));



        employeeToUpdate.setName(updatedEmployee.getName());
        employeeToUpdate.setEmail(updatedEmployee.getEmail());
        employeeToUpdate.setPosition(updatedEmployee.getPosition());
        employeeToUpdate.setSalary(updatedEmployee.getSalary());

        return employeeRepo.save(employeeToUpdate);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Id not found"));
        employeeRepo.delete(employee);
    }
}

