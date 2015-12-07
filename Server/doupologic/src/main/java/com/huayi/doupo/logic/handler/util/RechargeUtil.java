package com.huayi.doupo.logic.handler.util;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictRecharge;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstPlayerRechargeFail;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.EncryptUtil;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 充值工具类
 * @author mp
 * @date 2015-7-13 下午6:10:40
 */
public class RechargeUtil extends DALFactory{
	
	/**
	 * 充值处理
	 * @author mp
	 * @date 2015-7-13 下午6:16:52
	 * @param channel
	 * @param paramsMap
	 * @Description
	 */
	public static synchronized String recharge (Map<String, String> paramsMap) throws Exception{
//		if (1==1) {
//			writeResponse(channel, JsonUtil.toJson("hello netty server"));
//			return;
//		}
		
		Player player = null;
		LogUtil.out("================Insert Recharge Method=================");
		try {
			int instPlayerId = ConvertUtil.toInt(paramsMap.get("roleId"));
			int rechargeId = ConvertUtil.toInt(paramsMap.get("goodsId"));// 充值字典表Id 
			int regState = ConvertUtil.toInt(paramsMap.get("ctype"));// 充值状态
			String imei = paramsMap.get("deviceImei");
			
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId,instPlayerId);
			String openId = instPlayer.getOpenId();
			int operBeforeGoldNum = instPlayer.getGold();
			player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			MessageData syncMsgData = new MessageData();
			
			if (rechargeId == 4 && imei.equals("9")) {
				rechargeId = 9;
			}
		
			if (regState == 1 || regState == 2) {
				
//				if (player == null) {
//					Map<String, String> retMap = new HashMap<String, String>();
//					retMap.put("code", "3");
//					retMap.put("message", "充值发生异常-玩家对象为空");
//					//记录充值失败日志
//					recordRechargeFail(paramsMap, "充值发生异常-玩家对象为空");
//					return JsonUtil.toJson(retMap);
//				}
				
				//验证sign
				String sign = EncryptUtil.md5Hex(paramsMap.get("id") + "/" + paramsMap.get("orderId") + "/" + paramsMap.get("gameId") + "/" + paramsMap.get("serverId") + "/" + paramsMap.get("channelId") + "/" + 
						paramsMap.get("accountId") + "/" + paramsMap.get("roleId") + "/" + paramsMap.get("rolename") + "/" + paramsMap.get("money") + "/" + paramsMap.get("ctype") + "/" + 
						paramsMap.get("orderform") + "/" + paramsMap.get("userid") + "/" + paramsMap.get("proxy") + "/" + paramsMap.get("goodsId"));
				
				//签名不一致
				if (!paramsMap.get("sign").equals(sign)) {
					if (player != null) {
						MessageUtil.sendFailMsg(player.getChannelId(), null, StaticCnServer.fail_recharge_status);
					}
					Map<String, String> retMap = new HashMap<String, String>();
					retMap.put("code", "2");
					retMap.put("message", "签名未通过");
//					writeResponse(channel, JsonUtil.toJson(retMap));
					//记录充值失败日志
					recordRechargeFail(paramsMap, "签名未通过");
					return JsonUtil.toJson(retMap);
				}
				
				//验证订单是否已经存在
//				int count = 0;
//				if (player.getAloneServerId().equals("3")) {
//					count = getInstPlayerRechargeDAL().getStatResult("count", "*", " orderform = '"+paramsMap.get("orderform")+"'");
//				} else {
				int count = getInstPlayerRechargeDAL().getStatResult("count", "*", "instPlayerId=" + instPlayerId + " and orderform = '"+paramsMap.get("orderform")+"'");
//				}
				if (count > 0) {
					if (player != null) {
						MessageUtil.sendFailMsg(player.getChannelId(), null, StaticCnServer.fail_recharge_status);
					}
					Map<String, String> retMap = new HashMap<String, String>();
					retMap.put("code", "4");
					retMap.put("message", "已有此订单号记录");
//					writeResponse(channel, JsonUtil.toJson(retMap));
					//记录充值失败日志
					recordRechargeFail(paramsMap, "已有此订单号记录");
					makeSureRechargeOrder(paramsMap, "2");
					return JsonUtil.toJson(retMap);
				}
				
				//给充值服务器返回消息-处理中
//				Map<String, String> retMap = new HashMap<String, String>();
//				retMap.put("code", "1");
//				retMap.put("message", "充值处理中");
//				writeResponse(channel, JsonUtil.toJson(retMap));
				
				DictRecharge recharge = DictMap.dictRechargeMap.get(rechargeId + "");
				int thisGold = 0;
				int thisrmb = recharge.getRmb();
				int thisamt = thisrmb * 10;// 本次充值金额(游戏币)
				int extrGold = 0;
				
				int rechargeMoney = ConvertUtil.toInt(paramsMap.get("money"));//充值服务器返回的充了多少钱-分
				if (rechargeMoney != (thisrmb * 100)) {
					//特殊处理
					thisrmb = rechargeMoney / 100;//元
					thisGold = rechargeMoney / 10;//本次得到的元宝
					thisamt = thisGold;
					instPlayer.setGold(instPlayer.getGold() + thisGold);
				} else {
					if(recharge.getFirstAmt() == 0){
						thisGold = thisrmb * DictMapUtil.getSysConfigIntValue(StaticSysConfig.monthCardTimes);
						instPlayer.setGold(instPlayer.getGold() + thisGold);
						
						//确认月卡实例数据生效
						if (thisrmb == 30) {
							ActivityUtil.addMonthCard(instPlayerId, StaticActivity.monthCard, syncMsgData, 1);
						} else if (thisrmb == 50 || thisrmb == 68) {
							ActivityUtil.addMonthCard(instPlayerId, StaticActivity.monthCard, syncMsgData, 2);
						}
					}else{
						//倒叙排序
						List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
						Collections.sort(instPlayerRechargeList, new Comparator<Object>() {
							public int compare(Object a, Object b) {
								int one = ((InstPlayerRecharge)a).getSaveamt();
								int two = ((InstPlayerRecharge)b).getSaveamt();
								return (int)(two - one); 
							}
						}); 
						
						//处理额外赠送元宝（首充/非首充/没有这一档）
						int freeGold = PlayerUtil.getFreeGold(thisrmb, instPlayerRechargeList, openId);
						extrGold = freeGold;
						
						//处理元宝  赠送的元宝 + 充值的元宝
						thisGold = freeGold + thisrmb * 10;
						instPlayer.setGold(instPlayer.getGold() + thisGold);
					}
				}
				
				//如果在节假日活动期内,原本获得的元宝数有优惠 比如充10块得100元宝的经验,如果在节假日活动期内，给1.2倍的经验，则送100*1.2=120的元宝
				if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.vipExp)) {
					DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(StaticActivityHoliday.vipExp + "");
					thisamt = (int)(thisamt * activityHoliday.getMultiple());
				}
				
