package com.huayi.doupo.logic.handler.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictActivityOnlineRewards;
import com.huayi.doupo.base.model.DictAuctionShop;
import com.huayi.doupo.base.model.DictHJYStore;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstActivityOnlineRewards;
import com.huayi.doupo.base.model.InstActivityOpenServiceBag;
import com.huayi.doupo.base.model.InstAuctionShop;
import com.huayi.doupo.base.model.InstHJYStore;
import com.huayi.doupo.base.model.InstPlayerAward;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerMail;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ActivityUtil extends DALFactory{
	
	/**
	 * 购买团购箱子-全服处理
	 * @author mp
	 * @date 2015-12-18 下午7:01:21
	 * @Description
	 */
	public synchronized static void addGroupBox (int num) throws Exception{
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '" + StaticBigTable.groupBoxNum + "'", 0);
		if (instPlayerBigTableList.size() <= 0) {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(0);
			instPlayerBigTable.setProperties(StaticBigTable.groupBoxNum);
			instPlayerBigTable.setValue1(num + "");
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		} else {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			instPlayerBigTable.setValue1((ConvertUtil.toInt(instPlayerBigTable.getValue1()) + num) + "");
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
		}
	}
	
	/**
	 * 实例化拍卖行/黑角域数据
	 * @author hzw
	 * @date 2014-9-27下午3:12:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unused")
	public static void addInstActivity(int instPlayerId, int activityId, int isForever, MessageData syncMsgData) throws Exception{
		List<InstActivity> instActivityList = (List<InstActivity>)getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + activityId, instPlayerId);
		if(instActivityList != null && instActivityList.size() > 0){
			InstActivity instActivity = instActivityList.get(0);
			if(instActivity.getIsForever() == 0){
				instActivity.setActivityTime(DateUtil.getCurrTime());
				instActivity.setIsForever(isForever);
				instActivity.setUseNum(0);
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			}
		} else {
			SysActivity dictActivity = DictMap.sysActivityMap.get(activityId + "");
			InstActivity instActivity = new InstActivity();
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityId(activityId);
			instActivity.setActivityTime(DateUtil.getCurrTime());
			instActivity.setIsForever(isForever);
			instActivity.setUseNum(0);
			getInstActivityDAL().add(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			if(activityId == StaticActivity.auctionShop){
				ActivityUtil.addInstAuctionShop(instPlayerId, syncMsgData);
			}else if(activityId == StaticActivity.hJYStore){
				ActivityUtil.addInstHJYStore2(instPlayerId, syncMsgData);
			}
		}
	}
	
	/**
	 * 添加活动-ActivityTime有特殊含义的活动
	 * @author mp
	 * @date 2015-5-4 下午5:07:27
	 * @param instPlayerId
	 * @param activityId
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivity(int instPlayerId, int activityId, MessageData syncMsgData) throws Exception{
		InstActivity instActivity = new InstActivity();
		instActivity.setInstPlayerId(instPlayerId);
		instActivity.setActivityId(activityId);
		instActivity.setActivityTime("");//有特殊含义-购买时间
		instActivity.setIsForever(1);
		instActivity.setUseNum(0);
		getInstActivityDAL().add(instActivity, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
	}
	
	/**
	 * 添加活动-每日特惠
	 * @author mp
	 * @date 2015-6-4 下午12:11:26
	 * @param instPlayerId
	 * @param activityId
	 * @param activityTime
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivity(int instPlayerId, int activityId, String activityTime,String state) throws Exception{
		InstActivity instActivity = new InstActivity();
		instActivity.setInstPlayerId(instPlayerId);
		instActivity.setActivityId(activityId);
		instActivity.setActivityTime(activityTime);//有特殊含义-领取记录
		instActivity.setIsForever(1);
		instActivity.setUseNum(0);
		instActivity.setSpareOne(state);//特殊含义-购买状态
		getInstActivityDAL().add(instActivity, instPlayerId);//只服务器用,不用同步给客户端
	}
	
	/**
	 * 添加活动-限时抢购
	 * @author mp
	 * @date 2015-6-4 下午5:18:08
	 * @param instPlayerId
	 * @param activityId
	 * @param paramOne
	 * @param syncMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivityLimitShop(int instPlayerId, int activityId, String paramOne) throws Exception{
		InstActivity instActivity = new InstActivity();
		instActivity.setInstPlayerId(instPlayerId);
		instActivity.setActivityId(activityId);
		instActivity.setActivityTime("");
		instActivity.setIsForever(1);
		instActivity.setUseNum(0);
		instActivity.setSpareOne(paramOne);//记录限时抢购的物品 格式为：;1_2;2_10;
		getInstActivityDAL().add(instActivity, instPlayerId);//只服务器用,不用同步给客户端
	}
	
	/**
	 * 主要用于初始化月卡活动
	 * @author hzw
	 * @date 2015-5-6下午3:16:36
	 * @param instPlayerId
	 * @param activityId
	 * @param syncMsgData
	 * @param type 用于月卡以后的扩展当做月卡类型(充值字典Id)
	 * @throws Exception
	 * @Description
	 */
	public static void initInstActivity(int instPlayerId, int activityId, MessageData syncMsgData, int type) throws Exception{
		List<InstActivity> instActivityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + activityId + " and useNum = " + type, instPlayerId);
		if(instActivityList.size() > 0){
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime("");
			getInstActivityDAL().update(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData, 1);
		}else{
			InstActivity instActivity = new InstActivity();
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityId(activityId);
			instActivity.setActivityTime("");
			instActivity.setIsForever(0);
			instActivity.setUseNum(type);
			getInstActivityDAL().add(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
		}
	}
	
	/**
	 * 主要用于确认月卡活动生效
	 * @author hzw
	 * @date 2015-5-6下午3:16:36
	 * @param instPlayerId
	 * @param activityId
	 * @param syncMsgData
	 * @param type 用于通知前端是Add还是Update 1-add 2-update
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivity(int instPlayerId, List<InstActivity> instActivityList, MessageData syncMsgData, int type) throws Exception{
		if(instActivityList.size() > 0){
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime(DateUtil.getNumDayDate(30) + " 00:00:00");
			instActivity.setSpareOne("");
			if(type == 1){
				instActivity.setId(instActivity.getId());
				instActivity.setInstPlayerId(instActivity.getInstPlayerId());
				instActivity.setActivityId(instActivity.getActivityId());
				instActivity.setIsForever(instActivity.getIsForever());
				instActivity.setUseNum(instActivity.getUseNum());
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			}else{
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData, 2);
			}
		}
	}
	
	/**
	 * 添加月卡/续月卡
	 * @author hzw
	 * @date 2015-6-23下午4:32:09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addMonthCard(int instPlayerId, int activityId, MessageData syncMsgData, int type) throws Exception{
		List<InstActivity> instActivityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId + " and activityId = " + activityId + " and useNum = " + type, instPlayerId);
		if(instActivityList.size() > 0){
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime(DateUtil.getNumDayDate(30) + " 00:00:00");
			instActivity.setSpareOne("");
			getInstActivityDAL().update(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData, 1);
		}else{
			InstActivity instActivity = new InstActivity();
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityId(activityId);
			instActivity.setActivityTime(DateUtil.getNumDayDate(30) + " 00:00:00");
			instActivity.setSpareOne("");
			instActivity.setIsForever(0);
			instActivity.setUseNum(type);
			getInstActivityDAL().add(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
		}
		if (type == 1) {
			List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.monthCard + "'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
				instPlayerBigTable.setValue1("");
				getInstPlayerBigTableDAL().update(instPlayerBigTable,0);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerBigTable, instPlayerBigTable.getId(), instPlayerBigTable.getResult(), syncMsgData, 1);
			} 
		}
	}
	
	/**
	 * 实例化拍卖行物品数据
	 * @author hzw
	 * @date 2014-9-27下午4:19:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstAuctionShop(int instPlayerId, MessageData syncMsgData) throws Exception{
		List<InstAuctionShop> instAuctionShopList = (List<InstAuctionShop>)getInstAuctionShopDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(instAuctionShopList != null && instAuctionShopList.size() > 0){
			for(InstAuctionShop obj : instAuctionShopList){
				getInstAuctionShopDAL().deleteById(obj.getId(), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
			}
		}
		/*List<DictAuctionHJY> DictAuctionHJYs = DictList.dictAuctionHJYList;
		for(DictAuctionHJY obj : DictAuctionHJYs){
			if(obj.getType() != 1){
				DictAuctionHJYs.remove(obj);
			}
		}*/
		
		List<DictAuctionShop> DictAuctionShops = DictList.dictAuctionShopList;
		List<Integer> randoms = RandomUtil.getRandomNoRepeat(8, DictAuctionShops.size());
		for(int i : randoms){
			DictAuctionShop obj = DictAuctionShops.get(i - 1);
			InstAuctionShop instAuctionShop = new InstAuctionShop();
			instAuctionShop.setInstPlayerId(instPlayerId);
			instAuctionShop.setTableTypeId(obj.getTableTypeId());
			instAuctionShop.setTableFieldId(obj.getTableFieldId());
			instAuctionShop.setValue(obj.getValue());
			instAuctionShop.setSellType(obj.getSellType());
			instAuctionShop.setSellValue(obj.getSellValue());
			instAuctionShop.setSellOut(0);
			getInstAuctionShopDAL().add(instAuctionShop, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instAuctionShop, instAuctionShop.getId(), instAuctionShop.getResult(), syncMsgData);
		}
		
	}
	
	/**
	 * 实例化黑角域物品数据
	 * @author hzw
	 * @date 2014-9-27下午4:20:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstHJYStore(int instPlayerId, MessageData syncMsgData) throws Exception{
		List<InstHJYStore> instHJYStoreList = (List<InstHJYStore>)getInstHJYStoreDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(instHJYStoreList != null && instHJYStoreList.size() > 0){
			for(InstHJYStore obj : instHJYStoreList){
				getInstHJYStoreDAL().deleteById(obj.getId(), instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
			}
		}
		List<DictHJYStore> DictHJYStores = DictList.dictHJYStoreList;
		int weightTotal = 0;
		Map<Integer,Integer> weightMap=new HashMap<Integer,Integer>();
		Map<Integer,Integer> obtainMap=new HashMap<Integer,Integer>();
		for(int i=0;i<DictHJYStores.size();i++){
			if(DictHJYStores.get(i).getWeight()==0){
				continue;
			}
			if(i==0){
				weightMap.put(0, DictHJYStores.get(i).getWeight());
			}else{
				weightMap.put(weightTotal, weightTotal+DictHJYStores.get(i).getWeight());
			}
			obtainMap.put(weightTotal+DictHJYStores.get(i).getWeight(),DictHJYStores.get(i).getId());
			weightTotal+=DictHJYStores.get(i).getWeight();
		}
		Map<Integer,DictHJYStore> obtain = new HashMap<Integer,DictHJYStore>();
		do{
			int randomWeight=RandomUtil.getRandomInt(weightTotal);
			for(Integer key : weightMap.keySet()){
				if(randomWeight>=key&&randomWeight<weightMap.get(key)){
					obtain.put(key, DictMap.dictHJYStoreMap.get(obtainMap.get(weightMap.get(key))+""));
					break;
				}
			}
		}while(obtain.size()<8);
		for(Integer key:obtain.keySet()){
			DictHJYStore obj = obtain.get(key);
			InstHJYStore instHJYStore = new InstHJYStore();
			instHJYStore.setInstPlayerId(instPlayerId);
			instHJYStore.setTableTypeId(obj.getTableTypeId());
			instHJYStore.setTableFieldId(obj.getTableFieldId());
			instHJYStore.setValue(obj.getValue());
			instHJYStore.setSellType(obj.getSellType());
			instHJYStore.setSellValue(obj.getSellValue());
			instHJYStore.setSellOut(0);
			getInstHJYStoreDAL().add(instHJYStore, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instHJYStore, instHJYStore.getId(), instHJYStore.getResult(), syncMsgData);
		}
	}
	
	/**
	 * 用于引导实例化固定的黑角域物品数据
	 * @author hzw
	 * @date 2015-1-15下午2:39:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstHJYStore2(int instPlayerId, MessageData syncMsgData) throws Exception{
		String thingString = DictMap.dictGuipStepMap.get(10001 + "").getThings();
		for (String thing : thingString.split(";")) {
			DictHJYStore obj = DictMap.dictHJYStoreMap.get(thing);
			InstHJYStore instHJYStore = new InstHJYStore();
			instHJYStore.setInstPlayerId(instPlayerId);
			instHJYStore.setTableTypeId(obj.getTableTypeId());
			instHJYStore.setTableFieldId(obj.getTableFieldId());
			instHJYStore.setValue(obj.getValue());
			instHJYStore.setSellType(obj.getSellType());
			instHJYStore.setSellValue(obj.getSellValue());
			instHJYStore.setSellOut(0);
			getInstHJYStoreDAL().add(instHJYStore, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instHJYStore, instHJYStore.getId(), instHJYStore.getResult(), syncMsgData);
		
		}
	}
	
	/**
	 * 初始化在线奖励活动实例数据
	 * @author hzw
	 * @date 2014-10-14下午2:53:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivityOnlineRewards(int instPlayerId) throws Exception{
//		List<InstActivityOnlineRewards> instActivityOnlineRewardsList = (List<InstActivityOnlineRewards>)getInstActivityOnlineRewardsDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
//		if(instActivityOnlineRewardsList == null || instActivityOnlineRewardsList.size() <= 0){
//			List<DictActivityOnlineRewards> dictActivityOnlineRewardsList = (List<DictActivityOnlineRewards>)DictList.dictActivityOnlineRewardsList;
			//顺序 one-two是顺序， two-one是倒叙
//			Collections.sort(dictActivityOnlineRewardsList, new Comparator<Object>() {
//				public int compare(Object a, Object b) {
//					int one = ((DictActivityOnlineRewards)a).getOnlineTime();
//					int two = ((DictActivityOnlineRewards)b).getOnlineTime();
//					return (int)(one - two); 
//				}
//			}); 
//			DictActivityOnlineRewards obj = dictActivityOnlineRewardsList.get(0);
			DictActivityOnlineRewards obj = DictMap.dictActivityOnlineRewardsMap.get("1");
			InstActivityOnlineRewards instActivityOnlineRewards = new InstActivityOnlineRewards();
			instActivityOnlineRewards.setInstPlayerId(instPlayerId);
			instActivityOnlineRewards.setOnlineRewardsId(obj.getId());
			instActivityOnlineRewards.setOnlineTime(obj.getOnlineTime() * 60 * 1000);
			instActivityOnlineRewards.setThings(obj.getThings());
			getInstActivityOnlineRewardsDAL().add(instActivityOnlineRewards, instPlayerId);
//		}
		
	}
	
	/**
	 * 更新在线奖励活动实例数据
	 * @author hzw
	 * @date 2014-10-14下午3:02:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void updateInstActivityOnlineRewards(InstActivityOnlineRewards instActivityOnlineRewards, MessageData syncMsgData) throws Exception{
		List<DictActivityOnlineRewards> dictActivityOnlineRewardsList = (List<DictActivityOnlineRewards>)DictList.dictActivityOnlineRewardsList;
		//顺序 one-two是顺序， two-one是倒叙
		Collections.sort(dictActivityOnlineRewardsList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictActivityOnlineRewards)a).getOnlineTime();
				int two = ((DictActivityOnlineRewards)b).getOnlineTime(); 
				return (int)(one - two); 
			}
		}); 
		DictActivityOnlineRewards dictActivityOnlineRewards = DictMap.dictActivityOnlineRewardsMap.get(instActivityOnlineRewards.getOnlineRewardsId() + "");
		for(DictActivityOnlineRewards obj : dictActivityOnlineRewardsList){
			if(obj.getOnlineTime() > dictActivityOnlineRewards.getOnlineTime()){
				dictActivityOnlineRewards = obj;
				break;
			}
		}
		if(dictActivityOnlineRewards.getId() != instActivityOnlineRewards.getOnlineRewardsId()){
			instActivityOnlineRewards.setOnlineRewardsId(dictActivityOnlineRewards.getId());
			instActivityOnlineRewards.setOnlineTime(dictActivityOnlineRewards.getOnlineTime() * 60 * 1000);
			instActivityOnlineRewards.setThings(dictActivityOnlineRewards.getThings());
		}else if(dictActivityOnlineRewards.getId() == instActivityOnlineRewards.getOnlineRewardsId()){
			instActivityOnlineRewards.setOnlineTime(0);
		}
		getInstActivityOnlineRewardsDAL().update(instActivityOnlineRewards, instActivityOnlineRewards.getInstPlayerId());
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivityOnlineRewards, instActivityOnlineRewards.getId(), instActivityOnlineRewards.getResult(), syncMsgData);
		
	}
	
	/**
	 * 开服礼包登录处理
	 * @author mp
	 * @date 2015-7-3 上午11:47:32
	 * @param instPlayerId
	 * @param instUser
	 * @throws Exception
	 * @Description
	 */
	public static void loginInstActivityOpenServiceBag(int instPlayerId, InstUser instUser) throws Exception{
		List<InstActivityOpenServiceBag> instActivityOpenServiceBagList = (List<InstActivityOpenServiceBag>)getInstActivityOpenServiceBagDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if (instUser.getLastLeaveTime() != null && !instUser.getLastLeaveTime().equals("")) {
			if(!DateUtil.isSameDay(instUser.getLastLeaveTime(), DateUtil.getCurrTime(), DateFormat.YMDHMS)){
				InstActivityOpenServiceBag instActivityOpenServiceBag = instActivityOpenServiceBagList.get(0);
				if(instActivityOpenServiceBag.getDay() < DictList.dictActivityOpenServiceBagList.size() && instActivityOpenServiceBag.getDay() != 0){
					instActivityOpenServiceBag.setDay(instActivityOpenServiceBag.getDay() + 1);
					if(instActivityOpenServiceBag.getCan().equals("")){
						instActivityOpenServiceBag.setCan(instActivityOpenServiceBag.getDay() + "");
					}else{
						instActivityOpenServiceBag.setCan(instActivityOpenServiceBag.getCan() + ";" + instActivityOpenServiceBag.getDay());
					}
					getInstActivityOpenServiceBagDAL().update(instActivityOpenServiceBag, instPlayerId);
				}else if(instActivityOpenServiceBag.getDay() == DictList.dictActivityOpenServiceBagList.size()){
					instActivityOpenServiceBag.setDay(0);
					getInstActivityOpenServiceBagDAL().update(instActivityOpenServiceBag, instPlayerId);
				}
			}
		}
	}
	
	/**
	 * 初始化开服礼包
	 * @author hzw
	 * @date 2014-10-15下午2:47:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstActivityOpenServiceBag(int instPlayerId) throws Exception{
		InstActivityOpenServiceBag instActivityOpenServiceBag = new InstActivityOpenServiceBag();
		instActivityOpenServiceBag.setInstPlayerId(instPlayerId);
		instActivityOpenServiceBag.setDay(1);
		instActivityOpenServiceBag.setCan(1 + "");
		instActivityOpenServiceBag.setEnd("");
		getInstActivityOpenServiceBagDAL().add(instActivityOpenServiceBag, instPlayerId);
	}
	
	/**
	 * 添加领奖中心实例数据
	 * @author hzw
	 * @date 2014-10-21下午5:18:57
	 * @param name 1-天焚炼气塔    2-竞技场  3-系统 4-世界Boss 5-兑换码礼包
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerAward(int instPlayerId, int name, String things, String operTime, String description, MessageData syncMsgData) throws Exception{
		InstPlayerAward instPlayerAward = new InstPlayerAward();
		instPlayerAward.setInstPlayerId(instPlayerId);
		instPlayerAward.setName(name);
		instPlayerAward.setThings(things);
		instPlayerAward.setOperTime(operTime);
		instPlayerAward.setDescription(description);
		instPlayerAward.setInsertTime(DateUtil.getCurrTime());
		getInstPlayerAwardDAL().add(instPlayerAward, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerAward, instPlayerAward.getId(), instPlayerAward.getResult(), syncMsgData);
		LogUtil.info("领奖中心发奖:instPlayerId=" + instPlayerId + " name=" + name + " things=" + things + " operTime=" + operTime + " description=" + description);
	}
	
	/**
	 * 邮件实例数据
	 * @author hzw
	 * @date 2014-10-21下午5:18:57
	 * @param type 1-抢夺    2-竞技场
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addInstPlayerMail(int instPlayerId, int type, String enemyName, int value) throws Exception{
		InstPlayerMail instPlayerMail = new InstPlayerMail();
		instPlayerMail.setInstPlayerId(instPlayerId);
		instPlayerMail.setEnemyName(enemyName);
		instPlayerMail.setType(type);
		instPlayerMail.setValue(value);
		instPlayerMail.setInsertTime(DateUtil.getCurrTime());
		getInstPlayerMailDAL().add(instPlayerMail, instPlayerId);
		
		MessageData otherSyncMsgData = new MessageData();
		Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMail, instPlayerMail.getId(), instPlayerMail.getResult(), otherSyncMsgData);
		if(player != null){
			MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
		}
	
	}
	
	/**
	 * 是否在活动期内
	 * @author mp
	 * @date 2015-8-30 下午2:52:21
	 * @param activityId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static boolean isInActivity (int activityId) {
		boolean flag = false;
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(activityId + "");
			if (dictActivity != null) {
				if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
					if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			LogUtil.error("是否在活动期内Error", e);
		}
		return flag;
	}
	
	/**
	 * 是否在节假日活动期内
	 * @author mp
	 * @date 2015-9-23 下午4:14:32
	 * @param activityId
	 * @return
	 * @Description
	 */
	public static boolean isInHolidayActivity (int activityId) {
		boolean flag = false;
		try {
			DictActivityHoliday activityHoliday = DictMap.dictActivityHolidayMap.get(activityId + "");
			if (activityHoliday != null) {
				if (activityHoliday.getStartTime() != null && !activityHoliday.getStartTime().equals("") && activityHoliday.getEndTime() != null && !activityHoliday.getEndTime().equals("")) {
					if (DateUtil.getMillSecond(activityHoliday.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(activityHoliday.getEndTime())) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			LogUtil.error("是否在假日活动期内Error", e);
		}
		return flag;
	}
	
	
}
