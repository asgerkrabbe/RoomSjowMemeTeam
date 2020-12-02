package com.kea;

import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    Profile profile;
    ArrayList<Stream> streams;

    public Session(Profile profile, ArrayList<Stream> streams) {
        this.profile = profile;
        this.streams = streams;
    }


    public void sessionMenu() {
    System.out.println("What do you want next?");
        System.out.println("1. Create Stream.\n2. etc.");
        Scanner sc = new Scanner(System.in);

        String choice = sc.next();

        switch (choice) {
            case "1": {
                System.out.println("You pressed 1");
            }
        }
    }
}