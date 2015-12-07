package com.huayi.doupo.logic.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.fight.Warrior;

/**
 * 与战斗相关玩家数据缓存
 * 推图,激斗,决斗,抢碎片,抢碎片反击
 * @author mp
 * @date 2014-2-24 下午5:20:21
 */
public class FightPlayerCachUtil {
	
	/**
	 * 私有构造方法
	 */
	private FightPlayerCachUtil(){
		
	}
	
	/**
	 * 通用战斗玩家Map[推图,决斗,抢碎片,抢碎片反击]
	 */
	private static ConcurrentHashMap<Integer, List<Warrior>> commFightPlayerMap = null;
	
	/**
	 * 获取通用战斗玩家Map
	 * @author mp
	 * @date 2014-2-24 下午5:25:09
	 * @return
	 * @Description
	 */
	public static ConcurrentHashMap<Integer, List<Warrior>> getCommFightPlayerMap(){
		if(commFightPlayerMap == null){
			commFightPlayerMap = new ConcurrentHashMap<Integer, List<Warrior>>();
		}
		return commFightPlayerMap;
	}
	
	/**
	 * 是否存在某通用战斗玩家缓存信息
	 * @author mp
	 * @date 2014-2-24 下午5:26:41
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static boolean containsCommFightPlayer(int instPlayerId){
		return getCommFightPlayerMap().containsKey(instPlayerId);
	}
	
	/**
	 * 根据instPlayerId返回某个通用战斗的玩家
	 * @author mp
	 * @date 2014-2-24 下午5:28:48
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static List<Warrior> getCommFightPlayer(int instPlayerId){
		if(containsCommFightPlayer(instPlayerId)){
			return getCommFightPlayerMap().get(instPlayerId);
		}
		return null;
	}
	
	/**
	 * 添加某个通用战斗的玩家
	 * @author mp
	 * @date 2014-2-24 下午5:30:25
	 * @param instPlayerId
	 * @param commFightPlayerList
	 * @Description
	 */
	public static void addCommFightPlayer(int instPlayerId, List<Warrior> commFightPlayerList){
		if(!containsCommFightPlayer(instPlayerId)){
			getCommFightPlayerMap().put(instPlayerId, commFightPlayerList);
		}else{
			getCommFightPlayerMap().replace(instPlayerId, commFightPlayerList);
		}
	}
	
	/**
	 * 去掉某个通用战斗的玩家
	 * @author mp
	 * @date 2014-2-24 下午5:31:30
	 * @param instPlayerId
	 * @Description
	 */
	public static void removeCommFightPlayer(int instPlayerId){
		if(containsCommFightPlayer(instPlayerId)){
			getCommFightPlayerMap().remove(instPlayerId);
		}
	}
	
	/**
	 * 清空所有通用战斗的玩家
	 * @author mp
	 * @date 2014-2-24 下午5:37:03
	 * @Description
	 */
	public static void clearCommFightPlayer(){
		commFightPlayerMap = null;
	}
	
	
	/**
	 *推图中怪的缓存Map 
	 */
	private static ConcurrentHashMap<Integer, List<Warrior>> tuiTuEnemyMap = null;
	
	/**
	 * 获取推图中怪的缓存Map
	 * @author mp
	 * @date 2014-2-24 下午5:46:16
	 * @return
	 * @Description
	 */
	public static ConcurrentHashMap<Integer, List<Warrior>> getTuiTuEnemyMap(){
		if(tuiTuEnemyMap == null){
			tuiTuEnemyMap = new ConcurrentHashMap<Integer, List<Warrior>>();
		}
		return tuiTuEnemyMap;
	}
	
	/**
	 * 是否存在某关卡中怪的缓存信息
	 * @author mp
	 * @date 2014-2-24 下午5:48:19
	 * @param barrierId
	 * @return
	 * @Description
	 */
	public static boolean containsTuiTuEnemy(int barrierId){
		return getTuiTuEnemyMap().containsKey(barrierId);
	}
	
	/**
	 * 根据关卡Id获取关卡中怪的缓存信息
	 * @author mp
	 * @date 2014-2-24 下午5:49:53
	 * @param barrierId
	 * @return
	 * @Description
	 */
	public static List<Warrior> getTuiTuEnemy(int barrierId){
		if(containsTuiTuEnemy(barrierId)){
			return getTuiTuEnemyMap().get(barrierId);
		}
		return null;
	}
	
	/**
	 * 添加某关卡下怪的信息缓存
	 * @author mp
	 * @date 2014-2-24 下午5:52:07
	 * @param barrierId
	 * @param tuiTuEnemyList
	 * @Description
	 */
	public static void addTuiTuEnemy(int barrierId, List<Warrior> tuiTuEnemyList){
		if(!containsTuiTuEnemy(barrierId)){
			getTuiTuEnemyMap().put(barrierId, tuiTuEnemyList);
		}else{
			getTuiTuEnemyMap().replace(barrierId, tuiTuEnemyList);
		}
	}
	
}