				// 处理vip逻辑-根据总充值rmb金额,计算vip等级  ***2015-09-23加了一个国庆充值经验优惠活动,在算vip的时候，总充值金额非sum(thisrmb),而是sum(thisamt)/10,而且活动结束后，数据还不变****
//				int savermb = getInstPlayerRechargeDAL().getStatResult("sum", "thisrmb", "instPlayerId = " + instPlayerId) + thisrmb; // 累计充值金额 = 原始记录 + 本次记录
				int savermb = (getInstPlayerRechargeDAL().getStatResult("sum", "thisamt", "instPlayerId = " + instPlayerId) / 10) + (thisamt / 10); // 累计充值金额 = 原始记录 + 本次记录
				int vipSaveRmb = savermb;
				if (ParamConfig.rechargeMap.containsKey(openId)) {
					List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.rechargeRetGold+"'", 0);
					if (instPlayerBigTableList.size() > 0) {
						InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
						vipSaveRmb = vipSaveRmb + (Integer.valueOf(instPlayerBigTable.getValue4()) / 10);
					}
				}
				
				int vipLevel = PlayerUtil.getVipLevel(instPlayer, vipSaveRmb);
				if (player != null) {
					player.setVipLevel(vipLevel);
				}
				if (vipLevel > instPlayer.getVipLevel()) {
					instPlayer.setVipLevel(vipLevel);
				}
				
				// 更新玩家成就计数实例数据
				AchievementUtil.updateAchievement(instPlayerId, StaticAchievementType.vip, vipLevel, syncMsgData, null);

				// 记录充值日志
				recordRecharge(paramsMap, thisrmb, thisamt, savermb, rechargeId);
				
				// 记录金币获取日志
				int operAfterGoldNum = instPlayer.getGold();
				PlayerUtil.addInstPlayerGoldStatics(instPlayerId, GoldStaticsType.add, thisGold, 0, "充值(含赠送游戏币)", operBeforeGoldNum, operAfterGoldNum, "", "", "");
				
				// 同步消息
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				MessageUtil.sendSyncMsgToOne(openId, syncMsgData);
				
				if (player != null) {
					MessageData retMsgData = new MessageData();
					retMsgData.putStringItem("1", paramsMap.get("orderId"));
					MessageUtil.pushMsg(player.getChannelId(), StaticMsgRule.pushRechargeSucc, retMsgData);
				}
				
				//订单状态确认
				makeSureRechargeOrder(paramsMap, "1");
				LogUtil.out("========订单状态已确认,充值成功 ========");
				
				//记录充值日志
				try {
					String logContent = "" + "|" + "" + "|" + paramsMap.get("roleId") + "|" + "" + "|" + player.getZoneid() + "|" + player.getChannel() + "|" + player.getAccountId() + "|" + player.getPlayerId() + "|" +
							player.getPlayerName() + "|" + thisrmb * 100 + "|" + thisrmb * 10 + "|" + paramsMap.get("ctype") + "|" + "" + "|" + paramsMap.get("orderform") + "|" + "" + "|" + "" + "|" +
							paramsMap.get("proxyTime") + "|" + player.getOpenId() + "|" + player.getChannel() + "|" + player.getLevel() + "|" + player.getVipLevel() + "|" + rechargeId + "|" + thisamt + "元宝" + "|" +
							"1" + "|" + (savermb - thisrmb) * 100 + "|" + savermb * 100 + "|" + extrGold + "|" + player.getAloneServerId() + "|" + player.getChannelSub();
					LogicLogUtil.info("recharge", logContent.replace("null", ""));
				} catch (Exception e) {
					LogUtil.error("充值日志错误", e);
				}
				
				Map<String, String> retMap = new HashMap<String, String>();
				retMap.put("code", "6");
				retMap.put("message", "充值成功");
				return JsonUtil.toJson(retMap);
				
			} else {
				if (player != null) {
					MessageUtil.sendFailMsg(player.getChannelId(), null, StaticCnServer.fail_recharge_status);
				}
				Map<String, String> retMap = new HashMap<String, String>();
				retMap.put("code", "3");
				retMap.put("message", "接受到的充值状态为失败");
//				writeResponse(channel, JsonUtil.toJson(retMap));
				makeSureRechargeOrder(paramsMap, "3");
				//记录充值失败日志
				recordRechargeFail(paramsMap, "接受到的充值状态为失败");
				return JsonUtil.toJson(retMap);
			}
			
		} catch (Exception e) {
			LogUtil.error("充值发生异常", e);
			if (player != null) {
				MessageUtil.sendFailMsg(player.getChannelId(), null, StaticCnServer.fail_recharge_status);
			}
			Map<String, String> retMap = new HashMap<String, String>();
			retMap.put("code", "5");
			retMap.put("message", "充值发生异常");
//			writeResponse(channel, JsonUtil.toJson(retMap));
			//记录充值失败日志
			recordRechargeFail(paramsMap, "充值发生异常");
			return JsonUtil.toJson(retMap);
		}
	}
	
	/**
	 * 记录充值日志
	 * @author mp
	 * @date 2015-7-14 上午10:57:40
	 * @param paramsMap
	 * @param thisrmb
	 * @param thisamt
	 * @param savermb
	 * @Description
	 */
	private static void recordRecharge (Map<String, String> paramsMap, int thisrmb, int thisamt, int savermb, int rechargeId) throws Exception{
		InstPlayerRecharge instPlayerRecharge = new InstPlayerRecharge();
		instPlayerRecharge.setInstPlayerId(ConvertUtil.toInt(paramsMap.get("roleId")));
		instPlayerRecharge.setOpenId(paramsMap.get("userid") + "@" + paramsMap.get("channelId"));
		instPlayerRecharge.setPlayerName(paramsMap.get("rolename"));
		instPlayerRecharge.setChannel(paramsMap.get("channelId"));
		instPlayerRecharge.setThisrmb(thisrmb);
		instPlayerRecharge.setThisamt(thisamt);
		instPlayerRecharge.setSaveamt(savermb * 10);
		instPlayerRecharge.setPayChannel(-1);
		instPlayerRecharge.setBalance(0);
		instPlayerRecharge.setGenbalance(0);
		instPlayerRecharge.setSaveNum(thisamt);
		instPlayerRecharge.setSource("渠道充值");
		instPlayerRecharge.setOrderId(paramsMap.get("orderId"));
		instPlayerRecharge.setServerId(ConvertUtil.toInt(paramsMap.get("serverId")));
		instPlayerRecharge.setAccountId(paramsMap.get("accountId"));
		instPlayerRecharge.setOrderform(paramsMap.get("orderform"));
		instPlayerRecharge.setRoleLevel(ConvertUtil.toInt(paramsMap.get("roleLevel")));
//		instPlayerRecharge.setGoodsId(ConvertUtil.toInt(paramsMap.get("goodsId")));
		instPlayerRecharge.setGoodsId(rechargeId);
		instPlayerRecharge.setCtype(ConvertUtil.toInt(paramsMap.get("ctype")));
		instPlayerRecharge.setMoney(ConvertUtil.toInt(paramsMap.get("money")) / 10 / 10);
		instPlayerRecharge.setRechargeRecordId(paramsMap.get("id"));
		instPlayerRecharge.setOperateDate(DateUtil.getYmdDate());
		instPlayerRecharge.setOperateTime(DateUtil.getCurrTime());
		getInstPlayerRechargeDAL().add(instPlayerRecharge, 0);
	}
	
	/**
	 * 首次充值返利,插入充值记录
	 * @author mp
	 * @date 2015-8-13 上午10:43:30
	 * @param thisrmb
	 * @param instPlayerId
	 * @param openId
	 * @param channelMark
	 * @param serverId
	 * @param accountId
	 * @throws Exception
	 * @Description
	 */
	public static void retGoldRecordRecharge (int thisrmb, int instPlayerId, String openId, String channelMark, int serverId, String accountId) throws Exception{
		InstPlayerRecharge instPlayerRecharge = new InstPlayerRecharge();
		instPlayerRecharge.setInstPlayerId(instPlayerId);
		instPlayerRecharge.setOpenId(openId);
		instPlayerRecharge.setPlayerName("");
		instPlayerRecharge.setChannel(channelMark);
		instPlayerRecharge.setThisrmb(thisrmb);
		instPlayerRecharge.setThisamt(thisrmb * 10);
		instPlayerRecharge.setSaveamt(thisrmb * 10);
		instPlayerRecharge.setPayChannel(-1);
		instPlayerRecharge.setBalance(0);
		instPlayerRecharge.setGenbalance(0);
		instPlayerRecharge.setSaveNum(thisrmb * 10);
		instPlayerRecharge.setSource("计费删档返利");
		instPlayerRecharge.setOrderId("");
		instPlayerRecharge.setServerId(serverId);
		instPlayerRecharge.setAccountId(accountId);
		instPlayerRecharge.setOrderform("");
		instPlayerRecharge.setRoleLevel(0);
		instPlayerRecharge.setGoodsId(0);
		instPlayerRecharge.setCtype(0);
		instPlayerRecharge.setMoney(0);
		instPlayerRecharge.setRechargeRecordId("0");
		instPlayerRecharge.setOperateDate(DateUtil.getYmdDate());
		instPlayerRecharge.setOperateTime(DateUtil.getCurrTime());
		getInstPlayerRechargeDAL().add(instPlayerRecharge, 0);
	}
	
	/**
	 * 订单状态确认
	 * @author mp
	 * @date 2015-7-14 上午10:24:38
	 * @param paramsMap
	 * @throws Exception
	 * @Description
	 */
	private static void makeSureRechargeOrder (Map<String, String> paramsMap, String state) throws Exception{
		Map<String, String> responsMap = new HashMap<String, String>();
		responsMap.put("state", state);//1：游戏服未记录，2：游戏服已记录 3:接收状态为失败
		responsMap.put("friendid", paramsMap.get("id"));//充值服务器推送的充值记录编号（long）
		responsMap.put("orderid", paramsMap.get("orderId"));//自有服务器生成的订单
		responsMap.put("channelId", paramsMap.get("channelId"));//渠道标识
		responsMap.put("money", paramsMap.get("money"));//充值金额（int，单位：分）
		responsMap.put("getmoney", (ConvertUtil.toInt(paramsMap.get("money")) / 10) + "");//获得的虚拟货币
		responsMap.put("ctype", "1");//充值类型（1：成功，2：GM补点，3：充值失败）
		responsMap.put("gameId", "2");//游戏编号（int）
		responsMap.put("serverid", paramsMap.get("serverId"));//服务器编号
		responsMap.put("sign", EncryptUtil.md5Hex(paramsMap.get("id") + "/" + paramsMap.get("orderId") + "/" + paramsMap.get("channelId") + "/" + paramsMap.get("money") + "/" + (ConvertUtil.toInt(paramsMap.get("money")) / 10) + "/" + "2" + "/" + paramsMap.get("serverId")));//签名md5(friendid / orderId / channelId / money / getmoney gameId / serverId )
		String params = HttpClientUtil.httpBuildQuery(responsMap);
		HttpClientUtil.postMapSubmit(SysConfigUtil.getValue("recharge.sureOrder.url"), params);
	}
	
	/**
	 * 记录充值失败日志
	 * @author mp
	 * @date 2015-7-14 上午10:19:01
	 * @param paramsMap
	 * @throws Exception
	 * @Description
	 */
	private static void recordRechargeFail (Map<String, String> paramsMap, String des) throws Exception{
		InstPlayerRechargeFail instPlayerRechargeFail = new InstPlayerRechargeFail();
		instPlayerRechargeFail.setAccountId(paramsMap.get("accountId"));
		instPlayerRechargeFail.setChannel(paramsMap.get("channelId"));
		instPlayerRechargeFail.setCtype(ConvertUtil.toInt(paramsMap.get("ctype")));
		instPlayerRechargeFail.setInstPlayerId(ConvertUtil.toInt(paramsMap.get("roleId")));
		instPlayerRechargeFail.setMoney(ConvertUtil.toInt(paramsMap.get("money")) / 10 / 10);
		instPlayerRechargeFail.setOpenId(paramsMap.get("userid"));
		instPlayerRechargeFail.setOperateDate(DateUtil.getYmdDate());
		instPlayerRechargeFail.setOperateTime(DateUtil.getCurrTime());
		instPlayerRechargeFail.setOrderform(paramsMap.get("orderform"));
		instPlayerRechargeFail.setOrderId(paramsMap.get("orderId"));
		instPlayerRechargeFail.setPlayerName(paramsMap.get("rolename"));
		instPlayerRechargeFail.setRechargeRecordId(paramsMap.get("id"));
		instPlayerRechargeFail.setGoodsId(paramsMap.get("goodsId"));
		instPlayerRechargeFail.setDes(des);
		getInstPlayerRechargeFailDAL().add(instPlayerRechargeFail, 0);
	}
	
    /**
     * Response相应
     * @author mp
     * @date 2015-7-13 下午2:53:49
     * @param channel
     * @param returnStr
     * @Description
     */
	public static void writeResponse(Channel channel, String returnStr) {
        ByteBuf buf = copiedBuffer(returnStr, CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
        response.headers().set(CONTENT_LENGTH, buf.readableBytes());
        channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    
    /**
     * 获取地址中的所有map
     * @author mp
     * @date 2015-7-13 下午3:00:25
     * @param paramsUri
     * @param isJoint
     * @return
     * @Description
     */
    public static Map<String, String> params (String paramsUri, boolean isJoint) throws Exception{
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	if (isJoint) {
    		paramsUri = "some?" + paramsUri;
		}
    	QueryStringDecoder decoderQuery = new QueryStringDecoder(paramsUri);
        Map<String, List<String>> uriAttributes = decoderQuery.parameters();
        for (Entry<String, List<String>> attr : uriAttributes.entrySet()) {
            for (String attrVal : attr.getValue()) {
            	paramsMap.put(attr.getKey(), attrVal);
            }
        }
        return paramsMap;  
    }
	
}
