package com.huayi.doupo.logic.handler.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于每日竞技场发奖
 * @author hzw
 * @date 2014-11-1下午4:58:46
 */
public class AwardUtil {

	//key instplayerId : value Map<yyyy-MM-dd HH:mm:ss, rank>
	private static Map<Integer, Map<String, Integer>> AwarMap = null;
	
	public static Map<Integer, Map<String, Integer>> getMap() {
		if (AwarMap == null) {
			AwarMap = new HashMap<Integer, Map<String, Integer>>();
		}
		return AwarMap;
	}
	
	/**
	 * 设置竞技场奖励map(用于gm停服序列化后，启动服务器时反序列化)
	 * @author mp
	 * @date 2015-10-8 下午4:46:21
	 * @param newAreaAwardMap
	 * @Description
	 */
	public static void setAreaAward (Map<Integer, Map<String, Integer>> newAreaAwardMap) {
		AwarMap = newAreaAwardMap;
	}
}
