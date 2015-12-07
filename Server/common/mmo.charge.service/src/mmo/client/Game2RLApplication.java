package mmo.client;

import mmo.extension.application.DefaultApplication;
import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class Game2RLApplication extends DefaultApplication {
	public static Game2RLApplication instance      = null;
	private ILogicHandler            logicHandler;
	private UserSession              serverSession;

	private Game2RLApplication() {
		this.name = "ServerHandler";
	}

	public synchronized static Game2RLApplication getInstance() {
		if (instance == null) {
			instance = new Game2RLApplication();
		}
		return instance;
	}

	public void init() {
		logicHandler = new Channel2RLServerLogicHandler();
	}

	public ILogicHandler getLogicHandler() {
		return logicHandler;
	}

	@Override
	public UserSession generatorNetSession() {
		return new UserSession();
	}

	public void connected(UserSession session) {
		serverSession = session;
	}

	public UserSession getServerSession() {
		return serverSession;
	}

	public void pushData2Gateway(IoBuffer data) {
		if (serverSession == null || data == null) {
			return;
		}
		serverSession.sendData(data);
	}
}