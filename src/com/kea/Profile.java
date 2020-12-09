package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Declares 3 variables and an ArrayList
 */
public class Profile {
    private String username;
    private String password;
    private String email;

    private ArrayList<Stream> myStreams;

    /**
     * Declares profile attributes
     * @param username username for the user
     * @param email email for the user
     * @param password password for the user
     */
    public Profile(String username, String email, String password) throws FileNotFoundException {
        this.username = username;
        this.password = password;
        this.email = email;
        myStreams = new ArrayList<>();
        loadMyStreams();
    }

    public void loadMyStreams() throws FileNotFoundException {
        File MyStreams = new File("MyStreams.txt");
        Scanner fileScanner = new Scanner(MyStreams);
        while(fileScanner.hasNext()){
            String streamLine = fileScanner.nextLine();
            if (streamLine.contains(username)) {
                Stream stream = Session.convertStream(streamLine);
                myStreams.add(stream);
            }
        }
    }

    public ArrayList<Stream> getMyStreams() {
        return myStreams;
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
     * toString method that returns profile credentials
     * @return profile credentials in nice layout
     */
    public String toString() {
        return "Username: " + username;
    }
}