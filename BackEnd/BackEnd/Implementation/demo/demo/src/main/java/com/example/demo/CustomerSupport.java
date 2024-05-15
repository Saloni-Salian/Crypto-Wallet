package com.example.demo;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// CustomerSupport class to handle customer queries and use our system.
public class CustomerSupport extends Staff {// done by haresh
    CustomerQuery customerQuery;

    public CustomerSupport(int staffID) {
        // Call the constructor of the superclass Staff
        super(staffID);
    }
    
    // Method to view all customer queries
    public void viewCustomerQueries(){
        // Code to fetch and return all the customer queries
    }
    
    // Method to handle a customer query
    public void handleCustomerQuery(CustomerQuery ticket){
        // Code to handle the customer query passed as the parameter
    }
    
    // Method to raise a concern about a customer query
    public void raiseConcern(CustomerQuery ticket){
        // Code to raise a concern about the customer query passed as the parameter
    }
    
}

