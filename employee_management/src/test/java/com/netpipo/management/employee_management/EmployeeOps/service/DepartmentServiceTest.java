package com.netpipo.management.employee_management.EmployeeOps.service;


import com.netpipo.management.employee_management.EmployeeOps.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
import org.junit.jupiter.api.BeforeEach;
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
    void testCreateDepartment() {
        when(departmentRepo.save(any(Department.class))).thenReturn(department);
        Department created = departmentService.createDepartment(department);
        assertNotNull(created);
        assertEquals("IT", created.getName());
    }


    @Test
    void testGetAllDepartments() {
        when(departmentRepo.findAll()).thenReturn(Collections.singletonList(department));
        List<Department> departments = departmentService.getAllDepartments();
        assertFalse(departments.isEmpty());
        assertEquals(1, departments.size());
    }

    @Test
    void testGetDepartmentByName() {
        when(departmentRepo.findByName("IT")).thenReturn(Optional.of(department));
        Department found = departmentService.getDepartmentByName("IT");
        assertEquals("IT", found.getName());
    }

    @Test
    void testUpdateDepartment() {
        Department updatedDepartment = Department.builder().name("HR").build();
        when(departmentRepo.findById(Long.valueOf(1L))).thenReturn(Optional.of(department));
        when(departmentRepo.save(any(Department.class))).thenReturn(updatedDepartment);
        Department result = departmentService.updateDepartment(1L, updatedDepartment);
        assertEquals("HR", result.getName());
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentRepo).deleteById(Long.valueOf(1L));
        assertDoesNotThrow(() -> departmentService.deleteDepartment(1L));
        verify(departmentRepo, times(1)).deleteById(Long.valueOf(1L));
    }








}
