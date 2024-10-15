package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Response.RegistrationResponse;
import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.UserRepository;

@Service
public class RegistrationService {

    private final UserRepository userRepo;

    @Autowired
    public RegistrationService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public RegistrationResponse register(String username, String password, String confirmedPassword, String email) {
        if (password.equals(confirmedPassword)) {
            User newUser = new User(username, password, email);
            userRepo.save(newUser);
            return new RegistrationResponse(username, password, email);
        }
        return null;
    }
}