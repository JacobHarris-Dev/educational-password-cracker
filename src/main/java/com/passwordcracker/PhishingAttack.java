package com.passwordcracker;

import java.util.Scanner;

/**
 * Produces phishing email to get fake password from user. Used to demonstrate
 * real phishing on emails.
 */
public class PhishingAttack {

    private static Scanner scan;
    private static String phishedPass;

    /**
     * Initiates attack
     * 
     * @param userPassword the password entered by the user
     */
    public static void attack(String userPassword) {
        phishedPass = "";

        System.out.println("Attacking");
        printEmail();

        if (phishedPass.equals(userPassword)) {
            System.out.println("\nYou just gave out your password! You failed");
        } else {
            System.out.println("Good job not giving out your password! You passed");
        }
    }

    /**
     * Prints out fake email
     */
    public static void printEmail() {

        String response;

        scan = new Scanner(System.in);

        Ascii.generateEmail();

        System.out.println("\nWould you like to click on the link? y/n");

        response = scan.nextLine();

        // Prompts user to see if they give their password up
        while (true) {
            if (response.equals("y")) {
                generateWebsite();
                break;
            } else if (response.equals("n")) {
                System.out.println("Good idea! Exiting email...");
                break;
            } else {
                System.out.println("Invalid input");
                response = scan.nextLine(); // This was missing before
            }
        }

    }

    /**
     * Generates Fake Website
     */
    public static void generateWebsite() {

        String password;
        scan = new Scanner(System.in);

        Ascii.generateWebsite();

        System.out.println("");
        System.out.print("Please enter your password to sign in: ");

        password = scan.nextLine();

        phishedPass = password;

    }

}