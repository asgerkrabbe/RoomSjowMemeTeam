package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomSjow {
    String username = null;
    String email = null;
    String password = null;
    //final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
    Scanner sc = new Scanner(System.in);
    ArrayList<Stream> streams = new ArrayList<Stream>();
    ArrayList<Profile> profiles = new ArrayList<>();

    public void run() throws IOException {
        loadProfiles();
        loadStreams();
        homeMenu();
    }

    private void loadProfiles() throws FileNotFoundException {
        profiles = new ArrayList<>();
        File profilesFile = new File("Profiles.txt");
        Scanner fileSc = new Scanner(profilesFile);
        while (fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");
            Profile p = new Profile(splittedLine[0], splittedLine[1], splittedLine[2]);
            profiles.add(p);
        }
    }

    private void loadStreams() throws FileNotFoundException{
        streams = new ArrayList<>();
        File streamsFile = new File("Streams.txt");
        Scanner fileSc = new Scanner(streamsFile);

    }

    public void promptEmail() {
        //String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String emailRegex = "";
        boolean emailBoo = true;

        do  {
            System.out.println("Enter email address: ");
            String email1 = sc.nextLine();
            System.out.println("Confirm email address: ");
            String email2 = sc.nextLine();

            if (email1.matches(emailRegex)) {
                if (email1.equals(email2)) {
                    email = email1;
                    emailBoo = false;
                } else {
                    System.out.println("Emails do not match. Please try again.");
                }
            } else {
                System.out.println("Email format is invalid, please enter email again.");
            }
        } while (emailBoo);
    }

    public void promptPassword() {
        String passwordRegex = "";
        boolean pwBoo = true;

        do {
            System.out.println("Enter password: ");
            String password1 = sc.nextLine();
            System.out.println("Confirm password: ");
            String password2 = sc.nextLine();

            if (password1.matches(passwordRegex)) {
                if (password1.equals(password2)) {
                    password = password1;
                    pwBoo = false;
                } else {
                    System.out.println("Passwords does not match, please try again");
                }
            } else {
                System.out.println("Password format is invalid, please enter password again.");
            }
        } while (pwBoo);
    }

    public void promptUsername() {
        String userNameRegex = "asger";
        boolean usernameBoo = true;
        do {
            System.out.println("Enter username: ");
            String userName1 = sc.nextLine();
            if (isUserNameTaken(userName1)) {
                System.out.println("Typed user name is already taken, please try again.");
            } else if (userName1.matches(userNameRegex)) {
                username = userName1;
                usernameBoo = false;
            }
        } while (usernameBoo);
    }


    public void createProfile() throws IOException {
        promptEmail();
        promptUsername();
        promptPassword();

        Profile profile = new Profile(username, password, email);
        profiles.add(profile);

        FileWriter fileWriter = new FileWriter("Profiles.txt", true);
        fileWriter.write(profile.getUsername()+","+profile.getPassword()+","+profile.getEmail());
        fileWriter.close();

    }

    public boolean isUserNameTaken(String userName) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    public void homeMenu() throws IOException {
        System.out.println("Choose what to do next: ");
        System.out.println("Press 1 to sign in. \nPress 2 to Create a new profile. \nPress 3 to see our streams.");
        String choice = sc.nextLine();


        if (choice.contains("1")) {
            System.out.println("Pressed 1");
        }
        else if (choice.contains("2")) {
            System.out.println("Pressed 2");
            createProfile();
        }
        else if (choice.contains("3")) {
            System.out.println("Pressed 3");
        }
        else {
            System.out.println("Input incorrect, please try again.");
        }

    }
}