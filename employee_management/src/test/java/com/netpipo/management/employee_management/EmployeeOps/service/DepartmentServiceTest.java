package com.netpipo.management.employee_management.EmployeeOps.service;


import com.netpipo.management.employee_management.EmployeeOps.daoRepository.DepartmentRepository;
import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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











}
