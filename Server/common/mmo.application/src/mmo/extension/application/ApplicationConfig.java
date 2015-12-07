package mmo.extension.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.util.string.StringSplit;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ApplicationConfig {
	/** 管理器 */
	public final static int            TYPE_1_MANGER     = 1;
	/** 登录服 */
	public final static int            TYPE_2_LOGIN      = 2;
	/** 充值服 */
	public final static int            TYPE_3_CHARGE     = 3;
	/** 游戏服 */
	public final static int            TYPE_4_GAME       = 4;
	/** 资源更新 */
	public final static int            TYPE_5_RSSOURCE   = 5;
	private static final Object        COMMON_VALUE      = new Object();
	private static final String        LOCAL_IP          = "192.168";
	private static ApplicationConfig   instance          = new ApplicationConfig();
	private AtomicLong                 securityCodeIndex = new AtomicLong();
	private boolean                    isWin             = System.getProperty("os.name").startsWith("Win");
	/** 程序是否在正常运行：true正常运行，false进入维护或关闭状态 */
	private static boolean             runing            = true;
	/** 产品编号 */
	private int                        productId;
	/** 产品名称 */
	private String                     productName;
	/** 应用程序ID */
	private int                        appId;
	/** 应用程序名称 */
	private String                     appName;
	/** 版本号 **/
	private byte                       version;
	/** 代码版本号 */
	private String                     codeVersion       = "1.0.0";
	/** appKey */
	private String                     appKey;
	/** 最大连接限制 */
	private int                        connectMax        = 5000;
	/** 进入游戏的最大人数 */
	private int                        enterMax          = 3000;
	/** 充值秘钥 */
	private String                     chargeKey;
	/** 监听网络 */
	private boolean                    listenNet         = false;
	/** 是否为测试服务器 */
	private boolean                    testServer;
	/** 网络刷新间隔 */
	private int                        refreshOffset     = 1 * 60 * 1000;
	/** GM工具秘钥 */
	private String                     gmKey             = "hyfanrengm";

	/** 跳转的标识 */
	private boolean                    rdFlag;
	/** 跳转IP */
	private String                     rdIp;
	/** 官方微信 */
	private String                     weixin;
	/** 官方QQ群 */
	private String                     qqGroup;
	/** 官方论坛 */
	private String                     bbs;
	/** 跳转端口 */
	private int                        rdPort;
	private String                     logStdoutLinux    = "";
	private String                     logStdoutWin      = ",stdout";
	private String                     logDirLinux       = "/tmp/";
	private String                     logDirWin         = "logs/";
	private String                     logDirSubLinux    = "";
	private String                     logDirSubWin      = "";
	private String                     logLvLinux        = "WARN";
	private String                     logLvWin          = "INFO";
	private String                     logProduct        = "product";
	private boolean                    logZone           = false;
	private static String              configFile        = null;
	/** 是否为主服务器 */
	private boolean                    mainServer        = false;
	/** 1：管理器，2：登录服：3：充值，4：游戏 */
	private int                        serverType;
	private static boolean             isValidateNetUser;
	private static int                 notifyPhoneOffset = 4;
	private static String              message           = "游戏服务即将开启，敬请期待！";

	private static Map<String, Object> netUsers          = new HashMap<String, Object>();
	private final long                 startTime         = System.currentTimeMillis();

	private ApplicationConfig() {

	}

	public long getStartTime() {
		return startTime;
	}

	public synchronized static final ApplicationConfig initApplicationConfig(String configFile) {
		ApplicationConfig.configFile = configFile;
		if (instance == null) {
			instance = new ApplicationConfig();
		}
		instance.init(configFile);
		return instance;
	}

	public static ApplicationConfig getInstance() {
		if (instance == null) {
			instance = new ApplicationConfig();
			instance.init(configFile);
		}
		return instance;
	}

	private void init(String configFile) {
		parseXml(configFile);
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int applicationId) {
		this.appId = applicationId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String applicationName) {
		this.appName = applicationName;
	}

	public boolean isMainServer() {
		return mainServer;
	}

	public String getCodeVersion() {
		return codeVersion;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public int getProductId() {
		return productId;
	}

	public static boolean isRuning() {
		return runing;
	}

	public static void setRuning(boolean runing) {
		ApplicationConfig.runing = runing;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getConnectMax() {
		return connectMax;
	}

	public void setConnectMax(int connectMax) {
		this.connectMax = connectMax;
	}

	public int getEnterMax() {
		return enterMax;
	}

	public String getChargeKey() {
		return chargeKey;
	}

	public void setEnterMax(int enterMax) {
		this.enterMax = enterMax;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public boolean isListenNet() {
		return listenNet;
	}

	public void setListenNet(boolean listenNet) {
		this.listenNet = listenNet;
	}

	public boolean isRdFlag() {
		return rdFlag;
	}

	public String getRdIp() {
		return rdIp;
	}

	public int getRdPort() {
		return rdPort;
	}

	public String getLogStdoutLinux() {
		return logStdoutLinux;
	}

	public String getLogDirLinux() {
		return logDirLinux;
	}

	public String getLogDirWin() {
		return logDirWin;
	}

	public String getLogDir() {
		if (isWin) {
			return logDirWin;
		} else {
			return logDirLinux;
		}
	}

	public String getLogLv() {
		if (isWin) {
			return logLvWin;
		} else {
			return logLvLinux;
		}
	}

	public String getLogProduct() {
		return logProduct;
	}

	public String getLogDirSubLinux() {
		return logDirSubLinux;
	}

	public String getLogStdoutWin() {
		return logStdoutWin;
	}

	public String getLogStdout() {
		if (isWin) {
			return logStdoutWin;
		} else {
			return logStdoutLinux;
		}
	}

	public String getLogDirSubWin() {
		return logDirSubWin;
	}

	public String getLogDirSub() {
		if (isWin) {
			return logDirSubWin;
		} else {
			return logDirSubLinux;
		}
	}

	public boolean isLogZone() {
		return logZone;
	}

	protected static final String TAG_APPLICATION       = "application";
	protected static final String TAG_BELONG            = "belong";
	protected static final String TAG_NET               = "net";
	protected static final String TAG_REDIRECT          = "redirect";
	protected static final String TAG_LOG               = "log";
	private static final String   TAG_net_user          = "net-user";
	private static final String   TAG_user              = "user";
	private static final String   TAG_notify_phone      = "notify-phone";

	private static final String   ATT_value             = "value";
	private static final String   ATT_validate          = "validate";
	private static final String   ATT_TEST              = "test";
	private static final String   ATT_text              = "text";
	private static final String   ATT_OFFSET            = "offset";
	protected static final String ATT_APP_ID            = "appid";
	protected static final String ATT_APP_KEY           = "appkey";
	protected static final String ATT_APP_NAME          = "appname";
	protected static final String ATT_VERSION           = "version";
	protected static final String ATT_CODE_VERSION      = "codeversion";
	protected static final String ATT_PRODUCT_ID        = "productid";
	protected static final String ATT_PRODUCT_NAME      = "productname";
	protected static final String ATT_CONNECT_MAX       = "connnectmax";
	protected static final String ATT_ENTER_MAX         = "entermax";
	protected static final String ATT_CHARGE_KEY        = "chargekey";
	protected static final String ATT_GM_KEY            = "gmkey";
	protected static final String ATT_FLAG              = "flag";
	protected static final String ATT_IP                = "ip";
	protected static final String ATT_PORT              = "port";
	protected static final String ATT_NAME              = "name";

	protected static final String ATT_LOG_DIR_LINUX     = "dirlinux";
	protected static final String ATT_LOG_DIR_WIN       = "dirwin";
	protected static final String ATT_LOG_DIR_SUB_LINUX = "dirsublinux";
	protected static final String ATT_LOG_DIR_SUB_WIN   = "dirsubwin";
	protected static final String ATT_LOG_STDOUT_LINUX  = "stdoutlinux";
	protected static final String ATT_LOG_STDOUT_WIN    = "stdoutwin";
	protected static final String ATT_LOG_LV_LINUX      = "loglvlinux";
	protected static final String ATT_LOG_LV_WIN        = "loglvwin";
	protected static final String ATT_LOG_ZONE          = "zone";
	protected static final String ATT_LOG_PRODUCT       = "product";
	protected static final String ATT_MAIN_SERVER       = "mainserver";
	protected static final String ATT_SERVER_TYPE       = "servertype";

	private void parseXml(String file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
		} catch (Exception e) {
			LoggerError.error("file:" + file, e);
		}
		String text = null;
		Element appEle = doc.getRootElement();
		text = appEle.getAttributeValue(ATT_APP_ID);

		appId = Integer.parseInt(text.trim());
		text = appEle.getAttributeValue(ATT_APP_KEY);
		if (text != null) {
			appKey = text.trim();
		}
		text = appEle.getAttributeValue(ATT_APP_NAME);
		if (text != null) {
			appName = text.trim();
		}
		text = appEle.getAttributeValue(ATT_CODE_VERSION);
		if (text != null) {
			codeVersion = text.trim();
		}
		text = appEle.getAttributeValue(ATT_CHARGE_KEY);
		if (text != null) {
			chargeKey = text.trim();
		}
		text = appEle.getAttributeValue(ATT_GM_KEY);
		if (text != null) {
			gmKey = text.trim();
		}
		text = appEle.getAttributeValue(ATT_VERSION);
		if (text != null) {
			version = Byte.parseByte(text.trim());
		}
		text = appEle.getAttributeValue(ATT_MAIN_SERVER);
		if (text != null) {
			mainServer = "true".equalsIgnoreCase(text);
		}
		text = appEle.getAttributeValue(ATT_TEST);
		if (text != null) {
			testServer = "true".equalsIgnoreCase(text);
		}
		text = appEle.getAttributeValue(ATT_SERVER_TYPE);
		if (text != null) {
			serverType = Integer.parseInt(text);
		}
		List<Element> eleXingList = appEle.getChildren(TAG_BELONG);
		if (eleXingList != null) {
			for (Element ele : eleXingList) {
				text = ele.getAttributeValue(ATT_PRODUCT_ID);
				productId = Integer.parseInt(text.trim());
				text = ele.getAttributeValue(ATT_PRODUCT_NAME);
				if (text != null) {
					productName = text.trim();
				}
			}
		}
		List<Element> eleNetList = appEle.getChildren(TAG_NET);
		if (eleXingList != null) {
			for (Element ele : eleNetList) {
				text = ele.getAttributeValue(ATT_CONNECT_MAX);
				if (text != null) {
					connectMax = Integer.parseInt(text.trim());
				}
				text = ele.getAttributeValue(ATT_ENTER_MAX);
				if (text != null) {
					enterMax = Integer.parseInt(text.trim());
				}
			}
		}

		List<Element> eleRdList = appEle.getChildren(TAG_REDIRECT);
		if (eleRdList != null) {
			for (Element ele : eleRdList) {
				text = ele.getAttributeValue(ATT_FLAG);
				if (text != null) {
					rdFlag = "true".equals(text.toLowerCase().trim());
				}
				text = ele.getAttributeValue(ATT_IP);
				if (text != null) {
					rdIp = text;
				}
				if (rdIp == null || rdIp.trim().length() == 0) {
					rdFlag = false;
					continue;
				}
				text = ele.getAttributeValue(ATT_PORT);
				if (text != null) {
					rdPort = Integer.parseInt(text.trim());
				}
				if (rdPort == 0) {
					rdFlag = false;
					continue;
				}
			}
		}

		List<Element> eleLogList = appEle.getChildren(TAG_LOG);
		if (eleRdList != null) {
			for (Element ele : eleLogList) {
				text = ele.getAttributeValue(ATT_LOG_ZONE);
				if (text != null) {
					logZone = "true".equals(text.toLowerCase().trim());
				}
				text = ele.getAttributeValue(ATT_LOG_DIR_LINUX);
				if (text != null) {
					logDirLinux = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_DIR_WIN);
				if (text != null) {
					logDirWin = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_DIR_SUB_LINUX);
				if (text != null) {
					logDirSubLinux = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_DIR_SUB_WIN);
				if (text != null) {
					logDirSubWin = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_STDOUT_LINUX);
				if (text != null) {
					logStdoutLinux = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_STDOUT_WIN);
				if (text != null) {
					logStdoutWin = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_LV_LINUX);
				if (text != null) {
					logLvLinux = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_LV_WIN);
				if (text != null) {
					logLvWin = text.trim();
				}

				text = ele.getAttributeValue(ATT_LOG_PRODUCT);
				if (text != null) {
					logProduct = text.trim();
				}

			}
		}

		List<Element> eleNetUserList = appEle.getChildren(TAG_net_user);
		if (eleNetUserList != null) {
			for (Element ele : eleNetUserList) {
				text = ele.getAttributeValue(ATT_text);
				if (text != null) {
					message = StringSplit.transformString(text.trim());
				}
				text = ele.getAttributeValue(ATT_validate);
				if (text != null && text.trim().length() > 0) {
					if ("true".equalsIgnoreCase(text.trim())) {
						isValidateNetUser = true;
						List<Element> eleUserList = ele.getChildren(TAG_user);
						if (eleUserList != null) {
							for (Element user : eleUserList) {
								text = user.getAttributeValue(ATT_text);
								if (text != null && text.trim().length() > 0) {
									netUsers.put(text.trim(), COMMON_VALUE);
								}
							}
						}
					} else {
						isValidateNetUser = false;
					}
				}
			}
		}

		List<Element> eleNotifyPhoneList = appEle.getChildren(TAG_notify_phone);
		if (eleNotifyPhoneList != null && eleNotifyPhoneList.size() > 0) {
			Element offset = eleNotifyPhoneList.get(0);
			text = offset.getAttributeValue(ATT_OFFSET);
			if (text != null && text.trim().length() > 0) {
				notifyPhoneOffset = Integer.parseInt(text.trim());
			}
		}
		if (notifyPhoneOffset < 1) {
			notifyPhoneOffset = 4;
		}

	}

	public boolean isWin() {
		return isWin;
	}

	public int getRefreshOffset() {
		return refreshOffset;
	}

	public void setRefreshOffset(int refreshOffset) {
		if (refreshOffset < 1) {
			this.refreshOffset = 1 * 60 * 1000;
		}
		this.refreshOffset = refreshOffset;
	}

	public String getGmKey() {
		return gmKey;
	}

	public void setCodeVersion(String codeVersion) {
		this.codeVersion = codeVersion;
	}

	public String getSecurityCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("SC#").append(productId).append("#").append(appId).append("#").append(securityCodeIndex.incrementAndGet());
		return sb.toString();
	}

	public static boolean isValidateNetUser() {
		return isValidateNetUser;
	}

	public static boolean forbid(NetRole role) {
		if (isValidateNetUser && !role.getRemoteAddress().contains(LOCAL_IP)) {
			return netUsers.get(role.getUserid()) == null;
		}
		return false;
	}

	public final static void addSpecialAccount(String userid) {
		if (userid != null) {
			netUsers.put(userid, COMMON_VALUE);
		}
	}

	public final static void removeSpecialAccount(String userid) {
		if (userid != null) {
			netUsers.remove(userid);
		}
	}

	public final static void clearSpecialAccount() {
		netUsers.clear();
	}

	public static void setValidateNetUser(boolean isValidateNetUser) {
		ApplicationConfig.isValidateNetUser = isValidateNetUser;
	}

	public static int getNotifyPhoneOffset() {
		return notifyPhoneOffset;
	}

	public static String getMessage() {
		return message;
	}

	public final static void setLoginLimitMessage(String message) {
		ApplicationConfig.message = StringSplit.transformString(message);
	}

	public int getServerType() {
		return serverType;
	}

	public boolean isTestServer() {
		return testServer;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}

	public String getBbs() {
		return bbs;
	}

	public void setBbs(String bbs) {
		this.bbs = bbs;
	}

	public final static String getClassDir() {
		if (ApplicationConfig.getInstance().isWin()) {
			return ProjectCofigs.getFile("dir_classes_win");
		}
		return ProjectCofigs.getFile("dir_classes_linux");
	}

	@Override
	public String toString() {
		return "ApplicationConfig [productId=" + productId + ", productName=" + productName + ", appId=" + appId + ", appName=" + appName
		        + ", version=" + version + ", codeVersion=" + codeVersion + ", appKey=" + appKey + ", connectMax=" + connectMax + ", enterMax="
		        + enterMax + "]";
	}
}
