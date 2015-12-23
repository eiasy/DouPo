package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class UnionEntry extends BaseHandler{
	
	/**
	 * 联盟创建
	 * @author hzw
	 * @date 2015-7-16下午3:35:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void createUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().createUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发联盟信息
	 * @author hzw
	 * @date 2015-7-16下午3:35:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionDetail(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().unionDetail(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发成员信息
	 * @author hzw
	 * @date 2015-7-16下午3:35:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionMember(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().unionMember(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟排行
	 * @author hzw
	 * @date 2015-7-17下午2:10:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionRank(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().unionRank(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 申请加入联盟
	 * @author hzw
	 * @date 2015-7-17下午2:57:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void applyAddUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().applyAddUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取申请
	 * @author hzw
	 * @date 2015-7-17下午3:18:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void obtainApply(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().obtainApply(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 同意申请
	 * @author hzw
	 * @date 2015-7-17下午5:33:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void agreeApply(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().agreeApply(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 取消/拒绝申请
	 * @author hzw
	 * @date 2015-7-17下午6:17:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void refuseApply(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().refuseApply(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 踢出/退出联盟
	 * @author hzw
	 * @date 2015-7-17下午7:32:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void exitUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().exitUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 编写联盟公告/宣言
	 * @author hzw
	 * @date 2015-7-18上午10:39:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void writeUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().writeUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 任命
	 * @author hzw
	 * @date 2015-7-18下午12:02:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void appointUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().appointUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 解散联盟
	 * @author hzw
	 * @date 2015-7-18下午3:39:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void dissolveUnion(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getUnionHandler().dissolveUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟建设
	 * @author hzw
	 * @date 2015-7-20下午5:30:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionBuild(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().unionBuild(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取申请联盟列表
	 * @author hzw
	 * @date 2015-7-21下午5:25:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void applyUnion(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().applyUnion(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发联盟动态
	 * @author hzw
	 * @date 2015-7-22上午11:16:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionDynamic(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().unionDynamic(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟宝箱
	 * @author hzw
	 * @date 2015-7-22下午8:46:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionBox(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().unionBox(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟商店数据下发
	 * @author hzw
	 * @date 2015-7-27上午10:11:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void unionStore(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().unionStore(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟商店购买
	 * @author hzw
	 * @date 2015-7-27上午10:11:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void buyUnionStore(HashMap<String, Object> msgMap, String channelId){
	 try {
			HandlerFactory.getUnionHandler().buyUnionStore(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 联盟-弹劾盟主
	 * @author mp
	 * @date 2015-11-3 上午10:24:50
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void accuseLeader(HashMap<String, Object> msgMap, String channelId){
		 try {
				HandlerFactory.getUnionHandler().accuseLeader(msgMap, channelId);
			} catch (Exception e) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
			}
	}
	
	/**
	 * 是否满足弹劾条件
	 * @author mp
	 * @date 2015-11-3 上午11:35:41
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void isWishUnionAccCon(HashMap<String, Object> msgMap, String channelId){
		 try {
				HandlerFactory.getUnionHandler().isWishUnionAccCon(msgMap, channelId);
			} catch (Exception e) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
			}
	}
	
	/**
	 * 联盟申请全部清空
	 * @author mp
	 * @date 2015-12-15 下午2:31:06
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void clearUnionApplay(HashMap<String, Object> msgMap, String channelId){
		 try {
				HandlerFactory.getUnionHandler().clearUnionApplay(msgMap, channelId);
			} catch (Exception e) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
			}
	}
	
}
