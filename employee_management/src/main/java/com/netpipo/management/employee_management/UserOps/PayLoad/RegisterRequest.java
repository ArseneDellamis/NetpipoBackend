package com.netpipo.management.employee_management.UserOps.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//registration request class
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
}
