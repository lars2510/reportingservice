package com.jinengo.reporting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jinengo.reporting.service.mapper.PersonRowMapper;
import com.jinengo.reporting.model.UserModel;

public class PersonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserModel> selectUser(String vorname) {
		
		return jdbcTemplate.query("SELECT VORNAME, NACHNAME FROM [TestLars].[dbo].[User] where VORNAME = ?",
			new Object[] { vorname },
			new PersonRowMapper()
		);
	}
	
}
