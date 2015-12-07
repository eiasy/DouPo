package com.huayi.doupo.logic.handler.quartz;

import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;

/**
 * 定时器处理类-结算决斗排行-挑战疯狂之巅活动
 * @author mp
 * @date 2013-11-27 下午1:55:25
 */
public class DuelRankHandler extends BaseHandler implements Job {

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
//		try {
//			Log.info("结算决斗排行-挑战疯狂之巅活动定时器开始运行");
//			long t1 = getNowTimeMill();
//			
//			//决斗开放等级
//			DictFunctionOpen functionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.duel+"");
//			int duelOpenLevel = functionOpen.getLevel();
//			
//			//统计当日排行
//			Map<Integer, Integer> signlePlayerMap = new HashMap<Integer, Integer>();
//			
//			//只查询达到决斗系统开启等级的玩家
//			List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("level >= " + duelOpenLevel);
//			
//			for(int i = 0; i < instPlayerList.size(); i++){
//				try {
//					InstPlayer obj = instPlayerList.get(i);
//					int instPlayerId = obj.getInstPlayerId();
//					List<InstPlayerDuel> instPlayerDuelList = getInstPlayerDuelDAL().getList("instPlayerId = " + instPlayerId);
//					int currentDuelRank = 0;
//					if(instPlayerDuelList.size() > 0){
//						currentDuelRank = instPlayerDuelList.get(0).getCurrentDuelRank();
//					}
//					int duelRank = currentDuelRank;
//					//只更新排行不一致的，没发生变化的不去更新
//					if(obj.getYestdayDuelRank() != duelRank){
//						MessageData syncMsgData = new MessageData();
//						obj.setYestdayDuelRank(duelRank);
//						getInstPlayerDAL().update(obj);
//						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, syncMsgData, 0, "0");
//						MessageUtil.sendSyncMsgToOnePlayer(obj.getOpenId(), syncMsgData);
//					}
//				} catch (Exception e) {
//					InstPlayer obj = instPlayerList.get(i);
//					if(signlePlayerMap.containsKey(obj.getInstPlayerId())){
//						int count = signlePlayerMap.get(obj.getInstPlayerId());
//						if(count < 50){
//							InstPlayer newInstPlayer = getInstPlayerDAL().getModel(obj.getInstPlayerId());
//							instPlayerList.add(newInstPlayer);
//							signlePlayerMap.put(newInstPlayer.getInstPlayerId(), signlePlayerMap.get(newInstPlayer.getInstPlayerId()) + 1);
//						}
//					}else{
//						InstPlayer newInstPlayer = getInstPlayerDAL().getModel(obj.getInstPlayerId());
//						instPlayerList.add(newInstPlayer);
//						signlePlayerMap.put(newInstPlayer.getInstPlayerId(), 1);
//					}
//				}
//			}
//			
//			//删除昨日已领取记录
//			Map<Integer, Integer> signleActivityMap = new HashMap<Integer, Integer>();
//			List<InstActivity> instActivityList = getInstActivityDAL().getList("activityId = " + StaticActivity.crazyDuel);
//			for(int i = 0; i < instActivityList.size(); i++){
//				try {
//					InstActivity obj = instActivityList.get(i);
//					InstPlayer instPlayer = getInstPlayerDAL().getModel(obj.getInstPlayerId());
//					MessageData syncMsgData = new MessageData();
//					getInstActivityDAL().deleteById(obj.getId());
//					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, syncMsgData, obj.getId(), "0");
//					MessageUtil.sendSyncMsgToOnePlayer(instPlayer.getOpenId(), syncMsgData);
//				} catch (Exception e) {
//					InstActivity obj = instActivityList.get(i);
//					if(signleActivityMap.containsKey(obj.getId())){
//						int count = signleActivityMap.get(obj.getId());
//						if(count < 50){
//							InstActivity newInstActivity = getInstActivityDAL().getModel(obj.getId());
//							instActivityList.add(newInstActivity);
//							signleActivityMap.put(newInstActivity.getId(), signleActivityMap.get(newInstActivity.getId()) + 1);
//						}
//					}else{
//						InstActivity newInstActivity = getInstActivityDAL().getModel(obj.getId());
//						instActivityList.add(newInstActivity);
//						signleActivityMap.put(newInstActivity.getId(), 1);
//					}
//				}
//			}
//			Log.info("结算决斗排行-挑战疯狂之巅活动完成运行,共耗时" + (getNowTimeMill() - t1)+" 毫秒");
//		} catch (Exception e) {
//			Log.error(e);
//			Log.error("DuelRankHandler Job Exception");
//			e.printStackTrace();
//		}
	}


	
	@Test
	public void test(){
		try {
			SpringUtil.getSpringContext();
			DictData.dictData(0);
			execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
