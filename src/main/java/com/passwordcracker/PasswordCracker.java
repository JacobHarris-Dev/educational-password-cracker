package com.passwordcracker;

import java.util.Scanner;

/**
 * The driver of project, all methods run here 
 */
public class PasswordCracker {
    
    private static Scanner scan;
    private static String passToCrack;
    private static String attackChoice;
    private static boolean run;
    

    public static void main (String[] args) {

        attackChoice = "";
        run = true;

        System.out.println("=== Password Cracker ===");
        System.out.println("Version 0.1");
       
        scan = new Scanner(System.in);

        while (run) {

            System.out.println("\nChoose attack type (1, 2, or exit): ");
            System.out.println("1: Brute-Force Attack\n2: Dictionary Attack");
            System.out.println("exit: Stop program\n");
            System.out.print("Enter selection: ");

            attackChoice = scan.nextLine();

            if (attackChoice.equals("1")) { 

                System.out.print("\n--- Brute Force ---\nEnter password to crack: ");
                passToCrack = scan.nextLine(); 
                
                System.out.println("You entered: " + passToCrack);

            } else if (attackChoice.equals("2")) {

                System.out.print("\n--- Dictionary Attack ---\nEnter password to crack: ");
                passToCrack = scan.nextLine();

                System.out.println("You entered: " + passToCrack);

            } else if (attackChoice.equals("exit")) {

                System.out.println("\nExiting...");
                break;

            } else {

                System.out.println("Option not recognized, try again.");
            }
        }
    }
}

