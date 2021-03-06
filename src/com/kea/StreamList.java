package com.kea;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList implements Interface {
    /**
     * Declares 3 ArrayLists, 2 files and 3 scanners.
     */
    private ArrayList<Stream> streams;
    private File streamsFile;
    private Scanner fileSc;

    /**
     * StreamList object constructor with field initializers
     * @throws FileNotFoundException required exception when working with files
     */
    public StreamList() throws FileNotFoundException {
        streams = new ArrayList<>();
        streamsFile = new File("Streams.txt");
        fileSc = new Scanner(streamsFile);
        loadStreams();
    }

    /**
     * loadStreams method needs to be modified. New stream format consists of
     * 6 index' instead of the current 3. Check Streams.txt for examples
     */
    public void loadStreams() {
        while (fileSc.hasNext()) {
            Stream s = Session.convertStream(fileSc.nextLine());
            streams.add(s);
        }
    }
    /**
     * Sorts an ArrayList
     */
    public void sortArrayList(ArrayList list) {
        Collections.sort(list);
    }

    /**
     * Displays the list of streams
     */
    public void showList() {
        sortArrayList(streams);

        for (int i = 0; i < streams.size(); i++) {
            System.out.println(streams.get(i));
        }
    }

    public ArrayList<Stream> getStreams() {
        return streams;
    }
}