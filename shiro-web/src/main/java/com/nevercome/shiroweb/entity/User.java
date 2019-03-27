package com.nevercome.shiroweb.entity;


import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private boolean rememberMe;
}
