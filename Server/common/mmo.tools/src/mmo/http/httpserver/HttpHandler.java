package mmo.http.httpserver;

import org.apache.mina.core.session.IoSession;

/**
 * HTTP请求的处理接口
 * 
 * @author Ajita
 * 
 */
public interface HttpHandler {
	/**
	 * 自定义HTTP请求处理需要实现的方法
	 * 
	 * @param request
	 *            一个HTTP请求对象
	 * @return HTTP请求处理后的返回结果
	 */
	HttpResponseMessage handle(IoSession session, HttpRequestMessage request);
}
