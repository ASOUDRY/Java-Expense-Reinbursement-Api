package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.LoginRequest;
import com.soudry.expense_reimbursement.DTO.LoginResponse;
import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepo;

    @Autowired
    public LoginService (UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public LoginResponse login(LoginRequest request) {
        User user = userRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse(user.getUsername(), user.getPassword(), user.getEmail());
        return response;
    }
    
}
