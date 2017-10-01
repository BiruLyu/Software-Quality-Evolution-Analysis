package com.birulyu.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.birulyu.model.Applications;
import org.springframework.jdbc.core.RowMapper;


public class ApplicationsRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Applications application = new Applications();
		application.setRepo_url(rs.getString("repo_url"));
		application.setApplication(rs.getString("application"));
		return application;
	}
}
