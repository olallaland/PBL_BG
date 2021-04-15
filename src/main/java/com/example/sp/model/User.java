package com.example.sp.model;

public class User {
    private String identity;
    private String username;
    private String password;

    public User(String identity, String username, String password) {
        this.identity = identity;
        this.username = username;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
