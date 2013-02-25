package com.jinengo.reporting.controller.api.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @author lars schuettemeyer
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

	@Autowired
	private UserFiguresDao userFiguresDao;

	@Autowired
	private UserDao userDao;
	
	/**
	 * get key figures sum depending on the given key figure and year
	 * 
	 * @param keyFigure
	 * @param year
	 * @param friendId
	 * @param principal
	 * @return List of KeyFigure sum for each month of the given year
	 */
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

	/**
	 * get average key figure values for a given key figure and year
	 * 
	 * @param keyFigure
	 * @param year
	 * @param friendId
	 * @param principal
	 * @return List of KeyFigure average for each month of the given year
	 */
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
	
	/**
	 * get advantages and disadvanteges for a given key figure and year
	 * 
	 * @param keyFigure
	 * @param year
	 * @param friendId
	 * @param principal
	 * @return List of possible best and worst travel possibilities
	 */
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
	
	/**
	 * get transportation data for a given key figure
	 * 
	 * @param keyFigure
	 * @param year
	 * @param month
	 * @param friendId
	 * @param principal
	 * @return list of transportation key figures
	 */
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
	
	/**
	 * get user details depending on pricipal for the current logged in user
	 * 
	 * @param principal
	 * @return user details
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public @ResponseBody JinengoUser getDetails(Principal principal) {
		JinengoUser userDetails = userDao.getUserDetails(principal.getName());
		
		if (userDetails == null) {
			throw new SpringException("Für den aktuell eingeloggten Nutzer liegen keine Details vor!");
		}
		
		return userDetails;
	}
	
	/** 
	 * get user friend list
	 * 
	 * @param principal
	 * @return list of userDetails of users friends
	 */
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public @ResponseBody ArrayList<JinengoUser> getFriends(Principal principal) {
		ArrayList<JinengoUser> userFriends = userDao.getUserFriends(principal.getName());
		
		return userFriends;
	}
}
