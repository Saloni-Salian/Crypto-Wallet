package com.example.demo;

import java.util.*;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// Compartment is used to create folder-like spaces for organisational purposes as well as asset management
public class Compartment
{
  private String name;
  private ArrayList<Cryptocurrency> cryptocurrencies = new ArrayList<Cryptocurrency>();
  
  // Compartment constructor.
  public Compartment(String name, ArrayList<Cryptocurrency> cryptocurrencies) {
    this.name = name;
    this.cryptocurrencies = cryptocurrencies;
  }
}
