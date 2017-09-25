package com.birulyu.spring;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.birulyu.dao.ApplicationsDAO;
import com.birulyu.model.Applications;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("applicationContext.xml");
	    	 
			ApplicationsDAO applicationDAO = (ApplicationsDAO) context.getBean("applicationDAO");
			Applications application = new Applications("https://birulyu.com", "yiibai15");
			
			//applicationDAO.insert(application);
	    	
			Applications application1 = applicationDAO.findByRepo_url("https://github.com/ghc/ghc");

			
			
			
	        System.out.println(application1);
	        
	        
	        
	        List<Applications> applications2 = applicationDAO.findByRepo_url2("https://birulyu.com");
			for(Applications singleApp: applications2){
        	 System.out.println("Applications As : " + singleApp);
			}

	}

}
