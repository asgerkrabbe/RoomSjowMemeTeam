package com.kea;
import java.time.LocalDateTime;

/*
default time pattern to parse from a string
String time = "2019-03-27T10:15:30";
LocalDateTime localTimeObj = LocalDateTime.parse(time);
 */
public class Stream implements Comparable<Stream> {
    private double price;
    private String title;
    private LocalDateTime startTime = LocalDateTime.of(2020,9,1,13,30 );
    private Genre genre;

    public Stream(LocalDateTime start, String title, Genre genre){
        startTime = start;
        this.title = title;
        this.genre = genre;
        price = 5;
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

    public void createStream() {

    }
}