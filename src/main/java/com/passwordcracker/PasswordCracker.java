package com.passwordcracker;

import java.util.Scanner;

/**
 * The driver of project, all methods run here
 */
public class PasswordCracker {

    private static Scanner scan;
    private static String passToCrack;
    private static String attackChoice;
    private static String hashChoice;
    private static boolean run;
    private static String hashedPass;
    private static final char[] charset0 = "1234567890".toCharArray(); // numbers
    private static final char[] charset1 = "abcdefghijklmnopqrstuvxyz".toCharArray(); // letters - lowercase
    private static final char[] charset2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // letters - lowercase/uppercase
    private static final char[] charset3 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray(); // letters + numbers
    private static final char[] charset4 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?@#_".toCharArray(); // letters + numbers + !?@_#

    public static String promptHash(String choice) {

        Scanner scan = new Scanner(System.in);
        String hashChoice;
        String hashedPass = "";

        while (true) {
            System.out.println("\nChoose level of hashing (1, 2, 3, 4, or exit): ");
            System.out.println("1: Basic - Hash using MD5");
            System.out.println("2: Moderate - Hash using password salting");
            System.out.println("3: Advanced - Hash using password peppering");
            System.out.println("4: Robust - Hash using password salting and peppering");
            System.out.println("exit: Return to attack selection");
            System.out.print("Enter hash level: ");

            hashChoice = scan.nextLine();

            switch (hashChoice) {
                case "1":
                    hashedPass = Hasher.hashPassword(choice);
                    System.out.println("Generated Basic Hashed Password: " + hashedPass);
                    return hashedPass;
                case "2":
                    hashedPass = Hasher.saltHashPassword(choice);
                    System.out.println("Generated Salted Hashed Password: " + hashedPass);
                    return hashedPass;
                case "3":
                    hashedPass = Hasher.pepperHashPassword(choice);
                    System.out.println("Generated Peppered Hashed Password: " + hashedPass);
                    return hashedPass;
                case "4":
                    hashedPass = Hasher.saltedPepperHashPass(choice);
                    System.out.println("Generated Salt N Peppa Hashed Password: " + hashedPass);
                    return hashedPass;
                case "exit":
                    System.out.println("Returning to attack selection...");
                    return null;
                default:
                    System.out.println("Command not recognized, try again.");
            }
        }
    }

    public static void main(String[] args) {

        attackChoice = "";
        run = true;

        System.out.println("=== Password Cracker ===");
        System.out.println("Version 0.1");

        scan = new Scanner(System.in);
        while (run) {
            System.out.println("\nChoose attack type (1, 2, or exit): ");
            System.out.println("1: Brute-Force Attack\n2: Dictionary Attack");
            System.out.println("exit: Stop program\n");
            System.out.print("Enter attack type: ");

            attackChoice = scan.nextLine();

            if (attackChoice.equals("1")) {
                System.out.print("\n--- Brute Force ---\nEnter password to crack (5 characters): ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                // Get the hashed password from promptHash
                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue; // User chose "exit" from hash menu, go back to main menu
                }

                // Call bruteForceAttack class and begin cracking 
                System.out.println("Attacking... ");
                BruteForceAttack.attack(5, hashedPass, "Basic", charset3); // Edit charset to change selection

            } else if (attackChoice.equals("2")) {
                System.out.print("\n--- Dictionary Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue;
                }

                // Add dictionary attack logic here

            } else if (attackChoice.equals("exit")) {
                System.out.println("\nExiting...");
                break;
            } else {
                System.out.println("Option not recognized, try again.");
            }
        }

    }
}
