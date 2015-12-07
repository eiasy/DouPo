package mmo.common.module.clazz.event.context;

import java.util.List;

import mmo.common.bean.bi.EventDefault;
import mmo.common.service.eventcenter.service.Service;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectGoods extends AEventContextHandle {
	private int pageCount = 100;

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			String platform = request.getParameter("platform");
			String serverTag = request.getParameter("serverTag");
			String eventTag = request.getParameter("eventTag");
			String value1string = request.getParameter("value1string");
			String value2string = request.getParameter("value2string");
			String channelTag = request.getParameter("channelTag");
			String channelSub = request.getParameter("channelSub");
			String userId = request.getParameter("userId");
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");
			int targetPage = request.getIntParameter("page");
			String startTime = request.getParameter("start");
			String stopTime = request.getParameter("stop");
			int count = Service.getInstance().getAdminDefaultService().getCount(platform, serverTag, eventTag, value1string, value2string, channelTag, channelSub, userId, roleName, roleLevel, startTime, stopTime);
			int totalPage = count / pageCount;
			if (count % pageCount > 0) {
				totalPage = totalPage + 1;
			}
			if (targetPage > totalPage) {
				targetPage = totalPage;
			}
			if (targetPage < 1) {
				targetPage = 1;
			}
			int start = (targetPage - 1) * pageCount;
			List<EventDefault> list = Service.getInstance().getAdminDefaultService().getGoods(platform, serverTag, eventTag, value1string, value2string, channelTag, channelSub, userId, roleName, roleLevel, start, pageCount, startTime, stopTime);
			JSONObject json = new JSONObject();
			json.put("totalPage", totalPage);
			json.put("page", targetPage);
			json.put("code", "ok");
			json.put("message", "ok");
			json.put("events", list);
			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		return sendToClient(json.toString());
	}
}
