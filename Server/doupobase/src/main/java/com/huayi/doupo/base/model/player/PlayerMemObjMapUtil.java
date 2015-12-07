package com.huayi.doupo.base.model.player;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 玩家内存对象Map工具类
 * @author mp
 * @date 2014-5-8 下午2:30:45
 */
public class PlayerMemObjMapUtil{
	
	/**
	 * 私有构造方法
	 */
	private PlayerMemObjMapUtil() {
		
	}
	
	private static ConcurrentHashMap<Integer, PlayerMemObj> playerMemObjMap = null;
	
	/**
	 * 获取玩家内存对象Map
	 * @author mp
	 * @date 2014-5-8 下午2:32:03
	 * @return
	 * @Description
	 */
	public static ConcurrentHashMap<Integer, PlayerMemObj> getMap() {
		if (playerMemObjMap == null) {
			playerMemObjMap = new ConcurrentHashMap<Integer, PlayerMemObj>();
		}
		return playerMemObjMap;
	}
	
	/**
	 * 获取玩家内存对象Map的大小
	 * @author mp
	 * @date 2014-5-8 下午2:32:44
	 * @return
	 * @Description
	 */
	public static int getSize() {
		return getMap().size();
	}
	
	/**
	 * 根据玩家ID删除此玩家内存对象
	 * @author mp
	 * @date 2014-5-8 下午2:34:35
	 * @param playerId
	 * @Description
	 */
	public static void removeByPlayerId(Integer instPlayerId) {
		if (getMap().containsKey(instPlayerId)) {
			getMap().remove(instPlayerId);
		}
	}

	/**
	 * 向Map中添加玩家内存对象
	 * @author mp
	 * @date 2014-5-8 下午2:39:05
	 * @param playerId
	 * @param playerMemObj
	 * @Description
	 */
	public static void add(Integer instPlayerId, PlayerMemObj playerMemObj) {
		if(!getMap().containsKey(playerMemObj)){
			getMap().put(instPlayerId, playerMemObj);
		}
	}
	
	/**
	 * 根据指定playerId获取玩家内存对象
	 * @author mp
	 * @date 2014-5-8 下午2:44:12
	 * @param userId
	 * @return
	 * @Description
	 */
	public static PlayerMemObj getPlayerMemObjByPlayerId(Integer instPlayerId) {
		PlayerMemObj playerMemObj = null;
		if (getMap().containsKey(instPlayerId)) {
			playerMemObj = getMap().get(instPlayerId);
		}
		return playerMemObj;
	}
	
	/**
	 * 清除所有玩家内存对象
	 * @author mp
	 * @date 2014-5-8 下午2:51:32
	 * @Description
	 */
	public static void clearAll() {
		playerMemObjMap = null;
	}
	
}
