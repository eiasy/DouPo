package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.AccountDatabaseHeartbeat;
import mmo.common.module.account.doupo.cache.thread.database.UpdatePhoneRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20011_bindPhone extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_2_NO = "账号不存在";
	private String MSG_ERR  = "操作失败";

	public A20011_bindPhone() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int accountId = getInt(request, HttpCData.A20001.accountId);
			String phone = request.getParameter(HttpCData.A20001.phone);

			UserAccount currUA = AccountCache.getInstance().getUserAccount(accountId);
			if (currUA != null) {
				UserAccount oldUA = AccountCache.getInstance().bindPhone(currUA, phone);
				if (oldUA == null || oldUA.getAccountId() != accountId) {
					AccountDatabaseHeartbeat.getInstance().execute(new UpdatePhoneRun(oldUA, currUA));
				}
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			} else {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, MSG_2_NO);
			}

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20011_bindPhone", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
