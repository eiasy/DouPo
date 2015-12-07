package mmo.module.gm.net;

import mmo.extension.application.DefaultApplication;
import mmo.module.gm.net.logic.GMHandler;
import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.UserSession;


public class GMApplication extends DefaultApplication {
	public static GMApplication instance = null;
	private ILogicHandler               logicHandler;

	private GMApplication() {
	}

	public synchronized static GMApplication getInstance() {
		if (instance == null) {
			instance = new GMApplication();
		}
		return instance;
	}

	public void init() {
		logicHandler = new GMHandler();
	}

	public ILogicHandler getLogicHandler() {
		return logicHandler;
	}

	@Override
	public UserSession generatorNetSession() {
		return new UserSession();
	}
}
