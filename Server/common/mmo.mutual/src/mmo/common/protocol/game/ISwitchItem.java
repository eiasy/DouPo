package mmo.common.protocol.game;

public interface ISwitchItem {
	/** 分区流量 */
	int NET_ZONE               = 1;
	/** 登录服流量 */
	int NET_LOGIN              = 2;
	/** 充值服流量 */
	int NET_CHARGE             = 3;
	/** 重新加载客户端URL */
	int RELOAD_CLIENT_URL      = 4;
	/** 加载资源 */
	int SERVER_RELOAD_RESOURCE = 5;
	/** 重新加载宗门配置 */
	int RELOAD_SECT            = 6;
	/** 登录服加载资源 */
	int LOGIN_RELOAD_RESOURCE  = 7;
}
