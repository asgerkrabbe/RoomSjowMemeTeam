package com.kea;

import org.junit.jupiter.api.Test;

class StreamTest {

    @Test
    void example() {
        timeUntilStream();
    }

    public void timeUntilStream() {
        int time = 1232132;

        System.out.println(time / 24 / 60 + ":" + time / 60 % 24 + ':' + time % 60);


    }
}