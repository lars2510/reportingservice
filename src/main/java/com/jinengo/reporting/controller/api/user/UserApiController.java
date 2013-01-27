package com.jinengo.reporting.controller.api.user;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrUserFigures;
import com.jinengo.reporting.model.user.JinengoUser;
import com.jinengo.reporting.service.helper.SpringException;
import com.jinengo.reporting.service.user.UserDao;
import com.jinengo.reporting.service.user.UserFiguresDao;

/**
 * Jinengo REST-API to deliver BI-Data to Client Applications via JSON-Response
 * 
 * Every URL Request {BaseUrl}/api/user is mapped to this controller
 * @author larsschuttemeyer
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

	@Autowired
	private UserFiguresDao userFiguresDao;

	@Autowired
	private UserDao userAuthenticationDao;
	

	@RequestMapping(value = "/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> getFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			Principal principal) {			

		List<AggrUserFigures> keyFigures = null;
		
		int userId = userAuthenticationDao.getUserId(principal.getName());
		
		if (userId != -1) {
			keyFigures = userFiguresDao.getKeyFigureSum(userId, keyFigure, Integer.parseInt(year));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}
		
		return keyFigures;
	}

	@RequestMapping(value = "/averages", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> getAverages(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			Principal principal) {			

		List<AggrUserFigures> keyFigures = null;
		
		int userId = userAuthenticationDao.getUserId(principal.getName());
		
		if (userId != -1) {
			keyFigures = userFiguresDao.getKeyFigureAvg(userId, keyFigure, Integer.parseInt(year));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}
		
		return keyFigures;
	}
	
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigures> getTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			@RequestParam(value="month", required = false, defaultValue = "0") String month,
			Principal principal) {			

		List<AggrUserFigures> keyFigures = null;
		
		int userId = userAuthenticationDao.getUserId(principal.getName());
		
		if (userId != -1) {
			keyFigures = userFiguresDao.getTransportation(userId, keyFigure, Integer.parseInt(year), Integer.parseInt(month));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}

		return keyFigures;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public @ResponseBody JinengoUser getDetails(Principal principal) {
		JinengoUser userDetails = userAuthenticationDao.getUserDetails(principal.getName());
		
		if (userDetails == null) {
			throw new SpringException("Für den aktuell eingeloggten Nutzer liegen keine Details vor!");
		}
		
		return userDetails;
	}
	
}
