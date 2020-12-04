package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomSjow {
    public RoomSjow() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {

        RoomSjow rs = new RoomSjow();
        rs.run();
        //System.out.println(rs.profiles);

        //Stream s = new Stream(LocalDateTime.of(2020, 11, 5, 16, 0), "Once upon a time", Genre.JAVASCRIPT);

        /**System.out.println("BASIC_ISO_DATE format:      " + (s.getStartTime()));
         System.out.println("ISO_LOCAL_DATE format:      " + (DateTimeFormatter.ISO_LOCAL_DATE).format(s.getStartTime()));
         System.out.println("ISO_LOCAL_TIME format:      " + (DateTimeFormatter.ISO_LOCAL_TIME).format(s.getStartTime()));
         DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
         */
    }

    /**
     * Sæt modifiers
     */
    String username = null;
    String email = null;
    String password = null;
    Scanner sc = new Scanner(System.in);
    ArrayList<Profile> profiles = new ArrayList<>();
    StreamList streamlist;

    public void run() throws IOException {
        loadProfiles();

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

    public void homeMenu() throws IOException {
        System.out.println("Choose what to do next: ");
            System.out.println("Press 1 to sign in. \nPress 2 to Create a new profile. \nPress 3 to see our streams.");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": {
                    //StreamList streamlist = new StreamList();
                    //streamlist.signUpForStream();
                    login();
                    break;
                }
                case "2": {
                    System.out.println("Profile creation:\n");
                    createProfile();

                }
                case "3": {
                    System.out.println("Our streams:\n");
                    StreamList streamList1 = new StreamList();
                    streamList1.showList();
                    break;

                }
                default: {
                    System.out.println("Input incorrect, please try again.");
                }
            }
        }



    public void login() throws IOException {
        boolean isEmailFound = false;
        System.out.println("Enter Email: ");
        String email1 = sc.next();
        System.out.println("Enter Password: ");
        String password1 = sc.next();

        for (Profile p : profiles) {
            if (p.getEmail().equals(email1)) {
                isEmailFound = true;
                if (p.getPassword().equals(password1)) {
                    Session session = new Session(p, streamlist);
                    System.out.println("Username and password correct!!\n\n");
                    //streamlist.setCurrentUser("Asger");
                    session.sessionMenu();
                } else {
                    System.out.println("Password is incorrect");
                    login();
                }
            }
        }
        if (!isEmailFound) {
            System.out.println("Email was not found.");
            login();
        }
    }

    public void createProfile() throws IOException {
        promptUsername();
        promptEmail();
        promptPassword();

        Profile profile = new Profile(username, email, password);
        profiles.add(profile);

        FileWriter fileWriter = new FileWriter("Profiles.txt", true);
        fileWriter.write("\n" + profile.getUsername() + "," + profile.getPassword() + "," + profile.getEmail());
        fileWriter.close();
        System.out.println("Your profile was successfully created.");
        homeMenu();
    }

    public void promptUsername() {
        //String userNameRegex = "username";
        boolean usernameBoo = true;
        do {
            System.out.println("Enter username: ");
            String userName1 = sc.nextLine();
            if (isUserNameTaken(userName1)) {
                System.out.println("Typed user name is already taken, please try again.");
            } else/* (userName1.matches(userNameRegex))*/ {
                username = userName1;
                usernameBoo = false;
            }
        } while (usernameBoo);
    }

    public boolean isUserNameTaken(String userName) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    public void promptEmail() {
        //String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String emailRegex = "testemail";
        boolean emailBoo = true;

        do {
            System.out.println("Enter email address: ");
            String email1 = sc.nextLine();
            System.out.println("Confirm email address: ");
            String email2 = sc.nextLine();

            if (email1.matches(emailRegex)) {
                if (isEmailTaken(email1)) {
                    System.out.println("Typed user name is already taken, please try again.");
                    continue;
                }
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

    public boolean isEmailTaken(String email) {
        for (Profile p : profiles) {
            if (p.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void promptPassword() {
        String passwordRegex = "testpassword";
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


}