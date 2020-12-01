package com.kea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws IOException {
        //MobilePay mobilePay = new MobilePay();
        //mobilePay.test();

        RoomSjow rs = new RoomSjow();
        rs.run();
        //System.out.println(rs.profiles);
        //rs.createProfile();
        //rs.homeMenu();

        Stream s = new Stream(LocalDateTime.of(2020,11,5,16,0),"Once upon a time", Genre.JAVASCRIPT);
        //2020-11-5T16:00:01,Once upon a time,java
        //2020-11-5T16:00:01,Once upon a time,java
        //2020-11-03T17:00:00,Once upon a time,java)

        /*System.out.println("BASIC_ISO_DATE format:      " + (s.getStartTime()));
        System.out.println("ISO_LOCAL_DATE format:      " + (DateTimeFormatter.ISO_LOCAL_DATE).format(s.getStartTime()));
        System.out.println("ISO_LOCAL_TIME format:      " + (DateTimeFormatter.ISO_LOCAL_TIME).format(s.getStartTime()));

         */
    }
}
