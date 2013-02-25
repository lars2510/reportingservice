package com.jinengo.reporting.controller.api.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinengo.reporting.model.user.AggrUserFigure;
import com.jinengo.reporting.model.user.AggrUserFigurePerTransportation;
import com.jinengo.reporting.service.platform.PlatformFiguresDao;

/**
 * Jinengo REST-API to deliver BI-Data to Client Applications via JSON-Response
 * 
 * Every URL Request {BaseUrl}/api/platform is mapped to this controller
 * @author lars schuettemeyer
 *
 */
@Controller
@RequestMapping("/api/platform")
public class PlatformApiController {

	@Autowired
	private PlatformFiguresDao platformFiguresDao;
	
	/**
	 * get key figures sum depending on the given key figure and year
	 * 
	 * @param keyFigure
	 * @param year
	 * @return List of KeyFigure sum for each month of the given year
	 */
	@RequestMapping(value = "/figures", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getFigures(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrUserFigure> keyFigures = platformFiguresDao.getKeyFigureSum(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}

	/**
	 * get average key figure values for a given key figure and year
	 * 
	 * @param keyFigure
	 * @param year
	 * @return List of KeyFigure average for each month of the given year
	 */
	@RequestMapping(value = "/averages", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigure> getAverages(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year) {			

		List<AggrUserFigure> keyFigures = platformFiguresDao.getKeyFigureAvg(keyFigure, Integer.parseInt(year));

		return keyFigures;
	}
	
	/**
	 * get transportation data for a given key figure 
	 * 
	 * @param keyFigure
	 * @param year
	 * @param month
	 * @return return transportation key figures, aggregate by month or year
	 */
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public @ResponseBody List<AggrUserFigurePerTransportation> getTransportation(
			@RequestParam(value="keyFigure", required = false, defaultValue = "sumEcoImpact") String keyFigure, 
			@RequestParam(value="year", required = false, defaultValue = "2012") String year,
			@RequestParam(value="month", required = false, defaultValue = "0") String month) {			

		List<AggrUserFigurePerTransportation> keyFigures = platformFiguresDao.getTransportation(keyFigure, Integer.parseInt(year), Integer.parseInt(month));

		return keyFigures;
	}

}
