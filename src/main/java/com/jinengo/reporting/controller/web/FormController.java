package com.jinengo.reporting.controller.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinengo.reporting.model.user.UserAuthenticationModel;
import com.jinengo.reporting.service.user.UserAuthenticationDao;

@Controller
public class FormController {

	@Autowired
	private UserAuthenticationDao userAuthenticationDao;
	
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


	@RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
	public String savePassword(@Valid UserAuthenticationModel userModel, BindingResult result, Model model) {

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
			userAuthenticationDao.saveAuthModel(userModel);

			return "success";
		}

	}
	
}
