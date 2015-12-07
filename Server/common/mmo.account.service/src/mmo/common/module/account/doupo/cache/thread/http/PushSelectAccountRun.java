package mmo.common.module.account.doupo.cache.thread.http;

import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun;

public class PushSelectAccountRun extends RequestHttpRun {
	private static final String CHARGE_PATCH = "ACCOUNT_LIST";

	private String              callback;

	public PushSelectAccountRun(String callback, Map<String, String> parameter) {
		super(parameter);
		this.callback = callback;
	}

	@Override
	public void run() {

		String result = request(callback + HttpCData.ContextsManager.C_10002);
		StringBuilder sb = new StringBuilder();
		sb.append(CHARGE_PATCH);
		sb.append('|').append(result);
		LoggerError.messageLog.warn(sb.toString());
	}

}
