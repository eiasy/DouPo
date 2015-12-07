package com.huayi.doupo.logic.test;

import com.huayi.doupo.logic.handler.util.PlayerUtil;

public class HttpClientTest {
	
	public static void main(String[] args) throws Exception{
		String appid = "1104102741";
		String appKey = "DvE0ZGI9iXkPqGUn";
		String openid = "OOOO";
		String openkey = "openkey";
		String userip = "192.168.1.43";
		
		String accessToken = "accessToken";
		
		PlayerUtil.validateOpenKey(appid, appKey, openid, openkey, userip);
		
		PlayerUtil.validateToken(appid, appKey, openid, accessToken);
	}
	
}
