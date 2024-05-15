package com.example.demo;

import java.util.Observable;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// the Cryptocurrency class represents a type of digital currency, which can be observed by other classes.
public class Cryptocurrency extends Observable {

	private String name;
	private float price;
	private float balance;
	private float twentyFourHourVolume;
	private float hourlyPercChange;
	private float dailyPercChange;
	private float weeklyPercChange;
	private String publicKey;
	private String privateKey;

	// Cryptocurrency constructor/
	public Cryptocurrency(String name, String publicKey, String privateKey, float balance) {
		this.name= name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.balance = balance;
	}

	// get the name of the Cryptocurrency.
	public String getName() {
		return this.name;
	}

	// get the price of the Cryptocurrency.
	public float getPrice() {
		return this.price;
	}

	// get the balance of the Cryptocurrency.
	public float getBalance() {
		return this.balance;
	}

	// get the 24-hour trading volume of the Cryptocurrency.
	public float getTwentyFourHourVolume() {
		return this.twentyFourHourVolume;
	}

	// get the hourly percentage change in the price of the Cryptocurrency.
	public float getHourlyPercChange() {
		return this.hourlyPercChange;
	}

	// get the daily percentage change in the price of the Cryptocurrency.
	public float getDailyPercChange() {
		return this.dailyPercChange;
	}

	// get the weekly percentage change in the price of the Cryptocurrency.
	public float getWeeklyPercChange() {
		return this.weeklyPercChange;
	}

	// get the public key associated with the Cryptocurrency.
	public String getPublicKey() {
		return this.publicKey;
	}

	// get the private key associated with the Cryptocurrency.
	public String getPrivateKey() {
		return this.privateKey;
	}

	// set the balance of the Cryptocurrency.
	public void setBalance(float balance) {
		this.balance = balance;
	}

	// set the 24-hour trading volume of the Cryptocurrency.
	public void setTwentyFourHourVolume(float twentyFourHourVolume) {
		this.twentyFourHourVolume = twentyFourHourVolume;
	}

	// set the hourly percentage change in the price of the Cryptocurrency.
	public void setHourlyPercChange(float hourlyPercChange) {
		this.hourlyPercChange = hourlyPercChange;
	}

	// set the daily percentage change in the price of the Cryptocurrency.
	public void setDailyPercChange(float dailyPercChange) {
		this.dailyPercChange = dailyPercChange;
	}

	// set the weekly percentage change in the price of the Cryptocurrency.
	public void setWeeklyPercChange(float weeklyPercChange) {
		this.weeklyPercChange = weeklyPercChange;
	}

	// set the price of the Cryptocurrency.
	public void setPrice(float price) {
		this.price = price;
	}
}