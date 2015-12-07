package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class A20026_refreshAccountData extends AContextHandle {

	public A20026_refreshAccountData() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String accountData = request.getParameter(HttpCData.Receipt.receipt);
			int accountId = getInt(request, HttpCData.Receipt.accountId);
			String sign = request.getParameter(HttpCData.Receipt.sign);

			UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
			if (ua == null) {
				loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
				loginResult.put(HttpCData.Receipt.message, "账号数据不存在");
			} else {
				if (MD5.getHashString(accountId + ua.getUserid() + accountData).equals(sign)) {
					ua.setCustomData(accountData);
					loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_1_OK);
				} else {
					loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
					loginResult.put(HttpCData.Receipt.message, "签名未通过");
				}
			}
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20026_refreshAccountData", e);
			loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
			return sendToClient(loginResult.toString());
		}
	}

}
