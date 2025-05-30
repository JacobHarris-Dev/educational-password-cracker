package com.passwordcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Only purpose is to launch spring boot appliction, entry point 
 * to backend. 
 */
@SpringBootApplication
public class PasswordCrackerApp {
    public static void main(String[] args) {
        SpringApplication.run(PasswordCrackerApp.class, args);
    }
}
