package com.jinengo.reporting.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.UserModel;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@RequestMapping(value="/user/data/{firstName}", method = RequestMethod.GET)
	public @ResponseBody UserModel postUserData(@PathVariable String firstName, @RequestParam(value="lastName", required=true) String lastName) {
 
		UserModel user = new UserModel();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(firstName + "@" + lastName + ".de");
		user.setGender("m");
		
	 	return user;
	}
	
}
