package com.passwordcracker;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * While real rainbow tables use chains and reduce functions,
 * they take up a large amount of space. For this demo,
 * the code uses a hashmap of dictionary passwords and
 * their hashed equivalents
 */
public class RainbowTableAttack {
   

    private static HashMap<String, String> rainbowTable; // Generics
    private static String hash;
    private static boolean found;

    public static void attack(String target, String hashType) {

        hash = hashType;
        found = false;
        int attemps = 0;

        hashMessage();

        System.out.println("Building rainbow table...");
        buildTable();
        System.out.println("Done!");

        System.out.println("\nAttacking...");

        for (String hash : rainbowTable.keySet()) {
            if (hash.equals(target)) {
                 System.out.println("Hash Found: " + hash + "\nMatching table password: " + rainbowTable.get(hash));
                 
                 System.out.println("Attempts: " + attemps);
                 found = true;
                 break;
            } else {
                attemps++;
            }
        }
        if (!found) {
            System.out.println("Hash not contained in rainbow table!");
        }
    }

    /**
     * Constructs hashmap, only needs to be done once
     */
    public static void buildTable() {


        String line;

        rainbowTable = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("wordlists/10k-most-common.txt"))) {

            // rainbowTable.put(hashedPass, password)

            while ((line = reader.readLine()) != null) {
                String guess = line.trim();
                String hash = Hasher.hashPassword(guess);

                rainbowTable.put(hash, guess); // (key, value)

            }

            reader.close();

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }

    /**
     * Determs how to hash password based off hash type, then
     * returns correctly hashed password
     */
    private static void hashMessage() {

        // Rainbow Tables do not store salted or peppers
        if (hash.equals("1")) {
            System.out.println("\nRainbow table is effective against non salted passwords.");
        } else if (hash.equals("2")) {
            System.out.println("\nWarning: rainbow table innefective against salted passwords!");
           
        } else if (hash.equals("3")) {
            System.out.println("\nWarning: rainbow table innefective against peppered passwords!");
            
        } else if (hash.equals("4")) {
            System.out.println("\nWarning: rainbow table innefective against salted/pepperedexi passwords!");
            

        }

    }

}