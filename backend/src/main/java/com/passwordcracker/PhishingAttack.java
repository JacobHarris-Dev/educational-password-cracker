package com.passwordcracker;

import java.util.Scanner;

public class PhishingAttack {

    private static Scanner scan;
    private static String phishedPass;

    public static void attack(String userPassword) {
        phishedPass = "";

        System.out.println("Attacking");
        printEmail();

        if(phishedPass.equals(userPassword)) {
            System.out.println("\nPassword matches real password!");
        } else {
            System.out.println("Good job not giving out your password!");
        }
    }

    public static void printEmail() {

        String response;

        scan = new Scanner(System.in);


        System.out.println("\nSubject: Important: Unusual Sign-In Attempt Detected on Your Amazon Account");
        System.out.println("From: Amazon Security <support@amzn-security-verification.com>");
        System.out.println("To: your.email@example.com\n");

        System.out.println("Dear Customer,");
        System.out.println(
                "We detected an unauthorized login attempt to your Amazon account from an unrecognized device in Russia on May 29, 2025 at 2:42 AM GMT.");
        System.out.println(
                "For your protection, we have temporarily locked your account until this activity is verified.");
        System.out.println("Please confirm your identity and restore access by clicking the link below:");
        System.out.println("http://amzn-account-restore.com/login");
        System.out.println(
                "If you do not verify your account within 24 hours, your Amazon account will be permanently suspended.");
        System.out.println("Thank you for choosing Amazon.");
        System.out.println("— Amazon Account Security Team");
        System.out.println("© 2025 Amazon.com, Inc. All rights reserved.");

        System.out.println("\nWould you like to click on the link? y/n");

        response = scan.nextLine();

        while (true) {
            if (response.equals("y")) {
                generateWebsite();
                break;
            } else if (response.equals("y")) {
                System.out.println("Good idea! Exiting email...");
                break;
            } else {
                System.out.println("Invalid input");
            }
        }

    }

    public static void generateWebsite() {

        String password;
         scan = new Scanner(System.in);


        System.out.println("Fake website:");
        System.out.print("Please enter your password so we know its you: "); 

        password = scan.nextLine();

        phishedPass = password;
        

    }

}
