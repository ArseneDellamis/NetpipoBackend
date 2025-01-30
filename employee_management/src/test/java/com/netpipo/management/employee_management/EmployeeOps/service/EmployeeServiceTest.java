package com.netpipo.management.employee_management.EmployeeOps.service;

import com.netpipo.management.employee_management.EmployeeOps.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing employee service layer")
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("Arsene Nyirinkwaya")
                .email("arsene.nyirinkwaya@gmail.com")
                .position("Backend Developer")
                .salary("80000")
                .build();
    }

    @Test
    @DisplayName("test Create Employee")
    void testCreateEmployee_whenValidEmployee_ShouldReturnSavedEmployee() {
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        Employee created = employeeService.createEmployee(employee);
        assertNotNull(created);
        assertEquals("Arsene Nyirinkwaya", created.getName());
    }

    @Test
    @DisplayName("test Get All Employees")
    void testGetAllEmployees_whenEmployeesExist_ShouldReturnListOfEmployees() {
        when(employeeRepo.findAll()).thenReturn(Collections.singletonList(employee));
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
    }

    @Test
    @DisplayName("test Get Employee By Id")
    void testGetEmployeeById_whenEmployeeExists_ShouldReturnEmployee() {
        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        Optional<Employee> found = employeeService.getEmployeeById(Long.valueOf(1L));
        assertTrue(found.isPresent());
        assertEquals("Arsene Nyirinkwaya", found.get().getName());
    }

    @Test
    @DisplayName("test Update Employee")
    void testUpdateEmployee_whenEmployeeExists_ShouldReturnUpdatedEmployee() {
        Employee updatedEmployee = Employee.builder()
                .name("Arsene Nyirinkwaya")
                .email("arsene.nyirinkwaya@gmail.com")
                .position("Backend Developer")
                .salary("80000")
                .build();

        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        when(employeeRepo.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(Long.valueOf(1L), updatedEmployee);

        assertEquals("Arsene Nyirinkwaya", result.getName());
        assertEquals("80000", result.getSalary());
    }

    @Test
    @DisplayName("test Delete Employee")
    void testDeleteEmployee_whenEmployeeExists_ShouldDeleteSuccessfully() {
        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepo).delete(any(Employee.class));

        assertDoesNotThrow(() -> employeeService.deleteEmployee(Long.valueOf(1L)));
        verify(employeeRepo, times(1)).delete(employee);
    }




}
