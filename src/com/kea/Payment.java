package com.kea;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * declarates 2 variables and a scanner
 */
public class Payment {
    protected int phoneNumber;
    protected int cardNumber;
    Scanner scan = new Scanner(System.in);

    /**
     * Runs 3 methods
     * @throws IOException
     */
    public void runPay() throws IOException {
        pay();
        payMobilepay();
        payCreditcard();
    }

    /**
     * Prints pay menu and runs paying methods
     */
    protected void pay() {
        System.out.println("Choose your payment method: ");
        System.out.println("1 Pay with Creditcard");
        System.out.println("2 Pay with Mobilepay");
        int num = scan.nextInt();
        if (num==1){
            payCreditcard();
        }
        else if (num==2){
            payMobilepay();
        }
        else {
            System.out.println("Invalid input. Please try again.");
            pay();
        }
    }

    /**
     * Checks credit card credentials and withdraws money
     */
    public void payCreditcard() {
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
                payCreditcard();
            }
        } while (creditboo) ;
    }

    /**
     * Checks if mobile number is valid and withdraws money
     */
    public void payMobilepay() {
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
                payMobilepay();

            }
        } while (mobileboo) ;

    }
}