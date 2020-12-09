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
        if (comments!=null) {
            return "\nTitle: " + title + "\tGenre: " + genre + "\nDate: " +
                    (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                    "\nViewers: " + viewers + "\tPrice: " + price + " dkk" +
                    "\nComments:\n" + comments;
        } else {
            return "\nTitle: " + title + "\tGenre: " + genre + "\nDate: " +
                    (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                    "\nViewers: " + viewers + "\tPrice: " + price + " dkk";
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

    public void comment() {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Write your comment here:");
        String comment = inputScan.nextLine();
        comments.add(comment);
        System.out.println("Your comment was added.");
        //fjerne gammelt stream
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
