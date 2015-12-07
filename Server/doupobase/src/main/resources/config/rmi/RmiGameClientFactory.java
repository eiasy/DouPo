package com.huayi.doupo.base.config.rmi;

public class RmiGameClientFactory {/*
	
	private RmiGameClientFactory() {
		
	}

	private static ApplicationContext springContext = null;

	public static ApplicationContext getSpringContext() {
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(PathConfig.springRmiGame);
		}
		return springContext;
	}
	private static ApplicationContext springContext1 = null;

	public static ApplicationContext getClientSpringContext() {
		if (springContext1 == null) {
			springContext1 = new ClassPathXmlApplicationContext(PathConfig.springRmiGameClient);
		}
		return springContext1;
	}
	public static IRmiSocketGameHandler getHandler() {
		try {
			String ip = SysConfigHelper.getValue("rmiSocketGame.ip");
			String port = SysConfigHelper.getValue("rmiSocketGame.port");
			String serviceName = SysConfigHelper.getValue("rmiSocketGame.ServiceName");
		    RmiProxyFactoryBean factory= new RmiProxyFactoryBean();
	        factory.setServiceInterface(IRmiSocketGameHandler.class );
	        factory.setServiceUrl("rmi://"+ip+":"+port+"/"+serviceName);
	        factory.setLookupStubOnStartup(false );
	        factory.setRefreshStubOnConnectFailure(true );
	        factory.afterPropertiesSet();
	        IRmiSocketGameHandler obj=(IRmiSocketGameHandler)factory.getObject();
		//	IRmiSocketChatHandler obj = (IRmiSocketChatHandler)getClientSpringContext().getBean("chatRmiClient");
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

*/}