package com.passwordcracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.math.*;

/**
 * Turn password into Hash using java.security, then encode that
 * Hash in binary.
 */
public class Hasher {

    private static final String secretPepper = "dn38ws?k210dmsa!";
    private static String uniqueSalt = "";
    private static final MessageDigest md;

    // Use message Digest to hash code. Can use different hashing methods. Works
    // with
    // MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, and SHA 512.
    // Only created once to avoid slowdowns during recursion.
    static {
        try {
            md = MessageDigest.getInstance("MD5"); // swapable, currently using MD5
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not available", e);
        }
    }

    /**
     * Turns password into encoded String uses Message Digest that cannot be
     * converted
     * back into original string to protect password.
     * 
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        String hashedPassword = "";
        byte[] messageDigest;

        // Quickly turns string password into byte array
        // Hashing functions only operate on raw binary data, so we turn the password to
        // bytes
        synchronized (md) { // Use synchronizing to avoid method being called at same time
            md.reset();
            messageDigest = md.digest(password.getBytes());
        }

        // Converts hashed byte array into BitInteger, which converts byte array to hex
        // string.
        // This way, we can display our hashed password in a common format
        BigInteger bigInt = new BigInteger(1, messageDigest); // 1 means treat this number as unisgned

        // Converts hexadecimal into string representation
        hashedPassword = bigInt.toString(16); // 16 means base 16 string

        return hashedPassword;
    }

    /**
     * Adds random string to password before it is hashed,
     * then hashes password. Salt is public information and not hidden.
     * Unique for each password.
     * 
     * @param password
     * @return
     */
    public static String saltHashPassword(String password) {
        String saltedPassword = "";
        String saltedHashedPass = "";

        // Adds salt to end of password before it is hashed
        saltedPassword = password += uniqueSalt;

        // Hashes password
        saltedHashedPass = hashPassword(saltedPassword);

        return saltedHashedPass;
    }

    /**
     * Same string added to all passwords, but kept
     * hidden. Password is peppered, then hashed.
     * 
     * @param password
     * @return saltedPepperedPass
     */
    public static String pepperHashPassword(String password) {
        String pepperedPassword = "";
        String pepperedHashedPass = "";

        // Adds hidden pepper to beggining of password.
        pepperedPassword = secretPepper + password;

        // Hashes peppered password.
        pepperedHashedPass = hashPassword(pepperedPassword);

        return pepperedHashedPass;
    }

    public static String saltedPepperHashPass(String password) {
        String saltedPepperedPass = "";
        String saltedPepperedHashPass = "";

        // Adds salt and pepper to password
        saltedPepperedPass = secretPepper + password + uniqueSalt;

        // Hashes salted and peppered password
        saltedPepperedHashPass = hashPassword(saltedPepperedPass);

        return saltedPepperedHashPass;
    }

    /**
     * Creates random 16 byte array and converts
     * it to string to be added to the user's password.
     * 
     * @return generatedSalt
     */
    public static String generateSalt() {
        String generatedSalt;

        // Byte array with 16 empty Bytes/128 bits
        byte[] salt = new byte[16];

        // Fils salt array with random bytes using secure random number generator
        new SecureRandom().nextBytes(salt);

        // Turns byte array into Base64 text friendly String
        generatedSalt = Base64.getEncoder().encodeToString(salt);

        // Saves salt to variable with wider scope
        uniqueSalt = generatedSalt;

        System.out.println("Your unique salt: " + uniqueSalt);

        return generatedSalt;
    }

}