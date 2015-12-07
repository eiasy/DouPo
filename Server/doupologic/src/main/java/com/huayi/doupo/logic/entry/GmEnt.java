package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * Gm处理入口
 * @author mp
 * @date 2014-10-10 下午3:05:54
 */
public class GmEnt extends BaseHandler{
	
	public void highPlayer (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().highPlayer(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取在线人数
	 * @author mp
	 * @date 2014-10-10 下午3:09:22
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void onlineNum (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().onlineNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 关闭服务
	 * @author mp
	 * @date 2014-10-10 下午3:09:55
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void closeServer (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().closeServer(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 使用缓存
	 * @author mp
	 * @date 2014-10-13 上午9:33:22
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cacheControl (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().cacheControl(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 刷新字典数据
	 * @author mp
	 * @date 2014-11-5 下午2:55:27
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void refreshDictData (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().refreshDictData(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 派发奖励
	 * @author mp
	 * @date 2014-11-13 下午2:06:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sendReward (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().sendReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:57:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getUserMarquee (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().getUserMarquee(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 添加自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:57:25
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void addUserMarquee (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().addUserMarquee(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 修改自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:57:34
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void updateUserMarquee (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().updateUserMarquee(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 删除自定义跑马灯
	 * @author mp
	 * @date 2014-12-10 下午3:57:44
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void delUserMarquee (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().delUserMarquee(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取活动
	 * @author mp
	 * @date 2015-5-1 下午3:43:51
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getActivity (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().getActivity(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 更新活动
	 * @author mp
	 * @date 2014-12-12 上午11:26:21
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void updateActivity (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().updateActivity(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查看玩家内存map
	 * @author mp
	 * @date 2015-2-5 下午4:03:02
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookPlayerMap (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookPlayerMap(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 初始化世界Boss
	 * @author hzw
	 * @date 2015-2-11上午9:42:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void initWorldBossBlood (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().initWorldBossBlood(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 多服更新字典数据
	 * @author mp
	 * @date 2015-4-7 下午3:10:28
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void updateDict (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().updateDict(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 冻结账号
	 * @author mp
	 * @date 2015-4-10 上午10:25:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void frozen (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().frozen(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 禁言
	 * @author mp
	 * @date 2015-8-6 下午8:15:37
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void shutup (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().shutup(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取冻结账号列表
	 * @author mp
	 * @date 2015-4-10 上午11:17:51
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void frozenList (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().frozenList(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取禁言列表
	 * @author mp
	 * @date 2015-8-6 下午8:16:07
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void shutupList (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().shutupList(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 解除冻结
	 * @author mp
	 * @date 2015-4-10 下午12:15:57
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void frozenFree (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().frozenFree(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 解除禁言
	 * @author mp
	 * @date 2015-8-6 下午8:16:30
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void shutupFree (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().shutupFree(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 设置允许最大同时在线
	 * @author mp
	 * @date 2015-4-13 上午11:09:28
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void allowMaxOnline (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().allowMaxOnline(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取总值
	 * @author mp
	 * @date 2015-4-13 上午11:09:39
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookRecargeSum (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookRecargeSum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 模拟充值
	 * @author mp
	 * @date 2015-5-1 下午3:44:23
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void analogRecharge (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().analogRecharge(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查看玩家缓存大小
	 * @author mp
	 * @date 2015-7-7 上午10:11:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lookPlayerMapSize (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookPlayerMapSize(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 设置聊天Cd时间
	 * @author mp
	 * @date 2015-8-6 下午7:53:37
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void setChatCd (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().setChatCd(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 设置登录验证状态
	 * @author mp
	 * @date 2015-8-12 上午11:41:31
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void setLoginEvnState (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().setLoginEvnState(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 设置购买开服基金总人数
	 * @author mp
	 * @date 2015-8-20 下午5:24:53
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void setOpenServerFundNum (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().setOpenServerFundNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 是否向客服工具推送数据
	 * @author mp
	 * @date 2015-8-25 下午2:22:17
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void pushData (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().pushData(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 给玩家去引导
	 * @author mp
	 * @date 2015-8-28 下午3:53:06
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void dropGuip (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().dropGuip(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 查看游戏服中map/list大小信息
	 * @author mp
	 * @date 2015-8-29 下午6:09:44
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookMapSizeInfo (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookMapSizeInfo(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查看游戏服中map/list容量信息
	 * @author mp
	 * @date 2015-8-29 下午6:12:20
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookMapCaptainInfo (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookMapCaptainInfo(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查看玩家缓存容量
	 * @author mp
	 * @date 2015-8-29 下午6:43:11
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookPlayerMapCaptainInfo (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().lookPlayerMapCaptainInfo(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 清除下线几个小时的玩家缓存对象,默认2个小时
	 * @author mp
	 * @date 2015-8-30 下午4:56:52
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void clearNHour (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().clearNHour(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 设置允许的最大注册数
	 * @author mp
	 * @date 2015-8-31 上午7:15:20
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void setCanAllowMaxRegeditNum (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().setCanAllowMaxRegeditNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 添加月卡
	 * @author mp
	 * @date 2015-9-1 下午1:46:13
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void addMonthCard (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().addMonthCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 添加黄金月卡
	 * @author mp
	 * @date 2015-10-28 上午11:43:01
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void addGoldMonthCard (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().addGoldMonthCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查询当前设置的最大注册数
	 * @author mp
	 * @date 2015-9-2 上午11:25:03
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void currMaxRegeditNum (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getGmHandler().currMaxRegeditNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
