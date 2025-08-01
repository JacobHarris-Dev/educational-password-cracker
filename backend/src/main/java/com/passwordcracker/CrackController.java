package com.passwordcracker;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // React dev server
public class CrackController {

    @GetMapping("/brute-force")
    public String bruteForceAttack() {
        return "Started brute force!";
    }
}