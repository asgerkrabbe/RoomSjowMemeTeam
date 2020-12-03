package com.kea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    ArrayList<Stream> streams = new ArrayList<Stream>();


    public StreamList() throws FileNotFoundException {
        loadStreams();

    }

    /**
     *
     * loadStreams method needs to be modified. New stream format consists of
     * 6 index' instead of the current 3. Check Streams.txt for examples
     */

    private void loadStreams() throws FileNotFoundException {
        streams = new ArrayList<>();
        File streamsFile = new File("Streams.txt");
        Scanner fileSc = new Scanner(streamsFile);
        while(fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");
            Genre genreEnum = Genre.valueOf(splittedLine[3]);
            Stream s = new Stream(splittedLine[0], LocalDateTime.parse(splittedLine[1]), genreEnum,0,5.0);
        }
    }


    public void add(Stream stream){
        streams.add(stream);
     }

    public void showList() {
        Collections.sort(streams);
        for(int i=0; i<streams.size(); i++){
            System.out.println(streams.get(i));
        }
    }
}
