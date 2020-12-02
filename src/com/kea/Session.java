package com.kea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    Profile profile;
    ArrayList<Stream> streams;


    public Session(Profile profile) /*ArrayList<Stream> streams*/ {
        this.profile = profile;
        //this.streams = streams;
    }

    public void createStream() {
        System.out.println("Enter stream information.");
        Scanner sc = new Scanner(System.in);

        System.out.println("Stream title: ");
        String streamTitle = sc.nextLine();

        System.out.println("Type a stream genre: ");
        System.out.println(java.util.Arrays.asList(Genre.values()));
        String genre = sc.nextLine();
        Genre enumGenre = Genre.valueOf(genre);

        System.out.println("Enter stream duration (HH): ");
        String durationHoursRegex;
        int durationHours = sc.nextInt();


        System.out.println("Stream time and date (YYYY,MM,DD,HH,MM): ");
        String timeAndDateRegex;
        String time = sc.next();

       // LocalDateTime startTime = LocalDateTime.of();
        LocalDateTime localTimeObj = LocalDateTime.parse(time);

    }

    public void sessionMenu() {
        System.out.println("What do you want next?");
        System.out.println("1. Create Stream.\n2. etc.");
        Scanner sc = new Scanner(System.in);

        String choice = sc.next();

        switch (choice) {
            case "1": {
                System.out.println("You pressed 1");
                createStream();
            }
        }
    }
}