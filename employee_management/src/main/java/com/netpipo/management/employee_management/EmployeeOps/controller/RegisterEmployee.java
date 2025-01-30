package com.netpipo.management.employee_management.EmployeeOps.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployee {

    private String name;
    private String email;
    private String position;
    private String salary;

}
