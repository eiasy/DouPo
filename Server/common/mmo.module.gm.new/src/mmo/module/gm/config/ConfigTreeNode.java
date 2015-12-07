package mmo.module.gm.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigTreeNode {
	public final static String                NODE_GAME_MANAGER     = "账号管理";
	public final static String                NODE_GAME_LIST        = "道具查询";
	public final static String                NODE_GAME_NEW         = "添加游戏";
	
	public final static String                NODE_ACCOUNT          = "账号&设备";
	public final static String                NODE_ACCOUNT_SELECT   = "管理账号";
	public final static String                NODE_DEVICE_MANAGER   = "管理设备";
	
	public final static String                NODE_ACCOUNT_GM       = "GM账号";
	public final static String                NODE_ACCOUNT_GM_LIST  = "GM账号列表";
	public final static String                NODE_LOGIN            = "队列服务";
	public final static String                NODE_LOGIN_PARAMETER  = "队列服务";

	public final static int                   KEY_1_GAME_MANAGER    = 1;
	public final static int                   KEY_2_GAME_LIST       = 2;
	public final static int                   KEY_3_GAME_ADD        = 3;
	public final static int                   KEY_4_ACCOUNT         = 4;
	public final static int                   KEY_5_ACCOUNT_SELECT  = 5;
	public final static int                   KEY_6_ACCOUNT_GM      = 6;
	public final static int                   KEY_7_ACCOUNT_GM_LIST = 7;
	public final static int                   KEY_8_LOGIN           = 8;
	public final static int                   KEY_9_LOGIN_PARAMETER = 9;
	public final static int                   KEY_10_DEVICE_MANAGER = 10;

	private final static Map<String, Integer> NODE_KEY              = new HashMap<String, Integer>();

	static {
		NODE_KEY.put(NODE_GAME_MANAGER, KEY_1_GAME_MANAGER);
		NODE_KEY.put(NODE_GAME_LIST, KEY_2_GAME_LIST);
		NODE_KEY.put(NODE_GAME_NEW, KEY_3_GAME_ADD);
		NODE_KEY.put(NODE_ACCOUNT, KEY_4_ACCOUNT);
		NODE_KEY.put(NODE_ACCOUNT_SELECT, KEY_5_ACCOUNT_SELECT);
		NODE_KEY.put(NODE_ACCOUNT_GM, KEY_6_ACCOUNT_GM);
		NODE_KEY.put(NODE_ACCOUNT_GM_LIST, KEY_7_ACCOUNT_GM_LIST);
		NODE_KEY.put(NODE_LOGIN, KEY_8_LOGIN);
		NODE_KEY.put(NODE_LOGIN_PARAMETER, KEY_9_LOGIN_PARAMETER);
		NODE_KEY.put(NODE_DEVICE_MANAGER, KEY_10_DEVICE_MANAGER);
	}

	public final static int getNodeKey(String nodeText) {
		Integer key = NODE_KEY.get(nodeText);
		if (key == null) {
			return 0;
		}
		return key;
	}
}
