package com.passwordcracker;

/**
 * Used to generate ASCII images
 */
public class Ascii {

        /**
         * Prints welcome banner seen at start of code
         */
        public static void generateWelcome() {
                System.out.println(
                                " _____    _                 _   _                   _  ______                                   _   _____                _              ");
                System.out.println(
                                "|  ___|  | |               | | (_)                 | | | ___ \\                                 | | /  __ \\              | |             ");
                System.out.println(
                                "| |__  __| |_   _  ___ __ _| |_ _  ___  _ __   __ _| | | |_/ /_ _ ___ _____      _____  _ __ __| | | /  \\/_ __ __ _  ___| | _____ _ __  ");
                System.out.println(
                                "|  __|/ _` | | | |/ __/ _` | __| |/ _ \\| '_ \\ / _` | | |  __/ _` / __/ __\\ \\ /\\ / / _ \\| '__/ _` | | |   | '__/ _` |/ __| |/ / _ \\ '__| ");
                System.out.println(
                                "| |__| (_| | |_| | (_| (_| | |_| | (_) | | | | (_| | | | | | (_| \\__ \\__ \\\\ V  V / (_) | | | (_| | | \\__/\\ | | (_| | (__|   <  __/ |    ");
                System.out.println(
                                "\\____/\\__,_|\\__,_|\\___\\__,_|\\__|_|\\___/|_| |_|\\__,_|_| \\_|  \\__,_|___/___/ \\_/\\_/ \\___/|_|  \\__,_|  \\____/_|  \\__,_|\\___|_|\\_\\___|_|    ");
                System.out.println(
                                "                                                                                                                                         ");
                System.out.println("        ");

                System.out.println("Version 1.0");
                System.out.println("-By Jacob Harris");
        }

         /**
         * Prints hash selection box
         */
        public static void generateHashPrompt() {
                System.out.println();
                System.out.println("+-------------------------------------------------------------+");
                System.out.println("|                  Choose level of hashing                   |");
                System.out.println("|               (1, 2, 3, 4, or exit)                        |");
                System.out.println("+-------------------------------------------------------------+");
                System.out.println("| 1: Basic     - Hash using MD5                              |");
                System.out.println("| 2: Moderate  - Hash using password salting                 |");
                System.out.println("| 3: Advanced  - Hash using password peppering               |");
                System.out.println("| 4: Robust    - Hash using password salting and peppering   |");
                System.out.println("| exit        - Return to attack selection                   |");
                System.out.println("+-------------------------------------------------------------+");
                System.out.print("Enter hash level: ");
        }

        /**
         * Prints attack selection box
         */
        public static void generateCrackPrompt() {
                System.out.println();
                System.out.println("+-------------------------------------------------------------+");
                System.out.println("|                   Choose attack type                        |");
                System.out.println("|              (1, 2, 3, 4, 5, or exit)                       |");
                System.out.println("+-------------------------------------------------------------+");
                System.out.println("| 1: Brute-Force Attack                                       |");
                System.out.println("| 2: Dictionary Attack                                        |");
                System.out.println("| 3: Rainbow Table Attack (Not salt friendly)                 |");
                System.out.println("| 4: Dictionary + Brute Force Attack                          |");
                System.out.println("| 5: Phishing demonstration                                   |");
                System.out.println("| exit - Stop program                                         |");
                System.out.println("+-------------------------------------------------------------+");
                System.out.print("Enter attack type: ");
        }

        /**
         * Prints demo email used in phishing class 
         */
        public static void generateEmail() {
                System.out.println();
                System.out.println("+---------------------------------------------------------------------+");
                System.out.println("| Subject: Important: Unusual Sign-In Attempt Detected               |");
                System.out.println("| From:    Amazon Security <support@amzn-security-verification.com> |");
                System.out.println("| To:      your.email@example.com                                    |");
                System.out.println("+---------------------------------------------------------------------+");
                System.out.println();
                System.out.println("Dear Customer,");
                System.out.println();
                System.out.println("We detected an unauthorized login attempt to your Amazon account");
                System.out.println("from an unrecognized device in Russia on May 29, 2025 at 2:42 AM GMT.");
                System.out.println();
                System.out.println("For your protection, your account has been temporarily locked until");
                System.out.println("you verify this activity.");
                System.out.println();
                System.out.println("Please confirm your identity and restore access by clicking below:");
                System.out.println("http://amzn-account-restore.com/login");
                System.out.println();
                System.out.println("If you do not verify your account within 24 hours, your Amazon");
                System.out.println("account may be permanently suspended.");
                System.out.println();
                System.out.println("Thank you for choosing Amazon.");
                System.out.println();
                System.out.println("— Amazon Account Security Team");
                System.out.println("© 2025 Amazon.com, Inc. All rights reserved.");
        }

        /**
         * Prints fake website used for phishing demo
         */
        public static void generateWebsite() {
                System.out.println();
                System.out.println("************************************************************");
                System.out.println("*                     Amazon Login Page                    *");
                System.out.println("************************************************************");
                System.out.println("*                                                          *");
                System.out.println("*  Please log in to continue to your Amazon account.       *");
                System.out.println("*                                                          *");
                System.out.println("*  Email:    _________________________________              *");
                System.out.println("*                                                          *");
                System.out.println("*  Password: _________________________________              *");
                System.out.println("*                                                          *");
                System.out.println("*  [ Login ]                                               *");
                System.out.println("*                                                          *");
                System.out.println("*  Forgot your password?                                   *");
                System.out.println("*  Visit: http://amzn-account-restore.com/help             *");
                System.out.println("*                                                          *");
                System.out.println("************************************************************");
                System.out.println("*        © 2025 Amazon.com, Inc. All rights reserved.      *");
                System.out.println("************************************************************");
        }
}
