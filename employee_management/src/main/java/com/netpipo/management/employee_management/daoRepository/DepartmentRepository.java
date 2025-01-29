package com.netpipo.management.employee_management.daoRepository;

import com.netpipo.management.employee_management.manage.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByNameContainingIgnoreCase(String name);
}
