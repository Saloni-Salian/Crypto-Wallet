package com.example.demo;

// note: this enumeration is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// This is a Java class named "StaffHandler" that provides functionality to create and manage staff members.
public class StaffHandler {

    private String username;
    private String password;

    private static StaffHandler instance = null;

    // Private constructor, which ensures that only one instance of the class is created using the Singleton design pattern.
    private StaffHandler(){
    }

    // Method called "getInstance" that returns the instance of the "StaffHandler" class. If an instance of the class does not exist, it creates a new one and returns it.
    public static StaffHandler getInstance() {
        if (instance == null) {
            instance = new StaffHandler();
        }
        return instance;
    }

    // Used to create a new staff member with the given username and password.
    public void createStaff(String username, String password){
        this.username=username;
        this.password=password;

    }
}




