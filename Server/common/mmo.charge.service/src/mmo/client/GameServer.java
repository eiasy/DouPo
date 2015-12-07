package mmo.client;

import java.io.File;
import java.util.Date;

import mmo.extension.application.ApplicationConfig;
import mmo.extension.mina.client.ConnectorManager;
import mmo.extension.mina.server.ServerService;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.log.LoggerFilter;
import mmo.tools.log.MyLog4J;
import mmo.tools.packet.CustomBufferAllocator;
import mmo.tools.util.DateUtil;
import mmo.tools.util.FileUtil;

import org.apache.mina.core.buffer.PacketBufferPool;

public class GameServer {
	private static final String     CODE_VERSION   = "1.2.0.67(B44)";
	private static ConnectorManager connectManager = null;

	static {
		ProjectCofigs.init(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "config" + FileUtil.FILE_SEPARATOR + "configs.xml");
		ApplicationConfig.initApplicationConfig(ProjectCofigs.getFile("application"));
		String logTemplate = ProjectCofigs.getFile("log-template");
		String logConfig = ProjectCofigs.getFile("log-config");
		if (logConfig != null) {
			if (logTemplate != null) {
				LoggerFilter.filterFile(logTemplate, logConfig);
			}
			MyLog4J.init(logConfig);
		}
		PacketBufferPool.initPool(new CustomBufferAllocator(), ProjectCofigs.getPacketConfig("bigPacketBufferSize"),
		        ProjectCofigs.getPacketConfig("bigPacketBufferCount"), ProjectCofigs.getPacketConfig("packetBufferSize"),
		        ProjectCofigs.getPacketConfig("packetBufferCount"));
	}

	public static void main(String[] args) {
		startServer();
	}

	public static final void startServer() {
		String versionFile = deleteVersionFile();


		NetAddress adressManager = ProjectCofigs.getNetAddress("addressManager");
		connectManager = new ConnectorManager(Game2RLApplication.getInstance());
		connectManager.connect(adressManager.getIp(), adressManager.getPort(), -1);

		writeVersionFile(versionFile);
	}

	private final static void writeVersionFile(String versionFile) {
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtil.formatDate(new Date())).append(LoggerFilter.DIVIDE_CHAR);
		sb.append(ApplicationConfig.getInstance().getProductId()).append(LoggerFilter.DIVIDE_CHAR);
		sb.append(ApplicationConfig.getInstance().getProductName()).append(LoggerFilter.DIVIDE_CHAR);
		sb.append(ApplicationConfig.getInstance().getAppId()).append(LoggerFilter.DIVIDE_CHAR);
		sb.append(ApplicationConfig.getInstance().getAppName()).append(LoggerFilter.DIVIDE_CHAR);
		sb.append(ApplicationConfig.getInstance().getCodeVersion());
		FileUtil.writeDataToFile(versionFile, sb.toString(), "UTF-8");

		LoggerError.warn("Server code version:" + ApplicationConfig.getInstance().getCodeVersion());
		System.out.println("Server code version:" + ApplicationConfig.getInstance().getCodeVersion());
	}

	private final static String deleteVersionFile() {
		ApplicationConfig.getInstance().setCodeVersion(CODE_VERSION);
		String versionFile = ApplicationConfig.getInstance().getLogDir() + FileUtil.FILE_SEPARATOR + "fanren_"
		        + ApplicationConfig.getInstance().getAppId() + "_version";
		File _file = new File(versionFile);
		_file.deleteOnExit();
		return versionFile;
	}

}