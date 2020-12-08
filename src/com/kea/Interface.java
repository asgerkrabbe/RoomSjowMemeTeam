package com.kea;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface Interface {

    void loadStreams() throws FileNotFoundException;

    void sortArrayList(ArrayList list);

    void showList();
}