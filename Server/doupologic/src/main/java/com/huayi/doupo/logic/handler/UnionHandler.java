package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictUnionBox;
import com.huayi.doupo.base.model.DictUnionBuild;
import com.huayi.doupo.base.model.DictUnionStore;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstUnion;
import com.huayi.doupo.base.model.InstUnionApply;
import com.huayi.doupo.base.model.InstUnionBox;
import com.huayi.doupo.base.model.InstUnionDynamic;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUnionStore;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.wordfilter.WordFilterUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.UnionUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerFightValueMapUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;


public class UnionHandler  extends BaseHandler{
	
	/**
	 * 创建联盟
	 * @author hzw
	 * @date 2015-7-15下午7:30:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		String unionName = (String)msgMap.get("unionName"); //名字
		String unionManifesto = (String)msgMap.get("unionManifesto"); //宣言
		
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if(instUnionMemberList.size() > 0 && instUnionMemberList.get(0).getInstUnionId() != 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inUnion);
			return;
		}
		if(instPlayer.getLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionPlayerLevel)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		if(instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionGold)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//验证名字是否含有敏感字符
		if(WordFilterUtil.filterText(unionName, '*').getBadWords().length() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_filtername);
			return ;
		}
		
		//验证名字是否含特殊字符 类似空格 
		if(unionName.contains(" ") || unionName.contains("\\") || unionName.contains("/") || unionName.contains("|") || unionName.contains("\"") || unionName.contains("\'")){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_playerName_Rule);
			return ;
		}
		
		List<InstUnion> instUnionList2 = getInstUnionDAL().getList(" name = '" + unionName + "'", 0);
		if(instUnionList2.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUnionName);
			return;
		}
		
		//创建联盟
		InstUnion instUnion = UnionUtil.addInstUnion(instPlayerId, unionName, unionManifesto);
		int instUnionId = instUnion.getId();
		
		//扣除钻石
//		instPlayer.setGold(instPlayer.getGold() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionGold));
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionGold), msgMap);
//		instPlayer.setInstUnionId(instUnionId);
//		instPlayer.setUnionGradeId(1);
//		instPlayer.setUnionHeaderTime(DateUtil.getFormateDate(BaseHandler.getNowTimeMill()));
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//添加联盟成员列表
		if(instUnionMemberList.size() > 0){
			InstUnionMember instUnionMember = instUnionMemberList.get(0);
			instUnionMember.setInstUnionId(instUnionId);
			instUnionMember.setUnionGradeId(1);
			getInstUnionMemberDAL().update(instUnionMember, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
		}else{
			InstUnionMember instUnionMember = UnionUtil.addInstUnionMember(instUnionId, instPlayerId, 1);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
		}
		
		//删除他申请的其他公会的申请记录
		List<InstUnionApply> instPlayerUnionApplyList = getInstUnionApplyDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		for(InstUnionApply obj : instPlayerUnionApplyList){
			MessageData syncMsgData2 = new MessageData();
			getInstUnionApplyDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData2);
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			if(player != null){
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData2);
			}
		}
		
		//维护玩家联盟关系表
		ParamConfig.unionMap.put(instUnion.getId(), instUnion.getName());
		ParamConfig.unionPlayer.put(instPlayerId, instUnionId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
		
	}
	
	/**
	 * 下发联盟信息
	 * @author hzw
	 * @date 2015-7-16下午3:38:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionDetail(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
		
		MessageData retMsgData = new MessageData();
		
		//联盟信息
		MessageData msgData = new MessageData();
		msgData.putStringItem("2", instUnion.getName());//名字
		msgData.putIntItem("3", instUnion.getExp());//经验
		msgData.putIntItem("4", instUnion.getLevel());//等级
		msgData.putIntItem("5", instUnion.getGradeTypeId());//等级
		msgData.putIntItem("6", instUnion.getMaxMember());//最大成员数
		msgData.putIntItem("7", instUnion.getCurrentMember());//当前成员数
		msgData.putStringItem("10", instUnion.getUnionNotice());//公会公告
		msgData.putStringItem("11", instUnion.getUnionManifesto());//公会宣言
		msgData.putIntItem("12", instUnion.getPlan());//进度
		msgData.putIntItem("13", instUnion.getConstructionNum());//捐献人数
		retMsgData.putMessageItem("unionDetail", msgData.getMsgData());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
	}
	
	/**
	 * 下发成员信息
	 * @author hzw
	 * @date 2015-7-20下午4:16:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionMember(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		MessageData retMsgData = new MessageData();
		
		//联盟成员信息
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instUnionId = " + instUnionMember.getInstUnionId() + " order by instPlayerId ", 0);
		List<Map<String,Object>> playerList = getInstUnionMemberDAL().sqlHelper("SELECT a.`name`, a.`level`, c.lastLeaveTime, c.onlineState, a.headerCardId, a.vipLevel from Inst_Player a,Inst_Union_Member b, " +
				"Inst_User c where a.id = b.instPlayerId and a.openId = c.openId and  b.instUnionId = " + instUnionMember.getInstUnionId() + " order by a.id");
		MessageData testMsgData = new MessageData();
		String datetime = DateUtil.getCurrTime();
		for(int i = 0; i < instUnionMemberList.size(); i++){
			MessageData msgData2 = new MessageData();
			InstUnionMember obj = instUnionMemberList.get(i);
			msgData2.putIntItem("1", obj.getId());
//			msgData2.putIntItem("2", instUnionMember.getInstUnionId());
			msgData2.putIntItem("3", obj.getInstPlayerId());
			msgData2.putIntItem("4", obj.getUnionGradeId());
//			msgData2.putIntItem("5", obj.getSumOffer());
			//加上累计贡献逻辑
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + obj.getInstPlayerId() + " and properties = '"+StaticBigTable.unionSaveOffer+"'", 0);
			if (instPlayerBigTableList.size() <= 0) {
/*				InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
				instPlayerBigTable.setInstPlayerId(obj.getInstPlayerId());
				instPlayerBigTable.setProperties(StaticBigTable.unionSaveOffer);
				instPlayerBigTable.setValue1(obj.getSumOffer() + "");
				getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
				msgData2.putIntItem("5", obj.getSumOffer());*/
				
				InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
				instPlayerBigTable.setInstPlayerId(obj.getInstPlayerId());
				instPlayerBigTable.setProperties(StaticBigTable.unionSaveOffer);
				instPlayerBigTable.setValue1("0");
				getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
				msgData2.putIntItem("5", 0);
				
			} else {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				int oldSave = ConvertUtil.toInt(instPlayerBigTable.getValue1());
				msgData2.putIntItem("5", oldSave);
/*				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				int oldSave = ConvertUtil.toInt(instPlayerBigTable.getValue1());
				if (oldSave < obj.getSumOffer()) {
					msgData2.putIntItem("5", obj.getSumOffer());
				} else {
					msgData2.putIntItem("5", oldSave);
				}*/
			}
			
			msgData2.putIntItem("6", obj.getCurrentOffer());
			msgData2.putStringItem("10", (String)playerList.get(i).get("name"));
			msgData2.putIntItem("11", (int)playerList.get(i).get("level"));
			if((int)playerList.get(i).get("onlineState") == 0){
				msgData2.putLongItem("12", DateUtil.getMillSecond(datetime) - DateUtil.getMillSecond((String)playerList.get(i).get("lastLeaveTime")));
			}else{
				//不等于0的时候还有很多状态 1-在线  2-冻结 3-禁言,只有等于1的时候才是在线，其他（冻结，禁言）都显示离线
				if ((int)playerList.get(i).get("onlineState") == 1) {
					msgData2.putLongItem("12", 0);
				} else {
					msgData2.putLongItem("12", DateUtil.getMillSecond(datetime) - DateUtil.getMillSecond((String)playerList.get(i).get("lastLeaveTime")));
				}
			}
			msgData2.putIntItem("13", (int)playerList.get(i).get("headerCardId"));
			msgData2.putIntItem("14", (int)playerList.get(i).get("vipLevel"));
			msgData2.putIntItem("15", PlayerFightValueMapUtil.getPlayerFightValue(obj.getInstPlayerId())); //战力值
			testMsgData.putMessageItem(obj.getId() + "", msgData2.getMsgData());
		}
		retMsgData.putMessageItem("unionMember", testMsgData.getMsgData());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
	}
	
	/**
	 * 联盟排行
	 * @author hzw
	 * @date 2015-7-17下午2:11:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionRank(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int pageNum = (int) msgMap.get("pageNum");
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		
		//判断页码必须大于0
		if (pageNum <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return ;
		}
		
		int size = DictMapUtil.getSysConfigIntValue(StaticSysConfig.onePageNum);
		pageNum = (pageNum - 1) * size;
		List<InstUnion> instUnionList = getInstUnionDAL().getList(" 1=1 order by level desc, exp desc, createTime limit " + pageNum + ", " + size, 0);
		
		MessageData retMsgData = new MessageData();
		int index = 1 * (pageNum + 1);
		MessageData testMsgData = new MessageData();
		for(InstUnion instUnion : instUnionList){
			//联盟信息
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", instUnion.getId());//Id
			msgData.putStringItem("2", instUnion.getName());//名字
			msgData.putIntItem("3", instUnion.getExp());//经验
			msgData.putIntItem("4", instUnion.getLevel());//等级
			msgData.putIntItem("5", instUnion.getGradeTypeId());//职位类型
			msgData.putIntItem("6", instUnion.getMaxMember());//最大成员数
			msgData.putIntItem("7", instUnion.getCurrentMember());//当前成员数
			msgData.putStringItem("9", instUnion.getCreateTime());//当前成员数
			msgData.putStringItem("10", instUnion.getUnionNotice());//公会公告
			msgData.putStringItem("11", instUnion.getUnionManifesto());//公会宣言
			msgData.putStringItem("15", getInstPlayerDAL().getModel(instUnion.getHeadInstPlayerId(), 0).getName());//团长玩家名字
			msgData.putIntItem("16", index++);//联盟名次
			testMsgData.putMessageItem(instUnion.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("unionRank", testMsgData.getMsgData());
		
		if(pageNum == 0 && instUnionMemberId != 0){
			int myRank = 0;
			instUnionList = getInstUnionDAL().getList(" 1=1 order by level desc, exp desc, createTime ", 0);
			InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
			for(InstUnion obj : instUnionList){
				myRank++;
				if(obj.getId() == instUnionMember.getInstUnionId()){
					break;
				}
			}
			MessageData msg = new MessageData();
			msg.putIntItem("1", myRank);//自己联盟排名
			retMsgData.putMessageItem("myRank", msg.getMsgData());
		}
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		
	}
	
	/**
	 * 申请加入联盟
	 * @author hzw
	 * @date 2015-7-17下午2:58:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void applyAddUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instUnionId = (Integer)msgMap.get("instUnionId");
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if(instUnionMemberList.size() > 0 && instUnionMemberList.get(0).getInstUnionId() != 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inUnion);
			return;
		}
		if(instUnionMemberList.size() > 0 && instUnionMemberList.get(0).getInstUnionId() == 0 && DateUtil.isSameDay(instUnionMemberList.get(0).getUpdateTime(), DateUtil.getCurrTime(), DateFormat.YMDHMS)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUnionApply);
			return;
		}
		
		//验证联盟人数
		List<InstUnionApply> instUnionApplyList = getInstUnionApplyDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(instUnionApplyList.size() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.applyAddUnionNum)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApplyAddUnionNum);
			return;
		}
		if (instUnion.getCurrentMember() >= instUnion.getMaxMember() ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApplyAddUnion);
			return;
		}
		
		//添加联盟申请列表
		InstUnionApply instUnionApply = UnionUtil.addInstUnionApply(instUnionId, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instUnionApply, instUnionApply.getId(), instUnionApply.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 获取申请
	 * @author hzw
	 * @date 2015-7-17下午3:24:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void obtainApply(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instUnionMember.getUnionGradeId() > 2 ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
			return;
		}
		
		List<Map<String,Object>> playerList = getInstUnionApplyDAL().sqlHelper("SELECT b.instPlayerId, a.`name`, a.`level`, a.headerCardId, b.id, a.vipLevel from Inst_Player a,Inst_Union_Apply b " +
				"where a.id = b.instPlayerId and b.instUnionrId = " + instUnionMember.getInstUnionId() + " order by a.`level`");
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		for(int i = 0; i < playerList.size(); i++){
			MessageData msgData = new MessageData();
			msgData.putStringItem("1", (String)playerList.get(i).get("name"));
			msgData.putIntItem("2", (int)playerList.get(i).get("level"));
			msgData.putIntItem("3", (int)playerList.get(i).get("headerCardId"));
			msgData.putIntItem("4", (int)playerList.get(i).get("id"));
			msgData.putIntItem("5", (int)playerList.get(i).get("vipLevel"));
			msgData.putIntItem("6", PlayerFightValueMapUtil.getPlayerFightValue((int)playerList.get(i).get("instPlayerId"))); //战力值
			testMsgData.putMessageItem(i + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("unionApply", testMsgData.getMsgData());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 同意申请
	 * @author hzw
	 * @date 2015-7-17下午5:34:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void agreeApply(HashMap<String, Object> msgMap,String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		synchronized(ParamConfig.arenaRankLock){
			MessageData syncMsgData = new MessageData();
			int instUnionApplyId = (Integer)msgMap.get("instUnionApplyId");
			InstUnionApply instUnionApply = getInstUnionApplyDAL().getModel(instUnionApplyId, 0);
			if(instUnionApply == null){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inUnion);
				return;
			}
			
			List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instUnionApply.getInstPlayerId(), 0);
			if(instUnionMemberList.size() > 0 && instUnionMemberList.get(0).getInstUnionId() != 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_inUnion);
				return;
			}
			
			int instUnionId = instUnionApply.getInstUnionrId();
			InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
			//验证联盟人数
			if (instUnion.getCurrentMember() >= instUnion.getMaxMember() ) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApplyAddUnion);
				return;
			}
			
			//添加联盟成员列表
			if(instUnionMemberList.size() > 0){
				InstUnionMember instUnionMember = instUnionMemberList.get(0);
				instUnionMember.setInstUnionId(instUnionId);
				instUnionMember.setUnionGradeId(3);
				instUnionMember.setInsertTime(DateUtil.getCurrTime());
				getInstUnionMemberDAL().update(instUnionMember, instUnionApply.getInstPlayerId());
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
			}else{
				InstUnionMember instUnionMember = UnionUtil.addInstUnionMember(instUnionId, instUnionApply.getInstPlayerId(), 3);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
			}
			
			//删除联盟申请列表
			List<InstUnionApply> instUnionApplyList = getInstUnionApplyDAL().getList(" instPlayerId = " + instUnionApply.getInstPlayerId(), 0);
			for(int i = 0; i < instUnionApplyList.size(); i ++){
				getInstUnionApplyDAL().deleteById(instUnionApplyList.get(i).getId(), 0);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instUnionApplyList.get(i), instUnionApplyList.get(i).getId(), "", syncMsgData);
			}
			
			//更新联盟当前人员数
			instUnion.setCurrentMember(instUnion.getCurrentMember() + 1);
			getInstUnionDAL().update(instUnion, 0);
			
			//维护玩家联盟关系表
			ParamConfig.unionPlayer.put(instUnionApply.getInstPlayerId(), instUnionId);
			
			//更新联盟动态表
			UnionUtil.addInstUnionDynamic(instUnionId, instUnionApply.getInstPlayerId(), 0);
			
			if (instUnion.getCurrentMember() >= instUnion.getMaxMember() ) {
				List<InstUnionApply> instUnionApplyList2 = getInstUnionApplyDAL().getList(" instUnionrId = " + instUnionId, 0);
				for(InstUnionApply obj : instUnionApplyList2){
					getInstUnionApplyDAL().deleteById(obj.getId(), 0);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
				}
			}
			
			Player player = PlayerMapUtil.getPlayerByPlayerId(instUnionApply.getInstPlayerId());
			if(player != null){
				PlayerUtil.addPlayerMail(player, instUnionApply.getInstPlayerId(), "恭喜您，成为" + instUnion.getName() + "联盟的一员，快去建设联盟换取贡献度吧。", 0, 0, "加入联盟申请通过", 4);
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	}
	
	/**
	 * 取消/拒绝申请
	 * @author hzw
	 * @date 2015-7-17下午6:18:28
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refuseApply(HashMap<String, Object> msgMap,String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionApplyId = (Integer)msgMap.get("instUnionApplyId");
		InstUnionApply instUnionApply = getInstUnionApplyDAL().getModel(instUnionApplyId, 0);
		
		if(instUnionApply != null){
			//删除联盟申请列表
			getInstUnionApplyDAL().deleteById(instUnionApplyId, 0);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instUnionApply, instUnionApply.getId(), "", syncMsgData);
			
			Player player = PlayerMapUtil.getPlayerByPlayerId(instUnionApply.getInstPlayerId());
			if(player != null){
				if(instUnionApply.getInstPlayerId() != instPlayerId){
					//说明是被拒绝
					InstUnion instUnion = getInstUnionDAL().getModel(instUnionApply.getInstUnionrId(), 0);
					PlayerUtil.addPlayerMail(player, instUnionApply.getInstPlayerId(), "很抱歉，您加入" + instUnion.getName() + "联盟的申请，被拒绝。", 0, 0, "申请加入联盟被拒绝", 4);
				}
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
		}
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 踢出/退出联盟
	 * @author hzw
	 * @date 2015-7-17下午7:32:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void exitUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		MessageData syncMsgData = new MessageData();
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");
		int type = (Integer)msgMap.get("type");//-1-踢出 -2-退出
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, 0);
		int instPlayerIds = instUnionMember.getInstPlayerId();
		int instUnionId = instUnionMember.getInstUnionId();
		if(instUnionMember != null && instUnionMember.getInstUnionId() != 0){
			if(type == -1){
				int instPlayerId = getInstPlayerId(channelId);
				
				if (instPlayerId == 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
					return;
				}
				
				if(getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0).getUnionGradeId() == 3){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
					return;
				}
			}
			
			//更新联盟成员
			instUnionMember.setCurrentOffer(0);
			instUnionMember.setUnionBuildId(0);
			instUnionMember.setUnionGradeId(0);
			instUnionMember.setInstUnionId(0);
			getInstUnionMemberDAL().update(instUnionMember, 0);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
			
			//更新联盟当前人员数
			InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
			instUnion.setCurrentMember(instUnion.getCurrentMember() - 1);
			getInstUnionDAL().update(instUnion, 0);
			
			//更新联盟动态表
			UnionUtil.addInstUnionDynamic(instUnion.getId(), instUnionMember.getInstPlayerId(), type);
			
			//删除联盟宝箱信息
		    getInstUnionBoxDAL().deleteByWhere(" instPlayerId =  " + instUnionMember.getInstPlayerId());
					
			//删除联盟商店相关信息
			getInstUnionStoreDAL().deleteByWhere(" instPlayerId =  " + instUnionMember.getInstPlayerId());
			
			//维护玩家联盟关系表
			ParamConfig.unionPlayer.remove(instUnionMember.getInstPlayerId());
			
			Player player = PlayerMapUtil.getPlayerByPlayerId(instUnionMember.getInstPlayerId());
			if(player != null){
				if(instUnionMember.getInstPlayerId() != getInstPlayerId(channelId)){
					//说明是被踢出的
					PlayerUtil.addPlayerMail(player, instUnionMember.getInstPlayerId(), "很抱歉，您已被踢出" + instUnion.getName() + "联盟，24小时内无法加入其它联盟。", 0, 0, "您已被踢出联盟", 4);
				}
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
			//将累计贡献清零
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerIds + " and properties = '"+StaticBigTable.unionSaveOffer+"'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				instPlayerBigTable.setValue1("0");
				getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			}
		}
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 编写联盟公告/宣言
	 * @author hzw
	 * @date 2015-7-18上午10:40:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void writeUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int type = (Integer)msgMap.get("type");//1-公告 2-宣言
		String detail = (String)msgMap.get("detail");//信息
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instUnionMember.getUnionGradeId() > 2 ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
			return;
		}
		
		//更新联盟公告/宣言
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
		if(type == 1){
			instUnion.setUnionNotice(detail);
		}else{
			instUnion.setUnionManifesto(detail);
		}
		getInstUnionDAL().update(instUnion, 0);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 任命
	 * @author hzw
	 * @date 2015-7-18下午12:03:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void appointUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int unionGradeId = (Integer)msgMap.get("unionGradeId");//职位Id
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, 0);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		InstUnionMember obj = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		if (obj.getUnionGradeId() != 1 ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
			return;
		}
		if(instUnionMember.getInstUnionId() != obj.getInstUnionId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
			return;
		}
		if(unionGradeId == 2 && getInstUnionMemberDAL().getList(" instUnionId = " + obj.getInstUnionId() + " and unionGradeId = 2 ", 0).size() >= 2){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notAppointUnion);
			return;
		}
		//任命
		instUnionMember.setUnionGradeId(unionGradeId);
		getInstUnionMemberDAL().update(instUnionMember, instUnionMember.getInstPlayerId());
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
		
		if(unionGradeId == 1){
			InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
			instUnion.setHeadInstPlayerId(instUnionMember.getInstPlayerId());
			getInstUnionDAL().update(instUnion, 0);
			
			MessageData syncMsgData2 = new MessageData();
			obj.setUnionGradeId(3);
			getInstUnionMemberDAL().update(obj, obj.getInstPlayerId());
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(), syncMsgData2);
			MessageUtil.sendSyncMsg(channelId, syncMsgData2);
		}
		
		
		Player player = PlayerMapUtil.getPlayerByPlayerId(instUnionMember.getInstPlayerId());
		if(player != null){
			MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
		}
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 解散联盟
	 * @author hzw
	 * @date 2015-7-18下午3:39:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dissolveUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getUnionGradeId() != 1 ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notApply);
			return;
		}
		
		int instUnionId = instUnionMember.getInstUnionId();
		//删除联盟成员
		List<InstUnionMember> objList = getInstUnionMemberDAL().getList(" instUnionId = " + instUnionId, 0);
		for(InstUnionMember obj : objList){
			MessageData syncMsgData = new MessageData();
			obj.setCurrentOffer(0);
			obj.setUnionBuildId(0);
			obj.setUnionGradeId(0);
			obj.setInstUnionId(0);
			getInstUnionMemberDAL().update(obj, obj.getInstPlayerId());
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, obj, obj.getId(), obj.getResult(), syncMsgData);
			
			Player player = PlayerMapUtil.getPlayerByPlayerId(obj.getInstPlayerId());
			if(player != null){
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
			
			//维护玩家联盟关系表
			ParamConfig.unionPlayer.remove(instUnionMember.getInstPlayerId());
			
			//晴掉本联盟的贡献
			int instPlayerIds = obj.getInstPlayerId();
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerIds + " and properties = '"+StaticBigTable.unionSaveOffer+"'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				instPlayerBigTable.setValue1("0");
				getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			}
			
		}
		
		//删除该联盟申请
		List<InstUnionApply> instUnionApplyList = getInstUnionApplyDAL().getList(" instUnionrId = " + instUnionId, 0);
		for(InstUnionApply obj : instUnionApplyList){
			MessageData syncMsgData = new MessageData();
			getInstUnionApplyDAL().deleteById(obj.getId(), 0);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
			Player player = PlayerMapUtil.getPlayerByPlayerId(obj.getInstPlayerId());
			if(player != null){
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
		}
		
		//删除该联盟动态
		getInstUnionDynamicDAL().deleteByWhere(" instUnionId =  " + instUnionId);
		
		//删除联盟宝箱信息
		getInstUnionBoxDAL().deleteByWhere(" instUnionId =  " + instUnionId);
		
		//删除联盟商店相关信息
		getInstUnionStoreDAL().deleteByWhere(" instUnionId =  " + instUnionId);
		
		//删除该联盟信息
		getInstUnionDAL().deleteById(instUnionId, 0);
		
		//删除联盟Map记录
		if (ParamConfig.unionMap.containsKey(instUnionId)) {
			ParamConfig.unionMap.remove(instUnionId);
		}
		
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 联盟建设
	 * @author hzw
	 * @date 2015-7-20下午5:31:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionBuild(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int unionBuildId = (Integer)msgMap.get("unionBuildId");//联盟捐献Id
		
		DictUnionBuild dictUnionBuild = DictMap.dictUnionBuildMap.get(unionBuildId + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instUnionMember.getUnionBuildId() != 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUnionBuild);
			return ;
		}
		if(dictUnionBuild.getBuyType() == 1 && ConvertUtil.toInt(instPlayer.getCopper()) < dictUnionBuild.getBuyValue()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return ;
		}
		if(dictUnionBuild.getBuyType() == 2 && instPlayer.getGold() < dictUnionBuild.getBuyValue()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return ;
		}
		
		MessageData syncMsgData = new MessageData();
		int instUnionId = instUnionMember.getInstUnionId();
		
		//更新联盟经验，进度，当日建设人数
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
		Map<String, Integer> retMap = UnionUtil.calcLevel(instUnion.getLevel(), instUnion.getExp() + dictUnionBuild.getExp());
		int level = retMap.get("level");
		int exp = retMap.get("exp");
		if(level > instUnion.getLevel()){
			instUnion.setLevel(level);
			instUnion.setMaxMember(DictMap.dictUnionLevelPrivMap.get(level + "").getNum());
		}
		instUnion.setExp(exp);
		instUnion.setPlan(instUnion.getPlan() + dictUnionBuild.getPlan());
		instUnion.setConstructionNum(instUnion.getConstructionNum() + 1);
		getInstUnionDAL().update(instUnion, 0);
		
		//更新自己的联盟贡献
		instUnionMember.setCurrentOffer(instUnionMember.getCurrentOffer() + dictUnionBuild.getContribution());
		instUnionMember.setSumOffer(instUnionMember.getSumOffer() + dictUnionBuild.getContribution());
		instUnionMember.setUnionBuildId(unionBuildId);
		getInstUnionMemberDAL().update(instUnionMember, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
		
		//加上累计贡献逻辑
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.unionSaveOffer+"'", 0);
		if (instPlayerBigTableList.size() <= 0) {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(instPlayerId);
			instPlayerBigTable.setProperties(StaticBigTable.unionSaveOffer);
			instPlayerBigTable.setValue1(dictUnionBuild.getContribution() + "");
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		} else {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			int oldSave = ConvertUtil.toInt(instPlayerBigTable.getValue1());
			int currSave = oldSave + dictUnionBuild.getContribution();
//			if (currSave < instUnionMember.getSumOffer()) {
//				instPlayerBigTable.setValue1(instUnionMember.getSumOffer() + "");
//			} else {
//				instPlayerBigTable.setValue1(currSave + "");
//			}
			instPlayerBigTable.setValue1(currSave + "");
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
		}
		
		//更新联盟动态表
		UnionUtil.addInstUnionDynamic(instUnionId, instPlayerId, unionBuildId);
		
		//扣除银币，元宝
		if(dictUnionBuild.getBuyType() == 1){
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - dictUnionBuild.getBuyValue()) + "");
		}else{
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, dictUnionBuild.getBuyValue(), msgMap);
		}
		
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取申请联盟列表
	 * @author hzw
	 * @date 2015-7-21下午5:26:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void applyUnion(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		List<InstUnion> instUnionList = getInstUnionDAL().getList(" id in (SELECT instUnionrId from Inst_Union_Apply where instPlayerId = "+ instPlayerId +")", 0);
		
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		for(InstUnion instUnion : instUnionList){
			//联盟信息
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", instUnion.getId());//Id
			msgData.putStringItem("2", instUnion.getName());//名字
			msgData.putIntItem("3", instUnion.getExp());//经验
			msgData.putIntItem("4", instUnion.getLevel());//等级
			msgData.putIntItem("5", instUnion.getGradeTypeId());//职位类型
			msgData.putIntItem("6", instUnion.getMaxMember());//最大成员数
			msgData.putIntItem("7", instUnion.getCurrentMember());//当前成员数
			msgData.putStringItem("9", instUnion.getCreateTime());//当前成员数
			msgData.putStringItem("10", instUnion.getUnionNotice());//公会公告
			msgData.putStringItem("11", instUnion.getUnionManifesto());//公会宣言
			msgData.putStringItem("15", getInstPlayerDAL().getModel(instUnion.getHeadInstPlayerId(), 0).getName());//团长玩家名字
			testMsgData.putMessageItem(instUnion.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("applyUnion", testMsgData.getMsgData());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 下发联盟动态
	 * @author hzw
	 * @date 2015-7-22上午11:16:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionDynamic(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int type = (Integer)msgMap.get("type");//1-建设页面 2-动态
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		List<InstUnionDynamic> instUnionDynamicList = getInstUnionDynamicDAL().getList(" instUnionId = " +instUnionMember.getInstUnionId()+ " ORDER BY insertTime desc", 0);
		
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		if(type == 2){
			String insertTime = "";
			
			//用于清除超过的50条数据
			if(instUnionDynamicList.size() > 50){
				for(int i=0;i<instUnionDynamicList.size();i++){
					if(i > 49){
						
						instUnionDynamicList.remove(i);
						i--;
					}  
				}
			}
			
			//删除超过的50条数据
			if(!insertTime.equals("")){
				getInstUnionDynamicDAL().deleteByWhere(" insertTime <= " + insertTime);
			}
			
			//组织下发联盟动态
			for(InstUnionDynamic instUnionDynamic : instUnionDynamicList){
				//联盟信息
				MessageData msgData = new MessageData();
				msgData.putStringItem("3", getInstPlayerDAL().getModel(instUnionDynamic.getInstPlayerId(), 0).getName());
				msgData.putIntItem("4", instUnionDynamic.getUnionBuild());
				msgData.putStringItem("6", instUnionDynamic.getInsertTime());
				testMsgData.putMessageItem(instUnionDynamic.getId() + "", msgData.getMsgData());
			}
		}else{
			for(InstUnionDynamic instUnionDynamic : instUnionDynamicList){
				if(instUnionDynamic.getUnionBuild() > 0){
					MessageData msgData = new MessageData();
					msgData.putStringItem("3", getInstPlayerDAL().getModel(instUnionDynamic.getInstPlayerId(), 0).getName());
					msgData.putIntItem("4", instUnionDynamic.getUnionBuild());
					msgData.putStringItem("6", instUnionDynamic.getInsertTime());
					testMsgData.putMessageItem(instUnionDynamic.getId() + "", msgData.getMsgData());
					break;
				}
			}
		}
		retMsgData.putMessageItem("unionDynamic", testMsgData.getMsgData());
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 联盟宝箱
	 * @author hzw
	 * @date 2015-7-22上午11:16:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionBox(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int instUnionBoxId = (Integer)msgMap.get("instUnionBoxId");//联盟宝箱Id
		int unionBoxId = (Integer)msgMap.get("unionBoxId");//联盟宝箱字典Id
		
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
		DictUnionBox dictUnionBox = DictMap.dictUnionBoxMap.get(unionBoxId + "");
		MessageData syncMsgData = new MessageData();
		
		if(instUnion.getPlan() < dictUnionBox.getPlan()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notWelfareBox);
			return ;
		}
		
		if(instUnionBoxId == 0){
			InstUnionBox instUnionBox = UnionUtil.addInstUnionBox(instUnionMember.getInstUnionId(), instPlayerId, unionBoxId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instUnionBox, instUnionBox.getId(), instUnionBox.getResult(), syncMsgData);
		}else{
			InstUnionBox instUnionBox = getInstUnionBoxDAL().getModel(instUnionBoxId, instPlayerId);
			//验证是否已经领取
			if (instUnionBox.getInstPlayerId() != instPlayerId ) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(instUnionBox.getUnionBoxs().contains(unionBoxId + "")){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
				return ;
			}
			if(instUnionBox.getUnionBoxs().equals("")){
				instUnionBox.setUnionBoxs(unionBoxId + "");
			}else{
				instUnionBox.setUnionBoxs(instUnionBox.getUnionBoxs() + ";" + unionBoxId);
			}
			getInstUnionBoxDAL().update(instUnionBox, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionBox, instUnionBox.getId(), instUnionBox.getResult(), syncMsgData);
		}
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		String things = dictUnionBox.getThings();
		String thing[] = things.split(";");
		for(String str : thing){
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 联盟商店数据下发
	 * @author hzw
	 * @date 2015-7-22上午11:16:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void unionStore(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionStoreId = (Integer)msgMap.get("instUnionStoreId");//联盟商店实例Id
		MessageData retMsgData = new MessageData();
		InstUnionStore instUnionStore = null;
		if(instUnionStoreId == 0){
			List<InstUnionStore> instUnionStoreList = getInstUnionStoreDAL().getList(" instPlayerId = " + instPlayerId, 0);
			if(instUnionStoreList.size() > 0){
				instUnionStore = instUnionStoreList.get(0);
			}
		}else{
			instUnionStore = getInstUnionStoreDAL().getModel(instUnionStoreId, 0);
			if(instUnionStore == null){
				List<InstUnionStore> instUnionStoreList = getInstUnionStoreDAL().getList(" instPlayerId = " + instPlayerId, 0);
				if(instUnionStoreList.size() > 0){
					instUnionStore = instUnionStoreList.get(0);
				}
			}
		}
		if(instUnionStore != null){
			if (instUnionStore.getInstPlayerId() != instPlayerId ) {
				List<InstUnionStore> instUnionStoreList = getInstUnionStoreDAL().getList(" instPlayerId = " + instPlayerId, 0);
				if(instUnionStoreList.size() > 0){
					instUnionStore = instUnionStoreList.get(0);
				}else{
					instUnionStore = null;
				}
			}
			if(instUnionStore != null){
				retMsgData.putIntItem("1", instUnionStore.getId());
				retMsgData.putStringItem("4", instUnionStore.getUnionStoreOnes());
				retMsgData.putStringItem("6", instUnionStore.getUnionStoreThrees());
				retMsgData.putStringItem("7", instUnionStore.getUnionStores());
			}
		}
		
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 联盟商店购买
	 * @author hzw
	 * @date 2015-7-22上午11:16:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyUnionStore(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instUnionMemberId = (Integer)msgMap.get("instUnionMemberId");//联盟成员Id
		int instUnionStoreId = (Integer)msgMap.get("instUnionStoreId");//联盟商店实例Id
		int unionStoreId = (Integer)msgMap.get("unionStoreId");//联盟商店字典Id
		
		InstUnionMember instUnionMember = getInstUnionMemberDAL().getModel(instUnionMemberId, instPlayerId);
		if(instUnionMember == null || instUnionMember.getInstUnionId() == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		if (instUnionMember.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstUnion instUnion = getInstUnionDAL().getModel(instUnionMember.getInstUnionId(), 0);
		DictUnionStore dictUnionStore = DictMap.dictUnionStoreMap.get(unionStoreId + "");
		MessageData syncMsgData = new MessageData();
		
		//验证等级是否达到
		if(instUnion.getLevel() < dictUnionStore.getUnionLevel()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUnionLevel);
			return ;
		}
		
		//验证贡献值元宝是否足够
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(dictUnionStore.getBuyGold() > 0 && instPlayer.getGold() < dictUnionStore.getBuyGold()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return ;
		}
		if(dictUnionStore.getBuyOffer() > 0 && instUnionMember.getSumOffer() < dictUnionStore.getBuyOffer()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUnionOffer);
			return ;
		}
		
		if(instUnionStoreId == 0){
			UnionUtil.addInstUnionStore(instUnionMember.getInstUnionId(), instPlayerId, dictUnionStore, unionStoreId);
		}else{
			InstUnionStore instUnionStore = getInstUnionStoreDAL().getModel(instUnionStoreId, 0);
			//验证是否已经领取
			if (instUnionStore.getInstPlayerId() != instPlayerId ) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			//更新购买记录
			if(UnionUtil.updateInstUnionStore(instUnionStore, dictUnionStore, unionStoreId, channelId, msgMap)){
				return;
			}
		}
		
		//给物品
		ThingUtil.groupThing(instPlayer, dictUnionStore.getTableTypeId(), dictUnionStore.getTableFieldId(), dictUnionStore.getValue(), syncMsgData, msgMap);
		
		//扣除联盟贡献
		if(dictUnionStore.getBuyGold() > 0){
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, dictUnionStore.getBuyGold(), msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		if(dictUnionStore.getBuyOffer() > 0){
			instUnionMember.setSumOffer(instUnionMember.getSumOffer() - dictUnionStore.getBuyOffer());
			getInstUnionMemberDAL().update(instUnionMember, instUnionMember.getInstPlayerId());
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instUnionMember, instUnionMember.getId(), instUnionMember.getResult(), syncMsgData);
		}
		
		String log = "联盟商店兑换：instPlayerId=" + instPlayerId + " 消耗贡献=" + dictUnionStore.getBuyOffer() + " 消耗元宝=" + dictUnionStore.getBuyGold() + " 获得物品=" + (dictUnionStore.getTableTypeId() + "_" + dictUnionStore.getTableFieldId() + "_" + dictUnionStore.getValue());
		LogUtil.info(log);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 联盟-弹劾盟主
	 * @author mp
	 * @date 2015-11-3 上午10:25:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void accuseLeader(HashMap<String, Object> msgMap,String channelId) throws Exception {
		
		//同步块-防止多人同时弹劾盟主
		synchronized (ParamConfig.agreeAccuse) {
			int instPlayerId = getInstPlayerId(channelId);
			
			if (instPlayerId == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
				return;
			}
			
			int leaderUnionMemId = (Integer)msgMap.get("leaderUnionMemId");//联盟成员表中,要弹劾的盟主Id
			int accuseUnionMemId = (Integer)msgMap.get("accuseUnionMemId");//联盟成员表中,欲弹劾盟主的成员Id
			InstUnionMember leaderInstUnionMember = getInstUnionMemberDAL().getModel(leaderUnionMemId, 0);
			InstUnionMember accuseInstUnionMember = getInstUnionMemberDAL().getModel(accuseUnionMemId, 0);
			
			int unionLeaderLeaveDays = DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionLeaderLeaveDays);//弹劾的盟主需离线多少天
			int inUnionHours = DictMapUtil.getSysConfigIntValue(StaticSysConfig.inUnionHours);//联盟成员进盟多少小时可弹劾盟主
			int accLeaderConsGolds = DictMapUtil.getSysConfigIntValue(StaticSysConfig.accLeaderConsGolds);//联盟成员花多少元宝可弹劾盟主
			
			//验证弹劾人和弹劾对象是否为一人
			if (leaderUnionMemId == accuseUnionMemId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noSelf);
				return;
			}
			
			//验证他们是否都在联盟中，且都在同一联盟中
			if (leaderInstUnionMember == null || accuseInstUnionMember == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noIn);
				return;
			}
			if (leaderInstUnionMember.getInstUnionId() != accuseInstUnionMember.getInstUnionId()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noInCommon);
				return;
			}
			
			//验证盟主Id,是否为盟主
			if (leaderInstUnionMember.getUnionGradeId() != 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noLeader);
				return;
			}
			
			//验证成员Id，进盟时间是否超过72小时
			long inUnionTime = DateUtil.getMillSecond(accuseInstUnionMember.getInsertTime());
			if ((DateUtil.getCurrMill() - inUnionTime) < inUnionHours * 60 * 60 * 1000) {
				MessageUtil.sendFailMsg(channelId, msgMap, "进盟需"+inUnionHours+"小时才可弹劾盟主");
				return;
			}
			
			//验证盟主是否已离开5天
			long leaveTime = 0;
			InstUser instUser = getInstUserDAL().getModel(leaderInstUnionMember.getInstPlayerId(), 0);
			long lastLeaveTime = DateUtil.getMillSecond(instUser.getLastLeaveTime());
			long lastLoginTime = DateUtil.getMillSecond(instUser.getLastLoginTime());
			if (lastLeaveTime > lastLoginTime) {
				leaveTime = lastLeaveTime;
			} else {
				leaveTime = lastLoginTime;
			}
			
			if ((DateUtil.getCurrMill() - leaveTime) < unionLeaderLeaveDays * 24 * 60 * 60 * 1000) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟主需离开"+unionLeaderLeaveDays+"天以上才可弹劾");
				return;
			}
			
			//验证元宝是否充足
			InstPlayer instPlayer = getInstPlayerDAL().getModel(accuseInstUnionMember.getInstPlayerId(), 0);
			if (instPlayer.getGold() < accLeaderConsGolds) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}

			//扣除元宝
			MessageData syncMsgData = new MessageData();
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, accLeaderConsGolds, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			//更换盟主和成员的状态
			leaderInstUnionMember.setUnionGradeId(3);
			getInstUnionMemberDAL().update(leaderInstUnionMember, 0);
			
			accuseInstUnionMember.setUnionGradeId(1);
			getInstUnionMemberDAL().update(accuseInstUnionMember, 0);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, accuseInstUnionMember, accuseInstUnionMember.getId(), accuseInstUnionMember.getResult(), syncMsgData);
			
			//更新联盟盟主玩家实例Id
			int instUnionId = leaderInstUnionMember.getInstUnionId();
			InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
			instUnion.setHeadInstPlayerId(accuseInstUnionMember.getInstPlayerId());
			getInstUnionDAL().update(instUnion, 0);
			
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	}
	
	/**
	 * 是否满足弹劾条件
	 * @author mp
	 * @date 2015-11-3 上午11:38:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void isWishUnionAccCon(HashMap<String, Object> msgMap,String channelId) throws Exception {
		
		//同步块-防止多人同时弹劾盟主
		synchronized (ParamConfig.agreeAccuse) {
			int instPlayerId = getInstPlayerId(channelId);
			
			if (instPlayerId == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
				return;
			}
			
			int leaderUnionMemId = (Integer)msgMap.get("leaderUnionMemId");//联盟成员表中,要弹劾的盟主Id
			int accuseUnionMemId = (Integer)msgMap.get("accuseUnionMemId");//联盟成员表中,欲弹劾盟主的成员Id
			InstUnionMember leaderInstUnionMember = getInstUnionMemberDAL().getModel(leaderUnionMemId, 0);
			InstUnionMember accuseInstUnionMember = getInstUnionMemberDAL().getModel(accuseUnionMemId, 0);
			
			int unionLeaderLeaveDays = DictMapUtil.getSysConfigIntValue(StaticSysConfig.unionLeaderLeaveDays);//弹劾的盟主需离线多少天
			int inUnionHours = DictMapUtil.getSysConfigIntValue(StaticSysConfig.inUnionHours);//联盟成员进盟多少小时可弹劾盟主
			int accLeaderConsGolds = DictMapUtil.getSysConfigIntValue(StaticSysConfig.accLeaderConsGolds);//联盟成员花多少元宝可弹劾盟主
			
			//验证弹劾人和弹劾对象是否为一人
			if (leaderUnionMemId == accuseUnionMemId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noSelf);
				return;
			}
			
			//验证他们是否都在联盟中，且都在同一联盟中
			if (leaderInstUnionMember == null || accuseInstUnionMember == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noIn);
				return;
			}
			if (leaderInstUnionMember.getInstUnionId() != accuseInstUnionMember.getInstUnionId()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noInCommon);
				return;
			}
			
			//验证盟主Id,是否为盟主
			if (leaderInstUnionMember.getUnionGradeId() != 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_union_noLeader);
				return;
			}
			
			//验证成员Id，进盟时间是否超过72小时
			long inUnionTime = DateUtil.getMillSecond(accuseInstUnionMember.getInsertTime());
			if ((DateUtil.getCurrMill() - inUnionTime) < inUnionHours * 60 * 60 * 1000) {
				MessageUtil.sendFailMsg(channelId, msgMap, "进盟需"+inUnionHours+"小时才可弹劾盟主");
				return;
			}
			
			//验证盟主是否已离开5天
			long leaveTime = 0;
			InstUser instUser = getInstUserDAL().getModel(leaderInstUnionMember.getInstPlayerId(), 0);
			long lastLeaveTime = DateUtil.getMillSecond(instUser.getLastLeaveTime());
			long lastLoginTime = DateUtil.getMillSecond(instUser.getLastLoginTime());
			if (lastLeaveTime > lastLoginTime) {
				leaveTime = lastLeaveTime;
			} else {
				leaveTime = lastLoginTime;
			}
			
			if ((DateUtil.getCurrMill() - leaveTime) < unionLeaderLeaveDays * 24 * 60 * 60 * 1000) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟主需离开"+unionLeaderLeaveDays+"天以上才可弹劾");
				return;
			}
			
			//验证元宝是否充足
			InstPlayer instPlayer = getInstPlayerDAL().getModel(accuseInstUnionMember.getInstPlayerId(), 0);
			if (instPlayer.getGold() < accLeaderConsGolds) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	}
	
	/**
	 * 联盟申请全部清空
	 * @author mp
	 * @date 2015-12-15 下午2:31:28
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clearUnionApplay(HashMap<String, Object> msgMap,String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = (Integer)msgMap.get("instUnionId");//联盟实例Id
		getInstUnionApplyDAL().deleteByWhere("instUnionrId = " + instUnionId);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
}
