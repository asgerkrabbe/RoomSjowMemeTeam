package com.kea;

import java.util.ArrayList;

public class Profile {
    private String username;
    private String password;
    private String email;
    ArrayList<Stream> myStream = new ArrayList<>();

    public Profile() {
    }

    public Profile(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "Username: " + username;
    }
}