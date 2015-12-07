package mmo.common.module.clazz.account.doupo.context;

import java.util.Date;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.FreezeAccountDBRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20017_freezeAccount extends AContextHandle {
	private String MSG_ERR  = "操作失败";

	public A20017_freezeAccount() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int accountId =  getInt(request, HttpCData.A20001.accountId);
			int day =  getInt(request, HttpCData.A20001.days);

			UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
			if (ua == null) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, "账号ID【" + accountId + "】不存在");
			} else {
				ua.setTimeFreeze(System.currentTimeMillis() + day * 24 * 60 * 60 * 1000);
				ua.setFreezeDay(System.currentTimeMillis());
				ThreadManager.accessDatabase(new FreezeAccountDBRun(ua));
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				loginResult.put(HttpCData.A20001.message,"账号ID【" + accountId + "】冻结截止时间为：" + DateUtil.formatDate(new Date(ua.getTimeFreeze())));
			}
			
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20017_freezeAccount", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
