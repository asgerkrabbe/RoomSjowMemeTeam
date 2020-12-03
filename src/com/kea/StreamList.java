package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StreamList {
    ArrayList<Stream> streams = new ArrayList<Stream>();


    public StreamList() throws FileNotFoundException {
        loadStreams();
    }

    private void loadStreams() throws FileNotFoundException {
        streams = new ArrayList<>();
        File streamsFile = new File("Streams.txt");
        Scanner fileSc = new Scanner(streamsFile);
        while(fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");
            Genre genreEnum = Genre.valueOf(splittedLine[2]);
            Stream s = new Stream(LocalDateTime.parse(splittedLine[0]),splittedLine[1],genreEnum);
        }
    }
    private void writeFile(){

    };

    public void add(Stream stream){
        streams.add(stream);
        writeFile();
     };

    public void showList() {
        Collections.sort(streams);
        for(int i=0; i<streams.size(); i++){
            System.out.println(streams.get(i));
        }


    }

}