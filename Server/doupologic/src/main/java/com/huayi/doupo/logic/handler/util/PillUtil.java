package com.huayi.doupo.logic.handler.util;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictPillRecipe;
import com.huayi.doupo.base.model.DictPillThing;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerPill;
import com.huayi.doupo.base.model.InstPlayerPillRecipe;
import com.huayi.doupo.base.model.InstPlayerPillThing;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.logic.util.MessageData;

public class PillUtil extends DALFactory{
	
	
	/**
	 * 初始化命宫
	 * @author hzw
	 * @date 2014-8-4下午4:08:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerConstell initPlayerConstell(int instPlayerId,int instPlayerCardId, int constellId, int length) throws Exception{
		InstPlayerConstell obj = new InstPlayerConstell();
		obj.setConstellId(constellId);
		obj.setInstCardId(instPlayerCardId);
		obj.setInstPlayerId(instPlayerId);
		String pills = "";
		for(int i = 0; i < length; i++){
			pills += 0 + ";";
		}
		obj.setPills(pills.substring(0, pills.length() - 1));
		getInstPlayerConstellDAL().add(obj, instPlayerId);
		return obj;
	}
	
	
	
	/**
	 * 添加玩家丹药
	 * @author hzw
	 * @date 2014-8-4下午4:15:37
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unused")
	public static void addInstPlayerPill(int instPlayerId, int pillId, int num, MessageData syncMsgData) throws Exception{
		List<InstPlayerPill> instPlayerPillList = getInstPlayerPillDAL().getList("instPlayerId = " + instPlayerId + " and pillId = " + pillId, instPlayerId);
		InstPlayerPill instPlayerPill = null;
		if(instPlayerPillList.size() == 0){
			instPlayerPill = new InstPlayerPill();
			DictPill Pill = DictMap.dictPillMap.get(pillId + "");
			instPlayerPill.setInstPlayerId(instPlayerId);
			instPlayerPill.setPillId(pillId);
			instPlayerPill.setNum(num);
			getInstPlayerPillDAL().add(instPlayerPill, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPill, instPlayerPill.getId(), instPlayerPill.getResult(), syncMsgData);
		}
		if(instPlayerPillList.size() > 0){
			instPlayerPill = instPlayerPillList.get(0);
			instPlayerPill.setNum(instPlayerPill.getNum() + num);
			getInstPlayerPillDAL().update(instPlayerPill, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPill, instPlayerPill.getId(), instPlayerPill.getResult(), syncMsgData);
		}
	}
	
	
	/**
	 * 更新删除玩家丹药
	 * @author hzw
	 * @date 2014-8-4下午4:15:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstPlayerPill(int instPlayerId, InstPlayerPill instPlayerPill, int num, MessageData syncMsgData) throws Exception{
		
		if(instPlayerPill.getNum() - num <= 0){
			getInstPlayerPillDAL().deleteById(instPlayerPill.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerPill, instPlayerPill.getId(), "", syncMsgData);
		}else{
			instPlayerPill.setNum(instPlayerPill.getNum() - num);
			getInstPlayerPillDAL().update(instPlayerPill, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPill, instPlayerPill.getId(), instPlayerPill.getResult(), syncMsgData);
		}
	}
	
	
	
	
	/**
	 * 添加玩家丹药药方
	 * @author hzw
	 * @date 2014-8-5上午11:23:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unused")
	public static void addInstPlayerPillRecipe(int instPlayerId, MessageData syncMsgData) throws Exception{
		List<InstPlayerPillRecipe> instPlayerPillRecipeList = getInstPlayerPillRecipeDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		InstPlayerPillRecipe instPlayerPillRecipe = null;
		if(instPlayerPillRecipeList.size() <= 0){
			for(DictPillRecipe dictPillRecipe : DictList.dictPillRecipeList){
				instPlayerPillRecipe = new InstPlayerPillRecipe();
				DictPillRecipe PillRecipe = DictMap.dictPillRecipeMap.get(dictPillRecipe.getId() + "");
				instPlayerPillRecipe.setInstPlayerId(instPlayerId);
				instPlayerPillRecipe.setPillRecipeId(dictPillRecipe.getId());
				instPlayerPillRecipe.setNum(1);
				getInstPlayerPillRecipeDAL().add(instPlayerPillRecipe, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPillRecipe, instPlayerPillRecipe.getId(), instPlayerPillRecipe.getResult(), syncMsgData);
			}
		}
	}
	
	
	
	/**
	 * 添加玩家丹药材料
	 * @author hzw
	 * @date 2014-8-5上午11:26:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unused")
	public static void addInstPlayerPillThing(int instPlayerId, int pillThingId, int num, MessageData syncMsgData) throws Exception{
		List<InstPlayerPillThing> instPlayerPillThingList = getInstPlayerPillThingDAL().getList("instPlayerId = " + instPlayerId + " and pillThingId = " + pillThingId, instPlayerId);
		InstPlayerPillThing instPlayerPillThing = null;
		if(instPlayerPillThingList.size() == 0){
			instPlayerPillThing = new InstPlayerPillThing();
			DictPillThing PillThing = DictMap.dictPillThingMap.get(pillThingId + "");
			instPlayerPillThing.setInstPlayerId(instPlayerId);
			instPlayerPillThing.setPillThingId(pillThingId);
			instPlayerPillThing.setNum(num);
			getInstPlayerPillThingDAL().add(instPlayerPillThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPillThing, instPlayerPillThing.getId(), instPlayerPillThing.getResult(), syncMsgData);
		}
		if(instPlayerPillThingList.size() > 0){
			instPlayerPillThing = instPlayerPillThingList.get(0);
			instPlayerPillThing.setNum(instPlayerPillThing.getNum() + num);
			getInstPlayerPillThingDAL().update(instPlayerPillThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPillThing, instPlayerPillThing.getId(), instPlayerPillThing.getResult(), syncMsgData);
		}
	}
	
	
	
	
	
	/**
	 * 更新玩家丹药材料
	 * @author hzw
	 * @date 2014-8-5上午11:26:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstPlayerPillThing(int instPlayerId, InstPlayerPillThing instPlayerPillThing, int num, MessageData syncMsgData) throws Exception{
		
		if(instPlayerPillThing.getNum() - num <= 0){
			getInstPlayerPillThingDAL().deleteById(instPlayerPillThing.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerPillThing, instPlayerPillThing.getId(), "", syncMsgData);
		}else{
			instPlayerPillThing.setNum(instPlayerPillThing.getNum() - num);
			getInstPlayerPillThingDAL().update(instPlayerPillThing, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerPillThing, instPlayerPillThing.getId(), instPlayerPillThing.getResult(), syncMsgData);
		}
	}
	

}
