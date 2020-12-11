package com.kea;

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
    private Profile profile;
    private StreamList streamList;
    private Scanner inputSc = new Scanner(System.in);

    /**
     * A constructor with while loop to update an ArrayList when called.
     *
     * @param profile    user profile
     * @param streamList list of available streams
     */
    public Session(Profile profile, StreamList streamList) {
        this.profile = profile;
        this.streamList = streamList;
    }

    /**
     * runPay offers choice between 2 methods of payment via 'if' statements. The system input executes the chosen method.
     */
    protected void runPay() {
        System.out.println("All streams cost 5DKK to attend");
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

    /**
     * showMyStreams() sorts and loops through an Arraylist containing information about  the users streams.
     */
    public void showMyStreams() {
        Collections.sort(profile.getMyStreams());
        for (Stream str : profile.getMyStreams()){
            System.out.println(str);
        }
    }

    /**
     * Lets the user create a stream.
     * @throws IOException used for FileWriter method, in case the file is missing
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
            streamList.getStreams().add(stream);
            //updates streams file
            FileWriter myWriter = new FileWriter("Streams.txt", true);
            myWriter.write("\n" + (DateTimeFormatter.ISO_LOCAL_DATE).format(stream.getStartTime()) + "," +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(stream.getStartTime()) + "," +
                    stream.getTitle() + "," + stream.getGenre() + "," +
                    +stream.getViewers() + "," + stream.getPrice());
            myWriter.close();

        } else {
            System.out.println("You are already hosting a stream at the chosen time. Would you like to schedule a new stream at another time?");
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

    /**
     * A method for the user to sign up for streams and add them to the users list of streams.
     * A filewriter is used to save the matched stream from an Arraylist to a txt file.
     * @throws IOException used for FileWriter method, in case the file is missing.
     */
    public void signUpForStream() throws IOException {
        FileWriter fileWriter = new FileWriter("MyStreams.txt", true);

        Collections.sort(streamList.getStreams());
        for (Stream str: streamList.getStreams())
            System.out.println(str);

        System.out.println("Type title of the stream you want to enter: ");
        String search = inputSc.nextLine();
        boolean isFound = false;

        for (int i = 0; i < streamList.getStreams().size(); i++) {

            if (streamList.getStreams().get(i).getTitle().contains(search)) {
                isFound = true;
                Stream s = streamList.getStreams().get(i);

                if (!checkOverlap(s, profile.getMyStreams())) {
                    runPay();
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
                    break;
                }
            }
        }
        if (!isFound) {
            System.out.println("The stream could not be found, try to type the exact title name.");
            signUpForStream();
        }
    }

    /**
     * convertStream is used to convert strings from a read text file, and seperate it in 5 sections, consisting
     * of different types of information using 'splittedLine'.
     * @param line - String, addition of the comma seperated String fields in the textfile.
     * @return object 's', 5 sections saved as a Stream
     */
    public static Stream convertStream(String line) {
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
        if (startTime.isAfter(LocalDateTime.now())) {
            System.out.println();
            System.out.println("The start date chosen is: " + (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime));
            System.out.println("The start time chosen is: " + (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime));
            return startTime;
        } else {
            System.out.println("You chose a passed date");
            return promptDateTime();
        }
    }

    /**
     * Shows the session menu to the user with a switch case to call different methods.
     *
     * @throws IOException used for FileWriter method, in case the file is missing.
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
                    System.out.println("\nPress enter to return to session menu.");
                    String exit = inputSc.nextLine();
                    continue;
                }
                case "3": {
                    signUpForStream();
                    continue;
                }
                case "4": {
                    showMyStreams();
                    System.out.println("\nPress enter to return to session menu.");
                    String exit = inputSc.nextLine();
                    continue;
                }
                case "5": {
                    watchStream();
                    System.out.println("\nPress enter to return to session menu.");
                    String exit = inputSc.nextLine();
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

    /**
     * watchStream allows user to watch a stream if it is airing, or tell the time until the stream airs.
     * If stream has aired, methods are called to rate and comment the aired stream.
     */
    public void watchStream() {
        String choice;
        Stream foundStream = null;
        int index = -1;

        showMyStreams();

        System.out.println("Choose stream by typing its title.");
        choice = inputSc.nextLine();

        for (int i = 0; i < profile.getMyStreams().size(); i++) {
            if (profile.getMyStreams().get(i).getTitle().contains(choice)) {
                foundStream = profile.getMyStreams().get(i);
                index = i;
            } if (foundStream == null){
                System.out.println("Title was not found, please try again.");
                watchStream();
            }
        }

        int time = (int) LocalDateTime.now().until(foundStream.getStartTime(), ChronoUnit.MINUTES);
        int diff = LocalDateTime.now().compareTo(foundStream.getStartTime());

        if (diff < 1) {
            foundStream.timeUntilStream();
        } else {
            String s = null;
            if (time >= -120 && time <= 0) {
                System.out.println("Your stream is live! Please enjoy your content, press enter to watch the stream.");
                //String begin = inputSc.nextLine();

                Content content = new Content();

                content.createAndShowGUI();
            } else {
                System.out.println("Your stream has aired, please rate and comment");
                foundStream.rate();
                foundStream.calculateRating();
                foundStream.comment();
                streamList.getStreams().remove(index);
                streamList.getStreams().add(foundStream);
            }
        }
    }
}