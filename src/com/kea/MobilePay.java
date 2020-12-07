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
        Pattern p = Pattern.compile(mobileregex);
        Matcher m = p.matcher(mobileregex);

        do {
            System.out.println("Enter a valid 8 digit phonenumber: ");
            double number = scan.nextDouble();
            if (m.matches()) {
                System.out.println("X amount have been charged.");
            } else {
                System.out.println("Mobilenumber is not valid. Please try again.");
                pay();

            }
        } while (mobileboo) ;

    }
}