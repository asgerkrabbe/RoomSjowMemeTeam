package com.kea;

import java.util.Scanner;

/**
 * declarates 2 variables, a scanner and an empty bodied method
 */
abstract public class Payment {
    protected int phoneNumber;
    protected int cardNumber;
    private Scanner scan = new Scanner(System.in);

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    /**
     * Empty bodied method for later use in 2 sub classes
     */
    abstract void pay();
}
