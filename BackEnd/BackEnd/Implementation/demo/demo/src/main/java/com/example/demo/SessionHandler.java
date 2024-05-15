package com.example.demo;

// note: this enumeration is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// This is a Java class named "SessionHandler" that provides functionality to handle user sessions.
public class SessionHandler {

    private static boolean sessionActive = false;
    
    // Returns the value of sessionActive.
    public static boolean isSessionActive() {
        return sessionActive;
    }

    // Starts a session so sets the boolean to true.
    public static void startSession() {
        sessionActive = true;
    }

    // Ends a session so sets the boolean to false.
    public static void endSession() {
        sessionActive = false;
    }
}