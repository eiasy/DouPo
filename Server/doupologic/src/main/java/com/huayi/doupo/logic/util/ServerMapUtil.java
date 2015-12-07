package com.huayi.doupo.logic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.socket.Player;

/**
 * 服务玩家map管理类
 * @author mp
 * @version 1.0, 2013-4-16 上午11:57:54
 */
public class ServerMapUtil {
	
	private ServerMapUtil() {
	
	}

	private static ConcurrentHashMap<Integer, List<Player>> linkedServerMap = null;//发消息时按玩家进入顺序进行发送
	
	public static ConcurrentHashMap<Integer, List<Player>> getMap() {
		if (linkedServerMap == null) {
			linkedServerMap = new ConcurrentHashMap<Integer, List<Player>>();
		}
		return linkedServerMap;
	}
	
	/**
	 * 返回map的大小
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:19:40
	 * @return
	 * @throws Exception
	 */
	public static int getSize() {
		return getMap().size();
	}

	/**
	 * 添加
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:03:48
	 * @param serverId
	 * @param player
	 */
	public static void add(int serverId, Player player){
		if(getMap().containsKey(serverId)){
			List<Player> players = getMap().get(serverId);
			players.add(player);
		}else{
			List<Player> players = new ArrayList<Player>();
			players.add(player);
			getMap().put(serverId, players);
		}
	}
	
	/**
	 * 清空map
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:19:11
	 * @throws Exception
	 */
	public static void clearAll() {
		linkedServerMap = null;
	}
	
	/**
	 * 删除某服务区下的某个玩家
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:06:07
	 * @param player
	 */
	public static void removePlayer(Player player){
//		if(player != null){
//			List<Player> list = getMap().get(player.getServerId());
//			if (list != null) {
//				list.remove(player);
//			}
//		}
	}
	
	/**
	 * 删除某个服务区
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:07:35
	 * @param serverId
	 */
	public static void removeServer(int serverId){
		if(getMap().containsKey(serverId)){
			getMap().remove(serverId);
		}
	}
	
	/**
	 * 根据serverId获取此服务区下的玩家列表
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:08:28
	 * @param serverId
	 * @return
	 */
	public static List<Player> getPlayerList(int serverId){
		List<Player> players = null;
		if(getMap().containsKey(serverId)){
			players = getMap().get(serverId);
		}
		return players;
	}
	
	/**
	 * 根据玩家，获取此玩家所在服务区下的所有玩家列表
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:15:05
	 * @param player
	 * @return
	 */
//	public static List<Player> getPlayerList(Player player){
////		return getPlayerList(player.getServerId());
//	}
	
	/**
	 * 获取玩家所在服务区下除自己外的其他玩家列表
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:19:50
	 * @param player
	 * @return
	 */
//	public static  List<Player> getOtherPlayerList(Player player){
//		List<Player> otherPlayers = new ArrayList<Player>();
//		List<Player> players = getPlayerList(player);
//		for(Player p : players){
//			if(p.getUserId() == player.getUserId() && p.getServerId() == player.getServerId()){
//				continue;
//			}
//			otherPlayers.add(p);
//		}
//		return otherPlayers;
//	}

}
