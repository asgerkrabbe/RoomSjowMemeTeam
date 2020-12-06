package com.kea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    /**
     * Declares 3 ArrayLists, 2 files and 3 scanners.
     */
    ArrayList<Stream> streams = new ArrayList<>();
    ArrayList<String> showStreams = new ArrayList<>();
    ArrayList<String> myStreams = new ArrayList<>();
    File streamsFile = new File("Streams.txt");
    File myStreamsFile = new File("MyStreams.txt");
    Scanner myStreamsSc = new Scanner(myStreamsFile);
    Scanner fileSc = new Scanner(streamsFile);
    Scanner sc = new Scanner(System.in);

    private String currentUser;

    /**
     * Displays the list of streams
     * @throws FileNotFoundException required exception when working with files
     */
    public StreamList() throws FileNotFoundException {
        //loadStreams();
    }

    /**
     * Setter for currentUser
     * @param currentUser the user currently using the program
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }



    /**
     * Indexing arrays before usage in methods
     */
    public void arrayListIndexing() {

    }

    /**
     * loadStreams method needs to be modified. New stream format consists of
     * 6 index' instead of the current 3. Check Streams.txt for examples
     */
    public void loadStreams() {
        streams = new ArrayList<>();

        while (fileSc.hasNext()) {
            showStreams.add(fileSc.nextLine());
        }
       /* while (fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");

            Genre genreEnum = Genre.valueOf(splittedLine[3]);
            Stream s = new Stream(splittedLine[0], LocalDateTime.parse(splittedLine[1]), genreEnum, 0, 5.0);
        }*/
        fileSc.close();
    }

    /**
     * adds a stream to the streamlist
     * @param stream the desired stream
     */
    public void add(Stream stream) {
        streams.add(stream);
    }

    /**
     * Sorts the ArrayList
     */
    public void sortArrayList() {
        Collections.sort(showStreams);
    }

    /**
     * Displays the list of streams
     */
    public void showList() {
        while (fileSc.hasNext()) {
            showStreams.add(fileSc.nextLine());
        }
        fileSc.close();
        sortArrayList();

        for (int i = 0; i < showStreams.size(); i++) {
            System.out.println(showStreams.get(i));
        }
        System.out.println("\nPress enter to return to start menu.");
        String exit = sc.nextLine();

    }

    /**
     * Lets the user signup for a stream
     * @throws IOException
     */
    public void signUpForStream() throws IOException {
        FileWriter fileWriter = new FileWriter("MyStreams.txt", true);
        setCurrentUser("Hardy");

        while (fileSc.hasNext()) {
            showStreams.add(fileSc.nextLine());
        }
        sortArrayList();

        for (int i = 0; i < showStreams.size(); i++) {
            System.out.println(showStreams.get(i));
        }
        System.out.println("Type stream you want to enter: ");
        String search = sc.nextLine();

        for (int i = 0; i < showStreams.size(); i++) {

            if (showStreams.get(i).contains(search)) {
                fileWriter.write("\n" + showStreams.get(i) + "," + currentUser);
                fileWriter.close();
            }
        }
        fileSc.close();
    }

    public void myStreams() {
        setCurrentUser("Hardy");
        while (myStreamsSc.hasNext()) {
            myStreams.add(myStreamsSc.nextLine());
        }

        for (int i = 0; i < myStreams.size(); i++) {
            if (myStreams.get(i).contains(currentUser)) {
                System.out.println(myStreams.get(i));
            }
        }
        System.out.println();
    }

}

