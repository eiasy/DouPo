package mmo.tools.net.extension.logic;

import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class RoleLogicHandler extends DefaultLogicHandler {
	private ILogicHandler logicHandler;

	public RoleLogicHandler(ILogicHandler logicHandler) {
		this.logicHandler = logicHandler;
	}

	public boolean handlerReceiveData(UserSession session, IoBuffer packet) {
		if (logicHandler != null) {
			return logicHandler.handlerReceiveData(session.getRole(), packet);
		}
		return false;
	}

	public ILogicHandler getLogicHandler() {
		return logicHandler;
	}

	public void setLogicHandler(ILogicHandler logicHandler) {
		this.logicHandler = logicHandler;
	}
}
