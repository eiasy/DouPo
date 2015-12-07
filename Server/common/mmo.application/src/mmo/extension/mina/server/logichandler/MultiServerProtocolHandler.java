package mmo.extension.mina.server.logichandler;

import mmo.extension.application.INetApplication;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;

public class MultiServerProtocolHandler extends ServerProtocolHandler implements IoHandler {

	public MultiServerProtocolHandler() {

	}

	public MultiServerProtocolHandler(int netConfim, INetApplication application) {
		this.netConfim = netConfim;
		setApplication(application);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (logicHandler != null) {
			IoBuffer pb = (IoBuffer) message;
			logicHandler.handlerReceiveData((UserSession) session.getAttribute(UserSession.KEY_SESSION), pb);
			PacketBufferPool.freePacketBuffer(pb);
		}
	}
}
