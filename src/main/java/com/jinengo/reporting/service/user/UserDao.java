package com.jinengo.reporting.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jinengo.reporting.model.UserModel;

@Component
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<UserModel> selectUser(String vorname) {
		String sqlQuery = "SELECT VORNAME, NACHNAME FROM [TestLars].[dbo].[User] where VORNAME = ?";
		
		return jdbcTemplate.query(sqlQuery,
			new Object[] { vorname },
			new UserRowMapper()
		);
	}
	
}
