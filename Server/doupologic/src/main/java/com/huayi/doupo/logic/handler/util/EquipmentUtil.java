package com.huayi.doupo.logic.handler.util;

import java.util.HashMap;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictEquipStrengthen;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.InstEquipGem;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticEquipQuality;

/**
 * 装备工具类
 * @author mp
 * @date 2014-7-2 下午2:36:47
 */
public class EquipmentUtil extends DALFactory{
	
	/**
	 * 添加装备
	 * @author mp
	 * @date 2013-10-30 下午1:39:51
	 * @param instPlayerId
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerEquip addEquipment(int instPlayerId, int equipmentId) throws Exception{
		DictEquipment equipment = DictMap.dictEquipmentMap.get(equipmentId+"");
		InstPlayerEquip InstPlayerEquip = new InstPlayerEquip();
		InstPlayerEquip.setInstPlayerId(instPlayerId);
		InstPlayerEquip.setInstCardId(0);
		InstPlayerEquip.setEquipTypeId(equipment.getEquipTypeId());
		InstPlayerEquip.setEquipId(equipmentId);
		InstPlayerEquip.setIsInlay(0);
		InstPlayerEquip.setLevel(0);
		InstPlayerEquip.setEquipAdvanceId(0);
		getInstPlayerEquipDAL().add(InstPlayerEquip, instPlayerId);
		return InstPlayerEquip;
	}
	
	/**
	 * 测试用初始数据，非游戏代码
	 * @author mp
	 * @date 2014-10-7 上午11:54:10
	 * @param instPlayerId
	 * @param equipmentId
	 * @param instCardId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerEquip addEquipment(int instPlayerId, int equipmentId, int instCardId) throws Exception{
		DictEquipment equipment = DictMap.dictEquipmentMap.get(equipmentId+"");
		InstPlayerEquip InstPlayerEquip = new InstPlayerEquip();
		InstPlayerEquip.setInstPlayerId(instPlayerId);
		InstPlayerEquip.setInstCardId(instCardId);
		InstPlayerEquip.setEquipTypeId(equipment.getEquipTypeId());
		InstPlayerEquip.setEquipId(equipmentId);
		InstPlayerEquip.setIsInlay(0);
		InstPlayerEquip.setLevel(40);
		InstPlayerEquip.setEquipAdvanceId(0);
		getInstPlayerEquipDAL().add(InstPlayerEquip, instPlayerId);
		return InstPlayerEquip;
	}
	
/*	*//**
	 * 游戏启动时加载500个NPC玩家之装备
	 * @author mp
	 * @date 2014-1-3 下午2:36:31
	 * @param instPlayerId
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 * @Description
	 *//*
	public static InstPlayerEquip addNpcEquipment(int instPlayerId, int equipmentId, int level, int warriorId) throws Exception{
		DictEquipment equipment = DictMap.dictEquipmentMap.get(equipmentId+"");
		InstPlayerEquip instPlayerEquip = new InstPlayerEquip();
		
		getInstPlayerEquipDAL().add(instPlayerEquip, instPlayerId);
		return instPlayerEquip;
	}*/

	/**
	 * 更新装备强化后玩家装备信息
	 * @author mp
	 * @date 2013-10-17 下午4:34:21
	 * @param level
	 * @param equipmentId
	 * @param playerEquipment
	 * @throws Exception
	 * @Description
	 */
	public static void updatePlayerEquip(int level, int equipmentId, InstPlayerEquip playerEquipment) throws Exception{
//		int strengLevel = level - playerEquipment.getThingLevel();
//		DictEquipment equipment = DictMap.dictEquipmentMap.get(equipmentId+"");
//		playerEquipment.setFightPropValue(playerEquipment.getFightPropValue() + equipment.getFightValueGrow() * strengLevel);
//		playerEquipment.setThingLevel(level);
//		playerEquipment.setStrengCost(FormulaUtil.calcStrengCost(equipmentId, level));
//		playerEquipment.setPrice(playerEquipment.getPrice() + equipment.getPriceGrow() * strengLevel);
//		playerEquipment.setThingValue(playerEquipment.getThingValue() + equipment.getValueGrow() * strengLevel);
//		getInstPlayerEquipDAL().update(playerEquipment);
	}
	
	/**
	 * 更新随装备变化造成的武士战斗数据变化
	 * @author mp
	 * @date 2013-10-21 下午4:43:02
	 * @param equipmentId
	 * @param strengLevel
	 * @param instWarrior
	 * @throws Exception
	 * @Description
	 */
	/*public static void updateWarriorByEquip(int equipmentId, int strengLevel, InstWarrior instWarrior) throws Exception{
		DictEquipment equipment = DictMap.dictEquipmentMap.get(equipmentId+"");
		int fightPropId = equipment.getFightPropId();
		int grow = equipment.getFightValueGrow() * strengLevel;
		WarriorUtil.updateWarriorFightProp(instWarrior, fightPropId, grow);
		getInstWarriorDAL().update(instWarrior);
	}
	
	*//**
	 * 返回槽的颜色 - 颜色Id随机产生
	 * @author mp
	 * @date 2013-10-23 下午1:55:35
	 * @return
	 * @Description
	 *//*
	public static int getHoleRandomColor(){
		int [] colorArray = new int[3];
		colorArray [0] = StaticColor.red; 
		colorArray [1] = StaticColor.green; 
		colorArray [2] = StaticColor.blue;
		int random = RandomUtil.getRandomInt(3);
		return colorArray[random];
	}*/
	
