package com.kea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    /**
     * Sæt modifiers
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

    public StreamList() throws FileNotFoundException {
        //loadStreams();
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * loadStreams method needs to be modified. New stream format consists of
     * 6 index' instead of the current 3. Check Streams.txt for examples
     */

    public void arrayListIndexing() {
        /**
         * Indexing arrays before usage in methods
         */
    }

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
    }

    public void add(Stream stream) {
        streams.add(stream);
    }

    public void sortArrayList() {
        Collections.sort(showStreams);
    }

    public void showList() {
        while (fileSc.hasNext()) {
            showStreams.add(fileSc.nextLine());
        }
        sortArrayList();

        for (int i = 0; i < showStreams.size(); i++) {
            System.out.println(showStreams.get(i));
        }
        System.out.println("\nPress enter to return to start menu.");
        for (; ; ) {
            try {
                int data = System.in.read();
                if (data == 10) {
                    break;
                }
                System.out.print(data);
            } catch (IOException e) {
            }
            break;

        }
    }

    public void signUpForStream() throws IOException {
        FileWriter fileWriter = new FileWriter("MyStreams.txt", true);
        setCurrentUser("Asger");

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
                fileWriter.write("\n" + currentUser + "," + showStreams.get(i));
                fileWriter.close();
            }
        }
    }

public void myStreams() {
    setCurrentUser("Tobias");
    while (myStreamsSc.hasNext()){
        myStreams.add(myStreamsSc.nextLine());
    }

    for (int i = 0; i < myStreams.size(); i++) {
        if (myStreams.get(i).contains(currentUser)){
            System.out.println(myStreams.get(i));
        }
    }
}

}

