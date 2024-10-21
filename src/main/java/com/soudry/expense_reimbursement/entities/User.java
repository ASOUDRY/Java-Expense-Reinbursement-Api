package com.soudry.expense_reimbursement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

import jakarta.persistence.Column;;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(name="username")
    private String username = "";

    @Column(name="password")
    private String password = "";

    @Column(name="email")
    private String email = "";

    @Column(name="Role")
    private Boolean manager = false;

    public User(String username, String password, String email) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
        this.userId = UUID.randomUUID().toString();
        this.username = "John";
        this.password = "Snow";
        this.email = "WindsofWinter";
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
    
}
