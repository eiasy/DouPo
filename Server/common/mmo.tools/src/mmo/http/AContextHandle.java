package mmo.http;

import org.apache.mina.core.session.IoSession;

import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

public abstract class AContextHandle {
	abstract public HttpResponseMessage callback(IoSession session, HttpRequestMessage request);

	protected int getInt(HttpRequestMessage request, String name) {
		return Integer.parseInt(request.getParameter(name));
	}

	protected int getIntRelax(HttpRequestMessage request, String name) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	protected long getLongRelax(HttpRequestMessage request, String name) {
		try {
			return Long.parseLong(request.getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	protected double getDoubleRelax(HttpRequestMessage request, String name) {
		try {
			return Double.parseDouble(request.getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}

	protected short getShort(HttpRequestMessage request, String name) {
		return Short.parseShort(request.getParameter(name));
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		return sendToClient(content, "text/plain;charset=utf-8");
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content, String contentType) {
		HttpResponseMessage response = new HttpResponseMessage();
		if (contentType != null) {
			response.setContentType(contentType);
		} else {
			response.setContentType("text/plain;charset=utf-8");
		}
		response.appendBody(content);
		return response;
	}
}
