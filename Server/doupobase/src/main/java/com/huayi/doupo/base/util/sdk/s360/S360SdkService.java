package com.huayi.doupo.base.util.sdk.s360;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 百度Sdk操作类
 * @author mp
 * @date 2015-7-1 上午9:52:02
 */
public class S360SdkService {

	/**
	 * 登录验证session
	 * @author mp
	 * @date 2015-7-1 下午3:25:53
	 * @param accessToken
	 * @return
	 * @Description
	 */
	public static String verifySession (String access_token) throws Exception{
		
		//组织URL
		String url = ParamConfig.getSdkValue("360", "normalUrl");
		
		//组织参数
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("access_token=").append(access_token);
		
		url = url + "?" + paramBuilder.toString();
		
		LogUtil.out(url);
		
		String result = HttpClientUtil.httpGet(url, 3000, false);
		
		LogUtil.out(result);
		
		return result;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		//组织URL
		String url = "https://openapi.360.cn/user/me";
		
		//组织参数
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("access_token=").append("abcdefg");
		
		url = url + "?" + paramBuilder.toString();
		
		System.out.println(url);
		
		String result = HttpClientUtil.postSubmit(url);
		
		System.out.println(result);

	}
	
}
