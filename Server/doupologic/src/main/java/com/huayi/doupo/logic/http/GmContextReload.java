package com.huayi.doupo.logic.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

import org.apache.mina.core.session.IoSession;

import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.GmHttpHandler;

public class GmContextReload extends AContextHandle {

	public GmContextReload() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request){
		String ret = "操作发生异常";
		try {
			Map<String, String> paramsMap = getParamMap(request);
			LogUtil.info("gm工具Http模式: 参数 = " + paramsMap);
			String command = paramsMap.get("command");
			if (command.equals("updateDict")) {//多服更新字典表
				ret = GmHttpHandler.updateDict(paramsMap);
			} else if (command.equals("addMarqueeMulty")) {
				ret = GmHttpHandler.addMarqueeMulty(paramsMap);
			} else if (command.equals("provideAllMulty")) {
				ret = GmHttpHandler.provideAllMulty(paramsMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("gm工具Http模式-操作发生异常", e);
		}
		
		return sendToClient(ret);
	}
	
	private Map<String, String> getParamMap (HttpRequestMessage request) {
		List<String> keysList = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();
		for(int li=0; li<keysList.size(); li++){
			map.put(keysList.get(li), request.getParameter(keysList.get(li)));
		}
		return map;
	}

}
