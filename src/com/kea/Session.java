package com.kea;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Instantiation of RoomSjow, profile and streamlist
 */
public class Session {
    RoomSjow roomSjow = new RoomSjow();
    Profile profile;
    private StreamList streamList;


    /**
     * ?
     * @param profile user profile
     * @param streamList list of available streams
     * @throws FileNotFoundException required exception when working with files
     */
    public Session(Profile profile, StreamList streamList) throws FileNotFoundException /*ArrayList<Stream> streams*/ {
        this.profile = profile;
        this.streamList = streamList;
        //this.streams = streams;

    }

    /**
     * Lets the user create a stream.
     * @throws IOException
     */
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

    /**
     * checks if two streams overlap in time
     * @param stream the desired stream the user wants to signup to
     * @param myStreams list (ArrayList) of streams that is being checked
     * @return boolean true if the streams overlap
     */
    private boolean checkOverlap(Stream stream, ArrayList<Stream> myStreams) {
       /* LocalDateTime thisDateTime = stream.getStartTime().plusHours(2).;
        for (Stream s : myStreams){
            LocalDateTime DateTime = s.getStartTime().plusHours(2);
           //if thisDateTime "er minus end" DateTime...
        }*/
        return true;
    }

    /**
     * lets the user type the date and time of the stream
     * @return start time of the stream
     */
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

    /**
     * displays the session menu to the user
     * @throws IOException
     */
    public void sessionMenu() throws IOException {
        System.out.println("What do you want next?");
        Scanner sc = new Scanner(System.in);
        boolean choiceBoo = true;


        while (choiceBoo)
        {
            System.out.println("1. Create Stream.\n2. View our Streams.\n3. Sign up for a stream\n4. My Streams\n5. Sign out");
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    createStream();
                    continue;
                }
                case "2": {
                    streamList = new StreamList();
                    streamList.showList();
                    //sessionMenu();
                    continue;
                }
                case "3": {
                    streamList = new StreamList();
                    streamList.signUpForStream();
                    //sessionMenu();
                    continue;
                }
                case "4": {
                    streamList = new StreamList();
                    streamList.myStreams();
                    continue;
                }
                case "5": {
                    choiceBoo = false;
                    continue;
                }
                default: {
                    System.out.println("ERROR 40, please try again.");

                }
            }
        }
        roomSjow.homeMenu();
    }
}