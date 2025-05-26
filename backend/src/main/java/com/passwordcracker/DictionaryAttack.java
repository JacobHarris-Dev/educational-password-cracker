package com.passwordcracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryAttack {

    private static String hash;
    private static boolean found;
    
    public static void attack(String target, String hashType) {

        hash = hashType;
        found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("wordlists/10k-most-common.txt"))) {

            String line; 
            int attemps = 0; 

            System.out.println("\nStarting Dictionary attack");
            System.out.println("Searching dicitonary...");


            while ((line = reader.readLine()) != null) {
                String guess = line.trim();
                String hash = hashGuess(guess);
                if (hash.equals(target)) {
                    
                    System.out.println("\nPassword found: " + guess);
                    System.out.println("Attemps taken: " + attemps);
                    found = true;
                    return;

                } else {
                    attemps++;
                }

            }
            
            System.out.println("\nPassword not contained in dictionary!");
            
            reader.close();

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }

     
    /**
     * Determs how to hash password based off hash type, then
     * returns correctly hashed password
     */
    private static String hashGuess(String guess) {
        String transPass = "";

        if (hash.equals("1")) {
            transPass = Hasher.hashPassword(guess);
        } else if (hash.equals("2")) {
            transPass = Hasher.saltHashPassword(guess);
        } else if (hash.equals("3")) {
            transPass = Hasher.pepperHashPassword(guess);
        } else if (hash.equals("4")) {
            transPass = Hasher.saltedPepperHashPass(guess);
        }

        return transPass;
    }

    public static boolean getFound() {
        return found;
    }


}
