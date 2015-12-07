package mmo.extension.mina.client.logichandler;

import java.net.SocketAddress;

import mmo.extension.application.ApplicationConfig;
import mmo.extension.application.INetApplication;
import mmo.extension.mina.client.ConnectorManager;
import mmo.extension.mina.codec.CodecFactory;
import mmo.extension.mina.codec.LogNet;
import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.heartbeat.LogicHeartbeat;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientProtocolHandlerExtention implements IoHandler {
	protected final static Logger    log            = Logger.getLogger(ClientProtocolHandlerExtention.class);
	private ILogicHandler            logicHandler;
	private INetApplication             application;
	protected LogicHeartbeat         logicHeartbeat = null;
	private ConnectorManager         connectorManager;

	private static ApplicationConfig ac             = ApplicationConfig.getInstance();
	private static final String      TYPE           = "SEND";
	private long                     logTime        = System.currentTimeMillis();
	private long                     total          = 0;
	private int                      pkgCount       = 0;
	private int                      pkgMax         = 0;
	private CodecFactory             codecFactory;

	public CodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(CodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	public ClientProtocolHandlerExtention(INetApplication application) {
		setApplication(application);
	}

	public void setApplication(INetApplication application) {
		this.application = application;
		if (application != null) {
			logicHandler = application.getLogicHandler();
			logicHeartbeat = application.getHeartbeat();
			if (logicHeartbeat == null) {
				logicHeartbeat = new LogicHeartbeat();
			}
			ThreadObserver.getInstance().registerHeartbeat("Logic-" + application.getName(), logicHeartbeat);
		}
	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		// log.error("异常信息",arg1);
	}

	public ConnectorManager getConnectorManager() {
		return connectorManager;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (logicHandler != null) {
			IoBuffer pb = (IoBuffer) message;
			if (logicHeartbeat != null) {
				logicHeartbeat.addPacketEvent((UserSession) session.getAttribute(UserSession.KEY_SESSION), pb, logicHandler);
			}
		}
	}

	@Override
	public void messageSent(IoSession arg0, Object message) throws Exception {
		if (ac.isListenNet()) {
			IoBuffer pb = (IoBuffer) message;
			int dataLength = pb.limit();
			long curTime = System.currentTimeMillis();
			if (curTime > logTime + ac.getRefreshOffset()) {
				long offset = curTime - logTime;
				logTime = curTime;
				LogNet.loggerNetPkg(TYPE, pkgMax, pkgCount, total, offset, codecFactory.getType(), codecFactory.getDesc());
				pkgMax = dataLength;
				pkgCount = 1;
				total = dataLength;
			} else {
				if (dataLength > pkgMax) {
					pkgMax = dataLength;
				}
				pkgCount++;
				total += dataLength;
			}
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		final IoSession temp = session;
		final UserSession us = (UserSession) (session.getAttribute(UserSession.KEY_SESSION));
		if (us != null) {
			us.outline();
		}
		if (connectorManager != null) {
			new Thread() {
				public void run() {
					connectorManager.connect((SocketAddress) temp.getAttribute(UserSession.REMOTE_ADDRESS), us.getReconnectCount());
				}
			}.start();
		} else {
			us.close(true);
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		UserSession userSession = application.generatorNetSession();
		NetRole ur = application.generatorRole();
		userSession.setRole(ur);
		ur.setNetSession(userSession);
		userSession.setUserSession(session);
		session.setAttribute(UserSession.KEY_SESSION, userSession);
		session.setAttribute(UserSession.REMOTE_ADDRESS, session.getRemoteAddress());
		userSession.sessionCreated();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
		Object obj = session.getAttribute(UserSession.KEY_SESSION);
		if (obj != null && obj instanceof UserSession) {
			UserSession us = (UserSession) obj;
			us.heartbeat();
		}
	}

	public void sessionOpened(IoSession session) throws Exception {
	}

	public void setConnectorManager(ConnectorManager connectorManager) {
		this.connectorManager = connectorManager;
	}

}
