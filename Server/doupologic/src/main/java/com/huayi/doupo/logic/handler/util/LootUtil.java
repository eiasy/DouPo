package com.huayi.doupo.logic.handler.util;

import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictChip;
import com.huayi.doupo.base.model.InstPlayerChip;
import com.huayi.doupo.base.model.InstPlayerLoot;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;

public class LootUtil extends DALFactory{
	
	
	/**
	 * 添加碎片
	 * @author hzw
	 * @date 2014-9-6下午3:15:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerChip(int instPlayerId, int chipId, int num, MessageData syncMsgData, Map<String, Object> msgMap) throws Exception{
		List<InstPlayerChip> instPlayerChipList = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, instPlayerId);
		InstPlayerChip instPlayerChip = null;
		if(instPlayerChipList.size() == 0){
			instPlayerChip = new InstPlayerChip();
			instPlayerChip.setInstPlayerId(instPlayerId);
			instPlayerChip.setChipId(chipId);
			instPlayerChip.setNum(num);
			getInstPlayerChipDAL().add(instPlayerChip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerChip, instPlayerChip.getId(), instPlayerChip.getResult(), syncMsgData);
			
			//记录日志
			//获得时间
			//获得的是碎片id
			//获得的碎片名称
			//协议来源描述
			try {
				String desc = "";
				if (msgMap != null) {
					int source = (int) msgMap.get("header");
					desc = DictMap.sysMsgRuleMap.get(source + "").getName();
				}
				DictChip dictChipObj = DictMap.dictChipMap.get(chipId + "");
				LogUtil.info("获得功法法宝碎片:instPlayerId=" + instPlayerId + " chipName=" + dictChipObj.getName() + " operTime=" + DateUtil.getCurrTime() + " msgDesc=" + desc);
			} catch (Exception e) {
				LogUtil.error("记录功法法宝获得日志Error", e);
			}
		}
		if(instPlayerChipList.size() > 0){
			instPlayerChip = instPlayerChipList.get(0);
			instPlayerChip.setNum(instPlayerChip.getNum() + num);
			getInstPlayerChipDAL().update(instPlayerChip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChip, instPlayerChip.getId(), instPlayerChip.getResult(), syncMsgData);
		}
		
		//实例化抢夺数据
		List<InstPlayerLoot> instPlayerLootList = getInstPlayerLootDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		DictChip dictChip = DictMap.dictChipMap.get(chipId + "");
		int skillOrKungFuId = dictChip.getSkillOrKungFuId();
		if(instPlayerLootList == null || instPlayerLootList.size() <= 0){
			InstPlayerLoot instPlayerLoot = new InstPlayerLoot();
			instPlayerLoot.setInstPlayerId(instPlayerId);
			instPlayerLoot.setProtectTime("0");
			instPlayerLoot.setSkills("");
			instPlayerLoot.setKungFus("");
			instPlayerLoot.setMagics("");
			if(dictChip.getType() == 1){
					instPlayerLoot.setSkills(skillOrKungFuId + "_" + num);
			}else if(dictChip.getType() == 2){
					instPlayerLoot.setKungFus(skillOrKungFuId + "_" + num);
			}else if(dictChip.getType() == 3){
				instPlayerLoot.setMagics(skillOrKungFuId + "_" + num);
			}
			getInstPlayerLootDAL().add(instPlayerLoot, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerLoot, instPlayerLoot.getId(), instPlayerLoot.getResult(), syncMsgData);
		
		}else{
			InstPlayerLoot instPlayerLoot = instPlayerLootList.get(0);
			boolean tag = false;
			if(dictChip.getType() == 1){
				String skills = instPlayerLoot.getSkills();
				if(skills == null || skills.equals("")){
					instPlayerLoot.setSkills(skillOrKungFuId + "_" + num);
					tag = true;
				}else if(!(";" + skills).contains(";" + skillOrKungFuId + "_")){
					instPlayerLoot.setSkills(skills + ";" + skillOrKungFuId  + "_" + num);
					tag = true;
				}else if((";" + skills).contains(";" + skillOrKungFuId + "_")){
					skills = ";" + skills + ";";
					String oldSkills = skills.substring(skills.indexOf(";" + skillOrKungFuId + "_"));
					oldSkills = oldSkills.substring(0, oldSkills.indexOf(";", 2));
					if(instPlayerChip.getNum() > ConvertUtil.toInt(oldSkills.split("_")[1])){
						String newskills = oldSkills.split("_")[0] + "_" + (ConvertUtil.toInt(oldSkills.split("_")[1]) + num);
						skills = skills.replace(oldSkills, newskills);
						instPlayerLoot.setSkills(skills.substring(1, skills.length() - 1));
						tag = true;
					}
				}
			}else if(dictChip.getType() == 2){
				String kungFus = instPlayerLoot.getKungFus();
				if(kungFus == null || kungFus.equals("")){
					instPlayerLoot.setKungFus(skillOrKungFuId + "_" + num);
					tag = true;
				}else if(!(";" + kungFus).contains(";" + skillOrKungFuId + "_")){
					instPlayerLoot.setKungFus(kungFus + ";" + skillOrKungFuId  + "_" + num);
					tag = true;
				}else if((";" + kungFus).contains(";" + skillOrKungFuId + "_")){
					kungFus = ";" + kungFus + ";";
					String oldkungFus = kungFus.substring(kungFus.indexOf(";" + skillOrKungFuId + "_"));
					oldkungFus = oldkungFus.substring(0, oldkungFus.indexOf(";", 2));
					if(instPlayerChip.getNum() > ConvertUtil.toInt(oldkungFus.split("_")[1])){
						String newkungFus = oldkungFus.split("_")[0] + "_" + (ConvertUtil.toInt(oldkungFus.split("_")[1]) + num);
						kungFus = kungFus.replace(oldkungFus, newkungFus);
						instPlayerLoot.setKungFus(kungFus.substring(1, kungFus.length() - 1));
						tag = true;
					}
				}
			}else if(dictChip.getType() == 3){
				String magics = instPlayerLoot.getMagics();
				if(magics == null || magics.equals("")){
					instPlayerLoot.setMagics(skillOrKungFuId + "_" + num);
					tag = true;
				}else if(!(";" + magics).contains(";" + skillOrKungFuId + "_")){
					instPlayerLoot.setMagics(magics + ";" + skillOrKungFuId  + "_" + num);
					tag = true;
				}else if((";" + magics).contains(";" + skillOrKungFuId + "_")){
					magics = ";" + magics + ";";
					String oldmagics = magics.substring(magics.indexOf(";" + skillOrKungFuId + "_"));
					oldmagics = oldmagics.substring(0, oldmagics.indexOf(";", 2));
					if(instPlayerChip.getNum() > ConvertUtil.toInt(oldmagics.split("_")[1])){
						String newmagics = oldmagics.split("_")[0] + "_" + (ConvertUtil.toInt(oldmagics.split("_")[1]) + num);
						magics = magics.replace(oldmagics, newmagics);
						instPlayerLoot.setMagics(magics.substring(1, magics.length() - 1));
						tag = true;
					}
				}
			}
			if(tag){
				getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerLoot, instPlayerLoot.getId(), instPlayerLoot.getResult(), syncMsgData, dictChip.getType());
			}
		}
		
	}
	
	
	/**
	 * 更新删除玩家碎片
	 * @author hzw
	 * @date 2014-9-6下午3:15:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstPlayerChip(int instPlayerId, int chipId, int num, MessageData syncMsgData) throws Exception{
		List<InstPlayerChip> instPlayerChipList = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, instPlayerId);
		InstPlayerChip instPlayerChip = instPlayerChipList.get(0);
		if(instPlayerChip.getNum() - num <= 0){
			getInstPlayerChipDAL().deleteById(instPlayerChip.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerChip, instPlayerChip.getId(), "", syncMsgData);
		}else{
			instPlayerChip.setNum(instPlayerChip.getNum() - num);
			getInstPlayerChipDAL().update(instPlayerChip, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChip, instPlayerChip.getId(), instPlayerChip.getResult(), syncMsgData);
		}
	}

}
