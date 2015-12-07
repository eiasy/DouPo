package mmo.tools.thread.runnable;

import java.util.Map;

import mmo.tools.net.HttpsUtil;

abstract public class RequestHttpRun implements IHttpRequest {
	protected Map<String, String> parameter;

	public RequestHttpRun(Map<String, String> parameter) {
		this.parameter = parameter;
	}

	protected String request(String address) {
		return HttpsUtil.request(address, HttpsUtil.httpBuildQuery(parameter));
	}
}
