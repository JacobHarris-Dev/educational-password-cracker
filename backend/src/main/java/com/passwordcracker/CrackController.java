package com.passwordcracker;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


/*
 * Handle API requests from frontend and return data/results
 */
@RestController
@RequestMapping("/api")

public class CrackController {

    /**
     * Sends live updates to frontend from Brute Force method
     * 
     * @param hash
     * @param length
     * @param hashType
     * @return
     */
    @GetMapping("/brute-force")
    public SseEmitter runBruteForceAttack(
            @RequestParam String hash,        // hashed password (e.g., MD5)
            @RequestParam(defaultValue = "4") int length,  // length of original password
            @RequestParam(defaultValue = "1") String hashType // 1: normal, 2: salted, etc.
    ) {
        SseEmitter emitter = new SseEmitter();

        new Thread(() -> {
            try {
                System.out.println("Starting attack");
                BruteForceAttack.attack(length, hash, hashType, message -> {
                    try {
                        System.out.println("Sending event...");
emitter.send(SseEmitter.event().data(message));
System.out.println("Event sent");
                        emitter.send(SseEmitter.event().data(message));
                        System.out.println("Sending event...");
emitter.send(SseEmitter.event().data(message));
System.out.println("Event sent");
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                });
                System.out.println("Done!");
                emitter.complete(); // done
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }

    // ... other endpoints like /dictionary, /status, etc.


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