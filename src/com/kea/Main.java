package com.kea;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
/*Profile profile = new Profile();
profile.createProfile();*/

        //MobilePay mobilePay = new MobilePay();
        //mobilePay.test();

        RoomSjow rs = new RoomSjow();
        rs.run();
        System.out.println(rs.profiles);
    }
}
