/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.models;

import java.io.Serializable;
import java.lang.String;
import java.text.SimpleDateFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.Hashtable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cruise
 *
 */
@Entity
public class Cruise implements Serializable {
	   
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )   //IDENTITY only, no other type here	
	private Integer cruiseId;
	private String cruiseName;
	private String shipName;
	@Temporal(TemporalType.DATE)
	private Date startDate;  //https://www.baeldung.com/jpa-java-time
	@Temporal(TemporalType.DATE)	
	private Date endDate;
	private String destination;
	private Double basePrice;
	private static final long serialVersionUID = 1L;
	
	//constructor with fields
	public Cruise(Integer cruiseId, String cruiseName, String shipName, Date startDate, Date endDate,
			String destination, Double basePrice) {
		super();
		this.cruiseId = cruiseId;
		this.cruiseName = cruiseName;
		this.shipName = shipName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.destination = destination;
		this.basePrice = basePrice;
	}
	//constructor without any fields
	public Cruise() {
		super();
	} 
	
	//getters and setters	
	//getter and setter baseprice
	public Double getBasePrice() {
		return basePrice;
	}	
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	//getter and setter cruiseId
	public int getCruiseId() {
		return this.cruiseId;
	}
	public void setCruiseId(Integer cruiseId) {
		this.cruiseId = cruiseId;
	}
	
	//getter and setter cruiseName
	public String getCruiseName() {
		return this.cruiseName;
	}	
	public void setCruiseName(String cruiseName) {
		this.cruiseName = cruiseName;
	}   
	
	//getter and setter shipName
	public String getShipName() {
		return this.shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}   
	
	//getter and setter startDate
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   
	//getter and setter endDate
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	
	//getter and setter Destination
	public String getDestination() {
		return this.destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	//getStartDateString()	
	public String getStartDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.startDate);  
	}
	//getEndDateString()
	public String getEndDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.endDate);  
	}
	
	//method to calculate total price
	public double getTotalPrice(int totalGuests, String stateroomType) {
		Hashtable<String, Double> priceAdjust = new Hashtable<String, Double>();
		priceAdjust.put("Ocean view", 100.0);
		priceAdjust.put("Aqua class", 200.0);
		priceAdjust.put("Veranda", 300.0);
		priceAdjust.put("Sky Suite", 400.0);
		priceAdjust.put("Royal Suite", 500.0);
		priceAdjust.put("Standard", 0.0);
		return totalGuests * (this.basePrice+priceAdjust.get(stateroomType));				
	}
	
	// cruise validation
	public String validateData() {
		// validate the model data before update to database
		String errorMsg = "";
		if (this.cruiseName.isEmpty()) {
			errorMsg += "you must input a cruise name.\n";
		}
		if (this.shipName.isEmpty()) {
			errorMsg += "you must input a ship name.\n";
		}
		if (this.destination.isEmpty()) {
			errorMsg += "you must input a destination.\n";
		}
		if (this.basePrice <= 0.0) {
			errorMsg += "you must input a valid base price (postive number).\n";
		}
		return errorMsg;
	}
	
	//toString
	@Override
	public String toString() {
		return "Cruise [cruiseId=" + cruiseId + ", cruiseName=" + cruiseName + ", shipName=" + shipName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", destination=" + destination + ", basePrice=" + basePrice
				+ "]";
	}
   
}
