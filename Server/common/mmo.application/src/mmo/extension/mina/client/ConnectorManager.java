package mmo.extension.mina.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import mmo.extension.application.INetApplication;
import mmo.extension.mina.NetService;
import mmo.extension.mina.client.logichandler.ClientProtocolHandlerExtention;
import mmo.extension.mina.codec.CodecFactory;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ConnectorManager extends NetService {
	/** 日志管理类 */
	protected IoConnector     connector;
	protected INetApplication application;
	protected CodecFactory    codecFactory;

	public ConnectorManager(INetApplication application) {
		init(application);
	}

	protected void init(INetApplication application) {
		codecFactory = new CodecFactory("Connector", application.getCharset());
		this.application = application;
		application.init();
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(5000);
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 180);
		connector.getSessionConfig().setReadBufferSize(10240);
		IoFilter CODEC_FILTER = new ProtocolCodecFilter(codecFactory);
		connector.getFilterChain().addLast("codec", CODEC_FILTER);
		connector.getFilterChain().addLast("mdc", new MdcInjectionFilter());
		connector.getFilterChain().addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor()));
		ClientProtocolHandlerExtention gh = new ClientProtocolHandlerExtention(application);
		gh.setCodecFactory(codecFactory);
		application.start();
		gh.setConnectorManager(this);
		connector.setHandler(gh);
	}

	/***
	 * 
	 * @param address
	 *            远程地址
	 * @param connectCount
	 *            尝试连接的次数(-1无限制次数；0代表不进行重连；>0为重连次数)
	 * @return
	 */
	public UserSession connect(SocketAddress address, int connectCount) {
		try {
			int count = 0;
			ConnectFuture future = null;
			IoSession session = null;
			if (connectCount == -1) {
				connectCount = Integer.MAX_VALUE;
			}
			boolean reConnect = true;
			while (reConnect) {
				LoggerError.messageLog.warn("try connect - " + ++count + address);
				System.out.println("try connect - " + ++count + address);
				future = connector.connect(address);
				future.awaitUninterruptibly();
				if (future.isConnected()) {
					session = future.getSession();
					LoggerError.messageLog.warn("connected success:" + address);
					System.out.println("connected success:" + address);
					UserSession userSession = (UserSession) session.getAttribute(UserSession.KEY_SESSION);
					userSession.setReconnectCount(connectCount);
					application.connected(userSession);
					codecFactory.appendDesc(address.toString());
					reConnect = false;
					return userSession;
				}
				LoggerError.messageLog.warn("connect fail：" + address);
				System.out.println("connect fail：" + address);
				connectCount--;
				if (connectCount < 1) {
					reConnect = false;
				} else {
					Thread.sleep(10000);
				}
			}
		} catch (Exception e) {
			LoggerError.error("net error", e);// TODO: handle exception
		}
		return null;
	}

	public INetApplication getApplication() {
		return application;
	}

	public UserSession connect(String host, int port) {
		return connect(host, port, 0);
	}

	public UserSession connect(String host, int port, int connectCount) {
		return connect(new InetSocketAddress(host, port), connectCount);
	}

	public UserSession connect(SocketAddress address) {
		return connect(address, 0);
	}
}
