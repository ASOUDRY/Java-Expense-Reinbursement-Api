package com.soudry.expense_reimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.soudry.expense_reimbursement.DTO.RegistrationRequest;
import com.soudry.expense_reimbursement.DTO.RegistrationResponse;
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
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = registrationService.register(request.getUsername(), request.getPassword(), 
        request.getConfirmedPassword(), request.getEmail());
        // RegistrationResponse response = new RegistrationResponse();
        return ResponseEntity.ok(response);
    }
}
