package com.huayi.doupo.logic.entry;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class SystemEnt extends BaseHandler{
	
	/**
	 * 1000
	 * 心跳检测
	 * @author mp
	 * @version 1.0, 2013-4-5 上午10:12:07
	 * @param msg
	 * @param channelId
	 */         
	private int heartbeatIndex = 0;
	public void heartbeat(HashMap<String, Object> msgMap,String channelId) throws Exception{
		try {
			HandlerFactory.getSysHandler().heartbeat(msgMap, channelId);
		} catch (Exception e) {
			heartbeatIndex++;
			if (heartbeatIndex >= 5) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
				return;
			}
			TimeUnit.MILLISECONDS.sleep(5);
			heartbeat(msgMap, channelId);
		}
	}
	
	public void checkFight(HashMap<String, Object> msgMap,String channelId) {
	try {
		HandlerFactory.getSysHandler().checkFight(msgMap, channelId);
	} catch (Exception e) {
		LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
		e.printStackTrace();
		MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
	}
}
	
/*	*//**
	 * 系统补偿
	 * @author mp
	 * @date 2013-12-6 下午5:15:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 *//*
	public void sysIndemnity(HashMap<String, Object> msgMap,String channelId) {
		try {
			HandlerFactory.getSysHandler().sysIndemnity(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	*//**
	 * 领取系统补偿
	 * @author mp
	 * @date 2013-12-6 下午5:27:37
	 * @param msgMap
	 * @param channelId
	 * @Description
	 *//*
	public void getSysIndemnity(HashMap<String, Object> msgMap,String channelId) {
		try {
			HandlerFactory.getSysHandler().getSysIndemnity(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	*//**
	 * 获取系统时间
	 * @author mp
	 * @date 2013-12-14 下午2:53:46
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
//	public void getSysTime(HashMap<String, Object> msgMap,String channelId) {
//		try {
//			HandlerFactory.getSysHandler().getSysTime(msgMap, channelId);
//		} catch (Exception e) {
//			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
//			e.printStackTrace();
//			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
//		}
//	}
	
	/**
	 * 系统跑马灯
	 * @author mp
	 * @date 2014-1-15 下午2:33:24
	 * @param msgMap
	 * @param channelId
	 * @Description
	 *//*
	public void marqueen(HashMap<String, Object> msgMap,String channelId) {
		try {
			HandlerFactory.getSysHandler().getSysTime(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	*//**
	 * 领取CDK兑换码奖励
	 * @author mp
	 * @date 2014-1-16 下午2:15:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 *//*
	public void getCdkReward(HashMap<String, Object> msgMap,String channelId) {
		try {
			HandlerFactory.getSysHandler().getCdkReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	@Test
	public void test(){
		try {
//			long t1 = System.currentTimeMillis();
			SpringHelper.getSpringContext();
			DictData.dictData(0);
//			List<InstPlayer> instPlayerList = getInstPlayerDAL().getListFromMoreTale(", Inst_User b  where  a.openId = b.openId and (b.loginTime like '2014-01-14%' or b.loginTime like '2014-01-15%')");
//			System.out.println(instPlayerList.size());
//			System.out.println(System.currentTimeMillis() - t1);
			
//			List<Map<String,Object>> list = getInstPlayerDAL().sqlHelper("select ROWNUM as rank from ( select (@mycnt:=@mycnt+1) as ROWNUM , a.* from Inst_Player a order by level ) b where b.instPlayerId = 1");
			
//			Map<String,Object> map = list.get(0);
			
			JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringHelper.GetObjectWithSpringContext("JdbcTemplate");
			String sqls = "set @mycnt=0";
			jdbcTemplate.execute(sqls);
			List<Map<String,Object>> list = getInstPlayerDAL().sqlHelper("select ROWNUM as rank from ( select (@mycnt:=@mycnt+1) as ROWNUM , a.* from Inst_Player a order by level ) b where b.instPlayerId = 1");
			Map<String,Object> map = list.get(0);
			System.out.println(map.get("rank"));
			
			
			System.out.println();
//			System.out.println(map.get("rank"));
			
//			List<DictRecruitShadow> dictRecruitShadowLista = (List<DictRecruitShadow>)DictMapList.dictRecruitShadowMap.get(StaticQuality.a);
//			List<DictRecruitShadow> dictRecruitShadowListb = (List<DictRecruitShadow>)DictMapList.dictRecruitShadowMap.get(StaticQuality.b);
//			List<DictRecruitShadow> dictRecruitShadowListc = (List<DictRecruitShadow>)DictMapList.dictRecruitShadowMap.get(StaticQuality.c);
//			List<DictRecruitShadow> dictRecruitShadowListd = (List<DictRecruitShadow>)DictMapList.dictRecruitShadowMap.get(StaticQuality.d);
//			System.out.println();
			
//			for(int i = 1; i <= 30; i++){
//				List<Map<String, Object>> list = getInstPlayerDAL().sqlHelper("select count(*) as count from Inst_Player where level = " + i);
//				Map<String, Object> map = list.get(0);
//				System.out.println(i + "级    ：" + map.get("count"));
//			}
//			List<InstPlayer> instPlayerList = getInstPlayerDAL().sqlHelper("select count(*) from Inst_Player where level = " + );
//			System.out.println(instPlayerList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/}
