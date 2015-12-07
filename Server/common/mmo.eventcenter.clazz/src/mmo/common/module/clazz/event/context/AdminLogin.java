package mmo.common.module.clazz.event.context;

import java.net.SocketAddress;

import mmo.common.module.clazz.event.run.UpdateAdminDBRun;
import mmo.common.service.eventcenter.admin.AdminManager;
import mmo.common.service.eventcenter.module.Admin;
import mmo.common.service.eventcenter.service.Service;
import mmo.common.service.eventcenter.thread.ThreadManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminLogin extends AEventContextHandle {
	private static String USER_ID = "userId";
	private static String USER_PWD = "userPwd";

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			String addIp = "";
			SocketAddress sa = session.getRemoteAddress();
			if (sa != null) {
				addIp = sa.toString();
			}
			
			String userId = request.getParameter(USER_ID);
			String userPwd = request.getParameter(USER_PWD);
			Admin admin = Service.getInstance().getAdminService().getAdmin(" (userId='" + userId + "' or userName='" + userId + "') and userPwd='" + userPwd+"'");
			JSONObject json = new JSONObject();
			if (admin == null) {
				json.put("code", "fail");
				json.put("message", "账号不存在或密码不正确");
				LoggerError.warn("login|fail|"+userId+"|"+addIp);
			} else {
				admin.setSessionId(AdminManager.generateSession(userId, addIp));
				admin.setOvertime(System.currentTimeMillis()+1000*60*60);
				json.put("code", "ok");
				json.put("message", "登录成功");
				json.put("userId", admin.getUserId());
				json.put("powers", admin.getPowers());
				json.put("sessionId", admin.getSessionId());
				LoggerError.warn("login|success|"+userId+"|"+addIp);
				ThreadManager.handleContext(new UpdateAdminDBRun(admin));
			}
			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		return sendToClient(json.toString());
	}
}
