package com.example.demo;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// CreditCard class represents a customer's credit card details such as name, card number, CVV, and address.
public class CreditCard {
    private String name;
    private int cardNumber;
    private int CVV;
    private String address;

    // CreditCard constructor.
    public CreditCard(String name, int cardNumber, int CVV, String address) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.address = address;
    } 
}
