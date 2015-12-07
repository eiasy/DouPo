package mmo.common.module.clazz.charge.callback.bi;

import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.service.charge.advertise.IdfaManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.session.IoSession;

public class BI_10009_qiankaCheck extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject json = new JSONObject();
		try {
			String appId = request.getParameter("appid");
			String[] array = StringUtils.split(request.getParameter("idfa"), ',');
			if (array != null) {
				for (int ai = 0; ai < array.length; ai++) {
					if (IdfaManager.getInstance().isIdfaActive(appId, array[ai])) {
						json.put(array[ai], "1");
					} else {
						json.put(array[ai], "0");
					}
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理BI_10009_qiankaCheck异常", e);
		}
		return sendToClient(json.toString());
	}
}
