package com.jinengo.reporting.service.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jinengo.reporting.model.UserModel;

public class UserRowMapper implements RowMapper<UserModel> {

	  public UserModel mapRow(ResultSet rs, int line) throws SQLException {
		  UserResultSetExtractor extractor = new UserResultSetExtractor();
		  return extractor.extractData(rs);
	  }
	
}
