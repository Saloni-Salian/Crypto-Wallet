/*CHANGES:
Changed the crypto to a string. I dont think we need the crypto it's self
makes more sense imo. We can change it later
Need to add amount.
Need to add public and private address
*/


package com.example.demo;

import java.util.Date;

public class Transaction {

	private int transactionID;
	private Date date;
	private String crypto;
	private String receiverPublicAddr;
	private String senderPublicAddr;
	private float amount;
	private Boolean suspicious;


	public Transaction(int transactionID, Date date, String crypto, String receiverPublicAddr, String senderPublicAddr, float amount, boolean suspicious) {
		this.transactionID = transactionID;
		this.date = date;
		this.crypto = crypto;
		this.receiverPublicAddr = receiverPublicAddr;
		this.senderPublicAddr = senderPublicAddr;
		this.amount = amount;
		this.suspicious = suspicious;
	}

	public int getID() {
		return this.transactionID;
	}

	public Date getDate() {
		return this.date;
	}

	public String getCrypto() {
		return this.crypto;
	}

	public float getAmount() {
		return this.amount;
	}

	public String getReceiverPA() {
		return this.receiverPublicAddr;
	}

	public String getSenderPA() {
		return this.senderPublicAddr;
	}

	public boolean getSuspicious() {
		return this.suspicious;
	}

	public void setSuspicious() {
		this.suspicious = true;
	}

	public void setSuspiciousFalse() {
		this.suspicious = false;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
}
