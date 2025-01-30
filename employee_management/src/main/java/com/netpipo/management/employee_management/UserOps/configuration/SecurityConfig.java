package com.netpipo.management.employee_management.UserOps.configuration;

import com.netpipo.management.employee_management.UserOps.AuthenticationConfig.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
/*
security configuration class is responsible for
all web authorization,validation and authentication mechanism
 */

//    injecting some properties
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

//   SecurityFilterChain is core component used to define the security configuration and behavior for web requests
//    it sets up the security filters and rules that govern how requests are authenticated, authorized, and handled
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF to prevent attacks
            .authorizeHttpRequests((authorize) ->
                    authorize
                            // Allow access to authentication endpoints (register, login)
                            .requestMatchers("/netpipo/api/auth/**").permitAll()

                            // Allow GET requests to /netpipo/api/employees/** for all authenticated users
                            .requestMatchers(HttpMethod.GET, "/netpipo/api/employees/**").authenticated()

                            // Restrict POST, PUT, DELETE to only users with ADMIN role
                            .requestMatchers(HttpMethod.POST, "/netpipo/api/employees/**").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/netpipo/api/employees/**").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/netpipo/api/employees/**").hasAuthority("ADMIN")

                            // Any other request must be authenticated
                            .anyRequest().authenticated()
            )
            // Set session management policy to stateless (JWT-based authentication)
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // Register authentication provider
            .authenticationProvider(authenticationProvider)
            // Add JWT authentication filter before UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

}
