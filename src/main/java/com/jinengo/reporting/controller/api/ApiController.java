package com.jinengo.reporting.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrUserFigures;
import com.jinengo.reporting.model.user.UserModel;
import com.jinengo.reporting.service.user.AggrUserFiguresDao;

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
	
}
