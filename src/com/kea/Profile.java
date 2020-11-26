package com.kea;

import java.util.Scanner;

public class Profile {
    private String username;
    private String password;
    private String email;
    //private Scanner sc;
    Scanner sc = new Scanner(System.in);

    public Profile(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Profile() {
    }

    public void makeProfile() {
        System.out.println("Enter email address: ");
        String email1 = sc.nextLine();
        System.out.println("Confirm email address: ");
        String email2 = sc.nextLine();

        System.out.println("Enter username: ");
        String username1 = sc.nextLine();

        System.out.println("Enter password: ");
        String password1 = sc.nextLine();
        System.out.println("Confirm password: ");
        String password2 = sc.nextLine();

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email1.matches(emailRegex)) {
            if (email1.equals(email2)) {
                email = email1;
            } else {
                System.out.println("Email does not match. Please try again.");
            }
        } else {
            System.out.println("Email format is invalid, please enter email again.");
        }

        String userNameRegex = "";

        //if (*UsernameTaken method=boolean*){}
 /*       if (username.matches(userNameRegex)) {



            username = username1;
        }
*/

        String passwordRegex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

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
                    "\n" + " Password must contain at least one special character which includes !@#$%&*()-+=^.\n" +
                    "\n" + " Password must not contain any white space.");
        }
    }
}