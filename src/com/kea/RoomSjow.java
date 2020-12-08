package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the functionalities that a user that is not logged in encounters in the system.
 */
public class RoomSjow {
    /**
     * main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new RoomSjow().run();
    }

    /**
     * declares variables, scanner, ArrayList and an instantiation of streamlist
     */
    StreamList streamList;
    String username = null;
    String email = null;
    String password = null;
    Scanner sc = new Scanner(System.in);
    ArrayList<Profile> profiles;

    public RoomSjow() throws FileNotFoundException {
        streamList = new StreamList();
        profiles = new ArrayList<>();
    }

    /**
     * main run method that runs all the methods
     * @throws IOException
     */
    public void run() throws IOException {
        loadProfiles();
        homeMenu();
    }

    /**
     * loads profiles from txt file
     * @throws FileNotFoundException required exception when files are used
     */
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

    /**
     * Shows the home menu to the user from where the program starts
     * @throws IOException
     */
    public void homeMenu() throws IOException {
        System.out.println("Choose what to do next: ");
        Scanner sc1 = new Scanner(System.in);
        boolean choiceBoo = true;

        while (choiceBoo) {
            System.out.println("Press 1 to sign in. \nPress 2 to Create a new profile. \nPress 3 to see our streams.\nPress 4 to exit application");
            String choice = sc1.nextLine();

            switch (choice) {
                case "1": {
                    login();
                    continue;
                }
                case "2": {
                    System.out.println("Profile creation:\n");
                    createProfile();
                    continue;
                }
                case "3": {
                    System.out.println("Our streams:\n");
                    streamList.showList();
                    continue;
                }
                case "4": {
                    choiceBoo = false;
                    continue;
                }
                default: {
                    System.out.println("ERROR 40, please try again.");
                }
            }
        }
    }

    /**
     * asks the user to type their credentials and checks if they are taken/valid
     * @throws IOException
     */
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
                    Session session = new Session(p, streamList);
                    System.out.println("Username and password correct!!\n");
                    session.sessionMenu();
                } else {
                    System.out.println("Password or email was incorrect");
                    login();
                }
            }
        }
        if (!isEmailFound) {
            System.out.println("Password or email was incorrect");
            login();
        }
    }

    /**
     * adding the new credentials from the user to the master txt file
     * @throws IOException
     */
    public void createProfile() throws IOException {
        promptUsername();
        promptEmail();
        promptPassword();

        Profile profile = new Profile(username, email, password);
        profiles.add(profile);

        FileWriter fileWriter = new FileWriter("Profiles.txt", true);
        fileWriter.write("\n" + profile.getUsername() + "," + profile.getEmail() + "," + profile.getPassword());
        fileWriter.close();
        System.out.println("Your profile was successfully created.");
    }

    /**
     * Method for setting username with appropriate format and ensuring unique username.
     */
    public void promptUsername() {
        String userNameRegex = "^[a-zA-Z0-9]{4,}$";
        boolean usernameBoo = true;
        do {
            System.out.println("Enter username: ");
            String userName1 = sc.nextLine();
            if (isUserNameTaken(userName1)) {
                System.out.println("Username is already taken, please try again.");
            } else if (userName1.matches(userNameRegex)) {
                username = userName1;
                usernameBoo = false;
            }
            else {
                System.out.println("Username format wrong. \nFour characters consisting of:\na-z \nA-Z \n0-9");
            }
        } while (usernameBoo);
    }

    /**
     * checks if username is already taken
     * @param userName
     * @return boolean true if username is taken
     */
    public boolean isUserNameTaken(String userName) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    /**
     * Method for setting email with appropriate format and ensuring unique email.
     */
    public void promptEmail() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[(a-zA-Z0-9.-]+$";
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
                System.out.println("Email format is invalid, please enter email again following format:");
                System.out.println("Email must be sperated by @, and only consist of the following:\nA-Z\na-z\n0-9\n.-");
            }
        } while (emailBoo);
    }

    /**
     * checks if the email is already taken
     * @param email
     * @return boolean true if email is taken
     */
    public boolean isEmailTaken(String email) {
        for (Profile p : profiles) {
            if (p.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Method for setting password with appropriate format.
     */
    public void promptPassword() {
        String passwordRegex = "[a-zA-Z0-9_!.-]{4,16}";
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
                System.out.println("Password format is invalid, please enter password again in following format.");
                System.out.println("4 to 16 characters\na-z\nA-Z\n0-9\n._-!");
            }
        } while (pwBoo);
    }
}