package mmo.tools.log;

import mmo.extension.application.ApplicationConfig;
import mmo.tools.util.FileUtil;

public class LoggerFilter {
	public final static char    SYMBOL_E_COMMA     = ',';
	public final static char    SYMBOL_E_semicolon = ';';
	public static final String  logDivide          = "|";
	public static final char    DIVIDE_CHAR        = '|';

	private static final String ROOT_LOGGER        = "-root-logger";
	private static final String LOG_DIR            = "-log-dir";
	private static final String DIR_SUB            = "-dir-sub";
	private static final String ZONE_NO            = "-log-zone-no";
	private static final String LOG_LV             = "-log-lv";
	private static final String LOG_PRODUCT        = "-log-product";

	private static boolean      logInfo;

	public static final void filterFile(String logTemplate, String logFile) {
		String text = FileUtil.getFileBText(logTemplate);
		text = text.replaceAll(ROOT_LOGGER, ApplicationConfig.getInstance().getLogStdout());
		text = text.replaceAll(LOG_DIR, ApplicationConfig.getInstance().getLogDir());
		text = text.replaceAll(DIR_SUB, ApplicationConfig.getInstance().getLogDirSub());
		text = text.replaceAll(LOG_LV, ApplicationConfig.getInstance().getLogLv());
		text = text.replaceAll(LOG_PRODUCT, ApplicationConfig.getInstance().getLogProduct());
		text = text.replaceAll(ZONE_NO, "z" + ApplicationConfig.getInstance().getAppId());
		logInfo = "info".equalsIgnoreCase(ApplicationConfig.getInstance().getLogLv().trim());
		FileUtil.writeDataToFile(logFile, text);
	}

	public static boolean isLogInfo() {
		return logInfo;
	}
}
