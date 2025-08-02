package com.passwordcracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Searches password bank to find password matches
 */
public class DictionaryAttack {

    private static String hash;
    private static boolean found;
    
    /**
     * Initiates dictionary attack by traversing wordlist and comparing each word to the targeted password
     * 
     * @param target the password attempting to be guessed
     * @param hashType the method used to hash the password 
     */
    public static void attack(String target, String hashType) {

        hash = hashType;
        found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("wordlists/10k-most-common.txt"))) {

            String line; 
            int attemps = 0; 

            System.out.println("\nStarting Dictionary attack");
            System.out.println("Searching dicitonary...");

            // Loop that traverses through each index in wordlist and hashes it 
            while ((line = reader.readLine()) != null) {
                String guess = line.trim();
                String hash = hashGuess(guess);

                if (hash.equals(target)) { // If hashed index equals target, end loop
                    
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
     * 
     * @param guess the password to be hashed 
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

    /**
     * Returns true if password is found
     * 
     * @return found boolean expressing whether password is found or not
     */
    public static boolean getFound() {
        return found;
    }


}
