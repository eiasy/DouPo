package mmo.module.logger.develop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mmo.common.system.GameSystem;
import mmo.tools.log.LoggerError;
import mmo.tools.log.LoggerFilter;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.util.DateUtil;

import org.apache.log4j.Logger;

public class LoggerDevelop {
	/** 协议跟踪 */
	private static final String   commonProtocol = "protocol";
	/** 资源跟踪 */
	private static final String   commonResource = "resource";
	/** 充值记录 */
	private static final String   commonCharge   = "chargeinfo";
	/** gm操作日志 */
	private static final String   commonGm       = "gm";
	/** 短信日志 */
	private static final String   commonSMS      = "sms";

	protected static final Logger loggerProtocol = Logger.getLogger(commonProtocol);
	protected static final Logger loggerResource = Logger.getLogger(commonResource);
	protected static final Logger loggerCharge   = Logger.getLogger(commonCharge);
	protected static final Logger loggerGM       = Logger.getLogger(commonGm);
	protected static final Logger loggerSMS      = Logger.getLogger(commonSMS);

	public final static void loggerResource(String userId, int id, String name, int clientVersion, String file, int ver) {
		StringBuilder sb = new StringBuilder();
		sb.append(userId);// 账号
		sb.append(LoggerFilter.logDivide).append(id).append(LoggerFilter.logDivide).append(name);// 角色ID
		sb.append(LoggerFilter.logDivide).append(clientVersion);// 客户端版本
		sb.append(LoggerFilter.logDivide).append(file);// 文件名
		sb.append(LoggerFilter.logDivide).append(ver);// 最新版本号
		loggerResource.info(sb.toString());
	}

	public static void loggerProtocol(NetRole role, int protocol) {
		StringBuilder sb = new StringBuilder();
		if (role != null) {
			sb.append(role.getAccountId());// 账号ID
			sb.append(LoggerFilter.logDivide).append(role.getUserid());// 账号
			sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
			sb.append(LoggerFilter.logDivide).append(role.getUsername());// 角色名称
		} else {
			sb.append(0);// 账号ID
			sb.append(LoggerFilter.logDivide).append("null");// 账号
			sb.append(LoggerFilter.logDivide).append(0);// 角色ID
			sb.append(LoggerFilter.logDivide).append("null");// 角色名称
		}
		sb.append(LoggerFilter.logDivide).append(protocol);// 协议编号protocol
		loggerProtocol.info(sb.toString());
	}

	public static final void error(String message) {
		LoggerError.messageLog.error(message);
	}

	public static final void warn(String message) {
		LoggerError.messageLog.warn(message);
	}

	public static final void error(String message, Throwable t) {
		LoggerError.error(message, t);
	}

	public static final void charge(int protocol, boolean validate, int productId, int serverId, String roleName, int amount, int getAmount,
	        String orderId, String addition) {
		StringBuilder sb = new StringBuilder();
		sb.append(protocol);// 操作协议
		sb.append(LoggerFilter.logDivide).append(validate);// 验证结果
		sb.append(LoggerFilter.logDivide).append(productId);// 游戏编号
		sb.append(LoggerFilter.logDivide).append(serverId);// 服务器编号
		sb.append(LoggerFilter.logDivide).append(roleName);// 角色名称
		sb.append(LoggerFilter.logDivide).append(amount);// 金额
		sb.append(LoggerFilter.logDivide).append(getAmount);// 实得金额
		sb.append(LoggerFilter.logDivide).append(orderId);// 订单号
		sb.append(LoggerFilter.logDivide).append(addition);// 附加信息
		loggerCharge.info(sb.toString());
	}

	public static final void gm(String operate, String gmAccount, Object message) {
		StringBuilder sb = new StringBuilder();
		sb.append(gmAccount);// GM账号
		sb.append(LoggerFilter.logDivide).append(operate);// 操作
		sb.append(LoggerFilter.logDivide).append(message);// 操作信息
		loggerGM.info(sb.toString());
	}

	public static final void sms(String type, String phones, String channel, String result) {
		StringBuilder sb = new StringBuilder();
		sb.append(type);// 类型
		sb.append(LoggerFilter.logDivide).append(channel);// 通道
		sb.append(LoggerFilter.logDivide).append(phones);// 手机号
		sb.append(LoggerFilter.logDivide).append(result);// 结果
		loggerSMS.info(sb.toString());
	}

	static long nextLogTime = stringToMillisecond();

	public static long stringToMillisecond() {
		DateFormat _dateformat = new SimpleDateFormat("yyyy-MM-dd HH");
		String dateString = _dateformat.format(new Date(System.currentTimeMillis()));
		String dateStartStr = dateString + ":10:00";

		Date dateStart = DateUtil.stringToDate(dateStartStr);
		if (dateStart == null) {
			return Long.MAX_VALUE;
		}
		return dateStart.getTime();
	}

	public static final String NULL_LOG = "";

	public static final void nullLog() {
		long currTime = System.currentTimeMillis();
		if (currTime > nextLogTime) {
			nextLogTime = nextLogTime + GameSystem.ONE_HOUR;
			loggerProtocol.info(NULL_LOG);
			loggerResource.info(NULL_LOG);
			LoggerError.nullLog();
			loggerGM.info(NULL_LOG);
			loggerSMS.info(NULL_LOG);
			loggerCharge.info(NULL_LOG);
		}
	}
}
