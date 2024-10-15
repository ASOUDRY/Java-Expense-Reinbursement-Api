package com.soudry.expense_reimbursement.DTO;

public class RegistrationRequest {
    private String username = "";
    private String password = "";
    private String confirmedPassword = "";
    private String email = "";

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}