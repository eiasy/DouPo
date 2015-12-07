package mmo.common.service.account.doupo;

import java.io.File;
import java.util.Date;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.http.HttpServer4Login;
import mmo.common.module.account.doupo.server.AccountApplication;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.log.LoggerFilter;
import mmo.tools.util.DateUtil;
import mmo.tools.util.FileUtil;

public class AccountServer {
	private static final String CODE_VERSION = "1.0.0.1";
	static {
		AccountApplication.init();
	}

	public static void main(String[] args) {
		String versionFile = deleteVersionFile();

		NetAddress na = ProjectCofigs.getNetAddress(HttpCData.ACCOUNT_URL_NAME);
		if (na != null && na.getPort() > 0) {
			HttpServer4Login.startServer(na.getPort());
		}

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
