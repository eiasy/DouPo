package mmo.server.ptotobuf;

import mmo.extension.application.DefaultApplication;
import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.heartbeat.LogicHeartbeat;

public class GameServerApplication extends DefaultApplication {
	public static GameServerApplication instance = null;
	private ILogicHandler logicHandler;
	private static ServerHeartbeat serverHeartbeat = ServerHeartbeat
			.getInstance();

	private GameServerApplication() {
		this.name = "ServerHandler";
	}

	public synchronized static GameServerApplication getInstance() {
		if (instance == null) {
			instance = new GameServerApplication();
		}
		return instance;
	}

	public NetRole generatorRole() {
		return new NetRole();
	}

	public void init() {
		logicHandler = new Port4GameServerLogicHandler();
	}

	public void start() {
	}

	public ILogicHandler getLogicHandler() {
		return logicHandler;
	}

	@Override
	public UserSession generatorNetSession() {
		return new Port4GameServerSession();
	}

	public LogicHeartbeat getHeartbeat() {
		// TODO Auto-generated method stub
		return serverHeartbeat;
	}
}