package com.passwordcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * Only purpose is to launch spring boot appliction, entry point 
 * to backend. 
 */
@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")

public class PasswordCrackerApp {
    public static void main(String[] args) {
        SpringApplication.run(PasswordCrackerApp.class, args);
    }
}
