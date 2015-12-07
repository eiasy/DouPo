package com.huayi.doupo.base.config.sdk;

import java.util.HashMap;

/**
 * SDK配置信息
 * @author mp
 * @date 2015-6-30 下午1:52:43
 */
public class SdkConfig {
	
	private HashMap<String, HashMap<String, String>> sdkMap;

	public HashMap<String, HashMap<String, String>> getSdkMap() {
		return sdkMap;
	}

	public void setSdkMap(HashMap<String, HashMap<String, String>> sdkMap) {
		this.sdkMap = sdkMap;
	}
	
	
}
