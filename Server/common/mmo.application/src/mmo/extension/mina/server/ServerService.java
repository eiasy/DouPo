package mmo.extension.mina.server;

import java.net.InetSocketAddress;

import mmo.extension.application.INetApplication;
import mmo.extension.mina.NetService;
import mmo.extension.mina.codec.CodecFactory;
import mmo.extension.mina.server.logichandler.ServerProtocolHandler;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.config.Constants;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ServerService extends NetService {
	/** 日志管理类 */
	private int            port;
	private int            netConfirm;
	private SocketAcceptor acceptor;
	protected INetApplication application;
	private CodecFactory   codecFactory;

	public ServerService(int port, int netConfirm, INetApplication application) {
		this(port, netConfirm, application, null);
	}

	public ServerService(int port, INetApplication application) {
		this(port, 0, application, null);
	}

	public ServerService(int port, int netConfirm, INetApplication application, ServerProtocolHandler protocolHandler) {
		codecFactory = new CodecFactory("Server", application.getCharset());
		this.port = port;
		this.netConfirm = netConfirm;
		this.application = application;
		acceptor = new NioSocketAcceptor(Constants.CPU_NUM + 1);
		acceptor.setBacklog(1024);
		acceptor.getSessionConfig().setTcpNoDelay(true);
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setSendBufferSize(10240);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 200);
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(codecFactory));
		codecFactory.appendDesc(port + "");
		chain.addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor()));
		// chain.addLast("executor", new ExecutorFilter(Constants.CPU_NUM, Constants.CPU_NUM * 2+1));
		application.init();
		application.start();
		if (protocolHandler == null) {
			protocolHandler = new ServerProtocolHandler(netConfirm, application);
		} else {
			protocolHandler.setNetConfim(netConfirm);
			protocolHandler.setApplication(application);
		}
		protocolHandler.setCodecFactory(codecFactory);
		acceptor.setHandler(protocolHandler);
	}

	public void startServer() {
		try {
			LoggerError.messageLog.warn("starting port:" + port);
			System.out.println("starting port:" + port);
			acceptor.bind(new InetSocketAddress(port));
			LoggerError.messageLog.warn("port is started:" + port);
			System.out.println("port is started:" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getNetConfirm() {
		return netConfirm;
	}

	public void setNetConfirm(int netConfirm) {
		this.netConfirm = netConfirm;
	}

	public SocketAcceptor getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(SocketAcceptor acceptor) {
		this.acceptor = acceptor;
	}
}