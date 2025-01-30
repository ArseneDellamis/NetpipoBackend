package com.netpipo.management.employee_management.UserOps.AuthenticationConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//during jwt authentication implement authentication filter first
//responsible for validation and communicate to the dispatcherServlet
//for request and response
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {
// here we are creating the header for our jwt token
        final String authHeader = request.getHeader(AuthConstants.HEADER_STRING);
        final String jwtToken;
        final String userEmail;
        if (authHeader == null || !authHeader
//                token prefix is used is "Bearer "
                .startsWith(AuthConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
//        extracting token form autheader
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
        if (userEmail != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {


            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
    }
}
