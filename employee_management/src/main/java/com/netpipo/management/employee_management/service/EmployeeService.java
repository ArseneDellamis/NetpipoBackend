package com.netpipo.management.employee_management.service;

import com.netpipo.management.employee_management.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.manage.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
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
        return employeeRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepo.findByDepartment(department);
    }

    public Employee updateEmployee( long id,Employee updatedEmployee) {

        Employee getEmployeeToUpdateById = employeeRepo.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Employee Not Found")
                );
        getEmployeeToUpdateById.setFirstName(updatedEmployee.getFirstName());
        getEmployeeToUpdateById.setLastName(updatedEmployee.getLastName());
        getEmployeeToUpdateById.setEmail(updatedEmployee.getEmail());
        getEmployeeToUpdateById.setDepartment(updatedEmployee.getDepartment());
        getEmployeeToUpdateById.setSalary(updatedEmployee.getSalary());


        return employeeRepo.save(getEmployeeToUpdateById);

    }

    public void deleteEmployee(long id) {

        Employee getEmployeeById = employeeRepo.findById(id)
                        .orElseThrow(()-> new RuntimeException("Employee Id not found"));
        employeeRepo.deleteById(id);
    }

}
