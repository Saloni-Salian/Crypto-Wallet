package com.example.demo;

import java.util.*;

// note: this enumeration is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// PriceObserver class to observe the prices of supported cryptocurrencies.
public class PriceObserver implements Observer {

	// sets a fixed value for the new price of a cryptocurrency and updates the price of the object with this value.
	// It then checks to see whether any alerts have been triggered.
	@Override
	public void update(Observable o, Object obj) {
        float newPrice = 6.23F;
        Cryptocurrency temporary =  (Cryptocurrency) o;
        temporary.setPrice(newPrice);
		checkForPriceAlert(o);
    }

	// Retrieves all alerts from a customer iterating over them.
	// If any alerts match the cryptocurrency and the alert's price level, the alertHit method is called.
	public void checkForPriceAlert(Observable o) {
		//get all the alerts from a customer
		ArrayList<CoinPriceAlert> alerts = null; // = Customer.getAlerts();

		Cryptocurrency temporary =  (Cryptocurrency) o;
		for (CoinPriceAlert alert : alerts)
		{
			if (alert.getCrypto() == temporary && alert.getLevel() == temporary.getPrice())
			{
				alert.alertHit();
			}
		}
	}
}