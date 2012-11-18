package com.jinengo.reporting.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class PersonRowMapper implements RowMapper {

	  public Object mapRow(ResultSet rs, int line) throws SQLException {
		  PersonResultSetExtractor extractor = new PersonResultSetExtractor();
		  return extractor.extractData(rs);
	  }
	
}
