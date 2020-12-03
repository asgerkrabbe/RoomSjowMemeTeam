package com.kea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
default time pattern to parse from a string

LocalDateTime localTimeObj = LocalDateTime.parse(time);
 */
public class Stream implements Comparable<Stream> {
    private double price;
    private String title;
    private LocalDateTime startTime = LocalDateTime.of(2020, 9, 1, 13, 30);
    private Genre genre;
    private int viewers;

    public Stream(String title, LocalDateTime startTime, Genre genre, int viewers,double price) {
        this.title = title;
        this.startTime = startTime;
        this.genre = genre;
        this.viewers = viewers;
        this.price = price;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public int compareTo(Stream s) {
        if (this.startTime == null || s.startTime == null) {
            return 0;
        } else {
            return this.startTime.compareTo(s.startTime);
        }
    }
    @Override
    public String toString(){
        return "\n" + title + "," + (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "," +
        (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) + ","  + genre + "," +
                + viewers + "," +  price + " DKK";
    }
}