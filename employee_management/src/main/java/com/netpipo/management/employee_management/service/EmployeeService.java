package com.netpipo.management.employee_management.service;

import com.netpipo.management.employee_management.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.manage.Department;
import com.netpipo.management.employee_management.manage.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepo.findById(id);
    }

    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employeesByFirstName = employeeRepo.findByFirstNameContainingIgnoreCase(name);
        List<Employee> employeesByLastName = employeeRepo.findByLastNameContainingIgnoreCase(name);
        List<Employee> employeesByFullName = employeeRepo.searchByFullName(name);

        // Combine all results into one list without duplicates
        Set<Employee> resultSet = new HashSet<>();
        resultSet.addAll(employeesByFirstName);
        resultSet.addAll(employeesByLastName);
        resultSet.addAll(employeesByFullName);

        return new ArrayList<>(resultSet);
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
        Department department = departmentRepo.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Department Not Found"));

        return employeeRepo.findByDepartment(department);
    }

    public Employee updateEmployee(long id, Employee updatedEmployee) {
        Employee employeeToUpdate = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        employeeToUpdate.setFirstName(updatedEmployee.getFirstName());
        employeeToUpdate.setLastName(updatedEmployee.getLastName());
        employeeToUpdate.setEmail(updatedEmployee.getEmail());
        employeeToUpdate.setDepartment(updatedEmployee.getDepartment());
        employeeToUpdate.setSalary(updatedEmployee.getSalary());

        return employeeRepo.save(employeeToUpdate);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Id not found"));
        employeeRepo.delete(employee);
    }
}

