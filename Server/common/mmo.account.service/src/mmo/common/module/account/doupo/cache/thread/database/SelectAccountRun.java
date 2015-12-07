package mmo.common.module.account.doupo.cache.thread.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.AccountDetail;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.http.PushSelectAccountRun;
import mmo.tools.thread.runnable.IDatabaseRunnable;
import net.sf.json.JSONObject;

public class SelectAccountRun implements IDatabaseRunnable {
	private String callback;
	private String sessionId;
	private int    connectId;
	private String userId;

	public SelectAccountRun(String msResponse, String sessionId, int connectId, String userId) {
		super();
		this.callback = msResponse;
		this.sessionId = sessionId;
		this.connectId = connectId;
		this.userId = userId;
	}

	@Override
	public void run() {
		List<AccountDetail> accountList = ServiceAccount.getInstance().getUsers(userId);
		JSONObject result = new JSONObject();
		result.put(HttpCData.A20001.cdata, accountList);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(HttpCData.A20001.cdata, result.toString());
		parameters.put(HttpCData.A20001.sessionId, sessionId);
		parameters.put(HttpCData.A20001.connectId, connectId + "");
		ThreadManager.requestHttp(new PushSelectAccountRun(callback, parameters));
	}
}
