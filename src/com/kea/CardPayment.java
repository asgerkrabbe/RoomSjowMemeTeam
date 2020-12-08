package com.kea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class CardPayment extends Payment {

    /**
     * Checks credit card credentials and withdraws money
     */
    public void pay() {
        String creditregex = "^\\d{16}$";
        boolean creditboo = true;

        do {
            System.out.println("Enter a valid 16 diget creditcard number: ");
            String number = scan.nextLine();
            if (number.matches(creditregex)) {
                System.out.println("X amount have been charged.");
                creditboo = false;
            } else {
                System.out.println("Creditnumber is not valid. Please try again.");
                pay();
            }
        } while (creditboo) ;
    }

}