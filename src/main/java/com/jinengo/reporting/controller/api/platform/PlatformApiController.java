package com.jinengo.reporting.controller.api.platform;

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
import com.jinengo.reporting.service.helper.SpringException;
import com.jinengo.reporting.service.platform.PlatformFiguresDao;

/**
 * Jinengo REST-API to deliver BI-Data to Client Applications via JSON-Response
 * 
 * Every URL Request {BaseUrl}/api/platform is mapped to this controller
 * @author larsschuttemeyer
 *
 */
@Controller
@RequestMapping("/api/platform")
public class PlatformApiController {

	@Autowired
	private PlatformFiguresDao platformFiguresDao;
	
	@RequestMapping(value = "/figure", method = RequestMethod.GET)
	public @ResponseBody List<AggrPlatformFigures> getFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrPlatformFigures> keyFigures = platformFiguresDao.getKeyFigureSum(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}

	@RequestMapping(value = "/average", method = RequestMethod.GET)
	public @ResponseBody List<AggrPlatformFigures> getAverage(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrPlatformFigures> keyFigures = platformFiguresDao.getKeyFigureAvg(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}
	
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrPlatformFigures> getTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year,
			@RequestParam(value="month", required = false, defaultValue = "0") String month) {			

		List<AggrPlatformFigures> keyFigures = platformFiguresDao.getTransportation(keyFigure, Integer.parseInt(year), Integer.parseInt(month));

		return keyFigures;
	}

}
