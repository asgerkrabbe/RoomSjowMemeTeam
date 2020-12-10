package com.kea;

public class CardPayment extends Payment {

    /**
     * Checks credit card credentials format, and withdraws money
     */
    public void pay() {
        String creditregex = "^\\d{16}$";
        boolean creditboo = true;

        do {
            System.out.println("Enter a valid 16 digit credit card number: ");
            String number = scan.nextLine();
            if (number.matches(creditregex)) {
                System.out.println("5 DKK has been charged.");
                creditboo = false;
            } else {
                System.out.println("Credit number is not valid. Please try again.");
                pay();
            }
        } while (creditboo) ;
    }

}