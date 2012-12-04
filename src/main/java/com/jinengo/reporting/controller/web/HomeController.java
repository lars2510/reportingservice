package com.jinengo.reporting.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jinengo.reporting.model.user.AggrUserFigures;
import com.jinengo.reporting.service.user.AggrUserFiguresDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private AggrUserFiguresDao aggrUserFiguresDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("p", null);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/loggedout", method = RequestMethod.GET)
	public String handleForm() {
		return "loggedout";
	}
	
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String handleTimeout() {
		return "timeout";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String handleTest() {
		return "test";
	}
	
	
	@RequestMapping(value = "/show-aggr-user", method = RequestMethod.GET)
	public String showAggrUser(Model model) {	
		List<AggrUserFigures> aggrUsers = aggrUserFiguresDao.getAggrUsers();

		model.addAttribute("aggrUsers", aggrUsers);
		return "aggrUser";
	}
}
