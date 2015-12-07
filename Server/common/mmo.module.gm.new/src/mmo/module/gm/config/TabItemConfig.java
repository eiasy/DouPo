package mmo.module.gm.config;

import java.util.HashMap;
import java.util.Map;

public class TabItemConfig {
	/** 账号信息 */
	public static final int ITEM_1_ROLE_INFO = 1;
	/** 道具查询 */
	public static final int ITEM_2_ITEM_INFO = 2;
	/** 创建订单 */
	public static final int ITEM_3_CHARGE_CREATE_ORDER = 3;
	/** 重置密码 */
	public static final int ITEM_4_RESET_PWD = 4;
	/** 完成支付 */
	public static final int ITEM_5_CHARGE_FINISH = 5;
	/** 到帐记录 */
	public static final int ITEM_6_GET_GOLD = 6;
	/** 应用配置 */
	public static final int ITEM_7_APP_CONFIG = 7;

	private final static Map<Integer, String> tabItems = new HashMap<Integer, String>();

	static {

		tabItems.put(ITEM_1_ROLE_INFO, "账号信息");
		tabItems.put(ITEM_2_ITEM_INFO, "道具查询");
		tabItems.put(ITEM_3_CHARGE_CREATE_ORDER, "创建订单");
		tabItems.put(ITEM_4_RESET_PWD, "重置密码");
		tabItems.put(ITEM_5_CHARGE_FINISH, "完成支付");
		tabItems.put(ITEM_6_GET_GOLD, "到帐记录");
		tabItems.put(ITEM_7_APP_CONFIG, "应用配置");

	}

	public final static String getTabItemTitle(int item) {
		String itemName = tabItems.get(item);
		if (itemName == null) {
			return item + "-未知";
		}
		return itemName;
	}
}
