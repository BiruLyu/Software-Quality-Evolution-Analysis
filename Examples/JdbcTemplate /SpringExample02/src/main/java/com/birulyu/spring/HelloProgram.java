package com.birulyu.spring;

import com.birulyu.spring.helloworld.HelloWorld;
import com.birulyu.spring.helloworld.HelloWorldService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       ApplicationContext context =
	                new ClassPathXmlApplicationContext("beans.xml");
	         
	        HelloWorldService service =
	             (HelloWorldService) context.getBean("helloWorldService");
	          
	        HelloWorld hw= service.getHelloWorld();
	         
	        hw.sayHello();
	}

}
