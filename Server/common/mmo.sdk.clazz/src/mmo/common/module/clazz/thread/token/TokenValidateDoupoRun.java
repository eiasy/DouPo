package mmo.common.module.clazz.thread.token;

import mmo.common.account.HttpCData;
import mmo.common.module.clazz.callback.LoginSDKCallback;
import mmo.common.module.clazz.channel.ValidateChukongRun;
import mmo.common.module.sdk.http.HandRequestRun;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class TokenValidateDoupoRun {

	public TokenValidateDoupoRun() {
		super();
	}

	public final HttpResponseMessage handle(IoSession session, HttpRequestMessage request, LoginSDKCallback sdk) {

		String channel = request.getParameter("channel");

		try {
			if (channel != null && channel.length() > 0) {
				switch (channel) {
					case "qq": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateQQRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "uc": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateUCRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "360": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.Validate360Run").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "baidu": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateBaiduRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "xiaomi": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateXiaomiRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}

					case "huawei": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateHuaweiRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "jinli": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateJinliRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "kupai": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateKupaiRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "lianxiang": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateLianxiangRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "oppo": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateOppoRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "vivo": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateVivoRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "yiyou": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateYiyouRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "yijie": {
						HandRequestRun run = (HandRequestRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateYijieRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					case "anysdk":{
						ValidateChukongRun run = (ValidateChukongRun) sdk.getClass("mmo.common.module.clazz.channel.ValidateChukongRun").newInstance();
						run.setRequest(request);
						run.setSession(session);
						ThreadManager.requestHttp(channel, run);
						return null;
					}
					default: {
						JSONObject jsonResult = new JSONObject();
						jsonResult.put(HttpCData.A20001.result, 1);
						jsonResult.put(HttpCData.A20001.message, "登录失败");
						return sendToClient(jsonResult.toString());
					}
				}
			} else {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put(HttpCData.A20001.result, 1);
				jsonResult.put(HttpCData.A20001.message, "登录失败");
				return sendToClient(jsonResult.toString());
			}
		} catch (Exception e) {
			LoggerError.error("登录异常#parameters" + request.getParameter("params"), e);
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(HttpCData.A20001.result, 1);
			jsonResult.put(HttpCData.A20001.message, "登录失败");
			return sendToClient(jsonResult.toString());
		}
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}
}
