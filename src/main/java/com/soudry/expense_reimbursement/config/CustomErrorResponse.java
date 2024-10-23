package com.soudry.expense_reimbursement.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomErrorResponse {

       public static void setCustomErrorResponse(HttpServletRequest request, HttpServletResponse response, int status, String message) throws IOException {
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
