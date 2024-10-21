package com.soudry.expense_reimbursement.DTO.Response;

public class LoginResponse {

    private String username = "";
    private String password = "";
    private String email = "";
    public String jwt = "";

    public LoginResponse(String username, String password, String email, String jwt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getJwt() {
        return jwt;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    } 
    public void setEmail(String email) {
        this.email = email;
    } 
    public void setJwt(String jwt) {
        this.jwt = jwt;
    } 
}