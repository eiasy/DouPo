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

public class A20009_roleAlterPassword extends AContextHandle {

	private String MSG_1_OK      = "OK";
	private String MSG_3_ACCOUNT = "账号信息不匹配";
	private String MSG_2_NO      = "账号不存在";
	private String MSG_ERR       = "操作失败";

	public A20009_roleAlterPassword() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int belongto = getInt(request, HttpCData.A20001.belongto);
			String userid = request.getParameter(HttpCData.A20001.userid);
			String password = request.getParameter(HttpCData.A20001.password);
			String newPassword = request.getParameter(HttpCData.A20001.newpassword);
			String channelSub = request.getParameter(HttpCData.A20001.channelSub);
			int accountId = getInt(request, HttpCData.A20001.accountId);

			UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
			if (ua == null) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, MSG_2_NO);
			} else {
				if (belongto == ua.getBelongto() && channelSub.equals(ua.getChannelSub()) && userid.equals(ua.getUserid())
				        && MD5.getHashString(password).equals(ua.getPassword())) {
					ua.setPassword(MD5.getHashString(newPassword));
					ua.setPwdState((byte) 1);
					ThreadManager.accessDatabase(new UserAlertPasswordRun(ua));

					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
					loginResult.put(HttpCData.A20001.message, MSG_1_OK);
				} else {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_3_ACCOUNT);
					loginResult.put(HttpCData.A20001.message, MSG_3_ACCOUNT);
				}
			}

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20009_roleAlterPassword", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
