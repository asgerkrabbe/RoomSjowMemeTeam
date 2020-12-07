package com.kea;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class StreamTest {
    ArrayList<Stream> streams;
    File streamsFile = new File("Streams.txt");
    Scanner fileSc = new Scanner(streamsFile);

    StreamTest() throws FileNotFoundException {
    }


    @Test
    void watchStream() throws IOException {
        streamList();
        CompareLocalDateTimeExample();

        print();
    }

    LocalDateTime date1 = LocalDateTime.now();
    LocalDateTime date2 = LocalDateTime.of(2021, 2,17,22, 0, 0);

    public void CompareLocalDateTimeExample() {
        int diff = date1.compareTo(date2);
        if (diff > 0) {
            System.out.println(date1 + " is greater than " + date2);
        } else if (diff < 0) {
            System.out.println(date1 + " is less than " + date2);
        } else {
            System.out.println(date1 + " is equal to " + date2);
        }
    }

    public void print() {
        System.out.println(streams.get(4));
    }

    public void loadStreams() {
        while (fileSc.hasNext()) {
            String[] splittedLine = fileSc.nextLine().split(",");
            Stream s = Session.convertStream(splittedLine);
            streams.add(s);
        }
    }
    public void streamList() {
        streams = new ArrayList<>();
        loadStreams();
    }





}