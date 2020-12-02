package com.kea;

import java.util.Scanner;

public class Session {
    Profile profile;

    public Session(Profile profile) {
        this.profile = profile;
    }


    public void sessionMenu() {
    System.out.println("What do you want next?");
        System.out.println("1. Create Stream.\n 2. etc.");
        Scanner sc = new Scanner(System.in);

        String choice = sc.next();

        switch (choice) {
            case "1":
                System.out.println("1. Create Stream");
        }
    }
}