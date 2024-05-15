package com.example.demo;

import java.util.Date;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// CoinPriceAlert is used to keep track of alerts the customer sets (for target cryptocurrency prices).
public class CoinPriceAlert {

	private float coinLevel;
	private Cryptocurrency crypto;

	// CoinPriceAlert constructor.
	public CoinPriceAlert(float level, Cryptocurrency crypto) {
		this.coinLevel = level;
		this.crypto = crypto;
	}

	// details for notification when coinLevel is reached by the real-time coin price.
	public void alertHit() {
		Date currentDate = new Date();
		String message = "The "+ this.coinLevel +" level for the "+ this.crypto.getName() +" has been reached";
		new Notification(message,currentDate);
	}

	// get the desired value of the coin which will trigger the alert.
	public float getLevel() {
		return this.coinLevel;
	}

	//get the cryptocurrency associated with the alert set by the customer.
	public Cryptocurrency getCrypto() {
		return this.crypto;
	}
}