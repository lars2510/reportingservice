package com.jinengo.reporting.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jinengo.reporting.service.user.UserFiguresDao;

/**
 * default controller
 * handles requests for the application home page.
 *  
 * @author lars schuettemeyer
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserFiguresDao aggrUserFiguresDao;
	
	/**
	 * Mapping root url to home view an display welcome page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("p", "Willkommen");
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	/**
	 * Mapping loggedout url to loggedout view
	 * 
	 * @return view name
	 */
	@RequestMapping(value = "/loggedout", method = RequestMethod.GET)
	public String handleForm() {
		return "helper/loggedout";
	}
	
	/**
	 * Mapping timeout url timeout view
	 * 
	 * @return view name
	 */
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String handleTimeout() {
		return "helper/timeout";
	}
}
