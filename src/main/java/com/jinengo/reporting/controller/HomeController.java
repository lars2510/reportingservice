package com.jinengo.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinengo.reporting.service.HomeService;
import com.jinengo.reporting.service.PersonDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private PersonDao personDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(Model model) {
		
		model.addAttribute("myName", personDao.selectUser("Lars").get(0).getEmail() );
		
		return "home";
	}
	
}
