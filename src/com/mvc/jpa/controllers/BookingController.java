/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.controllers;

import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

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
import com.mvc.jpa.models.Booking;
import com.mvc.jpa.models.Cruise;
import com.mvc.jpa.models.Passenger;
import com.mvc.jpa.models.Payment;

@Controller
public class BookingController {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	//constructor
	public BookingController() {
		factory = Persistence.createEntityManagerFactory("mysqldbconfig"); //database config
		em = factory.createEntityManager(); // open database	
	}	
	
	//initialize all dropdown of booking controller here
	public void populateAllDropDown(ModelAndView view) {
		//initialize stateRoomList drop down
		//using TreeMap to sort the list
		TreeMap<String, String> stateRoomList = new TreeMap<String, String>();
		stateRoomList.put("Ocean view", "Ocean view (Standard price + CAD $100)");
		stateRoomList.put("Aqua class", "Aqua Class (Standard price + CAD $200)");
		stateRoomList.put("Veranda", "Veranda (Standard price + CAD $300)");
		stateRoomList.put("Sky Suite", "Sky Suite (Standard price + CAD $400)");
		stateRoomList.put("Royal Suite", "Royal Suite (Standard price + CAD $500)");	
		stateRoomList.put("Standard", "Standard");	
		
		view.addObject("stateRoomList", stateRoomList);
		
		//initialize expireMonthList drop down
		List<Integer> expireMonthList = new ArrayList<Integer>();
		for (int i=0; i<12; i++ ) {
			expireMonthList.add(i+1);
		}			
		view.addObject("expireMonthList", expireMonthList);
		
		//initialize expireYearList drop down
		List<String> expireYearList = new ArrayList<String>();
		for (int i=0; i<10; i++ ) {
			expireYearList.add((2020+i)+""); //int transfer to String
		}			
		view.addObject("expireYearList", expireYearList);
	}
	
