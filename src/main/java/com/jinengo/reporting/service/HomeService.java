package com.jinengo.reporting.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void getInfos() {
		String sql = "SELECT COUNT('VORNAME') FROM [TestLars].[dbo].[User]";
		int resCount = jdbcTemplate.queryForInt(sql);
		logger.info("-----COUNT: " + resCount);
	}

	public String getInfos2() {
	    String res = jdbcTemplate.query("SELECT VORNAME, NACHNAME FROM [TestLars].[dbo].[User]", new RowMapper() {
	    	
	    	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//	        	while (rs.next()) {	        		
//	        		logger.info("RS: " + rs.getString("VORNAME") + " " + rs.getString("NACHNAME"));
//	        	}
	        	return rs.getString("VORNAME") + " " + rs.getString("NACHNAME");
	    	}
	    	
	    }).toString(); 
	    return res;
	}
}
