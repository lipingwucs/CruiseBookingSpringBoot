/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homeGet(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView("home");
		view.addObject("pageTitle", "Homepage");
		view.addObject("currentUser", request.getSession().getAttribute("currentUser"));
		return view;
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public ModelAndView aboutGet(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView view = new ModelAndView("aboutus");
		view.addObject("pageTitle", "About this project");
		view.addObject("currentUser", request.getSession().getAttribute("currentUser"));
		return view;
	}

}
