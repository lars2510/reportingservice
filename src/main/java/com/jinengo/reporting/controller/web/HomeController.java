package com.jinengo.reporting.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jinengo.reporting.model.user.UserModel;
import com.jinengo.reporting.service.user.SimpleJdbcDaoImpl;
import com.jinengo.reporting.service.user.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserDao personDao = new UserDao();
	
	@Autowired
	private SimpleJdbcDaoImpl simpleJdbcDaoImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public List<UserModel> selectUserList() {
		List<UserModel> l = null;
		try {
			l = personDao.selectUser("1");
			
		} catch (IndexOutOfBoundsException e) {
			logger.info("Fehler im: " + e.getMessage() + " BAM");
		} catch (Exception e) {
			logger.info("allgemeiner Fehler: " + e.getMessage());
		}
		return l;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello(Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		
		modelAndView.addObject("p", selectUserList());
		
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
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String handleTest() {
		return "test";
	}
	
	@RequestMapping(value = "/simple-user", method = RequestMethod.GET)
	public String simpleUser(Model model) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("database-context.xml");
//		SimpleJdbcDaoImpl dao = ctx.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		model.addAttribute("userdata", "Anzahl: " + simpleJdbcDaoImpl.userCount());
		return "simpleUser";
	}
}
