package com.soudry.expense_reimbursement.config;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, 
                         org.springframework.security.core.AuthenticationException authException) throws IOException {
                // Send a 401 Unauthorized response when no authentication is provided
                CustomErrorResponse.setCustomErrorResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: No valid token provided");
    }
}
