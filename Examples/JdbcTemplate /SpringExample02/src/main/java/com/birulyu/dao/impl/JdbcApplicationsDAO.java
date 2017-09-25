package com.birulyu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.birulyu.dao.ApplicationsDAO;
import com.birulyu.model.Applications;
import com.birulyu.model.ApplicationsRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcApplicationsDAO extends JdbcDaoSupport implements ApplicationsDAO{
	
	
	private DataSource dataSource;
	
	// Using JdbcTemplate to reduce redundant code
//	private JdbcTemplate jdbcTemplate;
//	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	@Override
	public void insert(Applications application) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO applications " +
				"(repo_url, application) VALUES (?, ?)";
		
		//jdbcTemplate = new JdbcTemplate(dataSource);
		
		getJdbcTemplate().update(sql, new Object[] { application.getRepo_url(),
				application.getApplication() });
		/*
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, application.getRepo_url());
			ps.setString(2, application.getApplication());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}*/
	}

	
	
	//query single row with RowMapper
	@Override
	public Applications findByRepo_url(String repo_url) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM applications WHERE repo_url = ?";		
		
		@SuppressWarnings("unchecked")
		Applications application = (Applications)getJdbcTemplate().queryForObject( sql, new Object[] { repo_url }, new ApplicationsRowMapper());
		
		//query single row with BeanPropertyRowMapper (Applications.class)
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Applications application2 = (Applications)getJdbcTemplate().queryForObject(
				sql, new Object[] { repo_url }, 
				new BeanPropertyRowMapper(Applications.class));
		
		return application;
			
		/*
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, repo_url);
			Applications application = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				application = new Applications(
					rs.getString("repo_url"), 
					rs.getString("application")
				);
			}
			rs.close();
			ps.close();
			return application;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}*/
	}
	
	
	//query multiple row with BeanPropertyRowMapper (Applications.class)
	public List<Applications> findByRepo_url2(String repo_url){
		 
		String sql = "SELECT * FROM applications WHERE repo_url = ?";	
		
		//@SuppressWarnings({ "unchecked", "rawtypes" })
		//List<Applications> applications  = getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(Applications.class));
		
		List<Applications> applications  = getJdbcTemplate().query(sql, new Object[] { repo_url }, new ApplicationsRowMapper());
		
		return applications;
		
	}
	
	

	@Override
	public Applications findByApplication(String app) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM applications WHERE repo_url = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, app);
			Applications application = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				application = new Applications(
					rs.getString("repo_url"), 
					rs.getString("application")
				);
			}
			rs.close();
			ps.close();
			return application;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
}
