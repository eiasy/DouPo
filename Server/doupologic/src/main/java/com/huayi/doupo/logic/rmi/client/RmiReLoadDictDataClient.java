package com.huayi.doupo.logic.rmi.client;

import org.junit.Test;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.rmi.inter.IRmiReLoadDictData;

public class RmiReLoadDictDataClient {
	
	private String ip = "192.168.0.101";
	
	private String port = "8888";
	
	private String serviceName = "RmiReLoadDictDataService";
	
	/**
	 * 获取rmi factory
	 * @author mp
	 * @version 1.0, 2013-6-6 上午10:47:18
	 * @return
	 */
	private IRmiReLoadDictData getRmiClientInterface(){
		RmiProxyFactoryBean factory= new RmiProxyFactoryBean();
        factory.setServiceInterface(IRmiReLoadDictData.class );
        factory.setServiceUrl("rmi://"+ip+":"+port+"/"+serviceName);
        factory.setLookupStubOnStartup(false);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        IRmiReLoadDictData obj = (IRmiReLoadDictData)factory.getObject();
        return obj;
	}
	
	
	
	/**
	 * 重新加载字典数据
	 * @author mp
	 * @version 1.0, 2013-6-18 上午11:08:36
	 */
	@Test
	public void reLoadDictData(){
		IRmiReLoadDictData obj = getRmiClientInterface();
        obj.reLoadDictData();
		LogUtil.out("字典数据加载完毕");
	}
	
}
