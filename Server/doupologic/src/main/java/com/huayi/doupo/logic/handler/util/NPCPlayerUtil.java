package com.huayi.doupo.logic.handler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictArenaNPC;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.InstPlayerArenaNPC;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.quartz.ChapterActivityHandler;
import com.huayi.doupo.logic.util.NameUtil;

/**
 * NPC Player 工具类
 * @author mp
 * @date 2013-12-13 下午4:26:40
 */
public class NPCPlayerUtil extends DALFactory{
	
	public static ConcurrentHashMap<Integer, InstPlayerArenaNPC> instPlayerArenaNPCMap = new ConcurrentHashMap<Integer, InstPlayerArenaNPC>();
	
	/**
	 * 为决斗创建5000个NPC Player
	 * @author mp
	 * @date 2013-12-13 下午4:26:34
	 * @Description
	 */
	@SuppressWarnings({ "unchecked" })
	public static void loadNPCPlayer() throws Exception{
		//更新/初始活动副本实例表
		ChapterActivityHandler.chapterActivity();
	
		List<InstPlayerArenaNPC> instPlayerArenaNPCList = getInstPlayerArenaNPCDAL().getList("", 0);
		List<InstPlayerArena> instPlayerArenaList = new ArrayList<InstPlayerArena>();
		if(instPlayerArenaNPCList.size() <= 0){
			
			System.out.println("共需插入5000个NPC玩家");
			
			List<DictArenaNPC> dictArenaNPCList = DictList.dictArenaNPCList;
			int i = 1000000;
			for(DictArenaNPC obj : dictArenaNPCList){
				for(int q = obj.getDownRank(); q <= obj.getUpRank(); q++){
					i ++;
					int level = obj.getLevel();
					InstPlayerArenaNPC instPlayerArenaNPC  = new InstPlayerArenaNPC ();
					instPlayerArenaNPC.setId(i);
					instPlayerArenaNPC.setName(NameUtil.getRandomName());
					instPlayerArenaNPC.setLevel(level);
					
					String cardQualitys = ""; //组织卡牌的生成： 卡牌品质_个数
					if(obj.getGreen() > 0){
						cardQualitys += StaticQuality.green + "_" + obj.getGreen() + ";";
					}
					if(obj.getBlue() > 0){
						cardQualitys += StaticQuality.blue + "_" + obj.getBlue() + ";";
					}
					if(obj.getPurple() > 0){
						cardQualitys += StaticQuality.purple + "_" + obj.getPurple() + ";";
					}
					cardQualitys = cardQualitys.substring(0, cardQualitys.length() - 1);
					
					String cards = ""; //卡牌列表  : id|cardID|qualityId|StarLevelId|TitleDetailId|exp|Level;
					String formations = ""; //阵型列表  id|Type|InstCardId|Position|CardId
					String equips = ""; //装备列表  id|EquipId|Level
					String magics = ""; //功法法宝列表  id|MagicId|MagicType|Quality|MagicLevelId|InstCardId
					String lineups = ""; //阵容列表  id|InstPlayerFormationId|EquipTypeId|InstPlayerEquipId
					int y = 1;
					int z = 1;
					for(String str : cardQualitys.split(";")){
						int qualityId = ConvertUtil.toInt(str.split("_")[0]);
						int num = ConvertUtil.toInt(str.split("_")[1]);
						List<DictCard> dictCardListTemp = (List<DictCard>)DictMapList.dictCardMap.get(qualityId);
						List<DictCard> dictCardList = new ArrayList<>();
						for(DictCard objCard : dictCardListTemp){
							if(objCard.getId() <= 147){
								dictCardList.add(objCard);
							}
						}
						List<Integer> cardTempList = RandomUtil.getRandomNoRepeat(num, dictCardList.size());
						for(int j = 1; j <= cardTempList.size(); j++){
							DictCard dictCard = dictCardList.get(cardTempList.get(j - 1) - 1);
							int cardId = dictCard.getId();
							
							//组织卡牌列表
							cards += y + "|" + cardId + "|" + qualityId + "|" + "1|" + "1|" + "0|" + level + ";";
							
							//组织阵型列表
							DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(level + "");
							if(j <= dictLevelProp.getInTeamCard()){
								formations += y + "|1|" + y +"|" + y +"|" + cardId + ";";
							}else{
								formations += y + "|2|" +  y +"|" +(j - dictLevelProp.getInTeamCard()) +"|" + cardId + ";";
							}
							
							//组织装备|阵容列表
							List<DictEquipment> dictEquipmentList = (List<DictEquipment>)DictMapList.dictEquipmentMap.get(qualityId);
							for(int a = 1; a <= 5; a++){
								if(a != 4){
									int count = 0;
									while (true) {
										count ++;
										DictEquipment dictEquipment = dictEquipmentList.get(RandomUtil.getRandomInt(dictEquipmentList.size()));
										if(a == dictEquipment.getEquipTypeId()){
											
											equips +=  z + "|" + dictEquipment.getId() + "|" + level + ";";
											
											lineups += z + "|" + y +"|" + a + "|" + z + ";";
											break;
										}
										if (count > 100) {
											break;
										}
									}
									z++;
								}
							}
							
							//功法法宝列表
							for(int a = 1; a <= 2; a++){
								List<DictMagic> dictMagicList = (List<DictMagic>)DictMapList.dictMagicMap.get(a);
								DictMagic dictMagic = dictMagicList.get(RandomUtil.getRandomInt(dictMagicList.size() - 2));
								magics += z + a + "|" + dictMagic.getId() + "|" + a + "|" + dictMagic.getMagicQualityId() + "|" + dictMagic.getMagicLevelId()  + "|" + y + ";";
							}
							y ++;
						}
					}
					instPlayerArenaNPC.setCards(cards.substring(0, cards.length() - 1));
					instPlayerArenaNPC.setFormations(formations.substring(0, formations.length() - 1));
					instPlayerArenaNPC.setLineups(lineups.substring(0, lineups.length() - 1));
					instPlayerArenaNPC.setEquips(equips.substring(0, equips.length() - 1));
					instPlayerArenaNPC.setMagics(magics.substring(0, magics.length() - 1));
					instPlayerArenaNPCList.add(instPlayerArenaNPC);
					
					//将竞技场NPC放入内存MAP
					instPlayerArenaNPCMap.put(i, instPlayerArenaNPC);
					
					//组织排行
					InstPlayerArena instPlayerArena = new InstPlayerArena();
					instPlayerArena.setInstPlayerId(i);
					instPlayerArena.setRank(i - 1000000);
					instPlayerArena.setUpRank(-1);
					instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum));
					instPlayerArena.setArenaTime("");
					instPlayerArena.setPrestige(0);
					instPlayerArena.setAwardRank(0);
					instPlayerArena.setOperTime(DateUtil.getCurrTime());
					instPlayerArenaList.add(instPlayerArena);
				}
			}
			//添加竞技场NPC
			getInstPlayerArenaNPCDAL().batchAdd(instPlayerArenaNPCList);
			
			//添加竞技场实例数据
			getInstPlayerArenaDAL().batchAdd(instPlayerArenaList);
		}else{
			for(InstPlayerArenaNPC obj : instPlayerArenaNPCList){
				//将竞技场NPC放入内存MAP
				instPlayerArenaNPCMap.put(obj.getId(), obj);
			}
		}

	}
}
