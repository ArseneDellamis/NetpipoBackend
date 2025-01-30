package com.netpipo.management.employee_management.UserOps.configuration;

import com.netpipo.management.employee_management.UserOps.AuthenticationConfig.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .csrf(AbstractHttpConfigurer::disable) //csrf disable to avoid server vulnerability to attacks
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/api/auth/**")//allowing access to all http://localhost:8080/api/auth/** either register or login
                                .permitAll()
                                .anyRequest()
                                .authenticated() //any other request other than the above must be authenticated
                )
//                session management
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                filterChain to authenticate every request before it's passed to the new filterChain
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
}
