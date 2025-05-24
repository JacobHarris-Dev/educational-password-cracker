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

    // Array to be traversed through during brute force attack
    private static char[][] passBank = { charset0, charset1, charset2, charset3, charset4, charset5, charset6,
            charset7 };

    private static int attempts;
    private static long startTime;
    private static String targetPass;
    private static long totalCombinations;
    private static int bankNumber;
    private static boolean found;
    private static String hash;

    

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
        found = false;
        hash = hashType; // Saves to wider scope variabe

        startTime = System.nanoTime(); // Start timer

        // Begins recursion to crack password
        for (int i = 0; i < passBank.length && !found; i++) {
            bankNumber = i;

            attempts = 0;

            totalCombinations = (long) Math.pow(passBank[i].length, length);

            System.out.println("searching password bank " + i + " with " + passBank[i].length + " characters");
            System.out.println("Total combonations: " + totalCombinations);
            recursiveAttack(passBank[i], 0, length, new StringBuilder());
            if (!found) {
                System.out.println("No matches found in password bank " + i);
                System.out.println(" ------------ ");
            }
        }

      
        return 0;

    }

    /**
     * Method for recursive password generation
     * 
     * @param charset
     * @param depth
     * @param desiredLength
     * @param currentGuess
     */
    private static void recursiveAttack(char[] charset, int depth, int desiredLength, StringBuilder currentGuess) {
        if (found) {
            return;
        }

        if (depth == desiredLength) {
            String guess = currentGuess.toString();
            String hashed = hashGuess(guess);
            attempts++;

            if (hashed.equals(targetPass)) {
                long endTime = System.nanoTime();

                System.out.println("Password found: " + guess);
                //System.exit(0);
                found = true;
                System.out.println("Time taken: " + (endTime - startTime) / 1_000_000_000.0  + " seconds");
                return;
            } else {
                if (attempts % 100_000_0 == 0) {
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

}
