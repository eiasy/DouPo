package mmo.module.logger.role;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mmo.common.bean.goods.AGoods;
import mmo.common.bean.mission.GameMission;
import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.bean.scene.GameScene;
import mmo.common.bean.sect.IGameSect;
import mmo.common.config.ClientCustom;
import mmo.common.system.GameSystem;
import mmo.extension.application.ApplicationConfig;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.util.DateUtil;

import org.apache.log4j.Logger;

public class LoggerRole {
	/** 创建角色 */
	private final static String CREATE_ROLE          = "createrole";
	/** 创建角色-失败 */
	private final static String CREATE_ROLE_FAIL     = "createrolefail";
	/** 角色上线 */
	private final static String ROLE_LOGIN           = "rolelogin";
	/** 角色上线失败 */
	private final static String ROLE_LOGIN_FAIL      = "roleloginfail";
	/** 角色心跳 */
	private final static String HEARTBEAT            = "heartbeat";
	/** 角色属性 */
	private final static String ROLE_PROP            = "roleprop";
	/** 在线人数统计 */
	private final static String ROLE_ONLINE_COUNT    = "onlinecount";
	/** 聊天内容 */
	private final static String ROLE_TALK            = "talkcontent";

	/** 角色消费日志-物品获得-物品消耗-交易 */
	private final static String GOODS_LOG            = "goodslog";

	/** 副本通关 */
	private final static String DUPLICATE            = "duplicate";

	/** 角色消费日志-物品-精炼 */
	private final static String GOODS_STRENGTHEN_LOG = "strengthen";

	/** 在线角色的平均等级 */
	private final static String AVERAGE_LEVEL        = "averagelevel";
	/** 任务记录 */
	private final static String ROLE_MISSION         = "rolemission";

	/** 在线时长 */
	private final static String ONLINETIME           = "onlinetime";
	/** 宗门积分 */
	private final static String SECT                 = "sect";
	/** 领话费 */
	private final static String PHONE_MONEY          = "phonemoney";
	/** 次日留存领取话费 */
	private final static String NEXT_DAY_PHONE_MONEY = "nextdayphonemoney";

	/** 角色注册（创建） */
	private final static Logger createRole           = Logger.getLogger(CREATE_ROLE);
	/** 角色注册（创建失败） */
	private final static Logger createRoleFail       = Logger.getLogger(CREATE_ROLE_FAIL);
	/** 角色上线 */
	private final static Logger roleLogin            = Logger.getLogger(ROLE_LOGIN);
	/** 角色上线失败 */
	private final static Logger roleLoginFail        = Logger.getLogger(ROLE_LOGIN_FAIL);
	/** 角色心跳 */
	private final static Logger heartbeat            = Logger.getLogger(HEARTBEAT);
	/** 角色属性 */
	private final static Logger roleProp             = Logger.getLogger(ROLE_PROP);
	/** 在线人数 */
	private final static Logger roleOnlineCount      = Logger.getLogger(ROLE_ONLINE_COUNT);
	/** 角色消费日志-物品获得-物品消耗-交易 */
	private final static Logger goodsLog             = Logger.getLogger(GOODS_LOG);

	private final static Logger goodsExtensionLog    = Logger.getLogger(GOODS_STRENGTHEN_LOG);
	/** 角色聊天内容 */
	private final static Logger roleTalk             = Logger.getLogger(ROLE_TALK);
	/** 角色平均等级 */
	private final static Logger averageLevel         = Logger.getLogger(AVERAGE_LEVEL);
	/** 角色任务记录 */
	private final static Logger roleMission          = Logger.getLogger(ROLE_MISSION);
	/** 在线时长 */
	private static final Logger onlineTimeLog        = Logger.getLogger(ONLINETIME);
	/** 宗门积分 */
	private static final Logger sectLog              = Logger.getLogger(SECT);

