package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.UserAlertPasswordRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20018_gmAlterAccountPwd extends AContextHandle {
	private String MSG_ERR = "操作失败";

	public A20018_gmAlterAccountPwd() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int accountId = getInt(request, HttpCData.A20001.accountId);
			String newPassword = request.getParameter(HttpCData.A20001.password);

			UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
			if (ua == null) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, "账号ID【" + accountId + "】不存在");
			} else {
				ua.setPassword(MD5.getHashString(newPassword));
				ua.setPwdState((byte) 1);
				ThreadManager.accessDatabase(new UserAlertPasswordRun(ua));
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				loginResult.put(HttpCData.A20001.message, "账号ID【" + accountId + "】的密码已被重置");
			}

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20018_gmAlterAccountPwd", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
