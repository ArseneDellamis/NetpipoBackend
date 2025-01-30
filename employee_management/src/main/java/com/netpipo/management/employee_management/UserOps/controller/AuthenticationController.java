package com.netpipo.management.employee_management.UserOps.controller;

import com.netpipo.management.employee_management.UserOps.PayLoad.AuthenticationRequest;
import com.netpipo.management.employee_management.UserOps.PayLoad.AuthenticationResponse;
import com.netpipo.management.employee_management.UserOps.PayLoad.RegisterRequest;
import com.netpipo.management.employee_management.UserOps.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "registration & authenticate API", description = "API for user registration & authenticate, authorization usig Jjwt Token ")
// exposed RestfulApi for login and Register
public class AuthenticationController {
    private final AuthService service;

//    registering a new user http://localhost:8080/api/auth/register
    @PostMapping("/register")
    @Operation(summary = "register user", description = "register user for authentication and authorization purposes")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(service.register(request));
    }

    //    registering a new user http://localhost:8080/api/auth/authenticate
    @PostMapping("/authenticate")
    @Operation(summary = "authenticate the user", description = "login the authenticated user after registration")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(service.authenticate(request));
    }
}
