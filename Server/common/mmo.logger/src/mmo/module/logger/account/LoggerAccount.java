package mmo.module.logger.account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mmo.common.system.GameSystem;
import mmo.extension.application.ApplicationConfig;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.util.DateUtil;

import org.apache.log4j.Logger;

public class LoggerAccount {
	/** 账号统计-注册 */
	public static final String  ACCOUNT_REGISTER      = "accountregister";
	/** 账号统计-登录 */
	public static final String  ACCOUNT_LOGIN         = "accountlogin";
	/** 账号属性变更 */
	public static final String  ACCOUNT_PROP          = "accountprop";
	/** 账号统计-注册失败 */
	public static final String  ACCOUNT_REGISTER_FAIL = "registerfail";
	/** 账号统计-登录失败 */
	public static final String  ACCOUNT_LOGIN_FAIL    = "loginfail";
	/** 下载 */
	public static final String  DOWNLOAD              = "download";
	/** 联网 */
	private static final String CONNECTED             = "connectednet";
	/** 资源版本 */
	private static final String RES_VERSION           = "resversion";
	/** 密码兑换 */
	private final static String EXCHANGE_CODE         = "exchangecode";
	/** 代码版本号 */
	private final static String CODE_VERSION          = "codeversion";

	private static final Logger accountRegister       = Logger.getLogger(ACCOUNT_REGISTER);
	private static final Logger accountLogin          = Logger.getLogger(ACCOUNT_LOGIN);
	private static final Logger accountProp           = Logger.getLogger(ACCOUNT_PROP);
	private static final Logger registerFil           = Logger.getLogger(ACCOUNT_REGISTER_FAIL);
	private static final Logger loginFail             = Logger.getLogger(ACCOUNT_LOGIN_FAIL);
	private static final Logger connected             = Logger.getLogger(CONNECTED);
	private static final Logger resVersion            = Logger.getLogger(RES_VERSION);
	private static final Logger codeVersion           = Logger.getLogger(CODE_VERSION);
	/** 兑换码 */
	private static final Logger exchangeCodeLog       = Logger.getLogger(EXCHANGE_CODE);

	public static final void codeVersion(String channel_id, int product_id, String code_version, String imei, String phone_code, int screen_width,
	        int screen_height, String device_os, String os_version, String udid, String mac, String ua, String serial_code, String channel_sub,
	        String result) {
		StringBuilder sb = new StringBuilder();
		sb.append(ApplicationConfig.getInstance().getAppId());
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 服务器版本号
		sb.append(LoggerFilter.logDivide).append(product_id);
		sb.append(LoggerFilter.logDivide).append(channel_id);
		sb.append(LoggerFilter.logDivide).append(channel_sub);
		sb.append(LoggerFilter.logDivide).append(code_version);
		sb.append(LoggerFilter.logDivide).append(screen_width).append("*").append(screen_height);
		sb.append(LoggerFilter.logDivide).append(imei);
		sb.append(LoggerFilter.logDivide).append(device_os);
		sb.append(LoggerFilter.logDivide).append(os_version);
		sb.append(LoggerFilter.logDivide).append(udid);
		sb.append(LoggerFilter.logDivide).append(mac);
		sb.append(LoggerFilter.logDivide).append(ua);
		sb.append(LoggerFilter.logDivide).append(serial_code);
		sb.append(LoggerFilter.logDivide).append(result);
		codeVersion.info(sb.toString());
	}

