package com.soudry.expense_reimbursement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.DTO.Request.LoginRequest;
import com.soudry.expense_reimbursement.DTO.Response.LoginResponse;
import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import com.soudry.expense_reimbursement.util.JwtUtil;

@Service
public class LoginService {

    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final UserToSpringUserService userToSpringUserService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginService (UserRepository userRepo, 
    AuthenticationManager authenticationManager, 
    UserToSpringUserService userToSpringUserService, 
    JwtUtil jwtUtil
    ) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userToSpringUserService = userToSpringUserService;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        // User user = userRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        String jwt = this.authenticationLogic(request.getUsername(), request.getPassword());
        User user = userRepo.findByUsername(request.getUsername());
        LoginResponse response = new LoginResponse(user.getUsername(), user.getPassword(), user.getEmail(), jwt);
        return response;
    }

    public String authenticationLogic(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userToSpringUserService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }
}