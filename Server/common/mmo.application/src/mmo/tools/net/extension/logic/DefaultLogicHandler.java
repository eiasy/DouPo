package mmo.tools.net.extension.logic;

import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class DefaultLogicHandler implements ILogicHandler {
	public boolean handlerReceiveData(UserSession session, IoBuffer packet) {
		return false;
	}

	@Override
	public boolean handlerReceiveData(NetRole role, IoBuffer packet) {
		return false;
	}
}
