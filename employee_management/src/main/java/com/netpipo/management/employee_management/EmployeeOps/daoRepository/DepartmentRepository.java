package com.netpipo.management.employee_management.EmployeeOps.daoRepository;

import com.netpipo.management.employee_management.EmployeeOps.manage.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
    List<Department> findByNameContainingIgnoreCase(String name);
}
