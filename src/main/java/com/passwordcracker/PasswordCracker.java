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

    /**
     * Prompts user to select a method to hash their password.
     * Repeats until user selects valid choice.
     * 
     * @param choice the hash selected by the user 
     * @return hashedPass the newly hashed password 
     */
    public static String promptHash(String choice) {


        String hashedPass = "";

        while (true) {
            Ascii.generateHashPrompt();

            hashChoice = scan.nextLine();

            // Switch statement matches user input with list of options 
            switch (hashChoice) {
                case "1":
                    hashedPass = Hasher.hashPassword(choice);
                    System.out.println("\nGenerated Basic Hashed Password: " + hashedPass);
                    return hashedPass;
                case "2":
                    Hasher.generateSalt();
                    hashedPass = Hasher.saltHashPassword(choice);
                    System.out.println("\nGenerated Salted Hashed Password: " + hashedPass);
                    return hashedPass;
                case "3":
                    hashedPass = Hasher.pepperHashPassword(choice);
                    System.out.println("\nGenerated Peppered Hashed Password: " + hashedPass);
                    return hashedPass;
                case "4":
                    Hasher.generateSalt();
                    hashedPass = Hasher.saltedPepperHashPass(choice);
                    System.out.println("\nGenerated Salted Peppered Hashed Password: " + hashedPass);
                    return hashedPass;
                case "exit":
                    System.out.println("\nReturning to attack selection...");
                    return null;
                default:
                    System.out.println("\nCommand not recognized, try again.");
            }
        }
    }

    /**
     * Main method 
     * 
     * @param args
     */   
    public static void main(String[] args) {

        attackChoice = "";
        run = true;

        //Prints out welcome banner 
        Ascii.generateWelcome();

        scan = new Scanner(System.in);

        // Programs loops until user choose to exit 
        while (run) {
            Ascii.generateCrackPrompt();

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

                // Call dicitonary attack 
                DictionaryAttack.attack(hashedPass, hashChoice);

            } else if (attackChoice.equals("3")) {
                   System.out.print("\n--- Rainbow Table Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue;
                }

                // Call rainbow table attack 
                RainbowTableAttack.attack(hashedPass, hashChoice);

            
            } else if (attackChoice.equals("4")) {
             System.out.print("\n--- Dictionary+BF Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);

                hashedPass = promptHash(passToCrack);
                if (hashedPass == null) {
                    continue;
                }

                // Call dictionary attack 
                DictionaryAttack.attack(hashedPass, hashChoice);
                if (!DictionaryAttack.getFound()) {
                    BruteForceAttack.attack(passToCrack.length(), hashedPass, hashChoice);
                }

            // Initiate phishing demo
            } else if (attackChoice.equals("5")) {
                System.out.print("\n--- Phishing Demo ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();
                System.out.println("You entered: " + passToCrack);
                System.out.println("Because we are trying to find pass directly, password will not be hashed.");
                
                
                // Call phishing attack 
                PhishingAttack.attack(passToCrack);

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