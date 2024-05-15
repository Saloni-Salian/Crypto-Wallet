package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// CoinPriceAlert is used to keep track of alerts the customer sets (for target cryptocurrency prices).
class Consultant {
    private int cID;
    private String cName;
    private Calendar startingHour;
    private Calendar endingHour;
    private ArrayList<ConsultancySession> sessions;
    private ArrayList<Calendar> availableTimes;

    // Consultant constructor.
    public Consultant(int cID, String cName, Calendar startingHour, Calendar endingHour,ArrayList<ConsultancySession> sessions, ArrayList<Calendar> availableTimes) 
    {
        this.cID = cID;
        this.cName = cName;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.sessions = sessions;
        this.availableTimes = availableTimes;
    }
    
    // get the ID of the consultant.
    public int getConsultantID() {
        return cID;
    }

    // get the name of the consultant.
    public String getConsultantName() {
        return cName;
    }

    // get the consultant's daily starting hour for the services they provide.
    public Calendar getStartingHour() {
        return startingHour;
    }

    // get the consultant's daily ending hour for the services they provide (time they finish).
    public Calendar getEndingHour() {
        return startingHour;
    }

    // get the list of consultancy sessions booked for today with this specific consultant 
    // (can only book sessions for the current day).
    public ArrayList<ConsultancySession> getConsultancySessions() {
        return sessions;
    }

    // get the list of times that are available based on the consultant's working hours
    // and booked slots.
    public ArrayList<Calendar> getAvailableTimes() {
        return availableTimes;
    }

    // add a consultancy session to the consultant's existing list of booked sessions.
    public void addConsultingSession(ConsultancySession consultancySession) {
        sessions.add(consultancySession);
    }

    // remove a consultancy session to the consultant's existing list of booked sessions.
    public void cancelConsultancySession(ConsultancySession consultancySession) {
        sessions.remove(consultancySession);
    }

}