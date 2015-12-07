package com.huayi.doupo.logic.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.huayi.doupo.logic.rmi.inter.IRmiReLoadDictData;

public class RmiReLoadDictData  extends UnicastRemoteObject implements IRmiReLoadDictData{

	private static final long serialVersionUID = 323407481084562352L;

	public RmiReLoadDictData() throws RemoteException {

	}

	/**
	 * 重新加载字典数据
	 */
	@Override
	public void reLoadDictData() {
		try {
			//重新加载服务端字典数据
//			GenerDictData.flag = 0;
//			GenerDictData.getStaticDictData();//游戏加载时加载字典数据
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
