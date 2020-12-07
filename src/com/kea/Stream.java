package com.kea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * declares variables and getters for them
 */
public class Stream implements Comparable<Stream> {
    private double price;
    private String title;
    private LocalDateTime startTime = LocalDateTime.of(2020, 9, 1, 13, 30);
    private Genre genre;
    private int viewers;
    private double rating;
    ArrayList<String> comments;

    public Stream(LocalDateTime startTime, String title, Genre genre, int viewers, double price) {
        this.title = title;
        this.startTime = startTime;
        this.genre = genre;
        this.viewers = viewers;
        this.price = price;
        rating = -1;
        comments = new ArrayList<>();
    }

    public double getRating() {
        return rating;
    }
    /**
     * getter for LocalDateTime
     * @return start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * compares stream time
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
     * @return date and time in a nice layout
     */
    @Override
    public String toString() {
        return (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "," +
                (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) + "," +
                title + "," + genre + "," +
                +viewers + "," + price;
    }

    public void getDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
/*
    public void CompareLocalDateTimeExample() {
        int diff = date1.compareTo(date2);
        if (diff > 0) {
            System.out.println(date1 + " is greater than " + date2);
        } else if (diff < 0) {
            System.out.println(date1 + " is less than " + date2);
        } else {
            System.out.println(date1 + " is equal to " + date2);
        }
    }
*/
    public void watchStream() {
        //show a list of my streams
        //user selects one
        //"stream has been watched" with a countdown
        //FoundStream.rate();
        //FoundStream.comment();
    }
}