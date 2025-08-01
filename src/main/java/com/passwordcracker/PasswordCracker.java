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
   
    public static String promptHash(String choice) {

        
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

            // Switch statement matches user input with list of options 
            switch (hashChoice) {
                case "1":
                    hashedPass = Hasher.hashPassword(choice);
                    System.out.println("Generated Basic Hashed Password: " + hashedPass);
                    return hashedPass;
                case "2":
                    Hasher.generateSalt();
                    hashedPass = Hasher.saltHashPassword(choice);
                    System.out.println("Generated Salted Hashed Password: " + hashedPass);
                    return hashedPass;
                case "3":
                    hashedPass = Hasher.pepperHashPassword(choice);
                    System.out.println("Generated Peppered Hashed Password: " + hashedPass);
                    return hashedPass;
                case "4":
                    Hasher.generateSalt();
                    hashedPass = Hasher.saltedPepperHashPass(choice);
                    System.out.println("Generated Salted Peppered Hashed Password: " + hashedPass);
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
            System.out.println("3: Dictionary + Brute Force Attack");
            System.out.println("exit: Stop program\n");
            System.out.print("Enter attack type: ");

            attackChoice = scan.nextLine();

            if (attackChoice.equals("1")) {
                System.out.print("\n--- Brute Force ---\nEnter password to crack (5 characters or less reccomended): ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                // Get the hashed password from promptHash
                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue; // User chose "exit" from hash menu, go back to main menu
                }

                // Call bruteForceAttack class and begin cracking 
                System.out.println("Attacking... ");

                //System.out.println("------Hash Choice: " + hashChoice);
                BruteForceAttack.attack(passToCrack.length(), hashedPass, hashChoice); // Edit charset to change selection

            } else if (attackChoice.equals("2")) {

                System.out.print("\n--- Dictionary Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue;
                }

                // Add dictionary attack logic here
                DictionaryAttack.attack(hashedPass, hashChoice);

            } else if (attackChoice.equals("3")) {
             System.out.print("\n--- Dictionary+BF Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue;
                }

                // Add dictionary attack logic here
                DictionaryAttack.attack(hashedPass, hashChoice);
                if (!DictionaryAttack.getFound()) {
                    BruteForceAttack.attack(passToCrack.length(), hashedPass, hashChoice);
                }
            
            } else if (attackChoice.equals("exit")) {
                System.out.println("\nExiting...");
               
                break;
            } else {
                System.out.println("Option not recognized, try again.");
            }
        }

        scan.close();

    }

    
}
