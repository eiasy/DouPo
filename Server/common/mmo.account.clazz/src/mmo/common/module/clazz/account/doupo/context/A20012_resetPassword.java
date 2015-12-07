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

public class A20012_resetPassword extends AContextHandle {

	private String MSG_1_OK = "OK";
	private String MSG_2_NO = "手机号未绑定";
	private String MSG_ERR  = "操作失败";

	public A20012_resetPassword() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String phone = request.getParameter(HttpCData.A20001.phone);
			UserAccount ua = AccountCache.getInstance().getUserAccountByPhone(phone);
			if (ua == null) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, MSG_2_NO);
			} else {
				String password = AccountCache.generatePassword();
				ua.setPassword(MD5.getHashString(password));
				ua.setPwdState((byte) 0);
				ThreadManager.accessDatabase(new UserAlertPasswordRun(ua));
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				loginResult.put(HttpCData.A20001.message, MSG_1_OK);
				loginResult.put(HttpCData.A20001.userid, ua.getUserid());
				loginResult.put(HttpCData.A20001.password, password);
			}
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20012_resetPassword", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
