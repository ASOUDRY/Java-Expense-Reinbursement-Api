package com.soudry.expense_reimbursement.DTO;

public class RegistrationResponse {
   
    private String username = "";
    private String password = "";
    private String email = "";
    private Boolean manager = false;

    public RegistrationResponse(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public RegistrationResponse() {
        this.username = "John";
        this.password = "Snow";
        this.email = "WindsofWinter";
        this.manager = true;
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

    public Boolean getRole() {
        return manager;
    }
}