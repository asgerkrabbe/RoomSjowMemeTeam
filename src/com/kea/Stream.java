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
    private LocalDateTime startTime;
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
        return "\nTitle: "+title+"\tGenre: "+genre+"\nDate: "+
                (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                "\nViewers: " + viewers + "\tPrice: " + price +" dkk";
    }

    public void watchStream() {
        //show a list of my streams
        //user selects one
        //show stream info

        // show time left to startTime, method
        // eller stream is live.
        //eller stream is finished.

        // "stream has been watched" with a countdown//Ines
        //FoundStream.rate();
        //FoundStream.comment();
    }

    /**
     * getter for LocalDateTime
     * @return start time
     */
    public LocalDateTime getStartTime() { return startTime;}

    public double getPrice() { return price;}

    public String getTitle() { return title;}

    public Genre getGenre() { return genre;}

    public int getViewers() { return viewers;}

    public ArrayList<String> getComments() {return comments; }

    public double getRating() { return rating;}
}
