package com.passwordcracker;

public class BruteForceAttack {

    //private static final char[] charset = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#?".toCharArray();

    public static long attack(int length, String target, String hashType, char[] charset) {
        String guess;
        String hashedGuess;
        
        long startTime = System.nanoTime(); // Start timer 

        for (char c1 : charset) {
            for (char c2 : charset) {
                for (char c3 : charset) {
                    for (char c4 : charset) {
                        for (char c5 : charset) {

                            guess = "" + c1 + c2 + c3 + c4 + c5;
                            hashedGuess = Hasher.hashPassword(guess);

                            if (hashedGuess.equals(target)) {
                                
                                long endTime = System.nanoTime(); // End timer
                                long duration = endTime - startTime;

                                System.out.println("Password Found: " + guess);
                                System.out.println("Duration: " + duration /  1_000_000_000.0 + " seconds");
                                return duration;

                            } else {
                                System.out.println(guess + ": failed");

                            } 
                        
                        }
                    }
                }
            }
        }


        return 0;


    }

}
