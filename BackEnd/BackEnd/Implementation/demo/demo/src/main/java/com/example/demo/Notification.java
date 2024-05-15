package com.example.demo;

import java.util.Date;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// Class named "Notification" to help notify customers of their alerts being hit.
public class Notification {

	private String message;
	private Date created_at;
	private Boolean read;

	// The constructor takes in a "message" of type String and a "created_at" of type Date, and initializes
	// the instance variables accordingly.
	public Notification(String message, Date created_at) {
		this.message = message;
		this.created_at = created_at;
		this.read = false;
	}

	public Boolean read() {
		// TODO - implement Notification.read
		throw new UnsupportedOperationException();
	}

	// The "markRead" method sets the value of the "read" instance variable to true.
	public void markRead() {
		this.read = true;
	}

	// The "getMessage" method returns the value of the "message" instance variable.
	public String getMessage() {
		return this.message;
	}

	// The "getCreatedDate" method returns the value of the "created_at" instance variable.
	public Date getCreatedDate() {
		return this.created_at;
	}

	// The "getRead" method returns the value of the "read" instance variable.
	public Boolean getRead() {
		return this.read;
	}
}