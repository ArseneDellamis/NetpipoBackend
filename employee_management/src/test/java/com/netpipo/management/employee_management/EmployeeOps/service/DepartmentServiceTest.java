package com.netpipo.management.employee_management.EmployeeOps.service;


import com.netpipo.management.employee_management.EmployeeOps.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
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
@DisplayName("Testing Department service layer")
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepo;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().id(Long.valueOf(1L)).name("IT").build();
    }

    @Test
    @DisplayName("test Create Department")
    void testCreateDepartment_whenValidDepartment_ShouldReturnSavedDepartment() {
        when(departmentRepo.save(any(Department.class))).thenReturn(department);
        Department created = departmentService.createDepartment(department);
        assertNotNull(created);
        assertEquals("IT", created.getName());
    }

    @Test
    @DisplayName("test retrieval of all departments")
    void testGetAllDepartments_whenDepartmentsExist_ShouldReturnListOfDepartments() {
        when(departmentRepo.findAll()).thenReturn(Collections.singletonList(department));
        List<Department> departments = departmentService.getAllDepartments();
        assertFalse(departments.isEmpty());
        assertEquals(1, departments.size());
    }

    @Test
    @DisplayName("test get department by name")
    void testGetDepartmentByName_whenDepartmentExists_ShouldReturnDepartment() {
        when(departmentRepo.findByName("IT")).thenReturn(Optional.of(department));
        Department found = departmentService.getDepartmentByName("IT");
        assertEquals("IT", found.getName());
    }

    @Test
    @DisplayName("test update department")
    void testUpdateDepartment_whenDepartmentExists_ShouldReturnUpdatedDepartment() {
        Department updatedDepartment = Department.builder().name("HR").build();
        when(departmentRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(department));
        when(departmentRepo.save(any(Department.class))).thenReturn(updatedDepartment);
        Department result = departmentService.updateDepartment(1L, updatedDepartment);
        assertEquals("HR", result.getName());
    }

    @Test
    @DisplayName("test delete department")
    void testDeleteDepartment_whenDepartmentExists_ShouldDeleteSuccessfully() {
        doNothing().when(departmentRepo).deleteById(Long.valueOf(1L));
        assertDoesNotThrow(() -> departmentService.deleteDepartment(1L));
        verify(departmentRepo, times(1)).deleteById(Long.valueOf(1L));
    }



}
