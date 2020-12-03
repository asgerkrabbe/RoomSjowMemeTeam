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
        ArrayList<String>streams = new ArrayList<String>();
        File streamsFile = new File("Streams.txt");
        Scanner fileSc = new Scanner(streamsFile);
        while (fileSc.hasNext()){
            streams.add(fileSc.next());
            System.out.println(streams);
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
