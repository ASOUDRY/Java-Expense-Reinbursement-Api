package com.soudry.expense_reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.soudry.expense_reimbursement.DTO.Request.RegistrationRequest;
import com.soudry.expense_reimbursement.DTO.Response.RegistrationResponse;
import com.soudry.expense_reimbursement.Exceptions.AccountAlreadyExistsException;
import com.soudry.expense_reimbursement.Exceptions.ErrorResponse;
import com.soudry.expense_reimbursement.Exceptions.PasswordsDoNotMatchException;
import com.soudry.expense_reimbursement.services.RegistrationService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController( RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest request) {
        try {
        RegistrationResponse response = registrationService.register(request);
        return ResponseEntity.ok(response);
        } catch (AccountAlreadyExistsException e) {
            return ResponseEntity.status(409).body(
                new ErrorResponse("Account already exists", 
                HttpStatus.CONFLICT.value())
            );
        } catch (PasswordsDoNotMatchException e) {
            return ResponseEntity.status(400).body(
                new ErrorResponse("Passwords do not match", 
                HttpStatus.BAD_REQUEST.value())
            );
        }
    }
}
