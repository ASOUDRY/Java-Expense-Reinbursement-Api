package com.soudry.expense_reimbursement.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.soudry.expense_reimbursement.services.UserToSpringUserService;
import com.soudry.expense_reimbursement.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserToSpringUserService userToSpringUserService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserToSpringUserService userToSpringUserService) {
        this.jwtUtil = jwtUtil;
        this.userToSpringUserService = userToSpringUserService;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    // Continue chain for public endpoints if there is no authorization header
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        chain.doFilter(request, response);
        return;
    }

    String jwt = authorizationHeader.substring(7);
    String username;

    // Extract username from the JWT and handle potential errors
    try {
        username = jwtUtil.extractUsername(jwt);
    } catch (SignatureException e) {
        setCustomErrorResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Signature exception Issue");
        return;
    } catch (ExpiredJwtException e) {
        setCustomErrorResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "JWT token has expired");
        return;
    } catch (Exception e) {
        setCustomErrorResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Other JWT token");
        return;
    }

    // Authenticate the user if the username is found and the security context is not set
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userToSpringUserService.loadUserByUsername(username);

        if (jwtUtil.validateToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            setCustomErrorResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        }
    }

    // Continue the filter chain
    chain.doFilter(request, response);
}


    private void setCustomErrorResponse(HttpServletRequest request, HttpServletResponse response, int status, String message) throws IOException {
        // Set status code
        response.setStatus(status);
        response.setContentType("application/json");

        // Create a response body with the custom message
        String jsonResponse = String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                java.time.LocalDateTime.now(),
                status,
                HttpStatus.valueOf(status).getReasonPhrase(),
                message,
                request.getRequestURI());

        // Write the JSON response to the body
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}
