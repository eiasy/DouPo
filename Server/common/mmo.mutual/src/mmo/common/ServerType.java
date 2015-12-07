package mmo.common;

import java.util.HashMap;
import java.util.Map;

public class ServerType {
	/** 管理器 */
	public final static int                   TYPE_1_MANAGER       = 1;
	/** 登录服务 */
	public final static int                   TYPE_2_LOGIN         = 2;
	/** 账号缓存 */
	public final static int                   TYPE_3_ACCOUNT_CACHE = 3;
	/** 充值 */
	public final static int                   TYPE_4_CHARGE        = 4;
	/** 资源更新 */
	public final static int                   TYPE_5_RESOURCE      = 5;
	/** 游戏服 */
	public final static int                   TYPE_6_GAME          = 6;
	/** 验证第三方账号登录 */
	public final static int                   TYPE_7_CHANNELS      = 7;
	/** 推送消息 */
	public final static int                   TYPE_8_PUSH          = 8;
	/** BI数据存储 */
	public final static int                   TYPE_9_BI_DATA       = 9;

	private final static Map<Integer, String> TYPE_NAME            = new HashMap<Integer, String>();
	static {
		TYPE_NAME.put(TYPE_1_MANAGER, "管理器");
		TYPE_NAME.put(TYPE_2_LOGIN, "登录服务");
		TYPE_NAME.put(TYPE_3_ACCOUNT_CACHE, "账号缓存");
		TYPE_NAME.put(TYPE_4_CHARGE, "充值代理");
		TYPE_NAME.put(TYPE_5_RESOURCE, "资源更新");
		TYPE_NAME.put(TYPE_6_GAME, "游戏服");
		TYPE_NAME.put(TYPE_7_CHANNELS, "第三方账号验证");
		TYPE_NAME.put(TYPE_8_PUSH, "推送消息");
		TYPE_NAME.put(TYPE_9_BI_DATA, "BI数据存储");
	}

	public final static String getTypeName(int type) {
		String name = TYPE_NAME.get(type);
		if (name == null) {
			return "未知：" + type;
		}
		return name;
	}
}
