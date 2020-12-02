package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Streamlist {


    public Streamlist() throws FileNotFoundException {
        loadStreams();
    }

    ArrayList<Stream> streams = new ArrayList<Stream>();
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
}