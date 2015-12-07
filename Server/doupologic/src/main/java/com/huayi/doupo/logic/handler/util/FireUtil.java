package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictFire;
import com.huayi.doupo.base.model.InstPlayerFire;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.base.ConvertUtil;

public class FireUtil extends DALFactory{

	/**
	 * 判断异火中是否已存在当前异火技能
	 * @author hzw
	 * @date 2014-7-24上午11:25:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static boolean fireSkillIsHave(int fireSkillId, String skills[]) {
		boolean flag = false;
		for(String skill : skills){
			if(ConvertUtil.toInt(skill.split("_")[1]) == fireSkillId){
				flag = true;
			}
		}
		return flag;
	}
	
	
	public static InstPlayerFire addInstPlayerFire(int instPlayerId, int fireId) throws Exception{
		DictFire dictFire = DictMap.dictFireMap.get(fireId + "");
		InstPlayerFire instPlayerFire = new InstPlayerFire();
		instPlayerFire.setInstPlayerId(instPlayerId);
		instPlayerFire.setFireId(fireId);
		instPlayerFire.setLevel(1);
		instPlayerFire.setExp(0);
		if(dictFire.getType() == 1){
			String bySkillIds = "";
			for(int i = 1; i <= dictFire.getBySkills(); i++){
				if(i == 1){
					bySkillIds += i + "_" + dictFire.getFireSkillId() + ";";
				}else{
					bySkillIds += i + "_" + 0 + ";";
				}
			}
			instPlayerFire.setBySkillIds(bySkillIds.substring(0, bySkillIds.length() - 1));
		}
		instPlayerFire.setIsUse(0);
		instPlayerFire = getInstPlayerFireDAL().add(instPlayerFire, instPlayerId);
		
		return instPlayerFire;
	}
	
	
	
}
