package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static com.kea.Session.convertStream;

/**
 * declares variables and getters for them
 */
public class Stream implements Comparable<Stream> {
    private double price;
    private String title;
    private LocalDateTime startTime;
    private Genre genre;
    private int viewers;
    private int[] rating= new int[5];
    ArrayList<String> comments = new ArrayList<>();
    File myStreamsFile = new File("MyStreams.txt");
    Scanner myStreamsSc = new Scanner(myStreamsFile);
    Scanner sc = new Scanner(System.in);
    ArrayList<String> stringMyStreams = new ArrayList<>();

    public Stream(LocalDateTime startTime, String title, Genre genre, int viewers, double price) throws FileNotFoundException {
        this.title = title;
        this.startTime = startTime;
        this.genre = genre;
        this.viewers = viewers;
        this.price = price;
        comments = new ArrayList<>();
    }

    public Stream() throws FileNotFoundException {

    }


    /**
     * compares stream time
     *
     * @param s the desired stream
     * @return the start time or 0
     */
    @Override
    public int compareTo(Stream s) {
        if (this.startTime == null || s.startTime == null) {
            return 0;
        } else {
            return this.startTime.compareTo(s.startTime);
        }
    }

    /**
     * ToString method for the date and time
     *
     * @return date and time in a nice layout
     */
    @Override
    public String toString() {
        return "\nTitle: " + title + "\tGenre: " + genre + "\nDate: " +
                (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                "\nViewers: " + viewers + "\tPrice: " + price + " dkk";
    }

    public void watchStream(Profile profile) throws FileNotFoundException {

        System.out.println("Type 1 for comments, 2 to watch stream");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if(choice.contains("1")){
            viewCommentsAndArray();
        }
        if(choice.contains("2")){
            while (myStreamsSc.hasNext()) {
                stringMyStreams.add(myStreamsSc.nextLine());
            }
            Collections.sort(stringMyStreams);
            for (int i = 0; i < stringMyStreams.size(); i++) {
                if (stringMyStreams.get(i).contains(profile.getUsername())) {
                    System.out.println(stringMyStreams.get(i));
                }
            }

            System.out.println("Choose stream by typing.");
            String streamChoice = sc.nextLine();

            for (int i = 0; i < stringMyStreams.size(); i++) {
                if (stringMyStreams.get(i).contains(streamChoice)) {
                    Stream s = convertStream(stringMyStreams.get(i));
                    this.startTime = s.startTime;
                }
            }

            int time = (int) LocalDateTime.now().until(startTime, ChronoUnit.MINUTES);
            int diff = LocalDateTime.now().compareTo(startTime);
            if (diff < 1) {
                timeUntilStream();
            } else {
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
                                String s = sc.nextLine();
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
                    rate();
                    comment();
                }
            }

        }


    }

    public void viewCommentsAndArray(){
        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i));
        }
        System.out.println(Arrays.toString(rating));
    }

    public void rate() {
        System.out.println("Give a rating between 1 to 5.");
        String rate = sc.next();
        switch (rate) {
            case "1":
                rating[0]++;
                break;
            case "2":
                rating[1]++;
                break;
            case "3":
                rating[2]++;
                break;
            case "4":
                rating[3]++;
                break;
            case "5":
                rating[4]++;
                break;
            default: {
                System.out.println("ERROR 40, please try again.");
            }
        }
        System.out.println("Your rating has been added.");

        //Implement Print rating average method?
    }

    private void comment() {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Write your comment here:");
        String comment = inputScan.nextLine();
        comments.add(comment);
        System.out.println("Your comment was added.");
    }

    public void timeUntilStream() {
        int time = (int) LocalDateTime.now().until(startTime, ChronoUnit.MINUTES);
        System.out.println("Your stream has not started yet.\n Time left till airing:");
        System.out.println("Days: " + time / 24 / 60 + "\nHours: " + time / 60 % 24 + "\nMinutes: " + time % 60);
    }


    /**
     * getter for LocalDateTime
     *
     * @return start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getViewers() {
        return viewers;
    }

    public void addViewer() {
        viewers++;
    }
}
