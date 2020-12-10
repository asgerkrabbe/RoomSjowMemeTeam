package com.kea;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * interface for 3 methods in 3 different classes
 */
public interface Interface {

    void loadStreams() throws FileNotFoundException;

    void sortArrayList(ArrayList list);

    void showList();
}