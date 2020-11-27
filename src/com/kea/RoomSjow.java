package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomSjow {
    String username = null;
    String email = null;
    String password = null;

    Scanner sc = new Scanner(System.in);
    ArrayList<Stream> streams = new ArrayList<Stream>();
    ArrayList<Profile> profiles = new ArrayList<>();

    public void run() throws FileNotFoundException {
        loadProfiles();
        loadStreams();
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

    private static void loadStreams() {
    }

    public void promptEmail() {
        //String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String emailRegex = "";

        System.out.println("Enter email address: ");
        String email1 = sc.nextLine();
        System.out.println("Confirm email address: ");
        String email2 = sc.nextLine();

        if (email1.matches(emailRegex)) {
            if (email1.equals(email2)) {
                email = email1;
            } else {
                System.out.println("Emails do not match. Please try again.");
            }
        } else {
            System.out.println("Email format is invalid, please enter email again.");
        }
    }

    public void promptPassword() {
        String passwordRegex = "";

        System.out.println("Enter password: ");
        String password1 = sc.nextLine();
        System.out.println("Confirm password: ");
        String password2 = sc.nextLine();

        if (password1.matches(passwordRegex)) {
            if (password1.equals(password2)) {
                password = password1;

            } else {
                System.out.println("Passwords does not match, please try again");
            }
        } else {
            System.out.println("Password must contain at least 8 characters and at most 20 characters.\n" +
                    "\n" + " Password must contain at least one digit.\n" +
                    "\n" + " Password must contain at least one upper case alphabet.\n" +
                    "\n" + " Password must contain at least one lower case alphabet.\n" +
                    "\n" + " Password must not contain any white space.");
        }
    }

    public void promptUsername() {
        String userNameRegex = "";

        System.out.println("Enter username: ");
        String userName1 = sc.nextLine();
        if (isUserNameTaken(userName1)) {
            System.out.println("Typed user name is already taken, please try again.");
        } else if (userName1.matches(userNameRegex)) {
            username = userName1;
        }
    }


    public void createProfile() {
        RoomSjow roomSjow = new RoomSjow();

        roomSjow.promptEmail();
        roomSjow.promptUsername();
        roomSjow.promptPassword();

        Profile profile = new Profile(username, password, email);
        profiles.add(profile);
    }

    public boolean isUserNameTaken(String userName) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(userName))
                return true;
        }
        return false;
    }

}