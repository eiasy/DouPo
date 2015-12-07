package com.huayi.doupo.logic.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.socket.Player;

/**
 * 玩家管理Map,存入<channelId,Player>
 * @author mp
 * @date 2013-10-11 下午2:11:26
 */
public class PlayerMapUtil {
	
	private PlayerMapUtil() {
	}

	private static ConcurrentHashMap<String, Player> linkedPlayerMap = null;
	
	public static ConcurrentHashMap<String, Player> getMap() {
		if (linkedPlayerMap == null) {
			linkedPlayerMap = new ConcurrentHashMap<String, Player>();
		}
		return linkedPlayerMap;
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
	 * 清空map
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:19:11
	 * @throws Exception
	 */
	public static void clearAll() {
		linkedPlayerMap = null;
	}
	
	/**
	 * 根据channelId，删除player
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:23:11
	 * @param channelId
	 */
	public static void removeByChannelId(String channelId) {
		if (getMap().containsKey(channelId)) {
			getMap().remove(channelId);
		}
	}
	
	/**
	 * 根据openId，删除player
	 * @author mp
	 * @date 2013-10-11 下午2:20:56
	 * @param openId
	 * @Description
	 */
	public static void removeByOpenId(String openId) {
		Player player = getPlayerByOpenId(openId);
		if(player != null){
			String channelId = player.getChannelId();
			if (getMap().containsKey(channelId)) {
				getMap().remove(channelId);
			}
		}
	}

	/**
	 * 向map中加入player
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:22:40
	 * @param playerId
	 * @param player
	 * @throws Exception
	 */
	public static void add(String channelId, Player player) throws Exception{
		List<Player> playerList = getPlayerListByOpenId(player.getOpenId());
		for(Player p : playerList){
			PlayerMapUtil.removeByChannelId(p.getChannelId());
			ChannelMapUtil.remove(p.getChannelId());
		}
		
		if(!containsUniquePlayer(player)){
			getMap().put(channelId, player);
		}
	}

	/**
	 * 判断map是否包含指定的ChannelId
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:23:04
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public static boolean containsChannelId(String channelId) {
		return getMap().containsKey(channelId);
	}

	/**
	 * 判断map是否包含指定的player对象
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:24:37
	 * @param player
	 * @return
	 * @throws Exception
	 */
	public static boolean containsPlayer(Player player) {
		boolean flag = false;
		if (player != null) {
			if (containsChannelId(player.getChannelId())) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 验证此Player是否唯一
	 * @author mp
	 * @date 2013-10-24 上午11:20:57
	 * @param player
	 * @return
	 * @Description
	 */
	public static boolean containsUniquePlayer(Player player) {
		boolean flag = false;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(player.getOpenId()) && p.getPlayerId() == player.getPlayerId()) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 验证此Player是否唯一
	 * @author mp
	 * @date 2013-12-26 上午11:45:11
	 * @param openId
	 * @return
	 * @Description
	 */
	public static boolean containsUniquePlayer(String openId) {
		boolean flag = false;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(openId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 获取唯一Player
	 * @author mp
	 * @date 2013-10-24 上午11:27:19
	 * @param player
	 * @return
	 * @Description
	 */
	public static Player getUniquePlayer(Player player) {
		Player oldPlayer = null;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(player.getOpenId()) && p.getPlayerId() == player.getPlayerId()) {
				oldPlayer = p;
				break;
			}
		}
		return oldPlayer;
	}
	
	/**
	 * 获取唯一Player
	 * @author mp
	 * @date 2013-12-26 上午11:49:28
	 * @param openId
	 * @return
	 * @Description
	 */
	public static Player getUniquePlayer(String openId) {
		Player oldPlayer = null;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(openId)) {
				oldPlayer = p;
				break;
			}
		}
		return oldPlayer;
	}
	
	/**
	 * 根据ChannelId获取PlayerId
	 * @author mp
	 * @date 2013-10-14 上午9:58:58
	 * @param channelId
	 * @return
	 * @Description
	 */
	public static int getPlayerIdByChannelId(String channelId){
		Player player = getPlayerByChannelId(channelId);
		if(player != null){
			return player.getPlayerId();
		}
		return 0;
			
	}

	/**
	 * 根据指定的channelId，获取player对象
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:24:58
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public static Player getPlayerByChannelId(String channelId) {
		if(getMap().containsKey(channelId)){
			return getMap().get(channelId);
		}
		return null;
	}
	
	/**
	 * 根据指定的openId，获取player对象
	 * @author mp
	 * @date 2013-10-11 下午2:20:11
	 * @param openId
	 * @return
	 * @Description
	 */
	public static Player getPlayerByOpenId(String openId) {
		Player player = null;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(openId)) {
				player = p;
				break;
			}
		}
		return player;
	}
	
	/**
	 * 根据玩家名字返回玩家对象
	 * @author mp
	 * @date 2015-2-5 下午3:40:50
	 * @param playerName
	 * @return
	 * @Description
	 */
	public static Player getPlayerByPlayerName(String playerName) {
		Player player = null;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getPlayerName().equals(playerName)) {
				player = p;
				break;
			}
		}
		return player;
	}
	
	/**
	 * 根据指定的playerId，获取player对象
	 * @author mp
	 * @date 2014-6-26 上午11:27:12
	 * @param playerId
	 * @return
	 * @Description
	 */
	public static Player getPlayerByPlayerId(int playerId) {
		Player player = null;
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getPlayerId() == playerId) {
				player = p;
				break;
			}
		}
		return player;
	}
	
	/**
	 * 获取有可能出现的所有相同的openId
	 * @author mp
	 * @date 2014-1-13 下午4:00:01
	 * @param openId
	 * @return
	 * @Description
	 */
	public static List<Player> getPlayerListByOpenId(String openId) {
		List<Player> playerList = new ArrayList<Player>();
		Iterator<String> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			String channelId = it.next();
			Player p = getMap().get(channelId);
			if (p != null && p.getOpenId().equals(openId)) {
				playerList.add(p);
			}
		}
		return playerList;
	}
	
	public static String getLogHeader (String channelId) {
		Player player = getPlayerByChannelId(channelId);
		if (player != null) {
			return "" + "|" ;
		}
		return "";
	}
	
}
