package com.huayi.doupo.logic.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.fight.Warrior;

/**
 * 世界Boss玩家信息缓存工具类
 * @author mp
 * @date 2014-2-21 上午10:30:16
 */
public class WorldBossPlayerCacheUtil {
	
	/**
	 * 私有构造方法
	 */
	private WorldBossPlayerCacheUtil() {
		
	}
	
	/**
	 * 打世界Boss玩家信息缓存 Map->Map<instPlayerId, List<Warrior>>
	 */
	private static ConcurrentHashMap<Integer, List<Warrior>> worldBossPlayerMap = null;
	
	/**
	 * 获取打世界Boss玩家信息缓存  Map
	 * @author mp
	 * @date 2014-2-21 上午11:36:38
	 * @return
	 * @Description
	 */
	public static ConcurrentHashMap<Integer, List<Warrior>> getWorldBossPlayerMap() {
		if (worldBossPlayerMap == null) {
			worldBossPlayerMap = new ConcurrentHashMap<Integer, List<Warrior>>();
		}
		return worldBossPlayerMap;
	}
	
	/**
	 * 是否存在某玩家缓存信息
	 * @author mp
	 * @date 2014-2-21 上午11:41:46
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static boolean containsWorldBossPlayer(int instPlayerId){
		return getWorldBossPlayerMap().containsKey(instPlayerId);
	}
	
	/**
	 * 根据instPlayerId返回某个打Boss的玩家
	 * @author mp
	 * @date 2014-2-22 上午9:43:12
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static List<Warrior> getWorldBossPlayer(int instPlayerId){
		if(containsWorldBossPlayer(instPlayerId)){
			return getWorldBossPlayerMap().get(instPlayerId);
		}
		return null;
	}
	
	/**
	 * 添加打世界Boss的玩家
	 * @author mp
	 * @date 2014-2-21 上午11:54:35
	 * @param instPlayerId
	 * @param worldBossPlayerList
	 * @Description
	 */
	public static void addWorldBossPlayer(int instPlayerId, List<Warrior> worldBossPlayerList){
		if(!containsWorldBossPlayer(instPlayerId)){
			getWorldBossPlayerMap().put(instPlayerId, worldBossPlayerList);
		}
	}
	
	/**
	 * 去掉打世界Boss的玩家
	 * @author mp
	 * @date 2014-2-22 上午9:27:42
	 * @param instPlayerId
	 * @Description
	 */
	public static void removeWorldBossPlayer(int instPlayerId){
		if(containsWorldBossPlayer(instPlayerId)){
			getWorldBossPlayerMap().remove(instPlayerId);
		}
	}
	
	/**
	 * 清空打世界Boss的玩家
	 * @author mp
	 * @date 2014-2-22 上午9:33:31
	 * @Description
	 */
	public static void clearWorldBossPlayer(){
		worldBossPlayerMap = null;
	}
	
	
	
	/**
	 * 世界Boss信息缓存Map->Map<formationNum, List<Warrior>>
	 */
	private static ConcurrentHashMap<Integer, List<Warrior>> worldBossMap = null;
	
	/**
	 * 返回世界Boss信息缓存Map
	 * @author mp
	 * @date 2014-2-21 上午11:38:22
	 * @return
	 * @Description
	 */
	public static ConcurrentHashMap<Integer, List<Warrior>> getWorldBossMap() {
		if(worldBossMap == null) {
			worldBossMap = new ConcurrentHashMap<Integer, List<Warrior>>();
		}
		return worldBossMap;
	}
	
	/**
	 * 是否存在此阵型人数的世界Boss信息
	 * @author mp
	 * @date 2014-2-21 上午11:43:11
	 * @param hitBossNum
	 * @return
	 * @Description
	 */
	public static boolean containsWorldBoss(int formationNum){
		return getWorldBossMap().containsKey(formationNum);
	}
	
	/**
	 * 返回某个阵型的世界Boss
	 * @author mp
	 * @date 2014-2-22 上午9:46:46
	 * @param formationNum
	 * @return
	 * @Description
	 */
	public static List<Warrior> getWorldBoss(int formationNum){
		if(containsWorldBoss(formationNum)){
			return getWorldBossMap().get(formationNum);
		}
		return null;
	}
	
	/**
	 * 添加某阵型下的世界Boss
	 * @author mp
	 * @date 2014-2-22 上午9:24:21
	 * @param hitBossNum
	 * @param worldBossList
	 * @Description
	 */
	public static void addWorldBoss(int formationNum, List<Warrior> worldBossList){
		if(!containsWorldBoss(formationNum)){
			getWorldBossMap().put(formationNum, worldBossList);
		}
	}
	
	/**
	 * 去除某阵型下的世界Boss
	 * @author mp
	 * @date 2014-2-22 上午9:30:42
	 * @param formationNum
	 * @Description
	 */
	public static void removeWorldBoss(int formationNum){
		if(containsWorldBoss(formationNum)){
			getWorldBossMap().remove(formationNum);
		}
	}
	
	/**
	 * 清空某阵型下的世界Boss
	 * @author mp
	 * @date 2014-2-22 上午9:34:14
	 * @Description
	 */
	public static void clearWorldBoss(){
		worldBossMap = null;
	}
	
	
	
	/**
	 * 清空所有，包含打世界Boss的玩家和各阵型的世界Boss
	 * @author mp
	 * @date 2014-2-22 上午9:35:13
	 * @Description
	 */
	public static void clearAll(){
		clearWorldBossPlayer();
		clearWorldBoss();
	}
	
}
