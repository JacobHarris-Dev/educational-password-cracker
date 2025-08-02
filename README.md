# educational-password-cracker
A simple password cracker used for educational purposes that utilizes different password cracking methods to teach users about how passwords are cracked and promote password security 

---

## Disclaimer 
**This project is intended strictly for educational and ethical purposes.**  
Do **NOT** use this tool for unauthorized access or malicious activities.  
It is meant to raise awareness about password security and responsible programming practices.

--- 

## Features 
Users can select which attack method they would like to use, in addition to how they would like their password to be hashed before attempting to be cracked.

### Attack Methods Include:
1.) Brute Force Attack
2.) Dictionary Attack
3.) Rainbow Table Attack
4.) Phishing Attack

### Hash Methods Include:
1.) Basic Hashing using MD5 encryption
2.) Moderate Hashing using salting
3.) Advanced Hashing using peppering
4.) Robust Hashing using salting and pepering 

---

## How To Run:
### 1. Clone the Repository
git clone https://github.com/your-username/password-cracker.git
cd password-cracker

### 2. Execute Program
To run the program, use the command **.\run.ps1** in the terminal. This script compiles and launches the program automatically.

---

## Technologies Used 
- **Java 17** – Core programming language
- **Maven** – Project and dependency management
- **Java Security API**
  - `MessageDigest` – For cryptographic hash functions (MD5, SHA)
  - `SecureRandom` – For cryptographically strong salt generation
- **Base64 Encoding** – To safely encode binary salt values into readable format
- **BigInteger** – To convert byte arrays into hexadecimal strings
- **Recursion** – Used in password generation for brute-force attack
- **Backtracking Algorithm** – Efficiently generates and explores all possible password combinations
- **Password Hashing Techniques**
  - Raw hash
  - Salted hash
  - Peppered hash
  - Salted + peppered hash
- **Timing and Performance Metrics**
  - Uses `System.nanoTime()` to calculate total runtime
  - Tracks and prints progress updates every million attempts
- **Custom Charset Layers**
  - Multiple password banks, from numbers-only to full alphanumeric with symbols
  - Designed to simulate realistic brute-force escalation
- **Synchronized Access**
  - Ensures thread safety when hashing inside recursive methods

---

## Learning Objectives 
-Understand brute-force attack principles
-Learn how hash functions work and how they're used
-Practice clean, modular Java programming
-Develop awareness of ethical hacking boundaries

---

## Future Improvements
- **GUI Interface** Impliment user interface using React or similar medium
- **Quicker Brute Force** Optimize brute force algorithim to increase cracking speeds
- **Logging** Add detailed logs for attempts, timing, and results

--- 

## Author
**Jacob Harris**  
GitHub: [@JacobHarris-Dev](https://github.com/JacobHarris-Dev)  
Email: jah98589@uga.edu
