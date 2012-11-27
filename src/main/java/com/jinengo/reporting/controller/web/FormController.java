package com.jinengo.reporting.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinengo.reporting.model.user.UserModel;

@Controller
public class FormController {

	@RequestMapping(value = "/userform", method = RequestMethod.GET)
	public String handleForm(Model model) {
		model.addAttribute(new UserModel());
		
		return "forms/userForm";
	}
	
	@RequestMapping(value = "/showuserform", method = RequestMethod.POST)
	public String outputForm(@ModelAttribute UserModel user, Model model) {
		model.addAttribute("user", user);
		
		return "forms/showUserForm";
	}
	
}
