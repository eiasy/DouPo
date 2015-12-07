package mmo.common.protocol.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GmPower {
	private final static Map<Integer, String> menus = new HashMap<Integer, String>();
	private final static Map<Integer, String> menuItems = new HashMap<Integer, String>();


	static {
		menus.put(1, "客服工具");
		menuItems.put(1, "玩家账号");
		menuItems.put(2, "道具查询");
		menuItems.put(3, "创建订单");
		menuItems.put(5, "完成支付");
		menuItems.put(6, "到帐记录");
		menuItems.put(7, "分区配置");
	}
	
	public final static String getMenu(int id){
		String name = menus.get(id);
		if (name == null) {
			return "异常#" + id;
		}
		return name;
	}
	
	public final static String getMenuItem(int id){
		String name = menuItems.get(id);
		if (name == null) {
			return "异常#" + id;
		}
		return name;
	}

	public final static int getMenuItemId(String name){
		Set<Integer> keys = menuItems.keySet();
		for(int k:keys){
			if(menuItems.get(k).equalsIgnoreCase(name)){
				return k;
			}
		}
		return 0;
	}
}
