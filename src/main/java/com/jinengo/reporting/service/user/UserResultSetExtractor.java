package com.jinengo.reporting.service.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.jinengo.reporting.model.UserModel;

public class UserResultSetExtractor implements ResultSetExtractor<UserModel> {

	public UserModel extractData(ResultSet rs) throws SQLException {
		UserModel user = new UserModel();
	    user.setFirstName(rs.getString(1));
	    user.setGender(rs.getString(2));
	    return user;
	}
	
}
