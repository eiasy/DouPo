package mmo.http;

import org.apache.mina.core.session.IoSession;

import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

public interface ICallback {
	public HttpResponseMessage callback(IoSession session, String context, HttpRequestMessage request);
}
