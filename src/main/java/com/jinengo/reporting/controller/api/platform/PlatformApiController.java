package com.jinengo.reporting.controller.api.platform;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrUserFigurePerTransportation;
import com.jinengo.reporting.model.user.AggrUserFigure;
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
	
	@RequestMapping(value = "/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrUserFigure> keyFigures = platformFiguresDao.getKeyFigureSum(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}

	@RequestMapping(value = "/averages", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getAverages(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrUserFigure> keyFigures = platformFiguresDao.getKeyFigureAvg(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}
	
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigurePerTransportation> getTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year,
			@RequestParam(value="month", required = false, defaultValue = "0") String month) {			

		List<AggrUserFigurePerTransportation> keyFigures = platformFiguresDao.getTransportation(keyFigure, Integer.parseInt(year), Integer.parseInt(month));

		return keyFigures;
	}

}
