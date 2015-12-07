package mmo.common.module.clazz.charge.callback.channel;

import mmo.common.charge.ChargeCData;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class LoadServerCharges {

	public LoadServerCharges() {
	}

	public HttpResponseMessage callback(HttpRequestMessage request) {
		try {
			int gameid = Integer.parseInt(request.getParameter(ChargeCData.ServerCharges.gameid));
			int serverid = Integer.parseInt(request.getParameter(ChargeCData.ServerCharges.serverid));

			ThreadManager.loadServerCharges(gameid, serverid);
			JSONObject json = new JSONObject();
			json.put("code", 1);
			json.put("message", "success");
			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("LoadServerCharges", e);
			JSONObject json = new JSONObject();
			json.put("code", 2);
			json.put("message", e.getMessage());
			return sendToClient(json.toString());
		}
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}
}
