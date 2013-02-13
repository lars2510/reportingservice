package com.jinengo.reporting.controller.api.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrUserFigure;
import com.jinengo.reporting.model.user.AggrUserFigurePerTransportation;
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
	private UserDao userDao;
	

	@RequestMapping(value = "/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year,
			@RequestParam(value="friendId", required = false, defaultValue = "") String friendId,
			Principal principal) {			

		List<AggrUserFigure> keyFigures = null;
		
		int queryId = friendId.length() > 0 ? Integer.parseInt(friendId) : userDao.getUserId(principal.getName());
		
		if (queryId != -1) {
			keyFigures = userFiguresDao.getKeyFigureSum(queryId, keyFigure, Integer.parseInt(year));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}
		
		return keyFigures;
	}

	@RequestMapping(value = "/averages", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getAverages(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			@RequestParam(value="friendId", required = false, defaultValue = "") String friendId,
			Principal principal) {			

		List<AggrUserFigure> keyFigures = null;
		
		int queryId = friendId.length() > 0 ? Integer.parseInt(friendId) : userDao.getUserId(principal.getName());
		
		if (queryId != -1) {
			keyFigures = userFiguresDao.getKeyFigureAvg(queryId, keyFigure, Integer.parseInt(year));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}
		
		return keyFigures;
	}
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getBalance(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			@RequestParam(value="friendId", required = false, defaultValue = "") String friendId,
			Principal principal) {			

		List<AggrUserFigure> keyFigures = null;
		
		int queryId = friendId.length() > 0 ? Integer.parseInt(friendId) : userDao.getUserId(principal.getName());
		
		if (queryId != -1) {
			keyFigures = userFiguresDao.getBalance(queryId, keyFigure, Integer.parseInt(year));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}
		
		return keyFigures;
	}
	
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigurePerTransportation> getTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year, 
			@RequestParam(value="month", required = false, defaultValue = "0") String month,
			@RequestParam(value="friendId", required = false, defaultValue = "") String friendId,
			Principal principal) {			

		List<AggrUserFigurePerTransportation> keyFigures = null;
		
		int queryId = friendId.length() > 0 ? Integer.parseInt(friendId) : userDao.getUserId(principal.getName());
		
		if (queryId != -1) {
			keyFigures = userFiguresDao.getTransportation(queryId, keyFigure, Integer.parseInt(year), Integer.parseInt(month));
		} else {
			throw new SpringException("Der aktuell eingeloggte Nutzer besitzt keinen gültigen Jinengo Account!");
		}

		return keyFigures;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public @ResponseBody JinengoUser getDetails(Principal principal) {
		JinengoUser userDetails = userDao.getUserDetails(principal.getName());
		
		if (userDetails == null) {
			throw new SpringException("Für den aktuell eingeloggten Nutzer liegen keine Details vor!");
		}
		
		return userDetails;
	}
	
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public @ResponseBody ArrayList<JinengoUser> getFriends(Principal principal) {
		ArrayList<JinengoUser> userFriends = userDao.getUserFriends(principal.getName());
		
		return userFriends;
	}
}
