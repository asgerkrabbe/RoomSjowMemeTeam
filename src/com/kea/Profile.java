package com.kea;

import java.util.ArrayList;

public class Profile {
    private String username;
    private String password;
    private String email;
    ArrayList<Stream> myStreams = new ArrayList<>();

    public Profile(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Profile() {
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "Username: " + username;
    }
}