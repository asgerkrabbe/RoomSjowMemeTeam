package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Instantiation of RoomSjow, profile and streamlist
 */

public class Session {
    Profile profile;
    private StreamList streamList;
    Scanner inputSc = new Scanner(System.in);

    File streamsFile = new File("Streams.txt");
    Scanner streamsFileSc = new Scanner(streamsFile);
    ArrayList<String> stringStreams = new ArrayList<>();

    File myStreamsFile = new File("MyStreams.txt");
    Scanner myStreamsSc = new Scanner(myStreamsFile);
    ArrayList<String> stringMyStreams = new ArrayList<>();

    /**
     * ?
     *
     * @param profile    user profile
     * @param streamList list of available streams
     * @throws FileNotFoundException required exception when working with files
     */
    public Session(Profile profile, StreamList streamList) throws FileNotFoundException {
        this.profile = profile;
        this.streamList = streamList;
        while (myStreamsSc.hasNext()) {
            stringMyStreams.add(myStreamsSc.nextLine());
        }
    }

    protected void runPay() {
        System.out.println("Choose your payment method: ");
        System.out.println("1 Pay with Creditcard");
        System.out.println("2 Pay with Mobilepay");
        int num = inputSc.nextInt();
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

    public void showMyStreams() {

        Collections.sort(stringMyStreams);
        for (int i = 0; i < stringMyStreams.size(); i++) {
            if (stringMyStreams.get(i).contains(profile.getUsername())) {
                System.out.println(stringMyStreams.get(i));
            }
        }
        System.out.println();
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
        Stream stream = new Stream(startTime, streamTitle, enumGenre, 0, 5.0);

        if (!checkOverlap(stream, profile.getMyStreams())) {
            System.out.println("\nYour stream has been created\n");
            //updates MyStreams array
            profile.getMyStreams().add(stream);
            //updates MyStreams file
            FileWriter writer1 = new FileWriter("MyStreams.txt", true);
            writer1.write("\n" + (DateTimeFormatter.ISO_LOCAL_DATE).format(stream.getStartTime()) + "," +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(stream.getStartTime()) + "," +
                    stream.getTitle() + "," + stream.getGenre() + "," +
                    +stream.getViewers() + "," + stream.getPrice() + "," + profile.getUsername());
            writer1.close();
            //updates streams array
            streamList.streams.add(stream);
            //updates streams file
            FileWriter myWriter = new FileWriter("Streams.txt", true);
            myWriter.write("\n" + (DateTimeFormatter.ISO_LOCAL_DATE).format(stream.getStartTime()) + "," +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(stream.getStartTime()) + "," +
                    stream.getTitle() + "," + stream.getGenre() + "," +
                    +stream.getViewers() + "," + stream.getPrice());
            myWriter.close();

        } else {
            System.out.println("You already signed up for a stream at the selected time, would you like to choose another time?");
            System.out.println("1. Yes \n2. No");
            String answer = sc.next();
            switch (answer) {
                case "1":
                    LocalDateTime startTime2 = promptDateTime();
                    Stream stream2 = new Stream(startTime, streamTitle, enumGenre, 0, 5.0);
                    if (checkOverlap(stream2, profile.getMyStreams()))
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
        while (streamsFileSc.hasNext()) {
            stringStreams.add(streamsFileSc.nextLine());
        }
        Collections.sort(stringStreams);

        for (int i = 0; i < stringStreams.size(); i++) {
            System.out.println(stringStreams.get(i));
        }
        System.out.println("Type title of the stream you want to enter: ");
        String search = inputSc.nextLine();

        for (int i = 0; i < stringStreams.size(); i++) {

            if (stringStreams.get(i).contains(search)) {
                Stream s = convertStream(stringStreams.get(i));

                if (!checkOverlap(s, profile.getMyStreams())) {
                    System.out.println("You have now signed up to \"" + s.getTitle() + "\"");
                    s.addViewer();
                    fileWriter.write("\n" + (DateTimeFormatter.ISO_LOCAL_DATE).format(s.getStartTime()) + "," +
                            (DateTimeFormatter.ISO_LOCAL_TIME).format(s.getStartTime()) + "," +
                            s.getTitle() + "," + s.getGenre() + "," +
                            +s.getViewers() + "," + s.getPrice() + "," + profile.getUsername());
                    profile.getMyStreams().add(s);
                    fileWriter.close();
                    break;
                } else {
                    System.out.println("There is an overlap with one of your streams, you can not sign up to this stream.");
                    sessionMenu();
                }
            }
            System.out.println("The stream could not be found, try to type the exact title name.");
        }
    }

    public static Stream convertStream(String line) throws FileNotFoundException {
        String[] splittedLine = line.split(",");
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
        System.out.println();
        System.out.println("The start date chosen is: " + (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime));
        System.out.println("The start time chosen is: " + (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime));
        return startTime;
    }

    /**
     * Shows the session menu to the user
     *
     * @throws IOException
     */
    public void sessionMenu() throws IOException {
        System.out.println("What do you want to do next?");
        Scanner sc = new Scanner(System.in);
        boolean choiceBoo = true;


        while (choiceBoo) {
            System.out.println("\n1. Create Stream.\n2. View our Streams list.\n3. Sign up for a stream\n4. View my Streams\n5. Watch stream\n6. Sign out");
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
                    showMyStreams();
                    continue;
                }
                case "5": {
                    watchStream();
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

    }

    public void watchStream() throws FileNotFoundException {
        String choice;
        Stream foundStream = null;
        int index = -1;

        Collections.sort(stringMyStreams);
        for (int i = 0; i < stringMyStreams.size(); i++) {
            if (stringMyStreams.get(i).contains(profile.getUsername())) {
                System.out.println(stringMyStreams.get(i));
            }
        }

        System.out.println("Choose stream by typing its title.");
        choice = inputSc.nextLine();

        for (int i = 0; i < streamList.streams.size(); i++) {
            if (streamList.streams.get(i).getTitle().contains(choice)) {
                foundStream = streamList.streams.get(i);
                index = i;
  //          } else {
  //              System.out.println("We could not find that Stream, please try again");
  //              watchStream();
         }
        }

        int time = (int) LocalDateTime.now().until(foundStream.getStartTime(), ChronoUnit.MINUTES);
        int diff = LocalDateTime.now().compareTo(foundStream.getStartTime());
        if (diff < 1) {
            foundStream.timeUntilStream();
        } else {
            String s = null;
            if (time >= -120 && time <= 0) {
                System.out.println("Your stream is live! Please enjoy your content.");

                long sTime = System.currentTimeMillis();
                boolean stop = false;
                int count = 0;
                System.out.println("Streaming content. Enter a to exit.");
                do {
                    count++;
                    try {
                        if (System.in.available() > 0) {
                            s = inputSc.nextLine();
                            if (s.equals("a")) {
                                stop = true;
                                System.out.println("Stop requested");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (System.currentTimeMillis() - sTime < 60000 && !stop);
                System.out.println("Finished");
            } else {
                System.out.println("Your stream has aired, please rate and comment");
                foundStream.rate();
                foundStream.calculateRating();
                foundStream.comment();
                streamList.streams.remove(index);
                streamList.streams.add(foundStream);
            }
        }

    }


}


