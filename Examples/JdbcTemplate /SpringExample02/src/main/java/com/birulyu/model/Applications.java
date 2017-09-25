package com.birulyu.model;

import java.sql.Timestamp;

public class Applications {
	String repo_url;
	String application;
	
	public Applications(){
		
	}
	
	public Applications(String repo_url, String application){
		this.repo_url = repo_url;
		this.application = application;
		
	}
	
	public String getRepo_url(){
		
		return this.repo_url;
		
	}
	
	public void setRepo_url(String repo_url){
		this.repo_url = repo_url;
	}
	
	public String getApplication(){
		return this.application;
	}
	
	public void setApplication(String application){
		
		this.application = application;
	}
	
	
	@Override
	public String toString() {
		return "Application [repo_url=" + repo_url + ", application=" + application + "]";
	}
}
