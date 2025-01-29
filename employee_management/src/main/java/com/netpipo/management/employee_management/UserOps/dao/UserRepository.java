package com.netpipo.management.employee_management.UserOps.dao;

import com.netpipo.management.employee_management.UserOps.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
