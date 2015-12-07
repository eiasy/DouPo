package com.huayi.doupo.logic.handler.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.memcalc.CardCalcObj;
import com.huayi.doupo.base.model.memcalc.CardPropObj;
import com.huayi.doupo.base.model.memcalc.MemCalcObj;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 内存计算工具类
 * @author mp
 * @date 2014-6-26 上午9:54:40
 */
public class MemCalcUtil extends DALFactory{

	/**
	 * 组织内存计算对象
	 * @author mp
	 * @date 2014-6-26 上午9:55:12
	 * @Description
	 */
	public static void orgMemCalcObj (int instPlayerId){
		
		MemCalcObj memCalcObj = new MemCalcObj();
		
		//封装卡牌对象列表
		Map<Integer, CardCalcObj> cardCalcMap = new HashMap<Integer, CardCalcObj>();
		List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerCard instPlayerCard : instPlayerCardList) {
			CardCalcObj cardCalcObj = new CardCalcObj();
			cardCalcObj.setCardId(instPlayerCard.getCardId());//卡牌字典Id
			cardCalcObj.setInTeam(instPlayerCard.getInTeam());//是否在队伍中
			cardCalcObj.setAdvanceLevel(0);//进阶相关,暂未涉及
			cardCalcObj.setLevel(instPlayerCard.getLevel());//卡牌等级
			cardCalcObj.setTrainCalcMap(null);//培养相关,暂未涉及
			cardCalcObj.setEquipCalcMap(null);//装备相关,暂未涉及
			cardCalcMap.put(instPlayerCard.getId(), cardCalcObj);
		}
		memCalcObj.setCardCalcMap(cardCalcMap);
		
		//封装修行造成的影响,暂未涉及
		memCalcObj.setPracticeCalcObj(null);

		//封装公会造成的影响,暂未涉及
		memCalcObj.setUnionCalcObj(null);
		
