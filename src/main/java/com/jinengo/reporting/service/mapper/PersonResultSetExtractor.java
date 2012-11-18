package com.jinengo.reporting.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.jinengo.reporting.model.UserModel;

@SuppressWarnings("rawtypes")
public class PersonResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		UserModel user = new UserModel();
	    user.setUserName(rs.getString(1));
	    user.setEmail(rs.getString(2));
	    return user;
	}
	
}
