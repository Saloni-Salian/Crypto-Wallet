package com.example.demo;

import java.util.*;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// This is a Java class implementing the "Observer" interface. In the "update" method, a fixed value of daily percentage change
// is set to a float variable, and the "Cryptocurrency" object's daily percentage change is updated with this value.
public class TwentyFourHourVolumeObserver implements Observer {

	@Override
	public void update(Observable o, Object obj) {
        //you would get the new 24H percChange either as a
        // parameter or a get function in the method. 
        //E.g. float newWeeklyChange = get from library/api
        float new24HChange = 6.23F;
        Cryptocurrency temporary =  (Cryptocurrency) o;
        temporary.setTwentyFourHourVolume(new24HChange);
    }

}