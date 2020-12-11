package com.kea;

public class MobilePay extends Payment {

    /**
     * Checks if mobile number format is valid and withdraws money with the help from a do while loop
     */

    public void pay() {
        String mobileregex = "^\\d{8}$";
        boolean mobileboo = true;

        do {
            System.out.println("Enter a valid 8 digit phone number: ");
            String number = scan.nextLine();
            if (number.matches(mobileregex)) {
                System.out.println("5 DKK has been charged.");
                mobileboo = false;
            } else {
                System.out.println("Mobile number is not valid. Please try again.");
                pay();

            }
        } while (mobileboo);
    }
}