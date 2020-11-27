package com.kea;

public class Profile {
    private String username;

    private String password;
    private String email;
    public Profile(){}

    public Profile(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public String toString(){
        return "Username: "+username;
    }
}