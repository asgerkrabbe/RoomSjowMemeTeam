package com.kea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MobilePay extends Payment {



    /**
     * Checks if mobile number is valid and withdraws money
     */

    public void pay() {
        String mobileregex = "^\\d{8}$";
        boolean mobileboo = true;

        do {
            System.out.println("Enter a valid 8 digit phone number: ");
            String number = scan.nextLine();
            if (number.matches(mobileregex)) {
                System.out.println("5DKK has been charged.");
                mobileboo = false;
            } else {
                System.out.println("Mobile number is not valid. Please try again.");
                pay();

            }
        } while (mobileboo);

    }
}