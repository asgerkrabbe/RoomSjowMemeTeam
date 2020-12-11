package com.kea;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test for the Session class.
 */
class SessionTest {

    /**
     * Testing the capabilities of checkOverLap()
     * @throws FileNotFoundException in case file reader can't find the file
     */
    @Test
    void testCheckOverlap() throws FileNotFoundException {
        //arrange
        Stream stream1 = new Stream(LocalDateTime.of(2020,12,7,13,0),"title", Genre.JAVA,2,5);
        Stream stream2 = new Stream(LocalDateTime.of(2020,12,7,14,0),"title", Genre.JAVA,2,5);
        Stream stream3 = new Stream(LocalDateTime.of(2020,12,7,18,0),"title", Genre.JAVA,2,5);
        Stream stream4 = new Stream(LocalDateTime.of(2020,12,7,10,0),"title", Genre.JAVA,2,5);
        Stream stream5 = new Stream(LocalDateTime.of(2020,12,7,14,0),"title", Genre.JAVA,2,5);

        var streamsList = new ArrayList<Stream>();
        streamsList.add(stream2);
        streamsList.add(stream3);

        //act
        Session session = new Session(null,null);
        session.checkOverlap(stream1,streamsList);

        //assert
        assertTrue(session.checkOverlap(stream1,streamsList));
        assertFalse(session.checkOverlap(stream4,streamsList));
        assertTrue(session.checkOverlap(stream5,streamsList));
    }
}