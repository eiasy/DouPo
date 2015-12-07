package com.huayi.doupo.logic.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

import org.apache.mina.core.session.IoSession;

import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.RechargeUtil;

public class ContextReloadServerList extends AContextHandle {

	public ContextReloadServerList() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request){
		String ret = "";
		try {
			List<String> keysList = request.getParameterNames();
			Map<String, String> map = new HashMap<String, String>();
			for(int li=0;li<keysList.size();li++){
				map.put(keysList.get(li), request.getParameter(keysList.get(li)));
			}
			System.out.println("Recharge Http Map = " + map);
			ret = RechargeUtil.recharge(map);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("充值发生异常", e);
		}
		
//		Map<String, String> retMap = new HashMap<String, String>();
//		retMap.put("code", "1");
//		retMap.put("message", "充值处理中");
//		JsonUtil.toJson(retMap);
		
		return sendToClient(ret);
	}

}
