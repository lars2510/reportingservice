package com.jinengo.reporting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jinengo.reporting.model.UserModel;
import com.jinengo.reporting.service.user.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserDao personDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(Model model) {
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		try {
			List<UserModel> l = personDao.selectUser("1");
			logger.info("Größe: " + l.size());
			modelAndView.addObject("p", l);
			for (int i = 0; i < l.size(); i++) {
				UserModel m = l.get(i);
				//modelAndView.addObject("myName", m.getFirstName());
				//modelAndView.addObject("myGender", m.getGender());
			}
			
		} catch (IndexOutOfBoundsException e) {
			logger.info("Fehler im: " + e.getMessage() + " BAM");
		} catch (Exception e) {
			logger.info("allgemeiner Fehler: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/loggedout", method = RequestMethod.GET)
	public String handleForm(Model model) {
		model.addAttribute(new UserModel());
		
		return "loggedout";
	}
	
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String handleTimeout(Model model) {
		model.addAttribute(new UserModel());
		
		return "timeout";
	}
}
