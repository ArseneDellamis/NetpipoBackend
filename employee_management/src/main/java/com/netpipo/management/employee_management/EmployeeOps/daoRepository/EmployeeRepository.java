package com.netpipo.management.employee_management.EmployeeOps.daoRepository;

import com.netpipo.management.employee_management.EmployeeOps.manage.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

