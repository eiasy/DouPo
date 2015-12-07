package mmo.http.httpserver;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {
	private HttpHandler handler;

	public HttpHandler getHandler() {
		return handler;
	}

	public void setHandler(HttpHandler handler) {
		this.handler = handler;
	}

	@Override
	public void sessionOpened(IoSession session) {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		// Check that we can service the request context
		HttpRequestMessage request = (HttpRequestMessage) message;
		HttpResponseMessage response = handler.handle(session, request);
		// HttpResponseMessage response = new HttpResponseMessage();
		// response.setContentType("text/plain");
		// response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		// response.appendBody("CONNECTED");

		// msg.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		// byte[] b = new byte[ta.buffer.limit()];
		// ta.buffer.rewind().get(b);
		// msg.appendBody(b);
		// System.out.println("####################");
		// System.out.println("  GET_TILE RESPONSE SENT - ATTACHMENT GOOD DIAMOND.SI="+d.si+
		// ", "+new
		// java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss.SSS").format(new
		// java.util.Date()));
		// System.out.println("#################### - status="+ta.state+", index="+message.getIndex());

		// // Unknown request
		// response = new HttpResponseMessage();
		// response.setResponseCode(HttpResponseMessage.HTTP_STATUS_NOT_FOUND);
		// response.appendBody(String.format(
		// "<html><body><h1>UNKNOWN REQUEST %d</h1></body></html>",
		// HttpResponseMessage.HTTP_STATUS_NOT_FOUND));

		if (response != null) {
			session.write(response).addListener(IoFutureListener.CLOSE);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		session.close(false);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close(false);
	}
}
