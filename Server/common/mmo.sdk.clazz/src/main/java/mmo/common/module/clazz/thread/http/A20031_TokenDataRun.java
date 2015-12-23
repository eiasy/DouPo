package mmo.common.module.clazz.thread.http;

import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun;
import mmo.tools.util.MD5;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

public class A20031_TokenDataRun extends RequestHttpRun {
	private IoSession session;

	public A20031_TokenDataRun(IoSession session, Map<String, String> parameter) {
		super(parameter);
		this.session = session;
	}

	@Override
	public void run() {
		NetAddress address = ProjectCofigs.getNetAddress(HttpCData.ACCOUNT_URL_NAME);
		if (address == null) {
			LoggerError.error("未设置账号中心地址");
			return;
		}
		try {
			String result = request(address.getUrl() + HttpCData.ContextsAccount.C_20031);
			if (session != null) {
				String jsonResult = "fail";
				if ("ok".equals(result)) {
					
//					jsonResult.put("state", "ok");
//					jsonResult.put("message", "验证通过");
//					jsonResult.put("token", MD5.getHash(parameter.get("data") + parameter.get("time") + parameter.get("type")));
//					return sendToClient(parameter.get("data"));
//				} else {
//					jsonResult.put("state", "fail");
//					jsonResult.put("message", "验证失败[1]");
					StringBuilder sb = new StringBuilder();
					sb.append(parameter.get("data"));
					String sessionCustom = MD5.getHashString(parameter.get("data") + parameter.get("time") + parameter.get("type"));
					int index = sb.indexOf("ext");
					if (index > -1) {
						sb.insert(index + 6, sessionCustom);
					} else {
						index = sb.lastIndexOf("}");
						sb.insert(index, ",\"ext\":\"" + sessionCustom + "\"");
					}
					jsonResult = sb.toString();
				}
				
				HttpResponseMessage response = new HttpResponseMessage();
				response.setContentType("text/plain;charset=utf-8");
				response.appendBody(jsonResult);
				session.write(response).addListener(IoFutureListener.CLOSE);
			}
		} catch (Exception e) {
			LoggerError.error("渠道用户账号登录信息出错", e);
		}
	}

}
