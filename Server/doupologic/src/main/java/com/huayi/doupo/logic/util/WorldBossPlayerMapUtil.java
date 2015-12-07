package com.huayi.doupo.logic.util;

import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.socket.WorldBossPlayer;

/**
 * 世界Boss玩家管理Map,存入<instPlayerId, WorldBossPlayer>
 * @author hzw
 * @date 2015-1-6上午11:44:00
 */
public class WorldBossPlayerMapUtil {
	
	private WorldBossPlayerMapUtil() {
		
	}

	private static ConcurrentHashMap<Integer, WorldBossPlayer> linkedPlayerMap = null;
	public static int lastFightInstPlayerId = 0; //最后一击
	
	public static ConcurrentHashMap<Integer, WorldBossPlayer> getMap() {
		if (linkedPlayerMap == null) {
			linkedPlayerMap = new ConcurrentHashMap<Integer, WorldBossPlayer>();
		}
		return linkedPlayerMap;
	}
	
	/**
	 * 返回map的大小
	 * @author hzw
	 * @date 2015-1-6上午11:43:44
	 * @Description
	 */
	public static int getSize() {
		return getMap().size();
	}

	/**
	 * 清空map
	 * @author hzw
	 * @date 2015-1-6上午11:43:17
	 * @Description
	 */
	public static void clearAll() {
		linkedPlayerMap = null;
	}
	
	/**
	 * 根据instPlayerId，删除WorldBossPlayer
	 * @author hzw
	 * @date 2015-1-6上午11:42:51
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void removeByChannelId(Integer instPlayerId) {
		if (getMap().containsKey(instPlayerId)) {
			getMap().remove(instPlayerId);
		}
	}

	/**
	 * 向map中加入worldBossPlayer
	 * @author hzw
	 * @date 2015-1-6上午11:42:27
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void add(Integer instPlayerId, WorldBossPlayer worldBossPlayer) throws Exception{
		if(!containsChannelId(instPlayerId)){
			getMap().put(instPlayerId, worldBossPlayer);
		}
	}

	/**
	 * 判断map是否包含指定的instPlayerId
	 * @author hzw
	 * @date 2015-1-6上午11:42:09
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static boolean containsChannelId(Integer instPlayerId) {
		return getMap().containsKey(instPlayerId);
	}
	
	/**
	 * 根据指定的instPlayerId，获取WorldBoss对象
	 * @author hzw
	 * @date 2015-1-6上午11:41:21
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static WorldBossPlayer getWorldBossPlayerByChannelId(Integer instPlayerId) {
		if(getMap().containsKey(instPlayerId)){
			return getMap().get(instPlayerId);
		}
		return null;
	}
	

}
