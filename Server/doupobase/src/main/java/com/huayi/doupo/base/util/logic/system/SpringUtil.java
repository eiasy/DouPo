package com.huayi.doupo.base.util.logic.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huayi.doupo.base.config.PathConfig;

public class SpringUtil {
	
	private SpringUtil() {
		
	}

	private static ApplicationContext springContext = null;

	public static ApplicationContext getSpringContext() {
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(PathConfig.SPRINGXML);
		}
		return springContext;
	}

	public static Object GetObjectWithSpringContext(String objName) {
		try {
			if (getSpringContext().containsBean(objName)) {
				Object obj = getSpringContext().getBean(objName);
				return obj;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e);
			return null;
		}

	}

}