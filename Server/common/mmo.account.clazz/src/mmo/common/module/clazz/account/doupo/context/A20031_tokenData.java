package mmo.common.module.clazz.account.doupo.context;

import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.AddTokenDataRunnable;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.common.module.account.doupo.security.TokenData;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class A20031_tokenData extends AContextHandle {

	public A20031_tokenData() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		String data = request.getParameter("data");
		long time = request.getLongParameter("time");
		String type = request.getParameter("type");
		TokenData tokenData = new TokenData();
		tokenData.setKey(MD5.getHashString(data + time + type));
		tokenData.setType(type);
		tokenData.setData(data);
		SecurityCodeManager.addTokenData(tokenData);
		AccountValidateHeartbeat.getInstance().execute(new AddTokenDataRunnable(tokenData));
		return sendToClient("ok");
	}

}
