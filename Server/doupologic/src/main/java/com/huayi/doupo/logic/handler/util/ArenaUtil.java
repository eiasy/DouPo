package com.huayi.doupo.logic.handler.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictArenaAdvance;
import com.huayi.doupo.base.model.DictArenaReward;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArenaNPC;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.logic.util.MessageData;

public class ArenaUtil extends DALFactory{
	
	
	/**
	 * 组织竞技场人物
	 * @author hzw
	 * @date 2014-10-29下午4:11:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgPlayer(int instPlayerId, int rank, int upRank, List<DictArenaReward> dictArenaRewardList, MessageData playersMsgData) throws Exception{
		MessageData playerMsgData = new MessageData();
		if(upRank != -1){
			InstPlayer player = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			playerMsgData.putIntItem("1", rank);
			playerMsgData.putIntItem("2", instPlayerId);
			playerMsgData.putIntItem("3", player.getLevel());
			playerMsgData.putStringItem("4", player.getName());
			List<InstPlayerFormation> InstPlayerFormations = getInstPlayerFormationDAL().getList(" instPlayerId = " + instPlayerId + " and type = 1 limit 4", instPlayerId);
			String cards = "";
			for(InstPlayerFormation ipf : InstPlayerFormations){
				InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ipf.getInstCardId(), instPlayerId);
				cards += instPlayerCard.getCardId() + "_" + instPlayerCard.getQualityId() + ";";
			}
			playerMsgData.putStringItem("5", cards.substring(0, cards.length() - 1));
		}
		else{
			InstPlayerArenaNPC instPlayerArenaNPC = NPCPlayerUtil.instPlayerArenaNPCMap.get(instPlayerId);
			playerMsgData.putIntItem("1", rank);
			playerMsgData.putIntItem("2", instPlayerId);
			playerMsgData.putIntItem("3", instPlayerArenaNPC.getLevel());
			playerMsgData.putStringItem("4", instPlayerArenaNPC.getName());
			String cards = "";
			for(String card : instPlayerArenaNPC.getCards().split(";")){
				cards += card.split("\\|")[1] + "_" + card.split("\\|")[2] + ";";
			}
			playerMsgData.putStringItem("5", cards.substring(0, cards.length() - 1));
		}
		for(DictArenaReward dictArenaReward : dictArenaRewardList){
			if(rank <= dictArenaReward.getUpRank() && dictArenaReward.getDownRank() <= rank){
				playerMsgData.putIntItem("6", dictArenaReward.getGold());
				playerMsgData.putIntItem("7", dictArenaReward.getPrestige());
			}
		}
		playersMsgData.putMessageItem(rank + "", playerMsgData.getMsgData());
		
	}
	
	
	
	/**
	 * 竞技场
	 * @author hzw
	 * @date 2014-10-28下午3:24:34
	 * @param instPlayerId
	 * @param rank      玩家历史最高排名
	 * @param awardRank 领取前进将的排名
	 * @throws Exception
	 * @Description
	 */
	public static int arenaReward(int instPlayerId, int rank, int awardRank, MessageData syncMsgData) throws Exception{
		List<DictArenaAdvance> dictArenaAdvanceList = DictList.dictArenaAdvanceList;
		//顺序 one-two是顺序， two-one是倒叙
		Collections.sort(dictArenaAdvanceList, new Comparator<Object>() {
		public int compare(Object a, Object b) {
		int one = ((DictArenaAdvance)a).getDownRank();
		int two = ((DictArenaAdvance)b).getDownRank(); 
		return (int)(two - one); 
		}
		}); 
		
		String things = "";
		int awardRankTemp = awardRank; //领取前进将的排名
		int gold = 0; //前进将
		int extraGold = 0; //额外奖励
		if(awardRankTemp == 0){
			for(DictArenaAdvance obj : dictArenaAdvanceList){
				if(rank <= obj.getDownRank()){ //小于下限
					gold += (obj.getUpRank() + 1 - obj.getDownRank()) / obj.getAdvanceNum() * obj.getGold();
					extraGold += obj.getExtraGold();
					awardRank = obj.getDownRank() - 1;
				}
				if(rank >= obj.getDownRank() && rank <= obj.getUpRank()){
					gold += (obj.getUpRank() - rank) / obj.getAdvanceNum() * obj.getGold();
					extraGold += obj.getExtraGold();
					awardRank = rank + (obj.getUpRank() - rank) % obj.getAdvanceNum();
					break;
				}
			}
		}else{
			for(DictArenaAdvance obj : dictArenaAdvanceList){
				if(awardRankTemp >= obj.getDownRank() && awardRankTemp <= obj.getUpRank()){
					if(rank <= obj.getDownRank()){
						gold += (awardRank + 1 - obj.getDownRank()) / obj.getAdvanceNum() * obj.getGold();
						awardRank = obj.getDownRank()- 1;
					}
					if(rank >= obj.getDownRank() && rank <= obj.getUpRank()){
						gold += (awardRank + 1 - rank) / obj.getAdvanceNum() * obj.getGold();
						awardRank = rank + (awardRank + 1 - rank) % obj.getAdvanceNum();
						break;
					}
				}
				if(awardRank >= obj.getDownRank() && awardRank <= obj.getUpRank()){
					if(rank <= obj.getDownRank()){
						gold += (awardRank + 1 - obj.getDownRank()) / obj.getAdvanceNum() * obj.getGold();
						extraGold += obj.getExtraGold();
						awardRank = obj.getDownRank() - 1;
					}
					if(rank >= obj.getDownRank() && rank <= obj.getUpRank()){
						gold += (awardRank + 1 - rank) / obj.getAdvanceNum() * obj.getGold();
						extraGold += obj.getExtraGold();
						awardRank = rank + (awardRank + 1 - rank) % obj.getAdvanceNum();
						break;
					}
				}
			}
		}
		if(gold > 0){
			String description = "恭喜您在竞技场中获得前进奖励：";
			things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + gold;
			ActivityUtil.addInstPlayerAward(instPlayerId, 2, things, DateUtil.getCurrTime(), description, syncMsgData);
		}
		if(extraGold > 0){
			String description = "恭喜您在竞技场中获得额外奖励：";
			things = StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.gold + "_" + extraGold;
			ActivityUtil.addInstPlayerAward(instPlayerId, 2, things, DateUtil.getCurrTime(), description, syncMsgData);
		}
		
		return awardRank;
	}
	
	

}
