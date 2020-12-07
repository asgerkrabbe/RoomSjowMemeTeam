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
        Pattern p = Pattern.compile(creditregex);
        Matcher m = p.matcher(creditregex);

        do {
            System.out.println("Enter a valid 16 diget creditcard number: ");
            double number = scan.nextDouble();
            if (m.matches()) {
                System.out.println("X amount have been charged.");
                creditboo = false;
            } else {
                System.out.println("Creditnumber is not valid. Please try again.");
                pay();
            }
        } while (creditboo) ;
    }

}