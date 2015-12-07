package com.huayi.doupo.base.config.rmi;

public class RmiChatClientFactory {/*
	
	private RmiChatClientFactory() {
		
	}

	private static ApplicationContext springContext = null;

	public static ApplicationContext getSpringContext() {
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(PathConfig.springRmiChat);
		}
		return springContext;
	}
	private static ApplicationContext springContext1= null;

	public static ApplicationContext getClientSpringContext() {
		if (springContext1 == null) {
			springContext1 = new ClassPathXmlApplicationContext(PathConfig.springRmiChatClient);
		}
		return springContext1;
	}
	public static IRmiSocketChatHandler getHandler() {
		try {
		     String ip=SysConfigHelper.getValue("rmiSocketChat.ip");
			String port = SysConfigHelper.getValue("rmiSocketChat.port");
			String serviceName = SysConfigHelper.getValue("rmiSocketChat.ServiceName");
		    RmiProxyFactoryBean factory= new RmiProxyFactoryBean();
	        factory.setServiceInterface(IRmiSocketChatHandler.class );
	        factory.setServiceUrl("rmi://"+ip+":"+port+"/"+serviceName);
	        factory.setLookupStubOnStartup(false );
	        factory.setRefreshStubOnConnectFailure(true );
	        factory.afterPropertiesSet();
	        IRmiSocketChatHandler obj=(IRmiSocketChatHandler)factory.getObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

*/}