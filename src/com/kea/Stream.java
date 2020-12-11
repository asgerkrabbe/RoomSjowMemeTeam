package com.kea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stream object that represents a real stream
 */
public class Stream implements Comparable<Stream> {

    private double price;
    private String title;
    private LocalDateTime startTime;
    private Genre genre;
    private int viewers;
    private int[] ratings= new int[5];
    double rating;
    private ArrayList<String> comments;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor containing information about a stream
     * @param startTime - LocalDateTime of stream, not null
     * @param title - String of stream title, not null
     * @param genre - Enum gerne tag for stream, not null
     * @param viewers - Integer viewer count for stream
     * @param price - Price tag for stream
     */
    public Stream(LocalDateTime startTime, String title, Genre genre, int viewers, double price) {
        this.title = title;
        this.startTime = startTime;
        this.genre = genre;
        this.viewers = viewers;
        this.price = price;
        comments = new ArrayList<>();
    }

    /**
     * Operations to calculate the average rating of an array
     */
    public void calculateRating(){
        double number = ratings[0]+ratings[1]+ratings [2]+ratings[3]+ratings[4];
        double sum = ratings[0]+(ratings[1]*2)+(ratings [2]*3)+(ratings[3]*4)+(ratings [4]*5);
        double average = sum/number;
        rating = average;
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
     *
     * @return date and time in a nice layout
     */
    @Override
    public String toString() {
        if (comments.size()>0) {
            return "\nTitle: " + title + "\tGenre: " + genre + "\nDate: " +
                    (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                    "\nViewers: " + viewers + "\tPrice: " + price + " dkk" +
                    "\nComments:\n" + comments+"\nRating: "+ rating + "\n";
        } else {
            return "\nTitle: " + title + "\tGenre: " + genre + "\nDate: " +
                    (DateTimeFormatter.ISO_LOCAL_DATE).format(startTime) + "\tTime: " +
                    (DateTimeFormatter.ISO_LOCAL_TIME).format(startTime) +
                    "\nViewers: " + viewers + "\tPrice: " + price + " dkk" + "\n";
        }
    }

    /**
     * method to rate a stream via a switch case to an array.
     */
    public void rate() {
        System.out.println("Give a rating between 1 to 5.");
        String rate = sc.next();
        switch (rate) {
            case "1":
                ratings[0]++;
                break;
            case "2":
                ratings[1]++;
                break;
            case "3":
                ratings[2]++;
                break;
            case "4":
                ratings[3]++;
                break;
            case "5":
                ratings[4]++;
                break;
            default: {
                System.out.println("ERROR 40, please try again.");
            }
        }
        System.out.println("Your rating has been added.");
    }

    /**
     * method to comment a stream, adding the comment to an ArrayList
     */
    public void comment() {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Write your comment here:");
        String comment = inputScan.nextLine();
        comments.add(comment);
        System.out.println("Your comment was added.");
    }

    /**
     * Calculates time until stream converted from MM to DD/HH/MM
     */
    public void timeUntilStream() {
        int time = (int) LocalDateTime.now().until(startTime, ChronoUnit.MINUTES);
        System.out.println("Your stream has not started yet.\n Time left till airing:");
        System.out.println("Days: " + time / 24 / 60 + "\nHours: " + time / 60 % 24 + "\nMinutes: " + time % 60);
    }

    /**
     * getter for LocalDateTime
     * @return start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * getter for price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for genre
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * getter for viewers
     * @return viewers
     */
    public int getViewers() {
        return viewers;
    }

    /**
     * method to add a single viewer to a stream when signup method is executed.
     */
    public void addViewer() {
        viewers++;
    }
}
