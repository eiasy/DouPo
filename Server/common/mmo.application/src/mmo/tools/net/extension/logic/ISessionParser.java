package mmo.tools.net.extension.logic;

import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;


public interface ISessionParser {
	public void parse(UserSession session, IoBuffer packet);

	public int getProtocol();
}