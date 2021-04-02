/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.models;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Entity implementation class for Entity: Cruise
 *
 */
@Entity
public class Passenger implements Serializable {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )
	private Integer passengerId;
	
	@Column(unique=true)  //set the userName as unique
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private String city;
	private String postalCode;
	private String country;
	private static final long serialVersionUID = 1L;
	
	//constructor with fields
	public Passenger(int passengerId, String userName, String password, String firstName, String lastName,
			String email, String telephone, String address, String city, String postalCode, String country) {
		super();
		this.passengerId = passengerId;
		this.userName = userName;
		this.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	//constructor without anyfield
	public Passenger() {
		super();		
	}	
	
	//getters and setters
	public Integer getPassengerId() {
		return passengerId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	//validate the input of the profile
		public String validateData() {
			// validate the model data before update to database
			String errorMsg = "";
			if (this.firstName.isEmpty()) {
				errorMsg += "you must input a first name.\n";
			}
			if (this.lastName.isEmpty()) {
				errorMsg += "you must input a last name.\n";
			}
			if (this.email.isEmpty()) {
				errorMsg += "you must input an email.\n";
			}
			if (this.telephone.isEmpty()) {
				errorMsg += "you must input an telephone number.\n";
			}
			return errorMsg;
		}

		// method to display full name:
		public String displayName() {
			return  getFirstName() + " " + getLastName();
		}

		// method to display display full address
		public String displayAddress() {
			return  getAddress() + " " + "\n" + getCity() + ", " + getPostalCode() + ", " + getCountry();

		}
	    //method to checkPassword
		public boolean checkPassword(String password) {
			// encrypt by md5 then check if the password match or not
			//ref: https://www.codota.com/code/java/methods/org.apache.commons.codec.digest.DigestUtils/md5Hex
			String myPwd = DigestUtils.md5Hex(password); 
			return myPwd.equals(this.password);
			
		}
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", userName=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", telephone="
				+ telephone + ", address=" + address + ", city=" + city + ", postalCode=" + postalCode + ", country="
				+ country + "]";
	}
	

}
