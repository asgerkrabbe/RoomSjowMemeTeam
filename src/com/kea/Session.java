package com.kea;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    Profile profile;
    StreamList streamList = new StreamList();



    public Session(Profile profile, StreamList streamList) throws FileNotFoundException /*ArrayList<Stream> streams*/ {
        this.profile = profile;
        this.streamList = streamList;
        //this.streams = streams;
    }


    public void createStream() throws IOException {
        System.out.println("Enter stream information.");
        Scanner sc = new Scanner(System.in);

        System.out.println("Stream title: ");
        String streamTitle = sc.nextLine();

        System.out.println("Type a stream genre: ");
        System.out.println(java.util.Arrays.asList(Genre.values()));
        String genre = sc.nextLine();
        Genre enumGenre = Genre.valueOf(genre);

        LocalDateTime startTime = promptDateTime();

        Stream stream = new Stream(startTime, streamTitle,  enumGenre, 0, 5.0);
        if (checkOverlap(stream, profile.myStreams)) {
            profile.myStreams.add(stream);
           // streamList.add(stream);

            FileWriter myWriter = new FileWriter("Streams.txt", true);
            myWriter.write(String.valueOf(stream));
            myWriter.close();

        } else {
            System.out.println("You already signed up for a stream at the selected time, would you like to choose another time?");
            System.out.println("1. Yes \n2. No");
            String answer = sc.next();
            switch (answer) {
                case "1":
                    LocalDateTime startTime2 = promptDateTime();
                    Stream stream2 = new Stream(startTime, streamTitle,  enumGenre, 0, 5.0);
                    if (checkOverlap(stream2, profile.myStreams))
                        System.out.println("You are also signed up for a stream at the selected time. \n");
                    System.out.println("You will be forwarded to the start menu. ");
                    createStream();
                case "2":
                    sessionMenu();
            }
        }
    }

    private boolean checkOverlap(Stream stream, ArrayList<Stream> myStreams) {
       /* LocalDateTime thisDateTime = stream.getStartTime().plusHours(2).;
        for (Stream s : myStreams){
            LocalDateTime DateTime = s.getStartTime().plusHours(2);
           //if thisDateTime "er minus end" DateTime...
        }*/
        return true;
    }

    public LocalDateTime promptDateTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the stream date and start time in this format: YYYY MM DD HH MM");
        String date = sc.nextLine();
        Scanner stringSc = new Scanner(date);
        int year = stringSc.nextInt();
        int month = stringSc.nextInt();
        int day = stringSc.nextInt();
        int hours = stringSc.nextInt();
        int minutes = stringSc.nextInt();
        LocalDateTime startTime = LocalDateTime.of(year, month, day, hours, minutes);
        System.out.println(startTime);
        System.out.println("The start date chose is " + (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime));
        System.out.println("The start time chose is " + (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime));
        return startTime;
    }

    public void sessionMenu() throws IOException {
        System.out.println("What do you want next?");
        System.out.println("1. Create Stream.\n2. viwe our Streams.");
        Scanner sc = new Scanner(System.in);

        String choice = sc.next();

        switch (choice) {
            case "1": {
                System.out.println("You pressed 1");
                createStream();
            }
            case "2": {
                System.out.println("You pressed 2");
                streamList.showList();
                sessionMenu();
            }
            case "3": {
                streamList.signUpForStream();
            }
        }
    }
}