package com.jinengo.reporting.controller.api;

import java.security.Principal;
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

	/**
	 * Deliver aggregated user figures by user id and key figure
	 * 
	 * @param userId
	 * @param keyFigure
	 * @return List<AggrUserFigures>
	 */
	@RequestMapping(value = "/user/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> chartData(
			@RequestParam(value="keyFigure", required = false, defaultValue = "avgEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			Principal principal) {			

		List<AggrUserFigures> keyFigures = aggrUserFiguresDao.getKeyFigures(principal.getName(), keyFigure, Integer.parseInt(year));

		return keyFigures;
	}

	/**
	 * Deliver aggregated platform figures by key figure
	 * 
	 * @param keyFigure
	 * @return List<AggrUserFigures>
	 */
	@RequestMapping(value = "/platform/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> plattformChartData(@RequestParam(value="keyFigure") String keyFigure, Principal principal) {			

		List<AggrUserFigures> keyFigures = aggrUserFiguresDao.getPlattformKeyFigures(keyFigure);

		return keyFigures;
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
