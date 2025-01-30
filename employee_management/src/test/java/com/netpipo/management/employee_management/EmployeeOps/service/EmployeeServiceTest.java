package com.netpipo.management.employee_management.EmployeeOps.service;

import com.netpipo.management.employee_management.EmployeeOps.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.EmployeeOps.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private DepartmentRepository departmentRepo;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .id(1L)
                .name("IT")
                .build();

        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .department(department)
                .salary("5000")
                .build();
    }

    @Test
    void testCreateEmployee() {
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("john.doe@example.com", savedEmployee.getEmail());
        verify(employeeRepo, times(1)).save(any(Employee.class));
    }
//
//    @Test
//    void testGetEmployeesByDepartment() {
//        when(departmentRepo.findByName("IT")).thenReturn(Optional.of(department));
//        when(employeeRepo.findByDepartment(department)).thenReturn(Collections.singletonList(employee));
//
//        List<Employee> employees = employeeService.getEmployeesByDepartment("IT");
//        assertFalse(employees.isEmpty());
//        assertEquals(1, employees.size());
//        assertEquals("John", employees.get(0).getFirstName());
//        assertEquals("Doe", employees.get(0).getLastName());
//        verify(departmentRepo, times(1)).findByName("IT");
//        verify(employeeRepo, times(1)).findByDepartment(department);
//    }
//
//    @Test
//    void testSearchEmployeesByName() {
//        when(employeeRepo.findByFirstNameContainingIgnoreCase("John")).thenReturn(Collections.singletonList(employee));
//        when(employeeRepo.findByLastNameContainingIgnoreCase("John")).thenReturn(Collections.emptyList());
//        when(employeeRepo.searchByFullName("John")).thenReturn(Collections.emptyList());
//
//        List<Employee> result = employeeService.searchEmployeesByName("John");
//
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//        assertEquals("John", result.get(0).getFirstName());
//        assertEquals("Doe", result.get(0).getLastName());
//        verify(employeeRepo, times(1)).findByFirstNameContainingIgnoreCase("John");
//        verify(employeeRepo, times(1)).findByLastNameContainingIgnoreCase("John");
//        verify(employeeRepo, times(1)).searchByFullName("John");
//    }

























}
