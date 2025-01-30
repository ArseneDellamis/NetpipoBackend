package com.netpipo.management.employee_management.EmployeeOps.service;

import com.netpipo.management.employee_management.EmployeeOps.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.EmployeeOps.daoRepository.EmployeeRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
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

    @Mock
    private DepartmentRepository departmentRepo;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .id(Long.valueOf(1L))
                .name("IT")
                .build();

        employee = Employee.builder()
                .id(Long.valueOf(1L))
                .firstName("Arsene")
                .lastName("Nyirinkwaya")
                .email("arsene.nyirinkwaya@gmail.com")
                .department(department)
                .salary("5000")
                .build();
    }

    @Test
    @DisplayName("test Create employee")
    void testCreateEmployee_whenValidEmployee_ShouldReturnSavedEmployee() {
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        Employee created = employeeService.createEmployee(employee);
        assertNotNull(created);
        assertEquals("Arsene", created.getFirstName());
    }

    @Test
    @DisplayName("test retrieve all employees")
    void testGetAllEmployees_whenEmployeesExist_ShouldReturnListOfEmployees() {
        when(employeeRepo.findAll()).thenReturn(Collections.singletonList(employee));
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());
    }

    @Test
    @DisplayName("test get employee info by id")
    void testGetEmployeeById_whenEmployeeExists_ShouldReturnEmployee() {
        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        Optional<Employee> found = employeeService.getEmployeeById(1L);
        assertTrue(found.isPresent());
        assertEquals("Arsene", found.get().getFirstName());
    }

    @Test
    @DisplayName("test update Employee")
    void testUpdateEmployee_whenEmployeeExists_ShouldReturnUpdatedEmployee() {
        Employee updatedEmployee = Employee.builder()
                .firstName("Arsene")
                .lastName("nyirinkwaya")
                .email("Arsene.nyirinkwaya@gmail.com")
                .department(department) // Ensure department is set
                .salary("60000")
                .build();

        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        when(departmentRepo.findById(department.getId())).thenReturn(Optional.of(department)); // Mock department lookup
        when(employeeRepo.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertEquals("Arsene", result.getFirstName());
        assertEquals("60000", result.getSalary());
    }


    @Test
    @DisplayName("test Delete Employee")
    void testDeleteEmployee_whenEmployeeExists_ShouldDeleteSuccessfully() {
        when(employeeRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepo).delete(any(Employee.class));
        assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));
        verify(employeeRepo, times(1)).delete(employee);
    }























}
