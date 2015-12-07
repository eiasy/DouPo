package mmo.module.gm;

import java.lang.management.ManagementFactory;

import mmo.extension.application.ApplicationConfig;
import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.net.GMNetManager;
import mmo.module.gm.xml.XLSManager;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.MyLog4J;
import mmo.tools.util.FileUtil;

public class GMDestop {
	public static String processName;
	static {
		ProjectCofigs.init(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "config" + FileUtil.FILE_SEPARATOR + "configs.xml");
		String[] tmp = ManagementFactory.getRuntimeMXBean().getName().split("@");
		processName = tmp[1] + tmp[0];
		ApplicationConfig.initApplicationConfig(FileUtil.ROOT_DIR + "/config/application.xml");
		MyLog4J.init(FileUtil.ROOT_DIR + "/config/log4j.properties");
//		XLSManager.getInstance().loadXLS(FileUtil.ROOT_DIR + "/");
		ApplicationConfig.getInstance().setCodeVersion("0.13.10");
	}

	public static void main(String[] args) {
		NetAddress na = ProjectCofigs.getNetAddress("manager_server");
		if (na != null) {
			GMNetManager.addNetAddress(na.getIp(), na.getPort());
		}
		GMWindow gmWindow = GMWindow.getInstance();
		gmWindow.setBlockOnOpen(true);
		gmWindow.open();
		System.exit(0);
	}
}
