package com.soudry.expense_reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soudry.expense_reimbursement.DTO.Request.LoginRequest;
import com.soudry.expense_reimbursement.DTO.Response.LoginResponse;
import com.soudry.expense_reimbursement.Exceptions.ErrorResponse;
import com.soudry.expense_reimbursement.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }
    
    @GetMapping("/Test")
    public String foo() {
        return "Test";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = loginService.login(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ErrorResponse("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }  
    }
}