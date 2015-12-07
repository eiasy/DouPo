package mmo.server.ptotobuf;

import mmo.tools.thread.heartbeat.LogicHeartbeat;

public class ServerHeartbeat extends LogicHeartbeat {
	private static final ServerHeartbeat instance = new ServerHeartbeat();

	public static final ServerHeartbeat getInstance() {
		return instance;
	}

	private ServerHeartbeat() {
	}

}