		PlayerMapUtil.getPlayerByPlayerId(instPlayerId).setMemCalcObj(memCalcObj);
	}
	
	/**
	 * 计算单张卡牌的属性
	 * @author mp
	 * @date 2014-6-26 上午9:56:01
	 * @param instCardId
	 * @Description
	 */
	public static CardPropObj getCardProp (int instPlayerId, int instCardId) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		CardCalcObj cardCalcObj = memCalcObj.getCardCalcMap().get(instCardId);
		
		//计算属性,返回结果
		return calcCardProp(cardCalcObj);
	}
	
	/**
	 * 组织单个卡牌消息对象
	 * @author mp
	 * @date 2014-6-26 下午3:53:36
	 * @param instPlayerId
	 * @param instCardId
	 * @Description
	 */
	public static MessageData getCardPropMsg (int instPlayerId, int instCardId) {
		CardPropObj getCardProp = getCardProp(instPlayerId, instCardId);
		return OrgFrontMsgUtil.orgCardPropMsgData(getCardProp);
	}
	
	/**
	 * 计算所有卡牌的属性
	 * @author mp
	 * @date 2014-6-26 上午9:56:47
	 * @Description
	 */
	public static Map<Integer, CardPropObj> getAllCardProp (int instPlayerId) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		
		//组织返回所有卡牌属性的map
		Map<Integer, CardPropObj> allCardPropMap = new HashMap<Integer, CardPropObj>();
		
		//循环所有卡牌
		for (Entry<Integer, CardCalcObj> entry : memCalcObj.getCardCalcMap().entrySet()) {
			int instCardId = entry.getKey();
			CardCalcObj cardCalcObj = entry.getValue();
			CardPropObj cardPropObj = calcCardProp(cardCalcObj);
			allCardPropMap.put(instCardId, cardPropObj);
		}
		
		return allCardPropMap;
	}
	
	/**
	 * 返回所有卡牌属性消息对象
	 * @author mp
	 * @date 2014-6-26 下午4:14:19
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static MessageData getAllCardPropMsg (int instPlayerId) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		
		//定义返回MessageData
		MessageData allCardMsgData = new MessageData();
		
		//循环所有卡牌
		for (Entry<Integer, CardCalcObj> entry : memCalcObj.getCardCalcMap().entrySet()) {
			int instCardId = entry.getKey();
			CardCalcObj cardCalcObj = entry.getValue();
			MessageData msgData = OrgFrontMsgUtil.orgCardPropMsgData(calcCardProp(cardCalcObj));
			allCardMsgData.putMessageItem(instCardId + "", msgData.getMsgData());
		}
		
		return allCardMsgData;
	}
	
	/**
	 * 计算上阵卡牌的属性
	 * @author mp
	 * @date 2014-6-26 下午3:33:32
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static Map<Integer, CardPropObj> getInTeamCardProp (int instPlayerId) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		
		//组织返回所有卡牌属性的map
		Map<Integer, CardPropObj> allCardPropMap = new HashMap<Integer, CardPropObj>();
		
		//循环所有上阵卡牌
		for (Entry<Integer, CardCalcObj> entry : memCalcObj.getCardCalcMap().entrySet()) {
			int instCardId = entry.getKey();
			CardCalcObj cardCalcObj = entry.getValue();
			if (cardCalcObj.getInTeam() == StaticCustomDict.inTeam) {
				CardPropObj cardPropObj = calcCardProp(cardCalcObj);
				allCardPropMap.put(instCardId, cardPropObj);
			}
		}
		
		return allCardPropMap;
	}
	
	/**
	 * 获得卡牌处理
	 * @author mp
	 * @date 2014-6-26 下午4:29:39
	 * @param instPlayerId
	 * @param instCardId
	 * @Description
	 */
	public static void getCardHandle (int instPlayerId, InstPlayerCard instPlayerCard) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		
		//将获得的卡牌加入到计算属性的内存对象中
		CardCalcObj cardCalcObj = new CardCalcObj();
		cardCalcObj.setCardId(instPlayerCard.getCardId());//卡牌字典Id
		cardCalcObj.setInTeam(instPlayerCard.getInTeam());//是否在队伍中
		cardCalcObj.setAdvanceLevel(0);//进阶相关,暂未涉及
		cardCalcObj.setLevel(instPlayerCard.getLevel());//卡牌等级
		cardCalcObj.setTrainCalcMap(null);//培养相关,暂未涉及
		cardCalcObj.setEquipCalcMap(null);//装备相关,暂未涉及
		
		memCalcObj.getCardCalcMap().put(instPlayerCard.getId(), cardCalcObj);
	}
	
	/**
	 * 卡牌上阵处理
	 * @author mp
	 * @date 2014-6-26 上午9:58:16
	 * @Description
	 */
	public static void cardInTeamHandle(int instPlayerId, int instCardId) {
		
		//获取参数
		MemCalcObj memCalcObj = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getMemCalcObj();
		
		//将上阵状态修改为上阵
		memCalcObj.getCardCalcMap().get(instCardId).setInTeam(StaticCustomDict.inTeam);
	}
	
	/**
	 * 更换卡牌处理
	 * @author mp
	 * @date 2014-6-26 上午9:59:13
	 * @Description
	 */
	public static void convertCardHandle (int oldInstCardId, int newInstCardId) {
		
	}
	
	/**
	 * 卡牌升级处理
	 * @author mp
	 * @date 2014-6-26 上午10:03:39
	 * @param instCardId
	 * @param level
	 * @Description
	 */
	public static void cardUpgradeHandle (int instCardId, int level) {
		
	}
	
	/**
	 * 添加装备处理
	 * @author mp
	 * @date 2014-6-26 上午10:00:51
	 * @Description
	 */
	public static void addEquipHandle (int instCardId, int instEquipId) {
		
	}
	
	/**
	 * 卸掉装备处理
	 * @author mp
	 * @date 2014-6-26 上午10:01:51
	 * @param instCardId
	 * @param instEquipId
	 * @Description
	 */
	public static void dropEquiphandle (int instCardId, int instEquipId) {
		
	}
	
	/**
	 * 更换装备处理
	 * @author mp
	 * @date 2014-6-26 上午10:02:46
	 * @param instCardId
	 * @param oldInstEquipId
	 * @param newInstEquipId
	 * @Description
	 */
	public static void convertEquipHandle (int instCardId, int oldInstEquipId, int newInstEquipId) {
		
	}
	
	/**
	 * 私有方法：计算卡牌属性
	 * @author mp
	 * @date 2014-6-26 下午3:07:34
	 * @return
	 * @Description
	 */
	private static CardPropObj calcCardProp (CardCalcObj cardCalcObj) {
		
		//定义返回对象
		CardPropObj cardPropObj = new CardPropObj();
		
		//计算卡牌等级造成的影响
		FormulaUtil.calcPropByCardLevel(cardCalcObj, cardPropObj);
		
		//计算卡牌进阶造成的影响
//		FormulaUtil.calcPropByAdvance();
//		
//		//计算装备对上阵卡牌造成的影响
//		FormulaUtil.calcPropByEquip();
//		
//		//计算卡牌培养造成的影响
//		FormulaUtil.calcPropByTrain();
//		
//		//计算修行对上阵卡牌造成的影响
//		FormulaUtil.calcPropByPractice();
		
		//计算公会对上阵卡牌造成的影响
		FormulaUtil.calcPropByUnion();
		
		return cardPropObj;
	}
	
}
