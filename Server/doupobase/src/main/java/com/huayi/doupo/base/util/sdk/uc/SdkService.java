package com.huayi.doupo.base.util.sdk.uc;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.sdk.uc.protocol.CpRequestHelper;

/**
 * UC-Sdk验证
 * @author mp
 * @date 2015-6-30 下午3:31:03
 */
public class SdkService {
	
	/**
	 * 验证会话
	 * @author mp
	 * @date 2015-6-30 下午3:32:49
	 * @return
	 * @Description
	 */
	public static String verifySession (String sid, String gameId) throws Exception{

		String isTest = SysConfigUtil.getValue("uc.test");//是否为测试环境  0-test 1-normal

		//组织URL
		String url = "";
		if (isTest.equals("0")) {
			url = ParamConfig.getSdkValue("uc", "testUrl") + ParamConfig.getSdkValue("uc", "prefix") + ParamConfig.getSdkValue("uc", "verifySession");
		} else if (isTest.equals("1")) {
			url = ParamConfig.getSdkValue("uc", "normalUrl") + ParamConfig.getSdkValue("uc", "prefix") + ParamConfig.getSdkValue("uc", "verifySession");
		}

		LogUtil.out("url = " + url);
		
		//组织RequestBody参数[JSON]
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("id", DateUtil.getCurrMill() + "");
		
		Map<String, Object> sidMap = new HashMap<String, Object>();
		sidMap.put("sid", sid);
		jsonMap.put("data", sidMap);
		
		Map<String, Object> gameMap = new HashMap<String, Object>();
		gameMap.put("gameId", gameId);
		jsonMap.put("game", gameMap);
		
		jsonMap.put("sign", CpRequestHelper.createMD5Sign(sidMap, "", ParamConfig.getSdkValue("uc", "apiKey")));
		String param = JsonUtil.toJson(jsonMap);
		
		LogUtil.out("param = " + param);
		
		String result = HttpClientUtil.postSubmit(url, param);
		LogUtil.out("result ----  " + result);
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		
//		Load Spring Resource
//		SpringUtil.getSpringContext();
		
//		Load SdkConfig
//		ParamConfig.sdkConfig = (SdkConfig)SpringUtil.GetObjectWithSpringContext("SdkConfig");
		
//		verifySession("aaaa", "1");
		
		Map<String, Object> sidMap = new HashMap<String, Object>();
		sidMap.put("personid", "value1");
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("code", "value2");
		sidMap.put("name", testMap);
		
		String ret = JsonUtil.toJson(sidMap);
		JSONObject json = new JSONObject(ret);
		String jsName = String.valueOf(json.get("name"));
		json = new JSONObject(jsName);
		System.out.println(String.valueOf(json.get("code")));
		
	}
	
}
