package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Request.RegistrationRequest;
import com.soudry.expense_reimbursement.DTO.Response.RegistrationResponse;
import com.soudry.expense_reimbursement.Exceptions.AccountAlreadyExistsException;
import com.soudry.expense_reimbursement.Exceptions.PasswordsDoNotMatchException;
import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.UserRepository;

@Service
public class RegistrationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationResponse register(RegistrationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String comfirmedPassword = request.getConfirmedPassword();
        String email = request.getEmail();
        // Check if passwords match
        if (!password.matches(comfirmedPassword)) {
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }

        // Check if the account already exists
        if (userRepo.findById(username).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists");
        }

        // Encode the password and create a new user
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword, email);
        userRepo.save(newUser);
        
        // Return the response indicating successful registration
        return new RegistrationResponse(username, encodedPassword, email);
    }
}