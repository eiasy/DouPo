package com.huayi.doupo.logic.util;

import com.huayi.doupo.activity.PlayerActivityManager;
import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.cost.TotalCostManager;
import com.huayi.doupo.activity.run.ResetPlayerDantaDayRun;
import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.player.PlayerMemObjMapUtil;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.UserUtil;
import com.huayi.doupo.logic.handler.util.unionwar.UnionWarUtil;

public class SocketMapUtil {

	/**
	 * 清空所有socket中的map
	 * @author mp
	 * @version 1.0, 2013-4-17 下午5:19:08
	 * @param channelId
	 */
	public static void removeSocketMap(String channelId) throws Exception{
		
		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		
		if(player != null){
			
			//设置玩家缓存对象时间为当前时间
			if (DALFather.isUseCach()) {
				PlayerMemObj playerMemObj = PlayerMemObjMapUtil.getPlayerMemObjByPlayerId(player.getPlayerId());
				if (playerMemObj != null) {
					playerMemObj.currTime = DateUtil.getCurrMill();//登录跟初始化的时候设置成0,玩家退出时设置为当前时间,清除的是退出游戏2个小时的人,0的时候表示在线,不处理
				}
			}
			
			//如果充值队列中有此玩家,将此玩家移除
			PlayerUtil.removeDelayRechargePlayer(player);
			
			//将玩家从PlayerMap中清除
			PlayerMapUtil.removeByChannelId(channelId);
			
			//处理用户退出操作
			UserUtil.exitUpdate(player.getOpenId());
			
			//处理退出牵扯到的事
			PlayerUtil.disposeClose(player);
			
			//清理活动数据
			PlayerActivityManager.getInstance().removePlayerActivities(player.getPlayerId());
			TotalCostManager.getInstance().removePlayerData(player.getPlayerId());
			ThreadManager.execute(new ResetPlayerDantaDayRun(player.getPlayerId()));
			UnionWarUtil.delViewer(player.getPlayerId());
		}
		
		//将玩家从ChannelMap中清除
		ChannelMapUtil.remove(channelId);
	}
	
	/**
	 * 清空同步socket中的map
	 * @author mp
	 * @version 1.0, 2013-4-17 下午5:19:08
	 * @param channelId
	 */
	public static void removeSyncSocketMap(String channelId) throws Exception{

		Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
		
		if(player != null){
			//将玩家从PlayerMap中清除
			PlayerMapUtil.removeByChannelId(channelId);
			//将玩家从WorldBossPlayerMap中清除
//			WorldBossPlayerMapUtil.removeByChannelId(channelId);
		}
		
		//将玩家从ChannelMap中清除
		ChannelMapUtil.remove(channelId);
	}
	
	/**
	 * 更新ServerMap和PlayerMap
	 * @author mp
	 * @version 1.0, 2013-4-17 下午5:53:13
	 * @param player
	 * @param serverId
	 * @param playerId
	 */
	public static void updateServerAndPlayerMap(Player player, int serverId, int playerId){/*
		if(player.getServerId() != serverId){
			ServerMapUtil.removePlayer(player);
			player.setServerId(serverId);
			ServerMapUtil.add(serverId, player);
		}
		if(player.getPlayerId() != playerId){
			player.setPlayerId(playerId);
		}
	*/}
}