	/**
	 * 向装备上添加宝石槽
	 * @author mp
	 * @date 2014-7-2 下午2:36:04
	 * @param times
	 * @param instPlayerEquipmentId
	 * @param instPlayerId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstEquipGem addEquipmentGem(int times, int instPlayerEquipId, int instPlayerId) throws Exception{
		InstEquipGem instEquipGem = new InstEquipGem();
		instEquipGem.setInstPlayerEquipId(instPlayerEquipId);
		instEquipGem.setThingId(0);
		instEquipGem.setPosition(times + 1);
		instEquipGem.setInstPlayerId(instPlayerId);
		getInstEquipGemDAL().add(instEquipGem, instPlayerId);
		return instEquipGem;
	}
	
	/**
	 * 获取装备上每个装备的身价和，用于更换卡牌功能
	 * @author mp
	 * @date 2013-10-28 下午2:26:36
	 * @param instEquipIdList
	 * @return
	 * @Description
	 */
	/*public static int getEquipValue(List<Integer> instEquipIdList){
		int equipValue = 0;
		for(int instEquipId : instEquipIdList){
			InstPlayerEquip InstPlayerEquip = getInstPlayerEquipDAL().getModel(instEquipId);
			equipValue += InstPlayerEquip.getThingValue();
		}
		return equipValue;
	}
	
	*//**
	 * 获取装备上每个装备的战斗属性和具体值
	 * @author mp
	 * @date 2013-10-28 下午2:47:30
	 * @param instEquipIdList
	 * @return
	 * @Description
	 *//*
	public static Map<Integer, Integer> getEquipPropMap(List<Integer> instEquipIdList){
		Map<Integer, Integer> equipPropMap = new HashMap<Integer, Integer>();
		for(int instEquipId : instEquipIdList){
			InstPlayerEquip InstPlayerEquip = getInstPlayerEquipDAL().getModel(instEquipId);
			equipPropMap.put(InstPlayerEquip.getFightPropId(), InstPlayerEquip.getFightPropValue());
		}
		return equipPropMap;
	}*/
	
	/**
	 * 获取强化消耗的银币
	 * @author hzw
	 * @date 2014-9-26下午4:53:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> strengthen(InstPlayerEquip playerEquip, int addLevel, int playerCopper){
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		int copper = 0;
		int i = 1;
		DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(playerEquip.getEquipId() + "");
		if(dictEquipment.getEquipQualityId() == StaticEquipQuality.white) {
			for(; i<= addLevel; i++){
				DictEquipStrengthen obj = DictMap.dictEquipStrengthenMap.get((playerEquip.getLevel() + i) + "");
				copper += obj.getWhiteCopper();
				if(copper > playerCopper){
					copper -= obj.getWhiteCopper();
					i --;
					break;
				}
			}
		}
		else if(dictEquipment.getEquipQualityId() == StaticEquipQuality.green) {
			for(; i<= addLevel; i++){
				DictEquipStrengthen obj = DictMap.dictEquipStrengthenMap.get((playerEquip.getLevel() + i) + "");
				copper += obj.getGreenCopper();
				if(copper > playerCopper){
					copper -= obj.getGreenCopper();
					i --;
					break;
				}
			}
		}
		else if(dictEquipment.getEquipQualityId() == StaticEquipQuality.blue) {
			for(; i<= addLevel; i++){
				DictEquipStrengthen obj = DictMap.dictEquipStrengthenMap.get((playerEquip.getLevel() + i) + "");
				copper += obj.getBlueCopper();
				if(copper > playerCopper){
					copper -= obj.getBlueCopper();
					i --;
					break;
				}
			}
		}
		else if(dictEquipment.getEquipQualityId() == StaticEquipQuality.purple) {
			for(; i<= addLevel; i++){
				DictEquipStrengthen obj = DictMap.dictEquipStrengthenMap.get((playerEquip.getLevel() + i) + "");
				copper += obj.getPurpleCopper();
				if(copper > playerCopper){
					copper -= obj.getPurpleCopper();
					i --;
					break;
				}
			}
		}
		else if(dictEquipment.getEquipQualityId() == StaticEquipQuality.golden) {
			for(; i<= addLevel; i++){
				DictEquipStrengthen obj = DictMap.dictEquipStrengthenMap.get((playerEquip.getLevel() + i) + "");
				copper += obj.getGoldenCopper();
				if(copper > playerCopper){
					copper -= obj.getGoldenCopper();
					i --;
					break;
				}
			}
		}
		if(i > addLevel){
			i = addLevel;
		}
		retMap.put("copper", copper);
		retMap.put("addLevel", i);
		return retMap;
	}
}
