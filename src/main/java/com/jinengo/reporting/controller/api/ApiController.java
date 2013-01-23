package com.jinengo.reporting.controller.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrPlatformFigures;
import com.jinengo.reporting.model.user.AggrUserFigures;
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
	 * @return List<AggrPlatformFigures>
	 */
	@RequestMapping(value = "/platform/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrPlatformFigures> platformFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "avgEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrPlatformFigures> keyFigures = aggrUserFiguresDao.getPlatformKeyFigures(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}

	/**
	 * Deliver aggregated platform transportation
	 * 
	 * @param keyFigure
	 * @return List<AggrPlatformFigures>
	 */
	@RequestMapping(value = "/platform/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrPlatformFigures> platformTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "avgEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year,
			@RequestParam(value="month", required = false, defaultValue = "0") String month) {			

		List<AggrPlatformFigures> keyFigures = aggrUserFiguresDao.getPlatformTransportation(keyFigure, Integer.parseInt(year), Integer.parseInt(month));

		return keyFigures;
	}

}
