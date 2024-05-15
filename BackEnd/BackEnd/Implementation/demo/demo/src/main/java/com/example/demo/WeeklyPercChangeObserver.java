package com.example.demo;

import java.util.*;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// This is a Java class implementing the "Observer" interface. In the "update" method, a fixed value of weekly percentage change
// is set to a float variable, and the "Cryptocurrency" object's weekly percentage change is updated with this value.
public class WeeklyPercChangeObserver implements Observer {

    @Override
	public void update(Observable o, Object obj) {
        //you would get the new weekly percChange either as a
        // parameter or a get function in the method. 
        //E.g. float newWeeklyChange = get from library/api
        float newWeeklyChange = 6.23F;

        Cryptocurrency temporary =  (Cryptocurrency) o;
        temporary.setWeeklyPercChange(newWeeklyChange);


    }
}