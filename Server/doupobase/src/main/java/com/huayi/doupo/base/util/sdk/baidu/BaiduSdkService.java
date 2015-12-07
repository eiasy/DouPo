package com.huayi.doupo.base.util.sdk.baidu;

import java.net.URLDecoder;

import org.json.JSONObject;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.EncryptUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 百度Sdk操作类
 * @author mp
 * @date 2015-7-1 上午9:52:02
 */
public class BaiduSdkService {
	
	/**
	 * SDK-登录验证
	 * @author mp
	 * @date 2015-7-1 上午10:04:25
	 * @param accessToken
	 * @return
	 * @Description
	 */
	public static String verifyLoginState (String accessToken) {
		
		//组织URL
		String url = ParamConfig.getSdkValue("baidu", "normalUrl");
		
		LogUtil.out(url);
		
		//组织参数 格式为：name1=value1&name2=value2
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("AppID=").append(ParamConfig.getSdkValue("baidu", "appId")).append("&");
		paramBuilder.append("AccessToken=").append(accessToken).append("&");
		paramBuilder.append("Sign=").append(EncryptUtil.md5Hex(ParamConfig.getSdkValue("baidu", "appId") + accessToken + ParamConfig.getSdkValue("baidu", "appsecret")));
		
		LogUtil.out(paramBuilder.toString());
		
		String result = "";
		
		result = Sdk.sendPost(url, paramBuilder.toString());
		
		LogUtil.out(result);
		
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		
		//组织URL
//		String url = "http://querysdkapi.91.com/CpLoginStateQuery.ashx";
//		
//		System.out.println(url);
//		
//		
//		//组织参数 格式为：name1=value1&name2=value2
//		StringBuilder paramBuilder = new StringBuilder();
//		paramBuilder.append("AppID=").append("6283134").append("&");
//		paramBuilder.append("AccessToken=").append("abcdefg").append("&");
//		String sign = Sdk.md5("6283134" + "abcdefg" + "YUWN7AkWUDD9Kdp8NaFs4jW4T9V7po9g");
//		System.out.println(sign);
//		sign = EncryptUtil.md5Hex("6283134" + "abcdefg" + "YUWN7AkWUDD9Kdp8NaFs4jW4T9V7po9g");
//		System.out.println(sign);
//		
//		paramBuilder.append("Sign=").append(sign);
//		
//		System.out.println(paramBuilder.toString());
//		
//		String result = "";
//		
//		result = Sdk.sendPost(url, paramBuilder.toString());
//		
//		System.out.println(result);
		
		String str = "eyJVSUQiOjM1ODAzOTc0MTJ9";
		String deStr = URLDecoder.decode(str);
		String dedeString = Base64.decode(deStr);
		System.out.println(dedeString);
		JSONObject uidJson = new JSONObject(dedeString);
		String uid = String.valueOf(uidJson.get("UID"));
		System.out.println(uid);
	}
	
}