	//get the page /booking_add_step1
	@RequestMapping(value = "/booking_add_step1", method = RequestMethod.GET)
	public ModelAndView booking_add_step1_get(HttpServletRequest request, HttpServletResponse response) {
		// already selected a cruise and has the cruise detail information
		//continue to add booking information as stateroom type and totalGuests
		int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));  
		Cruise cruise = em.find(Cruise.class, cruiseId);		
		
		Booking booking= new Booking();
		booking.setCruise(cruise);
		
		// validate date which can't modify or cancel the reservation if the cruise will be start in 7 days
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, 0);
		
		//for testing only
		System.out.println("now: "+now+", c:"+c.getTime()+", startdate:"+cruise.getStartDate());
		
		//if less than 7 days, stop cancel or update and render back to booking_list
		if(c.getTime().compareTo(cruise.getStartDate()) >= 0) {
		request.getSession().setAttribute("tempMsg", "You can't book this cruise # "+cruiseId+", as the cruise has started ("+cruise.getStartDateString()+").");
			return new ModelAndView("redirect:/cruise_detail?id="+cruiseId);				
		}
		
		// render a view of booking_add_step1
		ModelAndView view = new ModelAndView("booking_add_step1");
		this.populateAllDropDown(view);	
		view.addObject("Title", "Book a cruise step 1");
		view.addObject("cruise", cruise);    //after use form tag
		view.addObject("booking", booking);  //after use form tag
		return view;		
	}
	
    //fill the form and post to //get the page /booking_add_step1
	@RequestMapping(value = "/booking_add_step1", method = RequestMethod.POST)
	public ModelAndView booking_add_step1_post(HttpServletRequest request, HttpServletResponse response) {
		
		//after add booking information as stateroom type and totalGuests
		//then post to booking_add_step2
		int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));  //hidden
		int totalGuests = Integer.parseInt(request.getParameter("totalGuests"));  
		String stateroomType = request.getParameter("stateroomType"); 
		
		//search cruise object with cruiseId
		Cruise cruise = em.find(Cruise.class, cruiseId);
		double totalprice = cruise.getTotalPrice(totalGuests, stateroomType);	
		
		Booking booking = new Booking(); // for calcualate the total cost
		booking.setCruise(cruise);		
		booking.setPrice(totalprice);
		booking.setStateroomType(stateroomType);
		booking.setTotalGuests(totalGuests);

		//validate totalGuests input
		if ( totalGuests <= 0) {
			ModelAndView view = new ModelAndView("booking_add_step1");  // render view booking_add_step2
			this.populateAllDropDown(view); //with dropdown list which initialized at previous
			view.addObject("Title", "Book a cruise step 1");
			view.addObject("cruise", cruise);
			view.addObject("booking", booking);
			view.addObject("errorMsg", "Total Guests number should be positive number");
			return view;
		}
		
		ModelAndView view = new ModelAndView("booking_add_step2");  // render view booking_add_step2
		this.populateAllDropDown(view); //with dropdown list which initialized at previous
		view.addObject("Title", "Book a cruise step 2");
		view.addObject("booking", booking);
		view.addObject("totalPrice", String.format("%.2f", booking.getPrice()));
		view.addObject("totalTax", String.format("%.2f", booking.calculateTotalTax()));
		view.addObject("totalCost", String.format("%.2f", booking.calculateTotalCost()));
		view.addObject("payment", new Payment());
		return view;
	}	
	
	//add payment information		
	//save to model	
	@RequestMapping(value = "/booking_add_step2", method = RequestMethod.POST)
	public ModelAndView booking_add_step2_post(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("currentUserId");
		int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));  
		int totalGuests = Integer.parseInt(request.getParameter("totalGuests"));  
		String stateroomType = request.getParameter("stateroomType");  
		
		//for testing, check the value 
		System.out.println("got request params: cruiseId:"+cruiseId+", totalGuests:"+totalGuests
				+", stateroomType: "+ stateroomType);
		
		//get payment info from page
		int paymentId = 0; //TODO for auto increament in database
		String paymentType = request.getParameter("paymentType");		
		String cardNumber =request.getParameter("cardNumber");
		String ownerName =request.getParameter("ownerName");
		String cvc = request.getParameter("cvc");
		int expireMonth = Integer.parseInt(request.getParameter("expireMonth"));
		int expireYear = Integer.parseInt(request.getParameter("expireYear"));
		String billingAddress = request.getParameter("billingAddress");
		
		//for testing, check the value 		
		System.out.println("got request params: paymentId:"+paymentId+", payment type:"+paymentType+", cardNumber:"+cardNumber+", ownerName: "+ ownerName
+", cvc: "+ cvc +", expireMonth: "+ expireMonth +", expireYear: "+ expireYear);
		
		Cruise cruise = em.find(Cruise.class, cruiseId);
		double totalprice = cruise.getTotalPrice(totalGuests, stateroomType);
		
		Passenger passenger = em.find(Passenger.class, userId);		
		
		Booking booking = new Booking();
		booking.setCruise(cruise);		
		booking.setPassenger(passenger);		
		booking.setPrice(totalprice);
		booking.setStateroomType(stateroomType);
		booking.setTotalGuests(totalGuests);
		
		// and more request paramters for credit card information,
		// which will send to a bank api to make the payment transaction		
		Payment payment= new Payment( paymentId,  paymentType,  cardNumber,  ownerName,  cvc,  expireMonth, expireYear,  billingAddress);	
		
		//if validation failed (need keep stay in page booking_add_step2 to finish )
		//payment validate from Payment Model
		String errorMsg = payment.validateData();  
		
		if (!errorMsg.isEmpty()) { //if validation failed
			ModelAndView view = new ModelAndView("booking_add_step2");//view render back to step 2
			//when render back to "booking_add_step2, the following attributes should display in the page
			this.populateAllDropDown(view);
			view.addObject("Title", "Book a cruise step 2");
			view.addObject("booking", booking);
			view.addObject("totalPrice", String.format("%.2f", booking.getPrice()));
			view.addObject("totalTax", String.format("%.2f", booking.calculateTotalTax()));
			view.addObject("totalCost", String.format("%.2f", booking.calculateTotalCost()));
			view.addObject("payment", payment);			
			view.addObject("errorMsg", errorMsg);
			return view;
		}	
		//if validation passed, continue to 		
		// submit payment transaction to bank
		payment.submitTransaction(booking.calculateTotalCost());
				
		// save to database
		em.getTransaction().begin();
		em.persist(booking);
		em.getTransaction().commit();

		//render to booking succeed page	
		session.setAttribute("tempMsg", "Your payment is successfully made. \nYour booking the cruise "+cruise.getCruiseName()+" succeed. \n Thanks for your booking! Enjoy your cruise trip!");
		return new ModelAndView("redirect:/booking_list");  //page redirect to /booking_list
		
	}
	
	//get page /booking_list
	@RequestMapping(value = "/booking_list", method = RequestMethod.GET)
	public ModelAndView booking_list(HttpServletRequest request, HttpServletResponse response) {
		//get userId from session
		Integer userId = (Integer)request.getSession().getAttribute("currentUserId");
		String userName = (String)request.getSession().getAttribute("currentUser");
		
		//search function start 
		//set searchtext
		String searchtext = request.getParameter("searchtext");
        List<Booking> bookingList;  //result type: List<Booking> 
		Query query;
		String sqlstr;
		// if admin user, then query/get the booking list of all users
		if (userName.equals("admin")) {
			//if searchtext is not null or empty, set up query with searchtext
			if (searchtext != null && !searchtext.isEmpty()) {
				//search cruisename, shipname, destination, ignore case and space
				query = em.createQuery("select b from Booking b where (lower(b.cruise.cruiseName) like :p or lower(b.cruise.shipName) like :p or lower(b.cruise.destination) like :p) "); 
				query.setParameter("p", "%"+searchtext.toLowerCase().trim()+"%");			
			} else { //if searchtext is null or empty, show the whole list of Booking
				//search all users' booking
				query = em.createQuery("select b from Booking b ");			
			}			
			
		} else { // if none-admin user, then only get the booking list of his own only
			
			//if searchtext is not null or empty, set up query with searchtext
			if (searchtext != null && !searchtext.isEmpty()) {
				//search cruisename, shipname, destination, ignore case and space
				query = em.createQuery("select b from Booking b where b.passenger.passengerId=:q and (lower(b.cruise.cruiseName) like :p or lower(b.cruise.shipName) like :p or lower(b.cruise.destination) like :p) ");
				query.setParameter("p", "%"+searchtext.toLowerCase().trim()+"%");			
			} else { //if searchtext is null or empty, show the whole list of this passenger's
				query = em.createQuery("select b from Booking b where b.passenger.passengerId=:q ");			
			}
			query.setParameter("q", userId);			
		}
		//search function end here 
		bookingList = query.getResultList();
		
		//render and return view 
		ModelAndView view =  new ModelAndView("booking_list");
		view.addObject("bookingList", bookingList);
		view.addObject("searchtext", searchtext);		
		return view;
	}
	
	//get booking_detail by userId
	@RequestMapping(value = "/booking_detail", method = RequestMethod.GET)
	public ModelAndView booking_detail_get(HttpServletRequest request, HttpServletResponse response) {
		//get session
		HttpSession session = request.getSession();
		//get passengerId from session
		Integer passengerId = (Integer)session.getAttribute("currentUserId");//
		//get reservationId from request(url id)
		int reservationId = Integer.parseInt(request.getParameter("id")); 
		
		Booking booking = em.find(Booking.class, reservationId);	
		
		Cruise cruise = booking.getCruise();
		Passenger passenger = booking.getPassenger();
		
		ModelAndView view = new ModelAndView("booking_detail");
		view.addObject("Title", "My booking details");
		view.addObject("booking", booking);
		view.addObject("cruise", cruise);		
		view.addObject("fullName", passenger.displayName());
		view.addObject("address", passenger.displayAddress());
		view.addObject("stateroomType", booking.getStateroomType());
		view.addObject("totalGuests", booking.getTotalGuests());
		
		view.addObject("totalPrice", String.format("%.2f", booking.getPrice()));
		view.addObject("totalTax", String.format("%.2f", booking.calculateTotalTax()));
		view.addObject("totalCost", String.format("%.2f", booking.calculateTotalCost()));		
		view.addObject("thanksMsg", "Thanks for your booking! Enjoy your cruise trip!");		
		return view;
	}
	
	//get /booking_update
	@RequestMapping(value = "/booking_update", method = RequestMethod.GET)
	public ModelAndView booking_update_get(HttpServletRequest request, HttpServletResponse response) {
		//get reservationId from request(url id)
		int reservationId = Integer.parseInt(request.getParameter("id"));  
		Booking booking = em.find(Booking.class, reservationId);
		Cruise cruise = booking.getCruise();
		
		// validate date which can't modify or cancel the reservation if the cruise will be start in 7 days
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, 7);
		
		//for testing only
		System.out.println("now: "+now+", c:"+c.getTime()+", startdate:"+cruise.getStartDate());
		
		//if less than 7 days, stop cancel or update and render back to booking_list
		if(c.getTime().compareTo(cruise.getStartDate()) >= 0) {
		request.getSession().setAttribute("tempMsg", "You can't modify the reservation "+reservationId+" as the cruise will start in 7 days ("+cruise.getStartDateString()+").");
			return new ModelAndView("redirect:/booking_list");				
		}
		
		//if longer than 7 days, continue to render view /booking_update
		ModelAndView view = new ModelAndView("booking_update");
		this.populateAllDropDown(view); 
		view.addObject("Title", "Update my Cruise Booking");
		view.addObject("booking", booking);
		view.addObject("cruise", cruise);		
		return view;
	}
	
	
	@RequestMapping(value = "/booking_update", method = RequestMethod.POST)
	public ModelAndView booking_update_post(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));  
		int totalGuests = 0;
		try {
			totalGuests = Integer.parseInt(request.getParameter("totalGuests")); 
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} 
				
		String stateroomType = request.getParameter("stateroomType");  
		int cruiseId = Integer.parseInt(request.getParameter("cruiseId")); 
		
		Cruise cruise = em.find(Cruise.class, cruiseId);
		double totalprice = cruise.getTotalPrice(totalGuests, stateroomType);
		
		// update the booking from request.getParameters
		//validate totalGuests number
		if (totalGuests <= 0) {
			Booking booking = new Booking();			
			booking.setReservationId(reservationId);
			booking.setStateroomType(stateroomType);
			booking.setTotalGuests(totalGuests);
			booking.setPrice(totalprice);	
			ModelAndView view = new ModelAndView("booking_update");
			view.addObject("Title", "Update my Cruise Booking");
			view.addObject("booking", booking);
			view.addObject("cruise", cruise);
			view.addObject("errorMsg", "Total guests must be a positive number.");
			return view;
		}
		
		Booking bookingInDb = em.find(Booking.class, reservationId);
		
		bookingInDb.setStateroomType(stateroomType);
		bookingInDb.setTotalGuests(totalGuests);
		bookingInDb.setPrice(totalprice);
		em.getTransaction().begin();		
		em.persist(bookingInDb);
		em.getTransaction().commit();

		session.setAttribute("tempMsg", "Updating a cruise booking "+reservationId+" succeed");
		return new ModelAndView("redirect:/booking_list");
	}	
	
	//get the booking_delete page
	@RequestMapping(value = "/booking_delete", method = RequestMethod.GET)
	public ModelAndView booking_delete_get(HttpServletRequest request, HttpServletResponse response) {
		int reservationId = Integer.parseInt(request.getParameter("id"));  
		String message="Are you sure you want to cancel this booking "+reservationId+" ?";

		Booking booking = em.find(Booking.class, reservationId);
		
		Cruise cruise = booking.getCruise();
		
		// can't modify or cancel the reservation if the cruise will be start in 7 days
		//same as update part
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, 7);
		if(c.getTime().compareTo(cruise.getStartDate()) >= 0) {
		// It's less than 7 days.
			request.getSession().setAttribute("tempMsg", "You can't cancel the reservation "+reservationId+" as the cruise will start in 7 days ("+cruise.getStartDateString()+").");
			return new ModelAndView("redirect:/booking_list");			
		}

		ModelAndView view = new ModelAndView("booking_delete");		
		view.addObject("Title", "Cancel Cruise Booking");
		view.addObject("booking", booking);
		view.addObject("cruise", cruise);
		view.addObject("confirmMsg", message);
		return view;
	}
	
	//post booking_delete to delete booking info from database
	@RequestMapping(value = "/booking_delete", method = RequestMethod.POST)
	public ModelAndView booking_delete_post(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int reservationId = Integer.parseInt(request.getParameter("reservationId"));  
		Booking booking = em.find(Booking.class, reservationId);
		
		em.getTransaction().begin();
		em.remove(booking);
		em.getTransaction().commit();

		session.setAttribute("tempMsg", "Deleting a cruise booking "+reservationId+" succeed");
		return new ModelAndView("redirect:/booking_list");		
	}

}
