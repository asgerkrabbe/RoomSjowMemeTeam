package com.kea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    ArrayList<Stream> streams = new ArrayList<Stream>();
    ArrayList<String> showStreams = new ArrayList<>();
    File streamsFile = new File("Streams.txt");
    Scanner fileSc = new Scanner(streamsFile);
    Scanner sc = new Scanner(System.in);


    public StreamList() throws FileNotFoundException {
        //loadStreams();
    }

    /**
     * loadStreams method needs to be modified. New stream format consists of
     * 6 index' instead of the current 3. Check Streams.txt for examples
     */

    private void loadStreams() {
        streams = new ArrayList<>();
        while (fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");

            Genre genreEnum = Genre.valueOf(splittedLine[3]);
            Stream s = new Stream(splittedLine[0], LocalDateTime.parse(splittedLine[1]), genreEnum, 0, 5.0);
        }
    }

    public void add(Stream stream) {
        streams.add(stream);
    }

    public void showList() throws IOException {
        while (fileSc.hasNext()) {
            showStreams.add(fileSc.nextLine());
        }

        for (int i = 0; i < showStreams.size(); i++) {
            System.out.println(showStreams.get(i));
        }
        System.out.println("\nPress enter to return to start menu.");
        for(;;){
            try{
                int data = System.in.read();
                if(data == 10){
                    break;
                }
                System.out.print(data);
            }catch(IOException e ){}
        }


    }
    public void singUpFOrStream() throws IOException {

        for (int i = 0; i < showStreams.size(); i++) {
            System.out.println(showStreams.get(i));
        }
        System.out.println("Type stream you want to enter: ");
        String search = sc.nextLine();

        for (int i = 0; i < showStreams.size(); i++) {

            if (showStreams.contains(search)){
                System.out.println(showStreams);
            }

        }

    }
}
