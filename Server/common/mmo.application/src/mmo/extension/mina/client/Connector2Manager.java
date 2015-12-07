package mmo.extension.mina.client;

import mmo.extension.application.INetApplication;
import mmo.extension.mina.client.logichandler.ClientProtocolHandlerExtention;
import mmo.extension.mina.codec.Codec2Factory;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Connector2Manager extends ConnectorManager {
	public Connector2Manager(INetApplication application) {
		super(application);
	}

	protected void init(INetApplication application) {
		codecFactory = new Codec2Factory("Connector", application.getCharset());
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
}
