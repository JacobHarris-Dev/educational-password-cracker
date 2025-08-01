package com.passwordcracker;

import org.springframework.web.bind.annotation.*;

/*
 * Handle API requests from frontend and return data/results
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // React dev server
public class CrackController {

    @GetMapping("/brute-force")
    public String runBruteForceAttack() {
        return "Started brute force!";
    }

      @PostMapping("/rainbow")
    public String runRainbowAttack(@RequestBody String hash) {
        // Use the hash input to simulate a rainbow table lookup
        return "Result of rainbow table attack on: " + hash;
    }

      @GetMapping("/dictionary")
    public String runDictionaryAttack() {
        // Call your existing dictionary attack logic here
        return "Dictionary attack completed!";
    }

        @GetMapping("/status")
    public String status() {
        return "Cracker is running!";
    }

}