package com.birulyu.dao;

import java.util.List;

import com.birulyu.model.Applications;

public interface ApplicationsDAO {
	public void insert(Applications application);
	public Applications findByRepo_url(String repo_url);
	public List<Applications> findByRepo_url2(String repo_url);
	public Applications findByApplication(String app);
	
}
