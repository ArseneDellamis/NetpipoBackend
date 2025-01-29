package com.netpipo.management.employee_management.daoRepository;

import com.netpipo.management.employee_management.manage.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee>findByDepartment(String department);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
