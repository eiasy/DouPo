package com.huayi.doupo.logic.handler.util;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictBarrier;
import com.huayi.doupo.base.model.DictBarrierLevel;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBarrier;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.InstPlayerChapterType;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.util.MessageData;

public class ChapterUtil extends DALFactory{
	
	/**
	 * 添加玩家副本关卡等级实例表
	 * @author hzw
	 * @date 2014-8-20下午3:54:17
	 * @param instPlayerId
	 * @param barrierLevelId
	 * @param isFailed     0-失败  1-成功
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void initInstPlayerBarrierLevel(int instPlayerId, int barrierLevelId, int isFailed, String currTime,  MessageData syncMsgData) throws Exception{
		
		//处理玩家副本关卡实例表
		int tag = 0;
		DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
		if (dictBarrierLevel != null) {
			int barrierId = dictBarrierLevel.getBarrierId();
			DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
			if (dictBarrier != null) {
				int chapterId = dictBarrier.getChapterId();
				List<InstPlayerBarrier> instPlayerBarrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId + " and barrierId = " + barrierId, instPlayerId);
				InstPlayerBarrier instPlayerBarrier = null;
				if(instPlayerBarrierList.size() == 0){
					tag = dictBarrierLevel.getLevel();
					instPlayerBarrier = new InstPlayerBarrier();
					instPlayerBarrier.setInstPlayerId(instPlayerId);
					instPlayerBarrier.setBarrierId(barrierId);
					instPlayerBarrier.setFightNum(1);
					instPlayerBarrier.setChapterId(chapterId);
					if(isFailed == 1){
						instPlayerBarrier.setBarrierLevel(dictBarrierLevel.getLevel());
						instPlayerBarrier.setWelfareBox(1);
					}else{
						instPlayerBarrier.setBarrierLevel(0);
						instPlayerBarrier.setWelfareBox(0);
					}
					instPlayerBarrier.setOperTime(currTime);
					getInstPlayerBarrierDAL().add(instPlayerBarrier, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerBarrier, instPlayerBarrier.getId(), instPlayerBarrier.getResult(), syncMsgData);
				}
				if(instPlayerBarrierList.size() > 0){
					instPlayerBarrier = instPlayerBarrierList.get(0);
					if(DateUtil.isSameDay(instPlayerBarrier.getUpdateTime(), currTime, DateFormat.YMDHMS)){
						instPlayerBarrier.setFightNum(instPlayerBarrier.getFightNum() + 1);
					}else{
						instPlayerBarrier.setFightNum(1);
						instPlayerBarrier.setResetNum(0);
					}
					if(dictBarrierLevel.getLevel() > instPlayerBarrier.getBarrierLevel() && isFailed == 1){
						tag = dictBarrierLevel.getLevel() - instPlayerBarrier.getBarrierLevel();
						instPlayerBarrier.setBarrierLevel(dictBarrierLevel.getLevel());
					}
					instPlayerBarrier.setOperTime(currTime);
					getInstPlayerBarrierDAL().update(instPlayerBarrier, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBarrier, instPlayerBarrier.getId(), instPlayerBarrier.getResult(), syncMsgData);
				}
				if(isFailed == 1){
					//处理玩家副本章节实例表
					List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId + " and chapterId = " + chapterId, instPlayerId);
					InstPlayerChapter instPlayerChapter = null;
					if(instPlayerChapterList.size() == 0){
						instPlayerChapter = new InstPlayerChapter();
						instPlayerChapter.setInstPlayerId(instPlayerId);
						instPlayerChapter.setChapterId(chapterId);
//						instPlayerChapter.setStarNum(1);
						instPlayerChapter.setStarNum(dictBarrierLevel.getLevel());
						instPlayerChapter.setIsPass(0);
						instPlayerChapter.setOperTime(currTime);
						getInstPlayerChapterDAL().add(instPlayerChapter, instPlayerId);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
						
						//更新玩家成就计数实例数据
						AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.chapter, dictBarrierLevel.getLevel(), syncMsgData);
					}
					if(instPlayerChapterList.size() > 0){
						instPlayerChapter = instPlayerChapterList.get(0);
						List<DictBarrier> dictBarrierList = (List<DictBarrier>)DictMapList.dictBarrierMap.get(chapterId);
						if(dictBarrierList.get(dictBarrierList.size() - 1).getId() == barrierId){
							instPlayerChapter.setIsPass(1);
							
							//验证副本成就
							AchievementUtil.barrier(instPlayerId, instPlayerChapter, syncMsgData);
						}
						if(tag >= 1){
							instPlayerChapter.setStarNum(instPlayerChapter.getStarNum() + tag);
							instPlayerChapter.setOperTime(currTime);
							getInstPlayerChapterDAL().update(instPlayerChapter, instPlayerId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
						
							//更新玩家成就计数实例数据
							AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.chapter, tag, syncMsgData);
						}
					}
					
					
				}
			}
		}
	}
	
	
	/**
	 * 更新玩家副本关卡战斗次数实例表
	 * @author hzw
	 * @date 2014-8-22下午2:00:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstPlayerBarrierFightNum(InstPlayerBarrier instPlayerBarrier, int barrierLevelId, int fightNum, String currTime, MessageData syncMsgData) throws Exception{
		
		//处理玩家副本关卡实例表
		int instPlayerId = instPlayerBarrier.getInstPlayerId();
		if(DateUtil.isSameDay(instPlayerBarrier.getUpdateTime(), currTime, DateFormat.YMDHMS)){
			instPlayerBarrier.setFightNum(instPlayerBarrier.getFightNum() + fightNum);
		}else{
			instPlayerBarrier.setFightNum(fightNum);
			instPlayerBarrier.setResetNum(0);
		}
		instPlayerBarrier.setOperTime(currTime);
		getInstPlayerBarrierDAL().update(instPlayerBarrier, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBarrier, instPlayerBarrier.getId(), instPlayerBarrier.getResult(), syncMsgData);
	
		//处理玩家副本章节实例表
		int chapterType = 1;
		List<InstPlayerChapterType> instPlayerChapterTypeList = getInstPlayerChapterTypeDAL().getList("instPlayerId = " + instPlayerId + " and chapterType = " + chapterType, instPlayerId);
		InstPlayerChapterType instPlayerChapterType = null;
		if(instPlayerChapterTypeList.size() == 0){
			instPlayerChapterType = new InstPlayerChapterType();
			instPlayerChapterType.setInstPlayerId(instPlayerId);
			instPlayerChapterType.setChapterType(chapterType);
			instPlayerChapterType.setAKeyTime(DateUtil.getTimeByMill(DateUtil.getMillSecond(currTime) - 5000));
			instPlayerChapterType.setOperTime(currTime);
			getInstPlayerChapterTypeDAL().add(instPlayerChapterType, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		}
		if(instPlayerChapterTypeList.size() > 0){
			instPlayerChapterType = instPlayerChapterTypeList.get(0);
			if(!DateUtil.isSameDay(instPlayerChapterType.getOperTime(), currTime, DateFormat.YMDHMS)){
				instPlayerChapterType.setBuyNum(0);
			}
			instPlayerChapterType.setAKeyTime(DateUtil.getTimeByMill(DateUtil.getMillSecond(currTime) - 5000));
			instPlayerChapterType.setOperTime(currTime);
			getInstPlayerChapterTypeDAL().update(instPlayerChapterType, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		}
	}
	
	
	/**
	 * 玩家副本章节类型实例表
	 * @author hzw
	 * @date 2014-8-23上午10:59:37
	 * @param instPlayerId
	 * @param chapterType
	 * @param chapterId
	 * @param isFailed     0-失败  1-成功
	 * @throws Exception
	 * @Description
	 */
	public static void initInstPlayerChapterType(int instPlayerId, int chapterType, int chapterId, int isFailed, String currTime, MessageData syncMsgData) throws Exception{
		
		//处理玩家副本章节实例表
		if(isFailed == 1){
			List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId + " and chapterId = " + chapterId, instPlayerId);
			InstPlayerChapter instPlayerChapter = null;
			if(instPlayerChapterList.size() == 0){
				instPlayerChapter = new InstPlayerChapter();
				instPlayerChapter.setInstPlayerId(instPlayerId);
				instPlayerChapter.setChapterId(chapterId);
				instPlayerChapter.setIsPass(1);
				instPlayerChapter.setOperTime(currTime);
				getInstPlayerChapterDAL().add(instPlayerChapter, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
				
				//验证副本成就
				AchievementUtil.barrier(instPlayerId, instPlayerChapter, syncMsgData);
			}
		}
		
		//处理玩家副本关卡实例表
		List<InstPlayerChapterType> instPlayerChapterTypeList = getInstPlayerChapterTypeDAL().getList(" instPlayerId = " + instPlayerId + " and chapterType = " + chapterType, instPlayerId);
		InstPlayerChapterType instPlayerChapterType = null;
		if(instPlayerChapterTypeList.size() == 0){
			instPlayerChapterType = new InstPlayerChapterType();
			instPlayerChapterType.setInstPlayerId(instPlayerId);
			instPlayerChapterType.setChapterType(chapterType);
			instPlayerChapterType.setFightNum(1);
			instPlayerChapterType.setOperTime(currTime);
			getInstPlayerChapterTypeDAL().add(instPlayerChapterType, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		}
		if(instPlayerChapterTypeList.size() > 0){
			instPlayerChapterType = instPlayerChapterTypeList.get(0);
			if(DateUtil.isSameDay(instPlayerChapterType.getUpdateTime(), currTime, DateFormat.YMDHMS)){
				instPlayerChapterType.setFightNum(instPlayerChapterType.getFightNum() + 1);
			}else{
				instPlayerChapterType.setFightNum(1);
				instPlayerChapterType.setBuyNum(0);
			}
			instPlayerChapterType.setOperTime(currTime);
			getInstPlayerChapterTypeDAL().update(instPlayerChapterType, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapterType, instPlayerChapterType.getId(), instPlayerChapterType.getResult(), syncMsgData);
		}
		
	}
	
	
	/**
	 * 活动副本章节实例数据
	 * @author hzw
	 * @date 2014-8-26下午3:31:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void initInstPlayerChapter(int instPlayerId, int chapterId, String currTime, MessageData syncMsgData) throws Exception{
		
		//处理玩家副本章节实例表
		List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId + " and chapterId = " + chapterId, instPlayerId);
		InstPlayerChapter instPlayerChapter = null;
		if(instPlayerChapterList.size() == 0){
			instPlayerChapter = new InstPlayerChapter();
			instPlayerChapter.setInstPlayerId(instPlayerId);
			instPlayerChapter.setChapterId(chapterId);
			instPlayerChapter.setFightNum(1);
			instPlayerChapter.setOperTime(currTime);
			getInstPlayerChapterDAL().add(instPlayerChapter, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
		}
		if(instPlayerChapterList.size() > 0){
			instPlayerChapter = instPlayerChapterList.get(0);
			if(DateUtil.isSameDay(instPlayerChapter.getUpdateTime(), currTime, DateFormat.YMDHMS)){
				instPlayerChapter.setFightNum(instPlayerChapter.getFightNum() + 1);
			}else{
				instPlayerChapter.setFightNum(1);
				instPlayerChapter.setBuyNum(0);
			}
			instPlayerChapter.setOperTime(currTime);
			getInstPlayerChapterDAL().update(instPlayerChapter, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChapter, instPlayerChapter.getId(), instPlayerChapter.getResult(), syncMsgData);
		}
		
	}
	
	

	
	/**
	 * 根据副本战斗胜利次数判断拍卖行是否开启
	 * @author hzw
	 * @date 2014-9-27上午10:33:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void auctionShopIsOpen(InstPlayer instPlayer, int fightNum, MessageData syncMsgData) throws Exception{
		instPlayer.setBarrierNum(instPlayer.getBarrierNum() + fightNum);
		
		List<InstActivity> instActivityList = (List<InstActivity>)getInstActivityDAL().getList(" instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.auctionShop, instPlayer.getId());
		if(instActivityList == null || instActivityList.size() <= 0){
			int random = RandomUtil.getRangeInt(DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopFightNumMin), DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopFightNumMax));
			if(instPlayer.getBarrierNum() >= random){
//				ActivityUtil.addInstActivity(instPlayer.getId(), StaticActivity.auctionShop, 0, syncMsgData);
				instPlayer.setBarrierNum(instPlayer.getBarrierNum() - random);
			}
		}
		
	}
	

}
