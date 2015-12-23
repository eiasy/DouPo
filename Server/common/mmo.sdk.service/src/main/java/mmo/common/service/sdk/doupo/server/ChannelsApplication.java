package mmo.common.service.sdk.doupo.server;

import mmo.common.module.sdk.server.ServerList;
import mmo.common.module.sdk.server.ServerManager;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.common.module.sdk.xml.platform.XmlPlatform;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerFilter;
import mmo.tools.log.MyLog4J;
import mmo.tools.util.FileUtil;
import mmo.tools.util.StringUtil;

public class ChannelsApplication {

	public final static String MQ_SERVER = "mqserver";

	public final static void init() {
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
		XmlPlatform.init(ProjectCofigs.getFile("client_version"));
		int[] platforms = StringUtil.string2IntArray(ProjectCofigs.getParameter("server_platform"), ',');
		for (int pi = 0; pi < platforms.length; pi++) {
			int subStart = Integer.MAX_VALUE;
			String subPre = ProjectCofigs.getParameter("sub_pre_" + platforms[pi]);
			if (subPre != null) {
				try {
					subStart = Integer.parseInt(ProjectCofigs.getParameter("sub_" + platforms[pi]));
				} catch (Exception e) {
					subStart = Integer.MAX_VALUE;
				}
			}
			ServerManager.getInstance().setServerList(platforms[pi], new ServerList(FileUtil.getFileBText(ProjectCofigs.getFile("servers_all_" + platforms[pi])), FileUtil.getFileBText(ProjectCofigs.getFile("servers_suggest_" + platforms[pi])), FileUtil.getFileBText(ProjectCofigs.getFile("servers_test_" + platforms[pi])), subStart, subPre));
		}
		ThreadManager.initThread();
	}
}
