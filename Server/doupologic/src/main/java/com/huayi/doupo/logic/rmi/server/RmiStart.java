package com.huayi.doupo.logic.rmi.server;

import com.huayi.doupo.logic.rmi.config.RmiFactory;

public class RmiStart {
	
	public static void main(String[] args) {
		RmiFactory.getSpringContext();
		System.out.println("------  Rmi Server Start  ------");
	}
	
	/**
	 * 启动RMI服务
	 * @author mp
	 * @version 1.0, 2013-6-6 上午11:20:52
	 */
	public static void startRmiServer(){
		RmiFactory.getSpringContext();
		System.out.println("------  Rmi Server Start  ------");
	}
	
}
