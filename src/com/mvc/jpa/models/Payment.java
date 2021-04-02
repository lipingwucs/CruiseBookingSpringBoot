/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.models;

import java.util.Date;  // not java.sql.Date

public class Payment {
	private int paymentId;
	private String paymentType;
	private String cardNumber;
	private String ownerName;
	private String cvc;
	private int expireMonth;
	private int expireYear;
	private String billingAddress;
	
	//getters and setters
	public int getPaymentId() {
		return paymentId;
	}

	public int getExpireMonth() {
		return expireMonth;
	}

	public int getExpireYear() {
		return expireYear;
	}

	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}

	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getCvc() {
		return cvc;
	}

	public String getBillingAddress() {
		return billingAddress;
	}



	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

    // constructor without fields
	public Payment() {
		super();
	}
	
	// constructor with fields
	public Payment(int paymentId, String paymentType, String cardNumber, String ownerName, String cvc, int expireMonth,
			int expireYear, String billingAddress) {
		super();
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.cardNumber = cardNumber;
		this.ownerName = ownerName;
		this.cvc = cvc;
		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
		this.billingAddress = billingAddress;
	}
	
		public String validateData() {
		// validate the model data before update to database
		String errorMsg = "";
	
		if (this.paymentType == null) {
			errorMsg += "Payment Type must be selected  .\n";
		} 	
		if (this.cardNumber.length() != 16) {
			errorMsg += "Card Number must 16 digitals .\n";
		}
		if (this.cvc.length() != 3) {
			errorMsg += "Cvc must 3 digitals .\n";
		}
		if (this.ownerName == null) {
			errorMsg += "Card owner's name must be filled .\n";
		}
		if (this.expireMonth < 1 ||this.expireMonth >12) {
			errorMsg += "expired Momth must betwen 1-12.\n";
		}
		
		if (this.billingAddress == null) {
			errorMsg += "you must fill your billing address.\n";
		}	
		
	
		return errorMsg;
	}


	//method toString()
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType=" + paymentType + ", cardNumber=" + cardNumber
				+ ", ownerName=" + ownerName + ", cvc=" + cvc + ", expireMonth=" + expireMonth + ", expireYear="
				+ expireYear + ", billingAddress=" + billingAddress + "]";
	}
	

	// submit the payment transaction to bank
	public boolean submitTransaction(Double amount) {
		System.out.println("submit payment transaction to bank to charge CAD $"+ amount + " on "+ this.ownerName);
		// TODO: call real bank api to finish the payment		
		return true;
	}
	

	
	
	
	
	

}
