package com.jinengo.reporting.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinengo.reporting.model.user.AggrUserFigures;
import com.jinengo.reporting.model.user.UserAuthenticationModel;
import com.jinengo.reporting.model.user.UserModel;
import com.jinengo.reporting.service.user.AggrUserFiguresDao;
import com.jinengo.reporting.service.user.UserAuthenticationDao;

/**
 * Jinengo REST-API to deliver BI-Data to Client Applications via JSON-Response
 * 
 * Every URL Request {BaseUrl}/api is mapped to this controller
 * @author larsschuttemeyer
 *
 */
@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private AggrUserFiguresDao aggrUserFiguresDao;
	
	@Autowired
	private UserAuthenticationDao userAuthenticationDao;
	
	/** Example **/
	@RequestMapping(value="/user/data/{firstName}", method = RequestMethod.GET)
	public @ResponseBody UserModel postUserData(@PathVariable String firstName, @RequestParam(value="lastName", required=true) String lastName) {
 
		UserModel user = new UserModel();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(firstName + "@" + lastName + ".de");
		user.setGender("m");
		
	 	return user;
	}

	/**
	 * Deliver all aggregated user figures as JSON-Response
	 * 
	 * @return List<AggrUserFigures>
	 */
	@RequestMapping(value = "/user/chartdata", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> chartData() {	
		
		List<AggrUserFigures> aggrUsers = aggrUserFiguresDao.getAggrUsers();
		
		return aggrUsers;
	}
	
	/**
	 * Deliver aggregated user figures by user id and key figure
	 * 
	 * @param userId
	 * @param keyFigure
	 * @return List<AggrUserFigures>
	 */
	@RequestMapping(value = "/user/chartdata/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> chartData(@PathVariable String userId, @RequestParam(value="keyFigure") String keyFigure) {			
		
		 List<AggrUserFigures> aggrUsers = aggrUserFiguresDao.getAggrUsers(userId, keyFigure);
		
		return aggrUsers;
	}
	
	/**
	 * Create new User
	 * 
	 * @param userEmail
	 * @param userPassword
	 * @return List<AggrUserFigures>
	 */
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String addUser(Model model) {
        
        model.addAttribute("userModel", model.addAttribute(new UserAuthenticationModel()));
        return "forms/createUserForm";
    }
	

	@RequestMapping(value = "/user/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid UserAuthenticationModel userModel, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<ObjectError> err = result.getAllErrors();
			for (int i = 0; i < err.size(); i++) {
				System.out.println(err.get(i).getDefaultMessage());
			}
			return "forms/createUserForm";
		} else {
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String passwordEncoded = encoder.encode(userModel.getUserPassword());
			userModel.setUserPassword(passwordEncoded);
			userModel.setUserRole("supervisor");
			userAuthenticationDao.saveOrUpdate(userModel);
			
			return "success";
		}
		
            //Logic to save data from userForm
    }
	
}
