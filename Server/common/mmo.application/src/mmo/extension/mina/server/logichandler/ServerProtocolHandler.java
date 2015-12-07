package mmo.extension.mina.server.logichandler;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import mmo.extension.application.ApplicationConfig;
import mmo.extension.application.INetApplication;
import mmo.extension.mina.codec.CodecFactory;
import mmo.extension.mina.codec.LogNet;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.heartbeat.LogicHeartbeat;
import mmo.tools.util.StringUtil;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

public class ServerProtocolHandler implements IoHandler {
	public static AtomicLong         sessionIdGenerator = new AtomicLong(1);
	protected final static Logger    log                = Logger.getLogger(ServerProtocolHandler.class);
	protected int                    netConfim;
	protected LogicHeartbeat         logicHeartbeat     = null;
	protected INetApplication           application;
	protected ILogicHandler          logicHandler;
	private static ApplicationConfig ac                 = ApplicationConfig.getInstance();
	private static final String      TYPE               = "SEND";
	private long                     logTime            = System.currentTimeMillis();
	private long                     total              = 0;
	private int                      pkgCount           = 0;
	private int                      pkgMax             = 0;
	private CodecFactory             codecFactory;

	public CodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(CodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	public ServerProtocolHandler() {

	}

	public ServerProtocolHandler(int netConfim, INetApplication application) {
		this.netConfim = netConfim;
		setApplication(application);
	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
	}

	public INetApplication getApplication() {
		return application;
	}

	public ILogicHandler getLogicHandler() {
		return logicHandler;
	}

	public int getNetConfim() {
		return netConfim;
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
		UserSession us = (UserSession) (session.getAttribute(UserSession.KEY_SESSION));
		application.closeConnect(us);
		us.close(true);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		try {
			SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
			cfg.setReceiveBufferSize(2048);
			cfg.setReadBufferSize(10240);
			cfg.setKeepAlive(true);
			cfg.setSoLinger(0); // 这个是根本解决问题的设置
			UserSession userSession = application.generatorNetSession();
			NetRole ur = application.generatorRole();
			ur.setSessionId(ApplicationConfig.getInstance().getAppId() + StringUtil.getChuanMa());
			userSession.setRole(ur);
			ur.setNetSession(userSession);
			userSession.setUserSession(session);
			session.setAttribute(UserSession.KEY_SESSION, userSession);
			userSession.sessionCreated();
			application.connected(userSession);
			System.out.println("建立连接：" + session.getRemoteAddress().toString() + " - " + Thread.currentThread());
			LoggerError.messageLog.warn("建立连接：" + session.getRemoteAddress().toString() + " - " + Thread.currentThread());
			application.createConnect(userSession);
		} catch (Exception e) {
			log.error("创建链接失败", e);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
		if (session != null) {
			Object obj = session.getAttribute(UserSession.KEY_SESSION);
			if (obj != null) {
				UserSession us = (UserSession) obj;
				us.heartbeat();

			}
		}
	}

	public void sessionOpened(IoSession session) throws Exception {
		if (netConfim > 0) {
			IoBuffer pb = PacketBufferPool.getPacketBuffer();
			pb.setProtocol(netConfim);
			pb.flip();
			pb.putInt(0, pb.limit() - 4);
			session.write(pb);
		}
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

	public void setLogicHandler(ILogicHandler logicHandler) {
		this.logicHandler = logicHandler;
	}

	public void setNetConfim(int netConfim) {
		this.netConfim = netConfim;
	}
}
