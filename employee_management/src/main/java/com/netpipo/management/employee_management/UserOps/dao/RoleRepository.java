package com.netpipo.management.employee_management.UserOps.dao;


import com.netpipo.management.employee_management.UserOps.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// data access object for role table. used for accessing database table role using Java persistent Api
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
