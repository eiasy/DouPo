package com.huayi.doupo.logic.handler;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictPillRecipe;
import com.huayi.doupo.base.model.DictPillThing;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerPill;
import com.huayi.doupo.base.model.InstPlayerPillRecipe;
import com.huayi.doupo.base.model.InstPlayerPillThing;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PillUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class PillHandler extends BaseHandler{

	/**
	 * 使用丹药
	 * @author hzw
	 * @date 2014-8-4下午3:36:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void usePill(Map<String, Object> msgMap, String channelId) throws Exception {
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerConstellId = (Integer)msgMap.get("instPlayerConstellId");
		int position = (Integer)msgMap.get("position");
		InstPlayerConstell instPlayerConstell = getInstPlayerConstellDAL().getModel(instPlayerConstellId, instPlayerId);

		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//验证玩家是否一致
		if (instPlayerConstell.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;			
		}
		
		String pills[] = instPlayerConstell.getPills().split(";");
		if (ConvertUtil.toInt(pills[position - 1]) == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_constellPositionUse);
			return;
		}
		
		DictConstell dictConstell = DictMap.dictConstellMap.get(instPlayerConstell.getConstellId() + "");
		int pillId = ConvertUtil.toInt(dictConstell.getPills().split(";")[position - 1]);
		DictPill dictPill = DictMap.dictPillMap.get(pillId + "");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerConstell.getInstCardId(), instPlayerId);
		if(instPlayerCard.getLevel() < dictPill.getCardlevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCardLevel);
			return;
		}
		
		List<InstPlayerPill> instPlayerPillList = getInstPlayerPillDAL().getList("instPlayerId = " + instPlayerId + " and pillId = " + pillId, instPlayerId);
		if(instPlayerPillList.size() <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPillNum);
			return;
		}
		//更新玩家丹药
		PillUtil.updateInstPlayerPill(instPlayerId, instPlayerPillList.get(0), 1, syncMsgData);
		
		
		//重新组织玩家命宫的丹药状态
		pills[position - 1] = 1 + "";
		String newPills = "";
		for(String pill : pills){
			newPills += pill + ";";
		}
		instPlayerConstell.setPills(newPills.substring(0,newPills.length() - 1));
		getInstPlayerConstellDAL().update(instPlayerConstell, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerConstell, instPlayerConstell.getId(), instPlayerConstell.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 炼制丹药
	 * @author hzw
	 * @date 2014-8-4下午4:57:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addPill(Map<String, Object> msgMap, String channelId) throws Exception {
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		int instPlayerConstellId = (Integer)msgMap.get("instPlayerConstellId");
		int position = (Integer)msgMap.get("position");
		
		InstPlayerConstell instPlayerConstell = getInstPlayerConstellDAL().getModel(instPlayerConstellId, instPlayerId);
		
		//验证玩家是否一致
		if (instPlayerConstell.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		String pills[] = instPlayerConstell.getPills().split(";");
		if (ConvertUtil.toInt(pills[position - 1]) == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_constellPositionUse);
			return;
		}
		
		DictConstell dictConstell = DictMap.dictConstellMap.get(instPlayerConstell.getConstellId() + "");
		int pillId = ConvertUtil.toInt(dictConstell.getPills().split(";")[position - 1]);
		DictPill dictPill = DictMap.dictPillMap.get(pillId + "");
		int pillRecipeId = dictPill.getPrescriptId();
		
		DictPillRecipe dictPillRecipe = DictMap.dictPillRecipeMap.get(pillRecipeId + "");
		String pillThings[] = {dictPillRecipe.getThingOne(), dictPillRecipe.getThingTwo(), dictPillRecipe.getThingThree()};
		for(String pillThing : pillThings){
			List<InstPlayerPillThing> instPlayerPillThings = getInstPlayerPillThingDAL().getList("instPlayerId = " + instPlayerId + " and pillThingId = " + ConvertUtil.toInt(pillThing.split("_")[0]), instPlayerId);
			if(instPlayerPillThings.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPillThingNum);
				return;
			}else if(instPlayerPillThings.get(0).getNum() < ConvertUtil.toInt(pillThing.split("_")[1])){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPillThingNum);
				return;
			}else{
				//更新丹药材料
				PillUtil.updateInstPlayerPillThing(instPlayerId, instPlayerPillThings.get(0), ConvertUtil.toInt(pillThing.split("_")[1]), syncMsgData);
			}
		}
		
		//更新玩家丹药
		PillUtil.addInstPlayerPill(instPlayerId, pillId, 1, syncMsgData);
		
		//处理每日任务
//		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.pill, 1, syncMsgData);
		
		//验证炼丹成就
		AchievementUtil.addPill(instPlayerId, dictPill, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 炼制/一键炼制
	 * @author hzw
	 * @date 2014-8-5下午3:38:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addPills(Map<String, Object> msgMap, String channelId) throws Exception {
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerPillRecipeId = (Integer)msgMap.get("instPlayerPillRecipeId");
		int type = (Integer)msgMap.get("type");     //1-炼制  2-一键炼制
		InstPlayerPillRecipe instPlayerPillRecipe = getInstPlayerPillRecipeDAL().getModel(instPlayerPillRecipeId, instPlayerId);
		
		if (instPlayerPillRecipe.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictPillRecipe dictPillRecipe = DictMap.dictPillRecipeMap.get(instPlayerPillRecipe.getPillRecipeId() + "");
		DictPill dictPill = DictMap.dictPillMap.get(dictPillRecipe.getPillId() + "");
		int num = 1;
		String pillThings[] = {dictPillRecipe.getThingOne(), dictPillRecipe.getThingTwo(), dictPillRecipe.getThingThree()};
		for(int i = 0; i < pillThings.length; i++){
			List<InstPlayerPillThing> instPlayerPillThings = getInstPlayerPillThingDAL().getList("instPlayerId = " + instPlayerId + " and pillThingId = " + ConvertUtil.toInt(pillThings[i].split("_")[0]), instPlayerId);
			if(instPlayerPillThings.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPillThingNum);
				return;
			}else if(instPlayerPillThings.get(0).getNum() < ConvertUtil.toInt(pillThings[i].split("_")[1])){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPillThingNum);
				return;
			}else{
				if(type == 2){
					if(i == 0){
						num = instPlayerPillThings.get(0).getNum() / ConvertUtil.toInt(pillThings[i].split("_")[1]);
					}
					if(instPlayerPillThings.get(0).getNum() / ConvertUtil.toInt(pillThings[i].split("_")[1]) < num){
						num = instPlayerPillThings.get(0).getNum() / ConvertUtil.toInt(pillThings[i].split("_")[1]);
					}
				}
			}
		}
		
		
		for(String pillThing : pillThings){
			List<InstPlayerPillThing> instPlayerPillThings = getInstPlayerPillThingDAL().getList("instPlayerId = " + instPlayerId + " and pillThingId = " + ConvertUtil.toInt(pillThing.split("_")[0]), instPlayerId);
			//更新丹药材料
			PillUtil.updateInstPlayerPillThing(instPlayerId, instPlayerPillThings.get(0), ConvertUtil.toInt(pillThing.split("_")[1]) * num, syncMsgData);
		}
		
		//更新玩家丹药
		PillUtil.addInstPlayerPill(instPlayerId, dictPill.getId(), num, syncMsgData);
		
		//处理每日任务
//		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.pill, num, syncMsgData);
		
		//验证炼丹成就
		AchievementUtil.addPill(instPlayerId, dictPill, num, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售丹药
	 * @author hzw
	 * @date 2014-8-6上午10:13:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deletePill(Map<String, Object> msgMap, String channelId) throws Exception {
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerPillId = (Integer)msgMap.get("instPlayerPillId");
		int num = (Integer)msgMap.get("num");
		InstPlayerPill instPlayerPill = getInstPlayerPillDAL().getModel(instPlayerPillId, instPlayerId);
		
		if (instPlayerPill.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictPill dictPill = DictMap.dictPillMap.get(instPlayerPill.getPillId() + "");
		
		//防止客户端传过来的参数是负数或者0
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		if(num > instPlayerPill.getNum()){
			num = instPlayerPill.getNum();
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictPill.getSellCopper() * num) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		
		//更新玩家丹药
		PillUtil.updateInstPlayerPill(instPlayerId, instPlayerPill, num, syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售丹药材料
	 * @author hzw
	 * @date 2014-8-16上午10:01:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deletePillThing(Map<String, Object> msgMap, String channelId) throws Exception {
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerPillThingId = (Integer)msgMap.get("instPlayerPillThingId");
		int num = (Integer)msgMap.get("num");
		
		//防止客户端传过来的参数是负数或者0
		if (num <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		InstPlayerPillThing instPlayerPillThing = getInstPlayerPillThingDAL().getModel(instPlayerPillThingId, instPlayerId);
		if (instPlayerPillThing.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictPillThing dictPillThing = DictMap.dictPillThingMap.get(instPlayerPillThing.getPillThingId() + "");
		if(num > instPlayerPillThing.getNum()){
			num = instPlayerPillThing.getNum();
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictPillThing.getSellCopper() * num) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		
		//更新玩家丹药材料
		PillUtil.updateInstPlayerPillThing(instPlayerId, instPlayerPillThing, num, syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
