package com.kea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomSjow {

    ArrayList<Stream> streams = new ArrayList<Stream>();
    ArrayList<Profile> profiles = new ArrayList<>();

    public void run() throws FileNotFoundException {
        loadProfiles();
        loadStreams();
    }

    private void loadStreams() throws FileNotFoundException {
        profiles = new ArrayList<>();
        File profilesFile = new File("Profiles.txt");
        Scanner fileSc = new Scanner(profilesFile);
        while (fileSc.hasNext()){
            String[] splittedLine = fileSc.nextLine().split(",");
            Profile p = new Profile(splittedLine[0],splittedLine[1],splittedLine[2]);
            profiles.add(p);
        }

    }

    private static void loadProfiles() {
    }

}