package mmo.tools.log;

import org.apache.log4j.Logger;

public class LoggerError {
	/** 错误信息 */
	private static final String commonError   = "error";
	private static final String commonMessage = "message";

	private static final Logger loggerError   = Logger.getLogger(commonError);

	public static final Logger  messageLog = Logger.getLogger(commonMessage);

	public static final void error(String message) {
		messageLog.error(message);
	}

	public static final void warn(String message) {
		messageLog.warn(message);
	}

	public static final void error(String message, Throwable t) {
		loggerError.error(message, t);
	}

	public static final String NULL_LOG = "";

	public static final void nullLog() {
		// loggerError.info(NULL_LOG);
	}
}
