package com.kea;

import java.io.IOException;
import java.util.Scanner;

/**
 * declarates 2 variables, a scanner and an empty bodied method
 */
abstract public class Payment {
    protected int phoneNumber;
    protected int cardNumber;
    Scanner scan = new Scanner(System.in);
    abstract void pay();
}
