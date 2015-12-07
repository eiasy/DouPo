package com.huayi.doupo.logic.entry;

import java.util.HashMap;
import java.util.List;

import com.huayi.doupo.base.model.DictAcupointNode;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 功法处理入口
 * @author hzw
 * @date 2014-7-16下午2:50:39
 */
public class KungFuEnt extends BaseHandler{

	/**
	 * 添加功法
	 * @author hzw
	 * @date 2014-7-16下午4:03:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addKungFu(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().addKungFu(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 运功
	 * @author hzw
	 * @date 2014-7-16下午4:14:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addNode(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().addNode(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键升满
	 * @author hzw
	 * @date 2014-7-18上午10:45:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addNodes(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().addNodes(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 更换功法
	 * @author hzw
	 * @date 2014-7-18上午11:42:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void convertKungFu(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().convertKungFu(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 吞噬升级功法
	 * @author hzw
	 * @date 2014-7-21下午4:34:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void deleteKungFu(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().deleteKungFu(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售功法
	 * @author hzw
	 * @date 2014-9-2下午4:30:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void sellKungFus(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getKungFuHandler().sellKungFus(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void test() throws Exception{
		SpringUtil.getSpringContext();
		DictData.loadDictData();
		List<DictAcupointNode> dictAcupointNodeList = (List<DictAcupointNode>)DictMapList.dictAcupointNodeMap.get(1);
	}
	
	
}
