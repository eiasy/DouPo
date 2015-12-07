package mmo.server;

import java.io.File;

import mmo.common.module.service.charge.ChargeManager;
import mmo.common.module.service.charge.IChargeApplication;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.advertise.IdfaManager;
import mmo.common.module.service.charge.http.thread.ClassloaderRun;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.service.TableManager;
import mmo.common.module.service.charge.tencent.QQCheckChargeHearbeat;
import mmo.common.module.service.charge.thread.AppStoreHeartbeat;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.xls.appstore.XlsAppStore;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerFilter;
import mmo.tools.log.MyLog4J;
import mmo.tools.util.FileUtil;

public class ChargeApplication implements IChargeApplication {
	public final static String             MQ_SERVER = "mqserver";

	private final static ChargeApplication instance  = new ChargeApplication();

	public final static ChargeApplication getInstance() {
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
		AppStoreHeartbeat.getInstance().init(Service.getInstance());
		QQCheckChargeHearbeat.getInstance().init();
		initAppStoreProduct();
		ChargeManager.init(getInstance());
		OrderformManager.getInstance().init();
		IdfaManager.getInstance().initIdfaActive();
		ThreadManager.initThread();
	}

	private static void initAppStoreProduct() {
		String filePath = ProjectCofigs.getFile("app_store_product");
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				XlsAppStore appStore = new XlsAppStore(filePath, false);
				appStore.execute();
			}
		}
	}

	private ChargeApplication() {

	}

	@Override
	public void reloadClass() {
		ChargeDatabaseHeartbeat.getInstance().execute(new ClassloaderRun());
	}
}
