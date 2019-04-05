package com.example.breath_of_the_bull;

//Defines basic user profile information
public abstract class UserInfo {

    protected String username;
    protected String password;

    public UserInfo(){}

    public void SetUsername(String user) {
        this.username = user;
    }

    public void SetPassword(String pass) {
        this.password = pass;
    }

    public String GetUsername() {
        return this.username;
    }

    public String GetPassword() {
        return this.password;
    }
}