	public static final void checkResource(NetRole role, int resNewVersion, int clientResVersion, String operate) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getProductId());// 产品代号
		sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
		sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
		sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
		sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
		sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 去到后
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端代码版本号
		sb.append(LoggerFilter.logDivide).append(resNewVersion);// 资源最新版本号
		sb.append(LoggerFilter.logDivide).append(clientResVersion);// 客户端资源版本号
		sb.append(LoggerFilter.logDivide).append(operate);// 后续操作
		resVersion.info(sb.toString());
	}

	public static final void checkCodeVersion(NetRole role, String clientNewVersion, String operate) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getProductId());// 产品代号
		sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
		sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
		sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
		sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
		sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 渠道号
		sb.append(LoggerFilter.logDivide).append(clientNewVersion);// 客户端最新代码版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端代码版本号
		sb.append(LoggerFilter.logDivide).append(operate);// 后续操作
		connected.info(sb.toString());
	}

	public static final void accountRegister(NetRole role) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getProductId());// 产品代号
		sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 通行证服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
		sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
		sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
		sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
		sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getEmail());// EMail
		sb.append(LoggerFilter.logDivide).append(role.getPhone());// 手机号
		accountRegister.info(sb.toString());
	}

	public static final void accountLogin(int gameId, String featureString, String loginServer, String loginVersion, String clientVersion,
	        String deviceOS, String osVersion, int screenWidth, int screenHeight, String imei, String udid, String mac, String ua, String phoneType,
	        String ip, String permit, String channelId, String channelSub, int accountId, String userId, String username, int operate) {
		StringBuilder sb = new StringBuilder();
		sb.append(operate);// 产品代号
		sb.append(LoggerFilter.logDivide).append(gameId);// 产品代号
		sb.append(LoggerFilter.logDivide).append(featureString);// 特征串
		sb.append(LoggerFilter.logDivide).append(loginServer);// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(loginVersion);// 通行证服务器版本号
		sb.append(LoggerFilter.logDivide).append(clientVersion);// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(deviceOS);// 设备OS
		sb.append(LoggerFilter.logDivide).append(osVersion);// OS版本
		sb.append(LoggerFilter.logDivide).append(screenWidth).append("*").append(screenHeight);// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(imei);// 设备序列号
		sb.append(LoggerFilter.logDivide).append(udid);// 设备UDID
		sb.append(LoggerFilter.logDivide).append(mac);// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(ua);// 设备UA
		sb.append(LoggerFilter.logDivide).append(phoneType);// 机型
		sb.append(LoggerFilter.logDivide).append(ip);// 客户端IP
		sb.append(LoggerFilter.logDivide).append(permit);
		sb.append(LoggerFilter.logDivide).append(channelId);
		sb.append(LoggerFilter.logDivide).append(channelSub);
		sb.append(LoggerFilter.logDivide).append(accountId);
		sb.append(LoggerFilter.logDivide).append(userId);
		sb.append(LoggerFilter.logDivide).append(username);
		accountLogin.info(sb.toString());
	}

	public static final void accountLogin(NetRole role) {
		if (role.isLogLogin()) {
			role.setLogLogin(false);
			StringBuilder sb = new StringBuilder();
			sb.append(role.getProductId());// 产品代号
			sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
			sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
			sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 通行证服务器版本号
			sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
			sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
			sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
			sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
			sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
			sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
			sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
			sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
			sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
			sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
			sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
			sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
			sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
			accountLogin.info(sb.toString());
		}
	}

	/**
	 * 账号属性变更
	 * 
	 * @param role
	 * @param string
	 * @param oldAttr
	 * @param newAttr
	 */
	public final static void updateAccountProp(NetRole role, String string, String newAttr, String oldAttr) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getProductId());// 产品代号
		sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(string);// 属性标识
		sb.append(LoggerFilter.logDivide).append(newAttr);// 新属性值
		sb.append(LoggerFilter.logDivide).append(oldAttr);// 旧属性值
		accountProp.info(sb.toString());
	}

	/**
	 * 账号属性变更
	 * 
	 * @param role
	 * @param string
	 * @param oldAttr
	 * @param newAttr
	 */
	public final static void updateAccountProp(int productId, String fecture, String codeVersion, String remoteAddress, String permit, int channelId,
	        int accountId, String propFlag, String newAttr, String oldAttr) {
		StringBuilder sb = new StringBuilder();
		sb.append(productId);// 产品代号
		sb.append(LoggerFilter.logDivide).append(fecture);// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(codeVersion);// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(remoteAddress);// 客户端IP
		sb.append(LoggerFilter.logDivide).append(permit);// 用户通行证
		sb.append(LoggerFilter.logDivide).append(channelId);// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(accountId);// UID
		sb.append(LoggerFilter.logDivide).append(propFlag);// 属性标识
		sb.append(LoggerFilter.logDivide).append(newAttr);// 新属性值
		sb.append(LoggerFilter.logDivide).append(oldAttr);// 旧属性值
		accountProp.info(sb.toString());
	}

	public static final void accountRegisterFail(NetRole role, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 通行证服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
		sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
		sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
		sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
		sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getEmail());// EMail
		sb.append(LoggerFilter.logDivide).append(role.getPhone());// 手机号
		sb.append(LoggerFilter.logDivide).append(message);// 信息
		registerFil.info(sb.toString());
	}

	public static final void loggerExchange(int productId, int roleId, int accountId, int appId, String exchangeCode, String checkResult) {
		StringBuilder sb = new StringBuilder();
		sb.append(productId);// 产品编号
		sb.append(LoggerFilter.logDivide).append(roleId);// 角色ID
		sb.append(LoggerFilter.logDivide).append(accountId);// 账户ID
		sb.append(LoggerFilter.logDivide).append(appId);// 应用服务器ID
		sb.append(LoggerFilter.logDivide).append(exchangeCode);// 兑换密码
		sb.append(LoggerFilter.logDivide).append(checkResult);// 状态
		exchangeCodeLog.info(sb.toString());
	}

	public static final void accountLoginFail(NetRole role, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getAppName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 通行证服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());// 设备OS
		sb.append(LoggerFilter.logDivide).append(role.getOSVersion());// OS版本
		sb.append(LoggerFilter.logDivide).append(role.getScreenWidth()).append("*").append(role.getScreenHeight());// 设备分辨率
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());// 设备序列号
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUdid());// 设备UDID
		sb.append(LoggerFilter.logDivide).append(role.getDeviceMac());// 设备物理地址
		sb.append(LoggerFilter.logDivide).append(role.getDeviceUA());// 设备UA
		sb.append(LoggerFilter.logDivide).append(role.getPhoneType());// 机型
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getEmail());// EMail
		sb.append(LoggerFilter.logDivide).append(role.getPhone());// 手机号
		sb.append(LoggerFilter.logDivide).append(message);// 信息
		sb.append(LoggerFilter.logDivide).append(role.getUserid());// 账号
		loginFail.info(sb.toString());
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

	public static final void nullLog() {
		long currTime = System.currentTimeMillis();
		if (currTime > nextLogTime) {
			nextLogTime = nextLogTime + GameSystem.ONE_HOUR;
			accountRegister.info(LoggerDevelop.NULL_LOG);
			accountLogin.info(LoggerDevelop.NULL_LOG);
			accountProp.info(LoggerDevelop.NULL_LOG);
			registerFil.info(LoggerDevelop.NULL_LOG);
			loginFail.info(LoggerDevelop.NULL_LOG);

		}
	}
}
