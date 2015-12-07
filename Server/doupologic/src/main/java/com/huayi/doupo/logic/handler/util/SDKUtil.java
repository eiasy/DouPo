package com.huayi.doupo.logic.handler.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.InputStreamUtils;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.system.log.ThreadOper;
import com.huayi.doupo.base.util.logic.system.log.ThreadPoolUtils;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.qq.open.SnsSigCheck;

/**
 * Sdk 工具类
 * @author mp
 * @date 2015-3-24 下午3:38:34
 */
public class SDKUtil {
	
	private static String appId = "1104102741";
	
	private static String appKey = "DvE0ZGI9iXkPqGUn";
	
	public static void main(String[] args) throws Exception{
		
		ThreadPoolUtils.execute(new ThreadOper() {
			@Override
			public void innerRun() {
				try {
					present_m("1234567", "100");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Player player = new Player();
		player.setAppid("1104102741");
		player.setAppKey("DvE0ZGI9iXkPqGUn");
		player.setOpenId("1234567");
		player.setOpenkey("openkey");
		player.setPay_token("g1*1*1");
		player.setPf("qq_m_qq-2001-android-2011-xxxx");
		player.setPfkey("pfkey");
		player.setSession_id("openid");
		player.setSession_type("kp_actoken");
		player.setUserip("");
		player.setZoneid("1");
		
		PlayerMapUtil.add("123", player);
		
		System.out.println(get_balance_m("1234567"));//查询余额
//		System.out.println(cancel_pay_m("1234567", 100+""));//扣费
//		System.out.println(present_m("1234567", "100"));//免费赠送
	}
	
	/**
	 * 查询余额接口
	 * @author mp
	 * @date 2015-3-24 下午4:48:34
	 * @param openId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> get_balance_m (String openId) throws Exception{
		
		Player player = PlayerMapUtil.getPlayerByOpenId(openId);
		
		String msdkUrl = SysConfigUtil.getValue("msdk.url");
		
		HashMap<String, Object> retMap = null;
		
		// http 请求方法
		String method = "GET";
		
		// http 请求URI
		String uri = "/mpay/get_balance_m";
		
		// 申请的appkey, 参数后需要加&
		String appkey = appKey + "&";
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		// 必选参数
		params.put("openid", player.getOpenId());
		params.put("openkey", player.getOpenkey());
		params.put("pay_token", player.getPay_token());
		params.put("appid", appId);
		params.put("ts", DateUtil.getCurrSec() + "");
		params.put("pf", player.getPf());
		params.put("pfkey", player.getPfkey());
		params.put("zoneid", player.getZoneid());
		params.put("format", "json");
		
		String sig = SnsSigCheck.makeSig(method, uri, params, appkey);
//		System.out.println("sig = " + sig);
		params.put("sig", StringUtil.encode(sig));
		
		InputStream is = null;
		try{
			
			String url = "http://"+ msdkUrl +"" + uri + "?" + StringUtil.toParams(params, "&");
//			System.out.println("url = " + url);
			
			// 组装Cookie
			Map<String, String> cookies = new HashMap<String, String>();
			cookies.put("session_id", player.getSession_id());
			cookies.put("session_type", player.getSession_type());
			cookies.put("org_loc", StringUtil.encode(uri));
//		    cookies.put("appip", "xxx.xxx.xxx.xxx");//可选
			
			String cookie = StringUtil.toParams(cookies, ";");
	
			// 发起http连接
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod(method);
			conn.addRequestProperty("Cookie", cookie);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.connect();
			is = conn.getInputStream();
			String content = InputStreamUtils.InputStreamTOString(is);
			retMap = JsonUtil.fromJson(content, HashMap.class);
//			System.out.println("openid = " + openId + " 查询余额结果：  " + content);
		} catch (Exception e) {
//			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return retMap;
	}
	
	/**
	 * 扣除游戏币接口
	 * @author mp
	 * @date 2015-3-24 下午4:54:01
	 * @param openId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> pay_m (String openId, String amt) throws Exception{
		
		Player player = PlayerMapUtil.getPlayerByOpenId(openId);
		
		String msdkUrl = SysConfigUtil.getValue("msdk.url");
		
		HashMap<String, String> retMap = null;
		
		// http 请求方法
		String method = "GET";
		
		// http 请求URI
		String uri = "/mpay/pay_m";
		
		// 申请的appkey, 参数后需要加&
		String appkey = appKey + "&";
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		// 必选参数
		params.put("openid", player.getOpenId());
		params.put("openkey", player.getOpenkey());
		params.put("pay_token", player.getPay_token());
		params.put("appid", appId);
		params.put("ts", DateUtil.getCurrSec() + "");
		params.put("pf", player.getPf());
		params.put("pfkey", player.getPfkey());
		params.put("zoneid", player.getZoneid());
		params.put("format", "json");
		params.put("amt", amt);
		
		// 可选参数，根据情况添加
//		params.put("userip", "xxx.xxx.xxx.xxx");//（可选）用户的外网IP
//		params.put("payitem", "1234567");//（可选）道具名称
//		params.put("accounttype", "common");//（可选）帐户类型ID， 基础货币（common） 安全货币（security）， 不填默认common
		
		String sig = SnsSigCheck.makeSig(method, uri, params, appkey);
//		System.out.println("sig = " + sig);
		params.put("sig", StringUtil.encode(sig));
		
		InputStream is = null;
		try{
			
			String url = "http://"+ msdkUrl +"" + uri + "?" + StringUtil.toParams(params, "&");
//			System.out.println("url = " + url);
			
			// 组装Cookie
			Map<String, String> cookies = new HashMap<String, String>();
			cookies.put("session_id", player.getSession_id());
			cookies.put("session_type", player.getSession_type());
			cookies.put("org_loc", StringUtil.encode(uri));
//		    cookies.put("appip", "xxx.xxx.xxx.xxx");//可选
			
			String cookie = StringUtil.toParams(cookies, ";");
	
			// 发起http连接
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod(method);
			conn.addRequestProperty("Cookie", cookie);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.connect();
			is = conn.getInputStream();
			String content = InputStreamUtils.InputStreamTOString(is);
			retMap = JsonUtil.fromJson(content, HashMap.class);
//			System.out.println(content);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return retMap;
	}
	
	/**
	 * 直接赠送接口
	 * @author mp
	 * @date 2015-3-24 下午5:05:26
	 * @param openId
	 * @param amt
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> present_m (String openId, String amt) throws Exception{
		
		Player player = PlayerMapUtil.getPlayerByOpenId(openId);
		
		String msdkUrl = SysConfigUtil.getValue("msdk.url");
		
		HashMap<String, String> retMap = null;
		
		// http 请求方法
		String method = "GET";
		
		// http 请求URI
		String uri = "/mpay/present_m";
		
		// 申请的appkey, 参数后需要加&
		String appkey = appKey + "&";
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		// 必选参数
		params.put("openid", player.getOpenId());
		params.put("openkey", player.getOpenkey());
		params.put("pay_token", player.getPay_token());
		params.put("appid", appId);
		params.put("ts", DateUtil.getCurrSec() + "");
		params.put("pf", player.getPf());
		params.put("pfkey", player.getPfkey());
		params.put("zoneid", player.getZoneid());
		params.put("format", "json");

		params.put("discountid", "UM15031316525774");
		params.put("giftid", "1325324172PID20150313165257833");
		params.put("presenttimes", amt);
		
		// 可选参数，根据情况添加
//		params.put("userip", "xxx.xxx.xxx.xxx");
		
		String sig = SnsSigCheck.makeSig(method, uri, params, appkey);
//		System.out.println("sig = " + sig);
		params.put("sig", StringUtil.encode(sig));
		
		InputStream is = null;
		try{
			
			String url = "http://"+ msdkUrl +"" + uri + "?" + StringUtil.toParams(params, "&");
//			System.out.println("url = " + url);
			
			// 组装Cookie
			Map<String, String> cookies = new HashMap<String, String>();
			cookies.put("session_id", player.getSession_id());
			cookies.put("session_type", player.getSession_type());
			cookies.put("org_loc", StringUtil.encode(uri));
//		    cookies.put("appip", "xxx.xxx.xxx.xxx");//可选
			
			String cookie = StringUtil.toParams(cookies, ";");
	
			// 发起http连接
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod(method);
			conn.addRequestProperty("Cookie", cookie);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.connect();
			is = conn.getInputStream();
			String content = InputStreamUtils.InputStreamTOString(is);
			retMap = JsonUtil.fromJson(content, HashMap.class);
//			System.out.println(content);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return retMap;
	}
	
}
