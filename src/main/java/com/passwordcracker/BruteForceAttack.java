package com.passwordcracker;

public class BruteForceAttack {

    // Work way from top to bottom during brute force
    private static final char[] charset0 = "1234567890".toCharArray(); // numbers
    private static final char[] charset1 = "1234567890!?@#".toCharArray(); // numbers + special characters
    private static final char[] charset2 = "abcdefghijklmnopqrstuvxyz".toCharArray(); // letters - lowercase
    private static final char[] charset3 = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray(); // lowercase + numbers
    private static final char[] charset4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray(); // uppercase + numbers
    private static final char[] charset5 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // letters
                                                                                                                 // -
                                                                                                                 // lowercase/uppercase
    private static final char[] charset6 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
            .toCharArray(); // letters + numebrs
    private static final char[] charset7 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?@#"
            .toCharArray(); // letters + numbers + !?@_#

    private static int attempts;
    private static long startTime;
    private static String targetPass;
    private static long totalCombinations;
    private static int bankNumber;

    // Array to be traversed through during brute force attack
    private static char[][] passBank = { charset0, charset1, charset2, charset3, charset4, charset5, charset6,
            charset7 };


    /**
     * Main attack method
     * 
     * @param length
     * @param target
     * @param hashType
     * @return
     */
    public static long attack(int length, String target, String hashType) {
        attempts = 0;
        targetPass = target;

        startTime = System.nanoTime(); // Start timer

        // Begins recursion to crack password
        for (int i = 0; i < passBank.length; i++) {
            bankNumber = i;

            attempts = 0;

            totalCombinations = (long) Math.pow(passBank[i].length, length);

            System.out.println("searching password bank " + i + " with " + passBank[i].length + " characters");
            System.out.println("Total combonations: " + totalCombinations);
            recursiveAttack(passBank[i], 0, length, new StringBuilder());
            System.out.println("No matches found in password bank " + i);
            System.out.println(" ------------ ");
        }

        /**
         * for (int i = 0; i < passBank.length; i++) {
         * attempts = 0; // reset attempts
         * // Formula to figure out how many unique passwords there are in bank
         * long totalCombinations = (long) Math.pow(passBank[i].length, length);
         * 
         * System.out.println("searching password bank " + i + " with " +
         * passBank[i].length + " characters");
         * System.out.println("Total combonations: " + totalCombinations);
         * 
         * for (char c1 : passBank[i]) {
         * for (char c2 : passBank[i]) {
         * for (char c3 : passBank[i]) {
         * for (char c4 : passBank[i]) {
         * for (char c5 : passBank[i]) {
         * 
         * guess = "" + c1 + c2 + c3 + c4 + c5;
         * 
         * // transformPassword (guess)
         * hashedGuess = Hasher.hashPassword(guess);
         * 
         * if (hashedGuess.equals(target)) {
         * 
         * long endTime = System.nanoTime(); // Takes a snapshot of current run time
         * long duration = endTime - startTime;
         * 
         * System.out.println("Password Found: " + guess);
         * System.out.println("Duration: " + duration / 1_000_000_000.0 + " seconds");
         * return duration;
         * 
         * } else {
         * attempts++;
         * if (attempts % 100_000 == 0) {
         * long now = System.nanoTime();
         * double elapsed = (now - startTime) / 1_000_000_00;
         * double guessesPerSecond = attempts / elapsed;
         * long remaining = totalCombinations - attempts;
         * double estRemaining = remaining / guessesPerSecond;
         * 
         * System.out.printf("Bank " + i + "Progress: %.2f%% (%d/%d), Est. time left:
         * %.1fs\n",
         * (100.0 * attempts) / totalCombinations,
         * attempts, totalCombinations,
         * estRemaining);
         * 
         * }
         * 
         * }
         * 
         * }
         * }
         * }
         * }
         * }
         * 
         * System.out.println("No matches found in password bank " + i);
         * System.out.println(" ------------ ");
         * } // end of for loop
         * 
         */

        return 0;

    }

    private static void recursiveAttack(char[] charset, int depth, int desiredLength, StringBuilder currentGuess) {
        if (depth == desiredLength) {
            String guess = currentGuess.toString();
            String hashed = Hasher.hashPassword(guess);
            attempts++;

            if (hashed.equals(targetPass)) {
                System.out.println("Password found: " + guess);
                System.exit(0);
            } else {
                if (attempts % 100_000_0 == 0) {
                    long now = System.nanoTime();
                    //double elapsed = (now - startTime) / 1_000_000_00;
                  //double guessesPerSecond = attempts / elapsed;
                    long remaining = totalCombinations - attempts;
                    //double estRemaining = remaining / guessesPerSecond;

                    System.out.printf("Bank " + bankNumber + " Progress: %.2f%% (%d/%d)\n",
                            (100.0 * attempts) / totalCombinations,
                            attempts, totalCombinations);
                }
            }

            return;
        }

        for (char c : charset) {
            currentGuess.append(c);
            recursiveAttack(charset, depth + 1, desiredLength, currentGuess);
            currentGuess.deleteCharAt(currentGuess.length() - 1); // backtrack
        }

    }

   
    /**
     * Determs how to hash password based off hash type, then
     * returns correctly hashed password
     */
    private static String transformPassword(String hashType) {
        String transPass = "";

        return transPass;
    }

}
