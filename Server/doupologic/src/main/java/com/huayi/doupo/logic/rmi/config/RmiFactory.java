package com.huayi.doupo.logic.rmi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class RmiFactory {
	
	private RmiFactory() {
		
	}

	private static ApplicationContext springContext = null;

	public static ApplicationContext getSpringContext() {
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext("/yy/socket/game/rmi/config/rmi.xml");
		}
		return springContext;
	}
	
}