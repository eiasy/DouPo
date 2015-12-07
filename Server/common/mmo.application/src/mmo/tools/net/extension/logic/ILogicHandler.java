package mmo.tools.net.extension.logic;

import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public interface ILogicHandler {
	public static final int PACKET_BLOCK = 0xFFFFFF;

	public boolean handlerReceiveData(NetRole role, IoBuffer packet);

	public boolean handlerReceiveData(UserSession session, IoBuffer packet);
}
