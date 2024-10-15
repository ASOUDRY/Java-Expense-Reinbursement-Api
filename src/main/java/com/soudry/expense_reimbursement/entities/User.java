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
    private String id;

    @Column(name="username")
    private String username = "";

    @Column(name="password")
    private String password = "";

    @Column(name="email")
    private String email = "";

    @Column(name="Role")
    private Boolean manager = false;

    public User(String username, String password, String email) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
        this.id = UUID.randomUUID().toString();
        this.username = "John";
        this.password = "Snow";
        this.email = "WindsofWinter";
    }
    
}
