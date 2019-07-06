package com.example.s10179502_week11practical;

public class Account {
    private String username;
    private String password;

    public Account() {}
    public Account(String usr, String pwd){
        username = usr;
        password = pwd;
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
