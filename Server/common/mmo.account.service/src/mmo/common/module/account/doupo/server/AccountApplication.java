package mmo.common.module.account.doupo.server;

import mmo.common.module.account.doupo.ParseWhiteList;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.account.charge.AccountChargeManager;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceDeviceFreeze;
import mmo.common.module.account.doupo.cache.account.service.ServiceServerLastEnter;
import mmo.common.module.account.doupo.cache.account.service.ServiceServerRoleCount;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerFilter;
import mmo.tools.log.MyLog4J;
import mmo.tools.util.FileUtil;

public class AccountApplication {
	public final static String           MQ_SERVER   = "mqserver";

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
		ParseWhiteList.init(ProjectCofigs.getFile("white_list"));
		DBConnection.init(ProjectCofigs.getFile("accountdb"));
		ServiceAccount.getInstance().loadActiveCode();
		ServiceAccount.getInstance().loadAccountToCache();
		ServiceServerLastEnter.getInstance().loadAccountToCache();
		ServiceServerRoleCount.getInstance().loadAccountToCache();
		ServiceDeviceFreeze.getInstance().loadDeviceFreezeToCache();
		AccountChargeManager.getInstance().loadAccountCharge();

		AccountCache.getInstance().resetPermitChannels(ProjectCofigs.getParameter("white_channels"));
		ThreadManager.initThread();
	}
}
