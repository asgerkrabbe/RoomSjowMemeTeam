package com.kea;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    /**
     * Declares 3 ArrayLists, 2 files and 3 scanners.
     */
    ArrayList<Stream> streams;
    ArrayList<String> showStreams = new ArrayList<>();
    File streamsFile = new File("Streams.txt");
    Scanner fileSc = new Scanner(streamsFile);
    Scanner sc = new Scanner(System.in);

    /**
     * Displays the list of streams
     * @throws FileNotFoundException required exception when working with files
     */
    public StreamList() throws FileNotFoundException {
        streams = new ArrayList<>();
        loadStreams();
    }

    /**
     * Setter for currentUser
     * @param currentUser the user currently using the program
     */



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
        while (fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");
            Stream s = Session.convertStream(splittedLine);
            streams.add(s);

        }
        //fileSc.close();
    }

    /**
     * adds a stream to the streamlist
     * @param stream the desired stream
     */
    public void add(Stream stream) {
        streams.add(stream);
    }

    /**
     * Displays the list of streams sorted
     */
    public void showList() {
        Collections.sort(streams);
        for (int i = 0; i < streams.size(); i++) {
            System.out.println(streams.get(i));
        }
        System.out.println("\nPress enter to return to start menu.");
        String exit = sc.nextLine();
    }

    /**
     * Lets the user signup for a stream
     * @throws IOException
     */

}