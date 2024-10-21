package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Response.RegistrationResponse;
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

    public RegistrationResponse register(String username, String password, String confirmedPassword, String email) {
        if (password.equals(confirmedPassword)) {
            String encodedPassword = passwordEncoder.encode(confirmedPassword);
            User newUser = new User(username, encodedPassword, email);
            userRepo.save(newUser);
            return new RegistrationResponse(username, encodedPassword, email);
        }
        return null;
    }
}