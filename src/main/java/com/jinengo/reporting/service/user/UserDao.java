package com.jinengo.reporting.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jinengo.reporting.model.UserModel;

@Component
public class UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<UserModel> selectUser(String gender) {
		String sqlQuery = "SELECT Name, Gender FROM [JinengoDataWarehouse].[dbo].[JinengoUser] where Gender = ?";
		
		return jdbcTemplate.query(sqlQuery,
			new Object[] { gender },
			new UserRowMapper()
		);
	}
	
}
