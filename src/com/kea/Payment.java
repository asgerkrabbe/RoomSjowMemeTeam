package com.kea;

import java.io.IOException;
import java.util.Scanner;

/**
 * declarates 2 variables and a scanner
 */
abstract public class Payment {
    protected int phoneNumber;
    protected int cardNumber;
    Scanner scan = new Scanner(System.in);
    abstract void pay();


    /**
     * Runs 3 methods
     *
     * @throws IOException
     */

    public void runPay() throws IOException {
        paymentOptions();
    }

    /**
     * Prints pay menu and runs paying methods
     */
    protected void paymentOptions() {
        System.out.println("Choose your payment method: ");
        System.out.println("1 Pay with Creditcard");
        System.out.println("2 Pay with Mobilepay");
        int num = scan.nextInt();
        if (num == 1) {
            CardPayment cP = new CardPayment();
            cP.pay();
        } else if (num == 2) {
            MobilePay mP = new MobilePay();
            mP.pay();
        } else {
            System.out.println("Invalid input. Please try again.");
            paymentOptions();
        }
    }
}