	/** 副本通关 */
	private static final Logger duplicate            = Logger.getLogger(DUPLICATE);
	/** 领取话费 */
	private static final Logger phoneMoney           = Logger.getLogger(PHONE_MONEY);
	/** 次日留存领取话费 */
	private static final Logger nextDayPhoneMoney    = Logger.getLogger(NEXT_DAY_PHONE_MONEY);
	static {
		RoleManager.averageLevel = averageLevel;
	}

	/**
	 * 创建角色
	 * 
	 * @param role
	 */
	public static final void createRole(Role role) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide);
		List<AGoods> moneys = role.getMoneys();
		int msize = moneys.size();
		AGoods goods = null;
		for (int mi = 0; mi < msize; mi++) {
			goods = moneys.get(mi);
			if (goods != null) {
				if (mi > 0) {
					sb.append(LoggerFilter.SYMBOL_E_semicolon);
				}
				sb.append(goods.getName());// 货币名称
				sb.append(LoggerFilter.SYMBOL_E_COMMA).append(goods.getCount());// 货币数量
			}
		}
		createRole.info(sb.toString());
	}

	/***
	 * 上线记录
	 * 
	 * @param role
	 */
	public static final void roleLogin(Role role) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide);
		List<AGoods> moneys = role.getMoneys();
		int msize = moneys.size();
		AGoods goods = null;
		for (int mi = 0; mi < msize; mi++) {
			goods = moneys.get(mi);
			if (goods != null) {
				if (mi > 0) {
					sb.append(LoggerFilter.SYMBOL_E_semicolon);
				}
				sb.append(goods.getName());// 货币名称
				sb.append(LoggerFilter.SYMBOL_E_COMMA).append(goods.getCount());// 货币数量
			}
		}
		roleLogin.info(sb.toString());
	}

	public static final void heartbeat(Role role) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		heartbeat.info(sb.toString());
	}

	/**
	 * 在线数量
	 * 
	 * @param onlineCount
	 */
	public static void onlineCount(int onlineCount) {
		StringBuilder sb = new StringBuilder();
		sb.append(ApplicationConfig.getInstance().getAppId());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(onlineCount);// 在线人数
		roleOnlineCount.info(sb.toString());
	}

	public final static void onlineTime(Role role, int time) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证,
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道,
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(time);// 在线时长
		sb.append(LoggerFilter.logDivide);
		List<AGoods> moneys = role.getMoneys();
		int msize = moneys.size();
		AGoods goods = null;
		for (int mi = 0; mi < msize; mi++) {
			goods = moneys.get(mi);
			if (goods != null) {
				if (mi > 0) {
					sb.append(LoggerFilter.SYMBOL_E_semicolon);
				}
				sb.append(goods.getName());// 货币名称
				sb.append(LoggerFilter.SYMBOL_E_COMMA).append(goods.getCount());// 货币数量
			}
		}
		onlineTimeLog.info(sb.toString());
	}

	public final static void duplicate(Role role, String operate, String category, String duplicateName, String sceneName, int areaId, int dupGateId,
	        int roleCount, String result) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getBelongServer());
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());
		sb.append(LoggerFilter.logDivide).append(role.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());
		sb.append(LoggerFilter.logDivide).append(role.getUserid());
		sb.append(LoggerFilter.logDivide).append(role.getId());
		sb.append(LoggerFilter.logDivide).append(role.getUsername());
		sb.append(LoggerFilter.logDivide).append(role.getLevel());
		sb.append(LoggerFilter.logDivide).append(operate);
		sb.append(LoggerFilter.logDivide).append(category);
		sb.append(LoggerFilter.logDivide).append(duplicateName);
		sb.append(LoggerFilter.logDivide).append(sceneName);
		sb.append(LoggerFilter.logDivide).append(areaId);
		sb.append(LoggerFilter.logDivide).append(dupGateId);
		sb.append(LoggerFilter.logDivide).append(roleCount);
		sb.append(LoggerFilter.logDivide).append(result);
		duplicate.info(sb.toString());
	}

	public final static void goodsLog(Role role, String operate, String project, String orderId, String message) {
		if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT || role.getId() < 1) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证,
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道,
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(operate);// 消费类型
		sb.append(LoggerFilter.logDivide).append(project);// 项目类型
		sb.append(LoggerFilter.logDivide).append(orderId);// 订单号
		sb.append(message);// 物品变化信息[角色ID|物品ID|物品名称|+-变化量|剩余数量]
		goodsLog.info(sb.toString());
	}

	/**
	 * 角色属性变更
	 * 
	 * @param role
	 * @param string
	 * @param oldAttr
	 * @param newAttr
	 */
	public final static void updateRoleProp(Role role, String string, int newAttr, int oldAttr, String reason) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(string);// 属性标识
		sb.append(LoggerFilter.logDivide).append(oldAttr);// 旧属性值
		sb.append(LoggerFilter.logDivide).append(newAttr);// 新属性值
		sb.append(LoggerFilter.logDivide).append(reason);// 新属性值
		roleProp.info(sb.toString());
	}

	/**
	 * 创建角色
	 * 
	 * @param role
	 */
	public static final void createRoleFail(Role role, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(message);// 消息
		createRoleFail.info(sb.toString());
	}

	/***
	 * 上下线记录
	 * 
	 * @param role
	 */
	public static final void roleLoginFail(Role role, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(message);// 消息
		roleLoginFail.info(sb.toString());
	}

	/**
	 * 聊天信息
	 * 
	 * @param source
	 * @param target
	 * @param cate
	 * @param content
	 */
	public final static void talkContent(Role source, Role target, GameScene scene, String cate, String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(source.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(source.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(source.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(source.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(source.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(source.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(source.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(source.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(source.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(source.getUsername());// 角色昵称
		sb.append(LoggerFilter.logDivide).append(source.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(source.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(cate);// 事件类型

		sb.append(LoggerFilter.logDivide).append(target != null ? target.getId() : 0);// 目标角色ID
		sb.append(LoggerFilter.logDivide).append(content);// 聊天内容
		roleTalk.info(sb.toString());
	}

	/**
	 * 在线角色的平均等级
	 * 
	 * @param roleCount
	 *            在线角色数量
	 * @param averageLevel
	 *            平均等级
	 */
	public final static void averageLevel(int roleCount, float averageLv) {
		StringBuilder sb = new StringBuilder();
		sb.append(roleCount);
		sb.append(LoggerFilter.logDivide).append(averageLv);
		averageLevel.info(sb.toString());
	}

	public static void roleMission(Role role, GameScene scene, String operate, String result, GameMission mission) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(operate);// 操作
		sb.append(LoggerFilter.logDivide).append(mission.getId());// 任务编号
		sb.append(LoggerFilter.logDivide).append(mission.getTitle());// 任务标题
		roleMission.info(sb.toString());
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
			createRole.info(LoggerDevelop.NULL_LOG);
			createRoleFail.info(LoggerDevelop.NULL_LOG);
			roleLogin.info(LoggerDevelop.NULL_LOG);
			roleLoginFail.info(LoggerDevelop.NULL_LOG);
			heartbeat.info(LoggerDevelop.NULL_LOG);
			roleProp.info(LoggerDevelop.NULL_LOG);
			roleOnlineCount.info(LoggerDevelop.NULL_LOG);
			goodsLog.info(LoggerDevelop.NULL_LOG);
			roleTalk.info(LoggerDevelop.NULL_LOG);
			averageLevel.info(LoggerDevelop.NULL_LOG);
			roleMission.info(LoggerDevelop.NULL_LOG);
			onlineTimeLog.info(LoggerDevelop.NULL_LOG);
			goodsExtensionLog.info(LoggerDevelop.NULL_LOG);
			sectLog.info(LoggerDevelop.NULL_LOG);
		}
	}

	public final static void goodsExtension(Role role, String operate, String project, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(role.getFeatureString());// 特征串
		sb.append(LoggerFilter.logDivide).append(role.getLoginServerName());// 通行证服务器标识
		sb.append(LoggerFilter.logDivide).append(role.getBelongServer());// 游戏逻辑服务器标识
		sb.append(LoggerFilter.logDivide).append(ApplicationConfig.getInstance().getCodeVersion());// 游戏逻辑服务器版本号
		sb.append(LoggerFilter.logDivide).append(role.getCodeVersion());// 客户端版本号
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证,
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道,
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(operate);// 消费类型
		sb.append(LoggerFilter.logDivide).append(project);// 项目类型
		sb.append(message);// 物品变化信息[精炼等级|物品ID|物品名称|消耗精炼石|是否成功]
		goodsExtensionLog.info(sb.toString());
	}

	private final static String SECT_PRESTIGE = "prestige";

	public final static void sect(IGameSect sect, int offset) {
		StringBuilder sb = new StringBuilder();
		sb.append(SECT_PRESTIGE);
		sb.append(LoggerFilter.logDivide).append(sect.getId());// 宗门编号
		sb.append(LoggerFilter.logDivide).append(sect.getId());// 宗门编号
		sb.append(LoggerFilter.logDivide).append(sect.getName());// 宗门名称
		sb.append(LoggerFilter.logDivide).append(sect.getPrestige());// 积分总值
		sb.append(LoggerFilter.logDivide).append(offset);// 积分变化量
		sb.append(LoggerFilter.logDivide).append(sect.getBattleCount());// 宗战次数
		sb.append(LoggerFilter.logDivide).append(sect.getWinBattleCount());// 宗战胜利次数
		sb.append(LoggerFilter.logDivide).append(sect.getLevel());// 宗门等级
		sectLog.info(sb.toString());
	}

	private static final String SECT_BATTLE = "battle";

	public final static void sect(String battle) {
		StringBuilder sb = new StringBuilder();
		sb.append(SECT_BATTLE);// 宗战公告
		sb.append(LoggerFilter.logDivide).append(battle);// 公告信息
		sectLog.info(sb.toString());
	}

	private static final String SECT_CREATE = "create";

	public final static void sectCreated(IGameSect sect, Role role) {
		StringBuilder sb = new StringBuilder();
		sb.append(SECT_CREATE);
		sb.append(LoggerFilter.logDivide).append(sect.getId());
		sb.append(LoggerFilter.logDivide).append(sect.getName());
		sb.append(LoggerFilter.logDivide).append(role.getId());
		sb.append(LoggerFilter.logDivide).append(role.getUsername());
		sectLog.info(sb.toString());
	}

	private static final String SECT_DISMISS = "dismiss";

	public final static void sectDismissed(IGameSect sect, Role role) {
		StringBuilder sb = new StringBuilder();
		sb.append(SECT_DISMISS);
		sb.append(LoggerFilter.logDivide).append(sect.getId());
		sb.append(LoggerFilter.logDivide).append(sect.getName());
		sb.append(LoggerFilter.logDivide).append(role.getId());
		sb.append(LoggerFilter.logDivide).append(role.getUsername());
		sectLog.info(sb.toString());
	}

	public final static void phoneMoneyLog(Role role, String phoneNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证,
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道,
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(phoneNumber);
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());
		phoneMoney.info(sb.toString());
	}

	public final static void nextDayPhoneMoneyLog(Role role, String exchangeNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(LoggerFilter.logDivide).append(role.getRemoteAddress());// 客户端IP
		sb.append(LoggerFilter.logDivide).append(role.getPermit());// 用户通行证,
		sb.append(LoggerFilter.logDivide).append(role.getChannelId());// 用户引流渠道,
		sb.append(LoggerFilter.logDivide).append(role.getAccountId());// UID
		sb.append(LoggerFilter.logDivide).append(role.getId());// 角色ID
		sb.append(LoggerFilter.logDivide).append(role.getLevel());// 角色等级
		sb.append(LoggerFilter.logDivide).append(role.getVipLevel());// 角色VIP等级
		sb.append(LoggerFilter.logDivide).append(exchangeNumber);
		sb.append(LoggerFilter.logDivide).append(role.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(role.getDeviceSerial());
		nextDayPhoneMoney.info(sb.toString());
	}
}
