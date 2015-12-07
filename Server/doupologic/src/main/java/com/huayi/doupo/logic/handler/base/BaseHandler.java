package com.huayi.doupo.logic.handler.base;

import java.util.HashMap;

import org.junit.Test;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class BaseHandler extends DALFactory {
	
	@Test
	public void test() throws Exception{
		
	}
	
	/**
	 * 根据ChannelId获取玩家ID
	 * @author mp
	 * @date 2014-4-3 上午8:58:08
	 * @param channelId
	 * @return
	 * @Description
	 */
	public static int getInstPlayerId(String channelId){
		return PlayerMapUtil.getPlayerIdByChannelId(channelId);
	}
	
	/**
	 * 错误提示
	 * @author mp
	 * @date 2014-11-4 下午5:38:41
	 * @param msgMap
	 * @return
	 * @Description
	 */
	public static String errorHint (HashMap<String, Object> msgMap) {
		return DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + " ERROR !";
	}
	
	/**
	 * 根据ChannelId获取Player对象
	 * @author mp
	 * @date 2014-4-3 上午8:57:48
	 * @param channelId
	 * @return
	 * @Description
	 */
	public static Player getPlayer (String channelId) {
		return PlayerMapUtil.getPlayerByChannelId(channelId);
	}

}
