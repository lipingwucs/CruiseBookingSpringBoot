package com.mvc.jpa.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.mvc.jpa.models.*;


/*
 
insert into cruise(baseprice, cruisename, shipname, destination, startdate, enddate) values(1000, 'Miami 7 days cruise', 'Diamond Princess', 'JialieBi', '2020-03-01', '2020-03-15');
insert into cruise(baseprice, cruisename, shipname, destination, startdate, enddate) values(2000, 'Miami 14 days cruise', 'Diamond Princess II', 'Guba', '2020-03-20', '2020-04-04');
insert into cruise(baseprice, cruisename, shipname, destination, startdate, enddate) values(3000, 'Miami 21 days cruise', 'Diamond Princess III', 'Malta', '2020-03-20', '2020-4-11');
insert into cruise(baseprice, cruisename, shipname, destination, startdate, enddate) values(2000, 'Malta European 14 days cruise', 'Diamond Princess II', 'Estonia', '2020-03-20', '2020-04-04');
insert into cruise(baseprice, cruisename, shipname, destination, startdate, enddate) values(3000, 'Sydney 21 days cruise', 'Diamond Princess III', 'Sydney', '2020-03-20', '2020-4-11');

commit;
 */

@Controller
public class CruiseController {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	//constructor
	public CruiseController() {
		factory = Persistence.createEntityManagerFactory("mysqldbconfig"); //database config
		em = factory.createEntityManager(); // open database	
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	//initialize all dropdown list of cruise controller
	public void populateAllDropDown(ModelAndView view) {
		
		// cruiseNameList
		List<String> cruiseNameList = new ArrayList<String>();
		cruiseNameList.add("Sunny");
		cruiseNameList.add("Ocean");
		cruiseNameList.add("Atlantic");
		cruiseNameList.add("Disney");		
		view.addObject("cruiseNameList", cruiseNameList);
		
		// shipNameList
		List<String> shipNameList = new ArrayList<String>();
		shipNameList.add("Diamond Princess");
		shipNameList.add("Royal Prince");
		shipNameList.add("Queen Elithberth");
		view.addObject("shipNameList", shipNameList);
		
		// destinationList
		List<String> destinationList = new ArrayList<String>();
		destinationList.add("Alaska");
		destinationList.add("Cuba");
		destinationList.add("Carribean");	
		destinationList.add("Mexico");		
		view.addObject("destinationList", destinationList);
		
	}
	
	// get Cruise information from form to construct a cruise object for other controller use.
	public Cruise getCruiseFromRequest(HttpServletRequest request) {
		Integer cruiseId= 0; // default auto increament
		if (request.getParameter("cruiseId") != null) {			
			cruiseId = Integer.parseInt(request.getParameter("cruiseId"));			
		}
		
		String cruiseName= request.getParameter("cruiseName");		
		String shipName= request.getParameter("shipName");		
		Date startDate= new Date(0) ;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
		} catch (ParseException e) {			
			e.printStackTrace();
		} 
		
		Date endDate=new Date(0);
		try {
			endDate =  new SimpleDateFormat("yyyy-MM-dd").parse( request.getParameter("endDate"));
		} catch (ParseException e) {			
			e.printStackTrace();
		} 
			
		String destination = request.getParameter("destination");	
		Double basePrice = 0.0;
		try {
			basePrice = Double.parseDouble(request.getParameter("basePrice"));	
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} 
			
		return new Cruise( cruiseId, cruiseName, shipName, startDate, endDate,
    			 destination,  basePrice);
	}

	//get cruise_list  
	// to read database and get all the cruise list
	@RequestMapping(value = "/cruise_list", method = RequestMethod.GET)
	public ModelAndView cruise_list(HttpServletRequest request, HttpServletResponse response) {
		//search function base on cruiselist
		//set searchtext
		String searchtext = request.getParameter("searchtext");
		List<Cruise> cruiseList;
		//if searchtext is not null or empty, set up query with searchtext
		if (searchtext != null && !searchtext.isEmpty()) {
			Query query = em.createQuery("select c from Cruise c where c.cruiseName like :p or c.shipName like :p or c.destination like :p");
			query.setParameter("p", "%"+searchtext+"%");
			cruiseList = query.getResultList();
		} else { //if searchtext is null or empty, show the whole list of Cruise
			cruiseList = em.createQuery("select c from Cruise c").getResultList();
		}
		
		
		//return view with msg
		ModelAndView view =  new ModelAndView("cruise_list");
		System.out.println("searchtext: "+ searchtext);
		view.addObject("cruiseList", cruiseList);
		view.addObject("searchtext", searchtext);
		return view;

	}

