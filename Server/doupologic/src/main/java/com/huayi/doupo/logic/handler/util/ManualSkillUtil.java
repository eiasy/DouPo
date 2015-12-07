package com.huayi.doupo.logic.handler.util;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictManualSkill;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerManualSkillLine;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.logic.util.MessageData;

public class ManualSkillUtil extends DALFactory{

	
	/**
	 * 添加手动技能实例数据
	 * @author hzw
	 * @date 2014-9-10下午6:02:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerManualSkill addInstPlayerManualSkill(int instPlayerId, int ManualSkillId) throws Exception{
		InstPlayerManualSkill instPlayerManualSkill = new InstPlayerManualSkill();
		DictManualSkill dictManualSkill = DictMap.dictManualSkillMap.get(ManualSkillId + "");
		
		instPlayerManualSkill.setInstPlayerId(instPlayerId);
		instPlayerManualSkill.setType(dictManualSkill.getType());
		instPlayerManualSkill.setManualSkillId(ManualSkillId);
		instPlayerManualSkill.setLevel(1);
		instPlayerManualSkill.setExp(0);
		instPlayerManualSkill.setIsUse(0);
		
		instPlayerManualSkill = getInstPlayerManualSkillDAL().add(instPlayerManualSkill, instPlayerId);
		return instPlayerManualSkill;
	}
	
	/**
	 * 添加玩家手动技能排布实例表
	 * @author hzw
	 * @date 2014-9-11上午10:54:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerManualSkillLine(int instPlayerId, int instPlayerManualSkillId, int position, MessageData syncMsgData) throws Exception{
		List<InstPlayerManualSkillLine> instPlayerManualSkillLines = getInstPlayerManualSkillLineDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(instPlayerManualSkillLines.size() <= 0){
			InstPlayerManualSkillLine instPlayerManualSkillLine = new InstPlayerManualSkillLine();
			instPlayerManualSkillLine.setInstPlayerId(instPlayerId);
			instPlayerManualSkillLine.setPosition1(0);
			instPlayerManualSkillLine.setPosition2(0);
			instPlayerManualSkillLine.setPosition3(0);
			instPlayerManualSkillLine.setPosition4(0);
			
			switch(position){
			case 1: instPlayerManualSkillLine.setPosition1(instPlayerManualSkillId); break;
			case 2: instPlayerManualSkillLine.setPosition2(instPlayerManualSkillId); break;
			case 3: instPlayerManualSkillLine.setPosition3(instPlayerManualSkillId); break;
			case 4: instPlayerManualSkillLine.setPosition4(instPlayerManualSkillId); break;
			}
			getInstPlayerManualSkillLineDAL().add(instPlayerManualSkillLine, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerManualSkillLine, instPlayerManualSkillLine.getId(), instPlayerManualSkillLine.getResult(), syncMsgData);
		}else{
			InstPlayerManualSkillLine instPlayerManualSkillLine = instPlayerManualSkillLines.get(0);
			switch(position){
			case 1: instPlayerManualSkillLine.setPosition1(instPlayerManualSkillId); break;
			case 2: instPlayerManualSkillLine.setPosition2(instPlayerManualSkillId); break;
			case 3: instPlayerManualSkillLine.setPosition3(instPlayerManualSkillId); break;
			case 4: instPlayerManualSkillLine.setPosition4(instPlayerManualSkillId); break;
			}
			getInstPlayerManualSkillLineDAL().update(instPlayerManualSkillLine, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerManualSkillLine, instPlayerManualSkillLine.getId(), instPlayerManualSkillLine.getResult(), syncMsgData);
		}
	
	}
}
