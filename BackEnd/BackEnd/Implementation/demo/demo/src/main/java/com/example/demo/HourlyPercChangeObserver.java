package com.example.demo;

import java.util.*;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// This is a Java class implementing the "Observer" interface. In the "update" method, a fixed value of hourly percentage change
// is set to a float variable, and the "Cryptocurrency" object's hourly percentage change is updated with this value.
public class HourlyPercChangeObserver implements Observer {

	@Override
	public void update(Observable o, Object obj) {
        float hourlyPercChange = 6.23F;
        Cryptocurrency temporary =  (Cryptocurrency) o;
        temporary.setHourlyPercChange(hourlyPercChange);
    }
}