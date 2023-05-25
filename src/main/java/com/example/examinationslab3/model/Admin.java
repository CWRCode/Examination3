package com.example.examinationslab3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Admin {
    @Id
    private String username = "admin";

    @NotBlank
    private String password = "admin";

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
