package mmo.server;

import mmo.common.service.eventcenter.IEventApplication;
import mmo.common.service.eventcenter.admin.MenuManager;
import mmo.common.service.eventcenter.service.TableManager;
import mmo.common.service.eventcenter.thread.ThreadManager;
import mmo.common.service.eventcenter.thread.run.ClassloaderRun;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerFilter;
import mmo.tools.log.MyLog4J;
import mmo.tools.util.FileUtil;

public class EventApplication implements IEventApplication {
	public final static String             MQ_SERVER = "mqserver";

	private final static EventApplication instance  = new EventApplication();

	public final static EventApplication getInstance() {
		return instance;
	}

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
		DBConnection.init(ProjectCofigs.getFile("chargesdb"));
		TableManager.initSQL();
		MenuManager.getInstance().init();
		ThreadManager.initThread();
	}

	private EventApplication() {

	}

	@Override
	public void reloadClass() {
		ThreadManager.handleContext(new ClassloaderRun());
	}

}
