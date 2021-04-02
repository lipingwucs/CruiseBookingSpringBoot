/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.models;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Booking
 *
 */
@Entity
public class Booking implements Serializable {
	   
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )  //IDENTITY only 
	private int reservationId;  
	
	@ManyToOne //many booking to one passenger
	@JoinColumn(name = "passengerId", referencedColumnName = "passengerId")
	private Passenger passenger;
	// instead of private int passengerId;   

	@ManyToOne  //many booking to one cruise
	@JoinColumn(name = "cruiseId", referencedColumnName = "cruiseId")
	
	private Cruise cruise;	// instead of private int cruiseId;  
	private String stateroomType;
	private int totalGuests;
	private double price;
	private static final long serialVersionUID = 1L;
	private static final double TAXRATE=0.13;  //static final- is not a column of database
	
	//method1 @CreationTimestamp // for hibernate	
    //method2 @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // depends on database
	//method3:
	@Temporal(TemporalType.TIMESTAMP) //https://www.baeldung.com/jpa-java-time
	private Date created; 
	
	//method1 @UpdateTimestamp //for hibernate	
    //method2 @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP") // actually create a trigger in database
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;		

	@PrePersist //Executed before the entity manager persist operation is actually executed or cascaded.
	void onCreate() {
		Date now = new Date();
		this.setCreated(now);
		this.setUpdated(now);
	}
	
	@PreUpdate //Executed before the database UPDATE operation.
	void onPersist() {
		Date now = new Date();
		this.setUpdated(now);
	}
	
	//calculate total tax
	public double calculateTotalTax() {
		return this.price*TAXRATE;		
	}
	//calculate total cost
	public double calculateTotalCost() {
		return  this.price + calculateTotalTax();	
	}
   
	//constructor without fields
	public Booking() {
		super();
	}
	//constructor with fields
	public Booking(int reservationId, Passenger passenger, Cruise cruise, String stateroomType, int totalGuests,
			double price, Date created, Date updated) {
		super();
		this.reservationId = reservationId;
		this.passenger = passenger;
		this.cruise = cruise;
		this.stateroomType = stateroomType;
		this.totalGuests = totalGuests;
		this.price = price;
		this.created = created;
		this.updated = updated;
	}
	
	//getters and setters
	public int getReservationId() {
		return reservationId;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public Cruise getCruise() {
		return cruise;
	}

	public String getStateroomType() {
		return stateroomType;
	}

	public int getTotalGuests() {
		return totalGuests;
	}

	public double getPrice() {
		return price;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public void setCruise(Cruise cruise) {
		this.cruise = cruise;
	}

	public void setStateroomType(String stateroomType) {
		this.stateroomType = stateroomType;
	}

	public void setTotalGuests(int totalGuests) {
		this.totalGuests = totalGuests;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	//toString Method
	@Override
	public String toString() {
		return "Booking [reservationId=" + reservationId + ", passenger=" + passenger + ", cruise=" + cruise
				+ ", stateroomType=" + stateroomType + ", totalGuests=" + totalGuests + ", price=" + price
				+ ", created=" + created + ", updated=" + updated + "]";
	}

	
}
