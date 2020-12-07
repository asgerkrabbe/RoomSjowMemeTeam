package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Instantiation of RoomSjow, profile and streamlist
 */
public class Session {
    RoomSjow roomSjow = new RoomSjow();
    Profile profile;
    private StreamList streamList;
    File streamsFile = new File("Streams.txt");
    File myStreamsFile = new File("MyStreams.txt");
    Scanner fileSc = new Scanner(streamsFile);
    ArrayList<String> showStreamsString = new ArrayList<>();
    ArrayList<String> myStreams = new ArrayList<>();
    Scanner myStreamsSc = new Scanner(myStreamsFile);
    Scanner sc = new Scanner(System.in);

    protected void runPay() {
        System.out.println("Choose your payment method: ");
        System.out.println("1 Pay with Creditcard");
        System.out.println("2 Pay with Mobilepay");
        int num = sc.nextInt();
        if (num == 1) {
            CardPayment cP = new CardPayment();
            cP.pay();
        } else if (num == 2) {
            MobilePay mP = new MobilePay();
            mP.pay();
        } else {
            System.out.println("Invalid input. Please try again.");
            runPay();
        }
    }

    /**
     * ?
     *
     * @param profile    user profile
     * @param streamList list of available streams
     * @throws FileNotFoundException required exception when working with files
     */
    public Session(Profile profile, StreamList streamList) throws FileNotFoundException /*ArrayList<Stream> streams*/ {
        this.profile = profile;
        this.streamList = streamList;
        //this.streams = streams;
    }

    public void myStreams() {
        while (myStreamsSc.hasNext()) {
            myStreams.add(myStreamsSc.nextLine());
        }

        for (int i = 0; i < myStreams.size(); i++) {
            if (myStreams.get(i).contains(profile.getUsername())) {
                System.out.println(myStreams.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Lets the user create a stream.
     *
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
        Stream stream = new Stream(startTime, streamTitle, enumGenre, 0, 5.0);
        if (!checkOverlap(stream, profile.myStreams)) {
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
                    Stream stream2 = new Stream(startTime, streamTitle, enumGenre, 0, 5.0);
                    if (checkOverlap(stream2, profile.myStreams))
                        System.out.println("You are also signed up for a stream at the selected time. \n");
                    System.out.println("You will be forwarded to the start menu. ");
                    createStream();
                case "2":
                    sessionMenu();
            }
        }
    }

    public void signUpForStream() throws IOException {

        FileWriter fileWriter = new FileWriter("MyStreams.txt", true);

        while (fileSc.hasNext()) {
            showStreamsString.add(fileSc.nextLine());
        }
        Collections.sort(showStreamsString);

        for (int i = 0; i < showStreamsString.size(); i++) {
            System.out.println(showStreamsString.get(i));
        }
        System.out.println("Type stream you want to enter: ");
        String search = sc.nextLine();

        for (int i = 0; i < showStreamsString.size(); i++) {

            if (showStreamsString.get(i).contains(search)) {

                String[] splittedLine = showStreamsString.get(i).split(",");
                Stream s = convertStream(splittedLine);

                if (!checkOverlap(s, profile.myStreams)) {
                    fileWriter.write("\n" + showStreamsString.get(i) + "," + profile.getUsername());
                    fileWriter.close();
                    profile.myStreams.add(s);
                } else {
                    System.out.println("There is an overlap with one of your streams, you can not sign up to this stream.");
                    sessionMenu();
                }
            }
        }
        fileSc.close();
    }

    public static Stream convertStream(String[] splittedLine) {
        LocalDate date = LocalDate.parse(splittedLine[0]);
        LocalTime time = LocalTime.parse(splittedLine[1]);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Genre genreEnum = Genre.valueOf(splittedLine[3]);
        int viewers = Integer.parseInt(splittedLine[4]);
        double price = Double.parseDouble(splittedLine[5]);
        Stream s = new Stream(dateTime, splittedLine[2], genreEnum, viewers, price);
        return s;
    }

    /**
     * checks if two streams overlap in time
     *
     * @param stream    the desired stream the user wants to signup to
     * @param myStreams list (ArrayList) of streams that is being checked
     * @return boolean true if the streams overlap
     */
    public boolean checkOverlap(Stream stream, ArrayList<Stream> myStreams) {

        for (Stream s : myStreams) {
            if (stream.getStartTime().isBefore(s.getStartTime())) {
                if (!stream.getStartTime().plusHours(2).isBefore(s.getStartTime()))
                    return true;
            } else if (stream.getStartTime().isAfter(s.getStartTime())) {
                if (!stream.getStartTime().isAfter(s.getStartTime().plusHours(2)))
                    return true;
            } else if (stream.getStartTime() == s.getStartTime())
                return true;
        }
        return false;
    }


    /**
     * lets the user type the date and time of the stream
     *
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
     *
     * @throws IOException
     */
    public void sessionMenu() throws IOException {
        System.out.println("What do you want next?");
        Scanner sc = new Scanner(System.in);
        boolean choiceBoo = true;


        while (choiceBoo) {
            System.out.println("1. Create Stream.\n2. View our Streams list.\n3. Sign up for a stream\n4. View my Streams\n5. Watch stream\n6. Sign out");
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    createStream();
                    continue;
                }
                case "2": {
                    streamList.showList();
                    continue;
                }
                case "3": {
                    signUpForStream();
                    continue;
                }
                case "4": {
                    myStreams();
                    continue;
                }
                case "5":{
                    new Stream.watchStream();
                    continue;
                }
                case "6": {
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