	//get cruise_detail
	@RequestMapping(value = "/cruise_detail", method = RequestMethod.GET)
	public ModelAndView siginPost(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));  
		Cruise cruise = em.find(Cruise.class, id);

		ModelAndView view = new ModelAndView("cruise_detail");
		view.addObject("Title", "cruise details");
		view.addObject("cruise", cruise);  //after using form tag
		return view;		
	}
	
	//ADD
	@RequestMapping(value = "/cruise_add", method = RequestMethod.GET)
	public ModelAndView cruise_add_get(HttpServletRequest request, HttpServletResponse response) {
				
		ModelAndView view= new ModelAndView ("cruise_add");
		this.populateAllDropDown(view);		
		view.addObject("Title", "Cruise Add");
		view.addObject("cruise", new Cruise());	
		return view;
	}
	
	@RequestMapping(value = "/cruise_add", method = RequestMethod.POST)
	public ModelAndView cruise_add_post(HttpServletRequest request, HttpServletResponse response) {

		Cruise cruise = this.getCruiseFromRequest(request);

		String errorMsg = cruise.validateData();
		if (!errorMsg.isEmpty()) {
			ModelAndView view= new ModelAndView ("cruise_add");
			this.populateAllDropDown(view);		
			view.addObject("Title", "Cruise Add");
			view.addObject("cruise", cruise);	
			view.addObject("errorMsg", errorMsg);
			return view;
		}		
		
		em.getTransaction().begin();
		em.persist(cruise);
		em.getTransaction().commit();
		
		return	new ModelAndView("redirect:/cruise_list");
		
	}	
	
	
	//UPDATE get
	@RequestMapping(value = "/cruise_update", method = RequestMethod.GET)
	public ModelAndView cruise_update_get(HttpServletRequest request, HttpServletResponse response) {
		int cruiseId = Integer.parseInt(request.getParameter("id"));  
		Cruise cruise = em.find(Cruise.class, cruiseId);
		
		ModelAndView view= new ModelAndView ("cruise_update");
		this.populateAllDropDown(view);	
		view.addObject("Title", "Cruise Update");	
		view.addObject("cruise", cruise);		
		return view;		
	}
	
	//UPDATE post	
	@RequestMapping(value = "/cruise_update", method = RequestMethod.POST)
	public ModelAndView cruise_update_post(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cruise cruise = this.getCruiseFromRequest(request);
		System.out.println("Cruise Information for update from request:" + cruise.toString());
		
		String errorMsg = cruise.validateData();//validate in cruise model
		
		//if not pass validate, will render back to cruise_update with errorMsg
		if (!errorMsg.isEmpty()) {  
			ModelAndView view= new ModelAndView ("cruise_update");
			this.populateAllDropDown(view);		
			view.addObject("Title", "Cruise Update");
			view.addObject("cruise", cruise);	
			view.addObject("errorMsg", errorMsg);
			return view;
		}
		
		//if pass validate, will continue to save the new input to database
		int cruiseId = cruise.getCruiseId();
		
		Cruise cruiseDb = em.find(Cruise.class, cruiseId);
		// update the booking from request.getParameters		
		cruiseDb.setCruiseName(cruise.getCruiseName());
		cruiseDb.setDestination(cruise.getDestination());
		cruiseDb.setShipName(cruise.getShipName());
		cruiseDb.setStartDate(cruise.getStartDate());
		cruiseDb.setEndDate(cruise.getEndDate());
		cruiseDb.setBasePrice(cruise.getBasePrice());
		
		//for testing only
		System.out.println("Cruise Information for update:" + cruiseDb.toString());
		
		em.getTransaction().begin();		
		em.persist(cruiseDb);
		em.getTransaction().commit();

		session.setAttribute("tempMsg", "Updating a cruise "+cruiseId+" succeed");
		return new ModelAndView("redirect:/cruise_list");
	}
	
	
	//DELETE get
	@RequestMapping(value = "/cruise_delete", method = RequestMethod.GET)
	public ModelAndView cruise_delete_get(HttpServletRequest request, HttpServletResponse response) {
		int cruiseId = Integer.parseInt(request.getParameter("id"));  
		String message="Are you sure you want to cancel this booking "+cruiseId+" ?";
				
		Cruise cruise = em.find(Cruise.class, cruiseId);		

		ModelAndView view = new ModelAndView("cruise_delete");
		view.addObject("Title", "Cancel Cruise Booking");		
		view.addObject("cruise", cruise);
		view.addObject("confirmMsg", message);
		return view;
	}
	
	//DELETE post	
	@RequestMapping(value = "/cruise_delete", method = RequestMethod.POST)
	public ModelAndView cruise_delete_post(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		int cruiseId = Integer.parseInt(request.getParameter("id"));  
		Cruise cruise = em.find(Cruise.class, cruiseId);
		
		em.getTransaction().begin();
		em.remove(cruise);
		em.getTransaction().commit();

		session.setAttribute("tempMsg", "Deleted a cruise  "+cruiseId+" successfully");
		return new ModelAndView("redirect:/cruise_list");	
	}
	
	

}
