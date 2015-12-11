package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictHoldStarGrade;
import com.huayi.doupo.base.model.DictHoldStarGradeReward;
import com.huayi.doupo.base.model.DictHoldStarRewardRefreshTimes;
import com.huayi.doupo.base.model.DictHoldStarStep;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerHoldStar;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.logic.UpStar;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.HoldStarUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 占星处理类
 * @author mp
 * @date 2015-12-3 下午7:24:08
 */
public class HoldStarHandler extends BaseHandler{
	
	/**
	 * 进入占星
	 * @author mp
	 * @date 2015-12-4 上午10:02:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoHoldStar (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		//给客户端同步占星数据
		List<InstPlayerHoldStar> holdStarList = getInstPlayerHoldStarDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(holdStarList.size() > 0){
			MessageData holdStarMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerHoldStar (holdStarMsgData, holdStarList);
			retMsgData.putMessageItem(InstPlayerHoldStar.class.getSimpleName(), holdStarMsgData.getMsgData());
		}
		
		int gradeId = 0;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
			if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
				gradeId = instPlayerHoldStar.getHoldStarGradeId();
				break;
			}
			if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
				gradeId = instPlayerHoldStar.getHoldStarGradeId();
				break;
			}
			if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
				gradeId = instPlayerHoldStar.getHoldStarGradeId();
				break;
			}
		}
		retMsgData.putIntItem("openGradeId", gradeId);//当天已开放的占星台   默认是0-都未开放,三个占星台都能进   ，其他为具体占星台Id
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 选择占星等级档
	 * @author mp
	 * @date 2015-12-4 上午10:03:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void selectHoldStarGrade (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//获取参数
		int gradeId = (Integer)msgMap.get("gradeId");//等级档次Id
		DictHoldStarGrade holdStarGrade = DictMap.dictHoldStarGradeMap.get(gradeId + "");
		
		//验证等级
		if (instPlayer.getLevel() < holdStarGrade.getOpenLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//验证是否有数据,没有数据,初始化数据,并add给客户端
		boolean sync = false;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId + " and holdStarGradeId = " + gradeId, instPlayerId);
		if (instPlayerHoldStarList.size() <= 0) {
			InstPlayerHoldStar instPlayerHoldStar = new InstPlayerHoldStar();
			instPlayerHoldStar.setInstPlayerId(instPlayerId);
			instPlayerHoldStar.setHoldStarGradeId(gradeId);
			instPlayerHoldStar.setStep(1);
			instPlayerHoldStar.setStarNum(0);
			instPlayerHoldStar.setHoldStarTimes(0);
			instPlayerHoldStar.setHoldStarTime("");
			instPlayerHoldStar.setHoldStarFreeRefreshedTimes(0);
			instPlayerHoldStar.setHoldStarNoFreeRefreshedTimes(0);
			instPlayerHoldStar.setHoldStarRefreshedTime("");
			instPlayerHoldStar.setRewardRefreshedTimes(0);
			instPlayerHoldStar.setRewardRefreshedTime("");
			instPlayerHoldStar.setUpStars(HoldStarUtil.refreshUpStars());
			instPlayerHoldStar.setDownStars(HoldStarUtil.refreshDownStars(gradeId, 1, instPlayerHoldStar.getUpStars()));
			instPlayerHoldStar.setRewards(HoldStarUtil.refreshReward(gradeId, 1));//0-人工刷新 1-系统刷新, 初始化时按系统刷新算
			instPlayerHoldStar.setSysRefreshTime(DateUtil.getCurrTime());
			getInstPlayerHoldStarDAL().add(instPlayerHoldStar, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerHoldStar, instPlayerHoldStar.getId(), instPlayerHoldStar.getResult(), syncMsgData);
			sync = true;
		} else {
			InstPlayerHoldStar instPlayerHoldStar = instPlayerHoldStarList.get(0);
			
			//如果有数据,再看是否需要刷新,如果需要刷新,初始化刷新数据,update给客户端
			if (!DateUtil.getYmdDate(instPlayerHoldStar.getSysRefreshTime()).equals(DateUtil.getYmdDate())) {
				instPlayerHoldStar.setStep(1);
				instPlayerHoldStar.setStarNum(0);
				instPlayerHoldStar.setHoldStarTimes(0);
				instPlayerHoldStar.setHoldStarTime("");
				instPlayerHoldStar.setHoldStarFreeRefreshedTimes(0);
				instPlayerHoldStar.setHoldStarNoFreeRefreshedTimes(0);
				instPlayerHoldStar.setHoldStarRefreshedTime("");
				instPlayerHoldStar.setRewardRefreshedTimes(0);
				instPlayerHoldStar.setRewardRefreshedTime("");
				instPlayerHoldStar.setUpStars(HoldStarUtil.refreshUpStars());
				instPlayerHoldStar.setDownStars(HoldStarUtil.refreshDownStars(gradeId, 1, instPlayerHoldStar.getUpStars()));
				instPlayerHoldStar.setRewards(HoldStarUtil.refreshReward(gradeId, 1));//0-自然刷新 1-系统刷新, 初始化时按系统刷新算
				instPlayerHoldStar.setSysRefreshTime(DateUtil.getCurrTime());
				getInstPlayerHoldStarDAL().update(instPlayerHoldStar, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStar, instPlayerHoldStar.getId(), instPlayerHoldStar.getResult(), syncMsgData);
				sync = true;
			}
		}
		
		//验证是否可以进入
		List<InstPlayerHoldStar> instPlayerHoldStarValidateList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarValidateList) {
			if (instPlayerHoldStar.getHoldStarGradeId() != gradeId) {
				//验证占星时间
				if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证占星刷新时间
				if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证奖励刷新时间
				if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
			}
		}
		
		if (sync) {
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 占星
	 * @author mp
	 * @date 2015-12-7 下午3:25:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void holdStar (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int gradeId = (Integer)msgMap.get("gradeId");//等级档次Id
		int pos = (Integer)msgMap.get("pos");//位置
		
		//验证占星资格(其他档次有占星刷新或奖励刷新就不允许其他的档次在占星或刷新)
		InstPlayerHoldStar instPlayerHoldStarObj = null;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
			if (instPlayerHoldStar.getHoldStarGradeId() != gradeId) {
				//验证占星时间
				if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证占星刷新时间
				if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证奖励刷新时间
				if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
			} else {
				instPlayerHoldStarObj = instPlayerHoldStar;
			}
		}
		
		//验证占星次数
		if (instPlayerHoldStarObj.getHoldStarTimes() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarCanHoldTimes)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_maxHold);
			return;
		}
		
		int downPosStarId = StringUtil.getSegValue(";" + instPlayerHoldStarObj.getDownStars() + ";", ";" + pos + "_");
		
		//是否能点亮上方星星,如果上方星星全亮,加额外星星,更新阶段,并刷新上方星星    格式：位置_星座id_状态
		List<UpStar> upStarList = new ArrayList<>();
		for (String upstars : instPlayerHoldStarObj.getUpStars().split(";")) {
			UpStar upStar = new UpStar();
			upStar.setPosition(ConvertUtil.toInt(upstars.split("_")[0]));
			upStar.setStarId(ConvertUtil.toInt(upstars.split("_")[1]));
			upStar.setState(ConvertUtil.toInt(upstars.split("_")[2]));
			upStarList.add(upStar);
		}
		
		int lightPos = 0;//能够点亮上方星星的位置，默认是0-没有点亮的位置
		//点亮星星  每次只点亮一个 (默认按位置从前往后点亮）
		for (int i = 0; i < upStarList.size(); i++) {
			UpStar upStar = upStarList.get(i);
			if (upStar.getStarId() == downPosStarId && upStar.getState() == 0) {
				upStar.setState(1);
				lightPos = upStar.getPosition();
				break;
			}
		}
		
		//计算点亮的星星个数,组织点亮后的上方星星列表
		StringBuilder holdedUpStarSb = new StringBuilder();
		int lightNum = 0;
		for (UpStar upStar : upStarList) {
			if (upStar.getState() == 1) {
				lightNum ++;
			}
			holdedUpStarSb.append(upStar.getPosition() + "_").append(upStar.getStarId() + "_").append(upStar.getState() + "").append(";");// 格式：位置_星座id_状态
		}
		
		if (lightNum >= 4) {//全点亮
			int extralAddStarNum = 0;
			for (DictHoldStarStep obj : DictList.dictHoldStarStepList) {
				if (obj.getHoldStarGradeId() == gradeId && obj.getStep() == instPlayerHoldStarObj.getStep()) {
					extralAddStarNum = obj.getRewardStarNum();
					break;
				}
			}
			instPlayerHoldStarObj.setStarNum(instPlayerHoldStarObj.getStarNum() + extralAddStarNum);//加额外星星
			instPlayerHoldStarObj.setStep(instPlayerHoldStarObj.getStep() + 1);//阶段加1
			instPlayerHoldStarObj.setUpStars(HoldStarUtil.refreshUpStars());//刷新上方星星
		} else {//未全点亮
			instPlayerHoldStarObj.setUpStars(StringUtil.noContainLastString(holdedUpStarSb.toString()));//更新上方星星状态
		}
		
		instPlayerHoldStarObj.setStarNum(instPlayerHoldStarObj.getStarNum() + 1);//获得星数+1
		instPlayerHoldStarObj.setHoldStarTimes(instPlayerHoldStarObj.getHoldStarTimes() + 1);//占星次数加1
		instPlayerHoldStarObj.setHoldStarTime(DateUtil.getCurrTime());//更新占星时间
		instPlayerHoldStarObj.setDownStars(HoldStarUtil.refreshDownStars(gradeId, instPlayerHoldStarObj.getStep(), instPlayerHoldStarObj.getUpStars()));//刷新下方星星
		getInstPlayerHoldStarDAL().update(instPlayerHoldStarObj, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStarObj, instPlayerHoldStarObj.getId(), instPlayerHoldStarObj.getResult(), syncMsgData);
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("lightPos", lightPos);//能够点亮上方星星的位置，默认是0-没有点亮时的值
		
		retMsgData.putIntItem("openGradeId", gradeId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap ,retMsgData);
	}
	
	/**
	 * 刷新下方星座
	 * @author mp
	 * @date 2015-12-4 上午10:03:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refreshStarZodiac (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int gradeId = (Integer)msgMap.get("gradeId");//等级档次Id
		
		//验证占星刷新资格(其他档次有占星刷新或奖励刷新就不允许其他的档次在占星或刷新)
		InstPlayerHoldStar instPlayerHoldStarObj = null;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
			if (instPlayerHoldStar.getHoldStarGradeId() != gradeId) {
				//验证占星时间
				if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证占星刷新时间
				if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证奖励刷新时间
				if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
			} else {
				instPlayerHoldStarObj = instPlayerHoldStar;
			}
		}
		
		//验证道具或元宝数是否足够(优先使用免费次数，其次是道具，最后是元宝)
		if (instPlayerHoldStarObj.getHoldStarFreeRefreshedTimes() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarFreeRefreshTimes)) {
			//使用免费刷新次数
			instPlayerHoldStarObj.setHoldStarFreeRefreshedTimes(instPlayerHoldStarObj.getHoldStarFreeRefreshedTimes() + 1);//更新占星免费已刷新次数
		} else {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.holdStarThing, instPlayerId);
			if (instPlayerThingList.size() <= 0) {
				//使用元宝
				if (instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshGoldNum)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				}
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshGoldNum), msgMap);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			} else {
				InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
				if (instPlayerThing.getNum() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshThingNum)) {
					//使用元宝
					if (instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshGoldNum)) {
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
						return;
					}
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshGoldNum), msgMap);
					getInstPlayerDAL().update(instPlayer, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				} else {
					//扣除道具
					ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, DictMapUtil.getSysConfigIntValue(StaticSysConfig.holdStarRefreshThingNum), syncMsgData, msgMap);
				}
			}
			instPlayerHoldStarObj.setHoldStarNoFreeRefreshedTimes(instPlayerHoldStarObj.getHoldStarNoFreeRefreshedTimes() + 1);//更新占星非免费已刷新次数
		}
		
		instPlayerHoldStarObj.setDownStars(HoldStarUtil.refreshDownStars(gradeId, instPlayerHoldStarObj.getStep(), instPlayerHoldStarObj.getUpStars()));//刷新下方星星列表
		instPlayerHoldStarObj.setHoldStarRefreshedTime(DateUtil.getCurrTime());//更新占星刷新时间
		getInstPlayerHoldStarDAL().update(instPlayerHoldStarObj, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStarObj, instPlayerHoldStarObj.getId(), instPlayerHoldStarObj.getResult(), syncMsgData);
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("openGradeId", gradeId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 刷新占星奖励
	 * @author mp
	 * @date 2015-12-4 上午10:03:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refreshStarReward (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int gradeId = (Integer)msgMap.get("gradeId");//等级档次Id
		
		//验证占星刷新资格(其他档次有占星刷新或奖励刷新就不允许其他的档次在占星或刷新)
		InstPlayerHoldStar instPlayerHoldStarObj = null;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
			if (instPlayerHoldStar.getHoldStarGradeId() != gradeId) {
				//验证占星时间
				if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证占星刷新时间
				if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证奖励刷新时间
				if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
			} else {
				instPlayerHoldStarObj = instPlayerHoldStar;
			}
		}
		
		//验证次数
		int refreshTimes = 0;
		int vipLevel = instPlayer.getVipLevel();
		List<DictVIP> vipList = DictList.dictVIPList;
		for (DictVIP dictVIP : vipList) {
			if (dictVIP.getLevel() == vipLevel) {
				refreshTimes = dictVIP.getHoldStarRewardRefreshTimes();
			}
		}
		if (instPlayerHoldStarObj.getRewardRefreshedTimes() >= refreshTimes) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_noVip);
			return;
		}
		
		//验证元宝数
		int needGold = 0;
		int currRefreshTimes = instPlayerHoldStarObj.getRewardRefreshedTimes() + 1;
		List<DictHoldStarRewardRefreshTimes> holdStarRewardRefreshTimeList = DictList.dictHoldStarRewardRefreshTimesList;
		for (DictHoldStarRewardRefreshTimes obj : holdStarRewardRefreshTimeList) {
			if (currRefreshTimes >= obj.getStarTimes() && currRefreshTimes <= obj.getEndTimes()) {
				needGold = obj.getNeedGold();
				break;
			}
		}
		if (instPlayer.getGold() < needGold) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, needGold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//刷新奖励
		instPlayerHoldStarObj.setRewardRefreshedTimes(currRefreshTimes);//更新刷新奖励次数
		instPlayerHoldStarObj.setRewardRefreshedTime(DateUtil.getCurrTime());//更新奖励刷新时间
		instPlayerHoldStarObj.setRewards(HoldStarUtil.refreshReward(gradeId, 0));//0-人工刷新 1-系统刷新, 初始化时按系统刷新算
		getInstPlayerHoldStarDAL().update(instPlayerHoldStarObj, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStarObj, instPlayerHoldStarObj.getId(), instPlayerHoldStarObj.getResult(), syncMsgData);
		
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("openGradeId", gradeId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 一键领取
	 * @author mp
	 * @date 2015-12-7 下午6:42:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void oneKeyGet (Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		//验证玩家在线状态
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		int gradeId = (Integer)msgMap.get("gradeId");//等级档次Id
		
		//验证一键领取资格(其他档次有占星刷新或奖励刷新就不允许其他的档次在占星或刷新)
		InstPlayerHoldStar instPlayerHoldStarObj = null;
		List<InstPlayerHoldStar> instPlayerHoldStarList = getInstPlayerHoldStarDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerHoldStar instPlayerHoldStar : instPlayerHoldStarList) {
			if (instPlayerHoldStar.getHoldStarGradeId() != gradeId) {
				//验证占星时间
				if (!instPlayerHoldStar.getHoldStarTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证占星刷新时间
				if (!instPlayerHoldStar.getHoldStarRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getHoldStarRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
				//验证奖励刷新时间
				if (!instPlayerHoldStar.getRewardRefreshedTime().equals("") && DateUtil.getYmdDate().equals(DateUtil.getYmdDate(instPlayerHoldStar.getRewardRefreshedTime()))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_haveHold);
					return;
				}
			} else {
				instPlayerHoldStarObj = instPlayerHoldStar;
			}
		}
		
		//验证星数
		int minStarNum = DictList.dictHoldStarGradeRewardList.get(0).getStarNum();
		if (instPlayerHoldStarObj.getStarNum() < minStarNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_holdStar_noGetThings);
			return;
		}
		
		String rewardList = "";
		//领取奖励     位置(根据位置找星数)_占星奖励Id(根据id找物品)_状态;
		String rewards = instPlayerHoldStarObj.getRewards();
		String holdRewardList = "";
		Map<String, String> thingMap = new HashMap<String, String>();
		for (String reward : rewards.split(";")) {
			int pos = ConvertUtil.toInt(reward.split("_")[0]);
			int rewardId = ConvertUtil.toInt(reward.split("_")[1]);
			int state = ConvertUtil.toInt(reward.split("_")[2]);
			DictHoldStarGradeReward holdStarGradeReward = DictMap.dictHoldStarGradeRewardMap.get(rewardId + "");
			if (holdStarGradeReward.getStarNum() <= instPlayerHoldStarObj.getStarNum() && state == 0) {
				rewardList += holdStarGradeReward.getThing() + ";";
				ThingUtil.groupThingMap(thingMap, ConvertUtil.toInt(holdStarGradeReward.getThing().split("_")[0]), ConvertUtil.toInt(holdStarGradeReward.getThing().split("_")[1]), ConvertUtil.toInt(holdStarGradeReward.getThing().split("_")[2]));
				state = 1;
			}
			holdRewardList += pos + "_" + rewardId + "_" + state + ";";
		}
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		
		instPlayerHoldStarObj.setRewards(StringUtil.noContainLastString(holdRewardList));
		getInstPlayerHoldStarDAL().update(instPlayerHoldStarObj, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerHoldStarObj, instPlayerHoldStarObj.getId(), instPlayerHoldStarObj.getResult(), syncMsgData);
		
		MessageData retMsgData = new MessageData();
		if (rewardList.length() > 0) {
			retMsgData.putStringItem("1", StringUtil.noContainLastString(rewardList));//奖励物品  tableTypeId_tableFieldId_value;
		} else {
			retMsgData.putStringItem("1", "");//奖励物品  tableTypeId_tableFieldId_value;
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
}
