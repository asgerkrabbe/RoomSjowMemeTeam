package com.kea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    Profile profile;
    StreamList streamList;


    public Session(Profile profile,StreamList streamList) /*ArrayList<Stream> streams*/ {
        this.profile = profile;
        this.streamList = streamList;
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

        System.out.println("Type the stream date and start time in this format: YYYY MM DD HH MM");
        String date = sc.nextLine();
        Scanner stringSc = new Scanner(date);
        int year = stringSc.nextInt();
        int month = stringSc.nextInt();
        int day = stringSc.nextInt();
        int hours = stringSc.nextInt();
        int minutes = stringSc.nextInt();
        LocalDateTime startTime = LocalDateTime.of(year,month,day,hours,minutes);
        System.out.println(startTime);
        System.out.println("The start date chose is " + (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime));
        System.out.println("The start time chose is " + (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime));

        Stream stream = new Stream(startTime,streamTitle,enumGenre);
        profile.myStreams.add(stream);
        streamList.streams.add(stream);


        //LocalDateTime startTime = LocalDateTime.of(year,month,day,5,2);
        //System.out.println(startTime);
        //LocalDateTime localTimeObj = LocalDateTime.parse(time);

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