package com.soudry.expense_reimbursement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username = "";

    @Column(name="password")
    private String password = "";

    @Column(name="email")
    private String email = "";

    @Column(name="Role")
    private Boolean manager = false;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
       
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }
    public Boolean getRole() {
        return this.manager;
    }
    public void setRole(Boolean role) {
        this.manager = role;
    }  
}