package com.kea;

import java.util.ArrayList;

/**
 * Declares 3 variables and an ArrayList
 */
public class Profile {
    private String username;
    private String password;
    private String email;
    ArrayList<Stream> myStreams = new ArrayList<>();

    /**
     * Declares profile attributes
     * @param username username for the user
     * @param email email for the user
     * @param password password for the user
     */
    public Profile(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     *
     */
    public Profile() {
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * toString method that returns profile crendtials
     * @return profile credentials in nice layout
     */
    public String toString() {
        return "Username: " + username;
    }
}