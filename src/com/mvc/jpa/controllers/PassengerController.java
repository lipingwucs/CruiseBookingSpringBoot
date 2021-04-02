/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mvc.jpa.models.Passenger;

@Controller
public class PassengerController {
	
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public PassengerController() {
		factory = Persistence.createEntityManagerFactory("mysqldbconfig"); //database config
		em = factory.createEntityManager(); // open database	
	}

	//to get the /signin page
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView siginGet(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("signin");
		view.addObject("Title", "Login");
		view.addObject("passenger", new Passenger());	//after form tag 
		return view;
	}

	//to post the /signin page
	@RequestMapping(value = "/signin", method = RequestMethod.POST)	
	public ModelAndView siginPost(HttpServletRequest request, HttpServletResponse response) {
		// to get the parameter values from the page which you input
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");		
		//for testing only, to check the value the view get
		System.out.println("got login userName: " + userName + ", " + password);		
		
		//search from the database, to check whether the passenger registered/exists
		List<Passenger> psgList = em.createQuery("select d from Passenger d where d.userName = :p ")
				.setParameter("p", userName)
				.getResultList();		
		
		//for when fail to sign in, render back with userName value
		Passenger psg = new Passenger();
		psg.setUserName(userName);		
		
		// if username not exists, page render back to sign in with error massage
		if (psgList.size() == 0 ) {
			// user not existed
			ModelAndView view = new ModelAndView("signin");
			view.addObject("Title", "Login");
			view.addObject("errorMsg", "userName not existed.");			
			view.addObject("passenger", psg ); //render the passenger userName back
			return view;
		}
		
		//if username exists, continue to check password

		Passenger passenger = psgList.get(0);
		// call the checkPassword method in passenger model to validate the user credentials
		boolean checkResult = passenger.checkPassword(password);
		//for testing only, to see the checkResult 
		System.out.print("password correct? "+checkResult);

		//if checkResult true,continue to redirect to home page with welcome massage 
		if (checkResult) {
			// forward to homepage after login
			HttpSession session = request.getSession();
			session.setAttribute("currentUserId", passenger.getPassengerId());
			session.setAttribute("currentUser", passenger.getUserName());
			session.setAttribute("tempMsg", " Welcome back "+userName);
			return new ModelAndView("redirect:/home");
		} else {
			
			//if checkResult false, forward to signin jsp page with errorMsg
			ModelAndView view = new ModelAndView("signin");
			view.addObject("Title", "Login");
			view.addObject("errorMsg", "Wrong password.");			
			view.addObject("passenger", psg ); //render the passenger userName back
			return view;
		}
	}
	
    // /logout  get and post on the same page
	// logout is simply remove the session attribute
	// it will redirect to home page with logout message
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
		session.removeAttribute("currentUser");
		session.removeAttribute("currentUserId");
		session.setAttribute("tempMsg","Logout successfully. See you next time!");
		return new ModelAndView("redirect:/home");
	}

	// to get /signup page for none registered user
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView sigupGet(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView view = new ModelAndView("signup");
		view.addObject("Title", "signup");
		view.addObject("passenger", new Passenger());	
		return view;

	}

	// to post /signup page for new user after filled the new information
	@RequestMapping(value = "/signup", method = RequestMethod.POST)	
	public ModelAndView sigupPost(HttpServletRequest request, HttpServletResponse response) {
	
		// to get the signup parameters from the signup page
		int passengerId = 0 ; //set the passenger Id=0 for auto increament
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String telephone =  request.getParameter("telephone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postalCode");
		String country = request.getParameter("country");
		
		Passenger passenger= new Passenger(passengerId,  userName,  password,  firstName,  lastName, email,
				   telephone,  address,  city,  postalCode,  country);
		
		//validate the passenger attributes through Passenger model
		String errorMsg=passenger.validateData(); //call validateDate() from model 
		// validation the password during registeration
		if (!password.equals(password2)){
			errorMsg += "verify your password, which is not match";
		}
		 
		
		if (!errorMsg.isEmpty()) {
			ModelAndView view = new ModelAndView("signup");
			view.addObject("Title", "signup");
			view.addObject("passenger", passenger );
			view.addObject("errorMsg", errorMsg);
			return view;
		}

		
		// to check the userName whether exists in database
		List<Passenger> psgList = em.createQuery("select d from Passenger d where d.userName = :p ")
										.setParameter("p", userName)
										.getResultList();
		
		// if it exists, return to error page with error message
		if (psgList.size() != 0 ) {			
			return new ModelAndView("error", "tempMsg", "failed to signup because the userName "+userName+" already been used. ");
		}
		
		//not exist, continue to add a new passenger
		//add with a constructor with fields can make add simpler than add objects one by one
		Passenger newPsg = new Passenger( passengerId,  userName,  password,  firstName,  lastName, email,
				   telephone,  address,  city,  postalCode,  country);		
		
		//database operation start
		em.getTransaction().begin();
		em.persist(newPsg);
		em.getTransaction().commit();		

		//render the signup page with success message
		HttpSession session = request.getSession();
		session.setAttribute("tempMsg", "sign up " + userName + " successfully.");
		//for testing only.
		System.out.println("session.tempMsg: "+session.getAttribute("tempMsg"));
		
		return new ModelAndView("redirect:/home");
	}

	// to render the profile page
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) {
		
		//to get the userId from the Session
		Integer userId = (Integer)request.getSession().getAttribute("currentUserId");
		
		//for testing only
		System.out.print("login user id from session: "+userId);
		
		// read from the database that my profile
		Passenger psg = em.find(Passenger.class, userId);		

		// render it back to the current user's profile
		ModelAndView view = new ModelAndView("profile");
		view.addObject("Title", "My profile");
		view.addObject("passenger", psg);
		return view;
	}
	
	// after update current user's information, then render back to the profile page 
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView profile_update(HttpServletRequest request, HttpServletResponse response) {
		
		// get parameters values from form input		
		int passengerId = Integer.parseInt(request.getParameter("passengerId")); //from form hidden
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String telephone =  request.getParameter("telephone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postalCode");
		String country = request.getParameter("country"); 	
		
		//
		Passenger passenger= new Passenger(passengerId,  userName,  "",  firstName,  lastName, email,
				   telephone,  address,  city,  postalCode,  country);
		
		//validate the passenger attributes through Passenger model
		String errorMsg = passenger.validateData(); //call validateDate() from model 
		
		// if any validation error, then return back to the profile to continue input
		if (!errorMsg.isEmpty()) {
			ModelAndView view = new ModelAndView("profile");
			view.addObject("Title", "My profile");
			view.addObject("passenger", passenger );
			view.addObject("errorMsg", errorMsg);
			return view;
		}
		// if pass validation, then continue to update profile to database			
		HttpSession session = request.getSession();//for tempMsg		
		
		//find the Passenger object from database
		Passenger psg = em.find(Passenger.class, passengerId);		
		// set new parameters values to existing object of database
		// keep the old passengerId
		psg.setFirstName(firstName);
		psg.setLastName(lastName);
		psg.setEmail(email);
		psg.setTelephone(telephone);
		psg.setAddress(address);
		psg.setCity(city);
		psg.setPostalCode(postalCode);
		psg.setCountry(country);
		
		//database operation 
		em.getTransaction().begin();
		em.persist(psg);
		em.getTransaction().commit();

		ModelAndView view = new ModelAndView("profile");
		view.addObject("Title", "My profile");
		view.addObject("passenger", psg);
		session.setAttribute("tempMsg", "Update profile succeed");
		return view;		
	}
}
