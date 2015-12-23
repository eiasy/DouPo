package mmo.common.module.clazz.channel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nearme.oauth.log.NearMeException;
import mmo.common.account.HttpCData;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.clazz.thread.http.A20006ChannelLoginRun;
import mmo.common.module.sdk.http.HandRequestRun;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MathUtil;
import net.sf.json.JSONObject;

import com.nearme.oauth.model.AccessToken;
import com.nearme.oauth.open.AccountAgent;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ValidateOppoRun extends HandRequestRun {

	@Override
	public void run() {
		try {
			int sw = 0;
			int sh = 0;
			try {
				sw = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.screen_width));
			} catch (Exception e) {

			}
			try {
				sh = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.screen_hight));
			} catch (Exception e) {

			}
			int platform = 2;
			try {
				platform = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.product_id));
			} catch (Exception e) {
				platform = 2;
			}
			String channel = request.getParameter("channel");


			if(!"".equals(request.getParameter("oauth_token"))){
				if(oppoSdkOld(channel,platform,sw,sh)){
					return;
				}
			}else{
				if(oppoSdkNew(channel,platform,sw,sh)){
					return;
				}
			}

		} catch (Exception e) {
			LoggerError.error("登录Oppo异常", e);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(HttpCData.A20001.result, 1);
		jsonObj.put(HttpCData.A20001.message, "登录失败");
		sendToClient(jsonObj.toString());
	}

	public boolean oppoSdkNew(String channel,int platform,int sw,int sh) throws UnsupportedEncodingException {
		String ssoid = URLDecoder.decode(request.getParameter("oppo_ssoid"), "utf-8");
		String token = request.getParameter("oppo_token");

		//因 request的 getParameter 获取的 token在框架内已经被转换瞎了，所以只能自己单独获取
		String oppo_uri = request.getHeaderValue("URI");
		token = getMathStr("(?:oppo_token=)(.*?)(&)",oppo_uri);
		// 组织URL
		String url = ProjectCofigs.getParameter("oppo_url") + "?fileId=" + URLEncoder.encode(ssoid,"utf-8") + "&token=" + URLEncoder.encode(token,"utf-8");

		long timestamp = System.currentTimeMillis() / 1000; //需要时间戳
		int cpRandNum = MathUtil.getRandom(1000,10000);     //cp随机生成的数
		String basStr = generateBaseString(URLEncoder.encode(token,"utf-8"),String.valueOf(timestamp),String.valueOf(cpRandNum));
		String sign =  generateSign(basStr);

		String result = request(url,basStr,sign);

		if (result != null && !result.equals("")) {
			System.out.println("result==="+result);
			JSONObject resultJson = JSONObject.fromObject(result);
			//新的格式
			if (resultJson.containsKey("resultCode") && "200".equals(resultJson.getString("resultCode"))) {
				String id = resultJson.getString("ssoid");
				Map<String, String> message = new HashMap<String, String>();
				message.put(HttpCData.A20001.channelId, channel);
				message.put(HttpCData.A20001.belongto, "0");
				message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
				message.put(HttpCData.A20001.clientVersion, "1");
				message.put(HttpCData.A20001.productId, platform+"");
				message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
				message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
				message.put(HttpCData.A20001.deviceOS, request.getParameter(HttpCData.AccountDoupo.device_os));
				message.put(HttpCData.A20001.osVersion, request.getParameter(HttpCData.AccountDoupo.os_version));
				message.put(HttpCData.A20001.deviceUdid, request.getParameter(HttpCData.AccountDoupo.udid));
				message.put(HttpCData.A20001.deviceMac, request.getParameter(HttpCData.AccountDoupo.mac));
				message.put(HttpCData.A20001.deviceUa, request.getParameter(HttpCData.AccountDoupo.ua));
				message.put(HttpCData.A20001.phone, request.getParameter(HttpCData.AccountDoupo.phone_code));
				message.put(HttpCData.A20001.screenWidth, sw + "");
				message.put(HttpCData.A20001.screenHeight, sh + "");
				message.put(HttpCData.A20001.phoneType, request.getParameter(HttpCData.AccountDoupo.phone_type));
				message.put(HttpCData.A20001.clientCode, request.getParameter(HttpParameter.Account.code_version));
				message.put(HttpCData.A20001.permit, channel);
				message.put(HttpCData.A20001.feature, "");
				message.put(HttpCData.A20001.registerFrom, "1");
				message.put(HttpCData.A20001.userid, id);
				if(resultJson.containsKey("name")){
					message.put(HttpCData.A20001.username, resultJson.getString("name"));
				}else if(resultJson.containsKey("userName")){
					message.put(HttpCData.A20001.username, resultJson.getString("userName"));
				}else{
					message.put(HttpCData.A20001.username, id);
				}
				message.put(HttpCData.A20001.loginServer, ApplicationConfig.getInstance().getAppId() + "-"
						+ ApplicationConfig.getInstance().getAppName());
				message.put(HttpCData.A20001.serverVersion, ApplicationConfig.getInstance().getCodeVersion());
				String addIp = "";
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					addIp = sa.toString();
				}
				message.put(HttpCData.A20001.remoteAddress, addIp);
				message.put(HttpCData.A20001.real_ip, request.getHeaderValue(HttpCData.A20001.real_ip));
				message.put(HttpCData.A20001.customData, "");

				ThreadManager.requestHttp("account", new A20006ChannelLoginRun(session, message));
				return true;
			}
		}
		return false;
	}

	public boolean oppoSdkOld(String channel,int platform,int sw,int sh) throws UnsupportedEncodingException, NearMeException {
		String oauth_token = request.getParameter("oauth_token");
		oauth_token = URLDecoder.decode(oauth_token, "utf-8");
		String oauth_token_secret = request.getParameter("oauth_token_secret");
		oauth_token_secret = URLDecoder.decode(oauth_token_secret, "utf-8");
		String result = AccountAgent.getInstance().getGCUserInfo(new AccessToken(oauth_token, oauth_token_secret));

		if (result != null && !result.equals("")) {
			System.out.println("result==="+result);
			JSONObject briefUserJson = JSONObject.fromObject(result);
			if (briefUserJson.containsKey("BriefUser") && !"".equals(briefUserJson.getString("BriefUser"))) {
				JSONObject resultJson = JSONObject.fromObject(briefUserJson.getString("BriefUser"));
				String id = resultJson.getString("id");
				Map<String, String> message = new HashMap<String, String>();
				message.put(HttpCData.A20001.channelId, channel);
				message.put(HttpCData.A20001.belongto, "0");
				message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
				message.put(HttpCData.A20001.clientVersion, "1");
				message.put(HttpCData.A20001.productId, platform+"");
				message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
				message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
				message.put(HttpCData.A20001.deviceOS, request.getParameter(HttpCData.AccountDoupo.device_os));
				message.put(HttpCData.A20001.osVersion, request.getParameter(HttpCData.AccountDoupo.os_version));
				message.put(HttpCData.A20001.deviceUdid, request.getParameter(HttpCData.AccountDoupo.udid));
				message.put(HttpCData.A20001.deviceMac, request.getParameter(HttpCData.AccountDoupo.mac));
				message.put(HttpCData.A20001.deviceUa, request.getParameter(HttpCData.AccountDoupo.ua));
				message.put(HttpCData.A20001.phone, request.getParameter(HttpCData.AccountDoupo.phone_code));
				message.put(HttpCData.A20001.screenWidth, sw + "");
				message.put(HttpCData.A20001.screenHeight, sh + "");
				message.put(HttpCData.A20001.phoneType, request.getParameter(HttpCData.AccountDoupo.phone_type));
				message.put(HttpCData.A20001.clientCode, request.getParameter(HttpParameter.Account.code_version));
				message.put(HttpCData.A20001.permit, channel);
				message.put(HttpCData.A20001.feature, "");
				message.put(HttpCData.A20001.registerFrom, "1");
				message.put(HttpCData.A20001.userid, id);
				if(resultJson.containsKey("name")){
					message.put(HttpCData.A20001.username, resultJson.getString("name"));
				}else if(resultJson.containsKey("userName")){
					message.put(HttpCData.A20001.username, resultJson.getString("userName"));
				}else{
					message.put(HttpCData.A20001.username, id);
				}
				message.put(HttpCData.A20001.loginServer, ApplicationConfig.getInstance().getAppId() + "-"
						+ ApplicationConfig.getInstance().getAppName());
				message.put(HttpCData.A20001.serverVersion, ApplicationConfig.getInstance().getCodeVersion());
				String addIp = "";
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					addIp = sa.toString();
				}
				message.put(HttpCData.A20001.remoteAddress, addIp);
				message.put(HttpCData.A20001.real_ip, request.getHeaderValue(HttpCData.A20001.real_ip));
				message.put(HttpCData.A20001.customData, "");

				ThreadManager.requestHttp("account", new A20006ChannelLoginRun(session, message));
				return true;
			}
		}
		return false;
	}


	public static final String OAUTH_CONSUMER_KEY = "oauthConsumerKey";
	public static final String OAUTH_TOKEN = "oauthToken";
	public static final String OAUTH_SIGNATURE_METHOD = "oauthSignatureMethod";
	public static final String OAUTH_SIGNATURE = "oauthSignature";
	public static final String OAUTH_TIMESTAMP = "oauthTimestamp";
	public static final String OAUTH_NONCE = "oauthNonce";
	public static final String OAUTH_VERSION = "oauthVersion";
	public static final String CONST_SIGNATURE_METHOD = "HMAC-SHA1";
	public static final String CONST_OAUTH_VERSION = "1.0";

	public static String generateBaseString(String token,String timestamp, String nonce) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(OAUTH_CONSUMER_KEY).
					append("=").
					append(URLEncoder.encode(ProjectCofigs.getParameter("oppo_key"), "UTF-8")).
					append("&").
					append(OAUTH_TOKEN).
					append("=").
					append(URLEncoder.encode(token, "UTF-8")).
					append("&").
					append(OAUTH_SIGNATURE_METHOD).
					append("=").
					append(URLEncoder.encode(CONST_SIGNATURE_METHOD, "UTF-8")).
					append("&").
					append(OAUTH_TIMESTAMP).
					append("=").
					append(URLEncoder.encode(timestamp, "UTF-8")).
					append("&").
					append(OAUTH_NONCE).
					append("=").
					append(URLEncoder.encode(nonce, "UTF-8")).
					append("&").
					append(OAUTH_VERSION).
					append("=").
					append(URLEncoder.encode(CONST_OAUTH_VERSION, "UTF-8")).
					append("&");

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sb.toString();
	}

	public static String generateSign(String baseStr) throws UnsupportedEncodingException {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = null;
			String oauthSignatureKey = ProjectCofigs.getParameter("oppo_appsecret") + "&";
			spec = new SecretKeySpec(oauthSignatureKey.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(baseStr.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return URLEncoder.encode(String.valueOf(base64Encode(byteHMAC)), "UTF-8");
	}

	public static char[] base64Encode(byte[] data) {
		final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
		char[] out = new char[((data.length + 2) / 3) * 4];
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;
			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	public static String getMathStr(String regex,String contents){
		Matcher m = Pattern.compile(regex).matcher(contents);
		while (m.find()) {
			for (int j = 0; j <= m.groupCount(); j++) {
				if(j == 1){
					return m.group(j);
				}
			}
		}
		return "";
	}

	public final static String request(String address,String param,String oauthSignature) {
		HttpGet httpGet = new HttpGet(address);
		httpGet.setHeader("param",param);
		httpGet.setHeader("oauthSignature",oauthSignature);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// 发送请求
			CloseableHttpResponse response = httpclient.execute(httpGet);
			// 返回实体
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		} finally {
			httpGet.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e1) {
				LoggerError.error("请求Http异常@" + address, e1);
			}
		}
	}
}
