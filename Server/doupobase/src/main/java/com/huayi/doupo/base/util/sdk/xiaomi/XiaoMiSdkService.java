package com.huayi.doupo.base.util.sdk.xiaomi;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 百度Sdk操作类
 * @author mp
 * @date 2015-7-1 上午9:52:02
 */
public class XiaoMiSdkService {

	/**
	 * 登录验证session
	 * @author mp
	 * @date 2015-7-1 下午3:25:53
	 * @param accessToken
	 * @return
	 * @Description
	 */
	public static String verifySession (String uid, String sessionId) throws Exception{
		
		String url = ParamConfig.getSdkValue("xiaomi", "normalUrl");
		String appId = ParamConfig.getSdkValue("xiaomi", "appId");
		String appsecret = ParamConfig.getSdkValue("xiaomi", "appsecret");
		
		//组织参数 格式为：name1=value1&name2=value2
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("appId=").append(appId).append("&");
		paramBuilder.append("session=").append(sessionId).append("&");
		paramBuilder.append("uid=").append(uid).append("&");
		
		//生成秘钥
		String signContent = StringUtil.noContainLastString(paramBuilder.toString());
		LogUtil.out(signContent);
		String signature = HmacSHA1Encryption.HmacSHA1Encrypt(signContent, appsecret);
		
		paramBuilder.append("signature=").append(signature);
		
		url = url + "?" + paramBuilder.toString();
		
		LogUtil.out(url);
		
		String result = HttpClientUtil.httpGet(url, 3000, false);
		
		LogUtil.out(result);
		
		return result;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		String url = "http://mis.migc.xiaomi.com/api/biz/service/verifySession.do";
		String appId = "2882303761517350957";
		String appsecret = "4UmhkpwXm98AuprG4/g5zg==";
		
		//组织参数 格式为：name1=value1&name2=value2
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append("appId=").append(appId).append("&");
		paramBuilder.append("session=").append("abcdefg").append("&");
		paramBuilder.append("uid=").append("123456");
		
		//生成秘钥
		String signContent = paramBuilder.toString();
		String signature = HmacSHA1Encryption.HmacSHA1Encrypt(signContent, appsecret);
		
		paramBuilder.append("signature=").append(signature);
		
		url = url + "?" + paramBuilder.toString();
		
		System.out.println(url);
		
		String result = HttpClientUtil.httpGet(url, 3000, false);
		
		System.out.println(result);
		
		//http://mis.migc.xiaomi.com/api/biz/service/verifySession.do?appId=2882303761517350957&session=abcdefg&uid=123456&signature=610b2e9b83fbb572ec069d690a5345e61e5a40d8
		//{"errcode":1525}

	}
	
}
