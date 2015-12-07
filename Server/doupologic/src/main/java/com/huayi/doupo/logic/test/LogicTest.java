package com.huayi.doupo.logic.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableList;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.util.base.CollectionUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.util.MessageUtil;

import flex.messaging.io.ArrayList;

/**
 * 逻辑测试类
 * @author mp
 * @date 2014-12-3 上午11:19:59
 */
public class LogicTest  extends DALFactory{
	
	public static void main(String[] args) throws Exception{
		
/*		String currYmd = DateUtil.getYmdDate();
		String bossStartTime = currYmd + " 21:00:00";
		String bossEndTime = currYmd + " 21:30:00";
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(bossStartTime) || DateUtil.getCurrMill() > DateUtil.getMillSecond(bossEndTime)) {
			System.out.println("--------no in time---------");
		}*/
		SpringUtil.getSpringContext();
		int count = getInstPlayerRechargeDAL().getStatResult("count", "*", " orderform = '191350983'");
		System.out.println(count);
		
//		String uid = UUID.randomUUID().toString();
//		System.out.println(uid);
		
/*		int sumJianValue = 54;
		
		Map<Integer, Integer> jiaMap = new HashMap<>();
		jiaMap.put(1, 5);
		jiaMap.put(2, 15);
		jiaMap.put(3, 25);
		
		
		int caozuoSumJianValue = sumJianValue;
		Map<Integer, Integer> trueJiaMap = new HashMap<>();
		if (sumJianValue > 0) {
			Map<Integer, Integer> sortedJiaMap = CollectionUtil.sortByValueDown(jiaMap);
			for (Entry<Integer, Integer> entry : sortedJiaMap.entrySet()) {
				int fightPId = entry.getKey();
				int fightVal = entry.getValue();
				if (fightVal - caozuoSumJianValue < 0) {
					caozuoSumJianValue = (caozuoSumJianValue - fightVal);
					trueJiaMap.put(fightPId, 0);
				} else {
					trueJiaMap.put(fightPId, (fightVal - caozuoSumJianValue));
					caozuoSumJianValue = 0;
				}
			}
		}
		
		System.out.println(trueJiaMap);*/
		
/*		List<Integer> intList = new ArrayList();
		intList.add(1);
		intList.add(12);
		intList.add(13);
		System.out.println(intList);
		
		
		int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);
		System.out.println(h);
		
		
		SpringUtil.getSpringContext();
		
		InstLuckRank lr = new InstLuckRank();
		lr.setItems("items");
		lr.setName("name");
		lr.setOrderIndex(1);
		lr.setPlayerId(2);
		lr.setRankValue(3);
		lr.setRefreshRemain(4);
		lr.setStartRemain(5);
		lr.setCountReset(DateUtil.getCurrTime());
		lr.setVersion(1);
		
		getInstLuckRankDAL().add(lr,0);*/
		
//		SpringUtil.getSpringContext();
//		String sql = "select equipId from Inst_Player_Equip where instPlayerId = 316";
//		List<Map<String,Object>> equipIdList = getInstPlayerEquipDAL().sqlHelper(sql);
//		for (Map<String,Object> innerMap : equipIdList) {
//			int equipId = (int)(double)Double.valueOf(innerMap.get("equipId").toString());
//			System.out.println(equipId);
//		}
//		System.out.println();
		
/*		try {
			int i = 0; 
			for(; i <= 5; i++){
				System.out.println("abc");
			}
			
			int savermb = 22; // 累计充值金额 = 原始记录 + 本次记录
			int vipSaveRmb = savermb;
			
			vipSaveRmb = vipSaveRmb + 20;
			
			System.out.println(vipSaveRmb + "  " + savermb);
			
			
			System.out.println(DateUtil.getTimeInfo(DateType.DayOfWeek));
			
			
			
			String str = "";
//			System.out.println(str.split(";").length);
			
			
			
			for (String activity : str.split(";")) {
				System.out.println(activity);
			}
			
			
			System.out.println("aaaaaaaaaaa");
			
			Map<Integer, ConcurrentHashMap<Integer, String>> zhMap = new HashMap<>();
			
			ConcurrentHashMap<Integer, Integer> recruitJifenMap = new ConcurrentHashMap<Integer, Integer>();
			recruitJifenMap.put(1, 1);
			recruitJifenMap.put(2, 1);
			recruitJifenMap.put(3, 1);
			
			recruitJifenMap.put(10, 2);
			recruitJifenMap.put(20, 2);
			recruitJifenMap.put(30, 2);
			
			recruitJifenMap.put(1006, 5);
			recruitJifenMap.put(366, 5);
			recruitJifenMap.put(444, 5);
			recruitJifenMap.put(333, 5);
			recruitJifenMap.put(666, 50);
			recruitJifenMap.put(888, 500);
			
			//对int int 形式进行排序
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (Entry<Integer, Integer> entry : recruitJifenMap.entrySet()) {
				map.put(entry.getKey(), entry.getValue());
			}
			Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
			System.out.println(sortedMap);
			
			//组织重复的积分  [积分 玩家id列表]
			Map<Integer, String> orgRepeatJifenMap = new HashMap<>();
			for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
				int instPlayerId = entry.getKey();
				int jifen = entry.getValue();
				if (orgRepeatJifenMap.containsKey(jifen)) {
					String playerIdList = orgRepeatJifenMap.get(jifen);
					orgRepeatJifenMap.put(jifen, playerIdList + ";" + instPlayerId);
				} else {
					orgRepeatJifenMap.put(jifen, instPlayerId + "");
				}
			}
			System.out.println(orgRepeatJifenMap);
			
			//为重复的组织时间
			HashMap<Integer, Map<Integer, Long>> orgRepeatTimeMap = new HashMap<>();
			for (Entry<Integer, String> entry : orgRepeatJifenMap.entrySet()) {
				int jifen = entry.getKey();
				String playerIdList = entry.getValue();
				if (playerIdList.split(";").length > 1) {
					Map<Integer, Long> repeatPidJifenMap = new HashMap<>();
					for (String pId : playerIdList.split(";")) {
						repeatPidJifenMap.put(ConvertUtil.toInt(pId), (long)RandomUtil.getRandomInt(100));
					}
					orgRepeatTimeMap.put(jifen, repeatPidJifenMap);
				}
			}
			System.out.println(orgRepeatTimeMap);
			
			//把组织好的map按时间排序
			HashMap<Integer, Map<Integer, Long>> orgRepeatTimeNewMap = new HashMap<>();
			for (Entry<Integer, Map<Integer, Long>> orgRepeatTimeEntry : orgRepeatTimeMap.entrySet()) {
				int jifen = orgRepeatTimeEntry.getKey();
				Map<Integer, Long> pidTimeMap = orgRepeatTimeEntry.getValue();
				Map<Integer, Long> sortedTimeMap = CollectionUtil.sortByValueUp(pidTimeMap);
				orgRepeatTimeNewMap.put(jifen, sortedTimeMap);
			}
			System.out.println(orgRepeatTimeNewMap);
			
			//重新组织结果map
			String jifenRecord = "";
			Map<Integer, Integer> resultMap = new LinkedHashMap<>();
			for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
				int pId = entry.getKey();
				int jifen = entry.getValue();
				
				if (!StringUtil.contains(jifenRecord, jifen + "", ";")) {
					if (orgRepeatTimeNewMap.containsKey(jifen)) {
						Map<Integer, Long> timeNewMap = orgRepeatTimeNewMap.get(jifen);
						for (Entry<Integer, Long> entryTimeNewEntry : timeNewMap.entrySet()) {
							resultMap.put(entryTimeNewEntry.getKey(), jifen);
						}
					} else {
						resultMap.put(pId, jifen);
					}
				}
			}
			
			System.out.println(resultMap);
			
//			String one = "2015-08-22";
//			String two = "2015-08-24";
//			System.out.println(DateUtil.dayDiff(one, two));
//			System.out.println(DateUtil.getCurrTime());
//			System.out.println(DateUtil.getNumDayDate("2015-08-22",3));
			
			
		/*	long youxiaoqi = 30 * 24 * 60 * 60 * 1000L;
			System.out.println(youxiaoqi);
			
//			String str = "6B11&";
//			System.out.println(str.split("&").length);
			
			
			//内网包版本更新地址【强制更新】
			String checkVersionURL = SysConfigUtil.getValue("activity.request.url");
			URL url = new URL(checkVersionURL);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(10000);
			connection.connect();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = connection.getInputStream();
			byte[] buf = new byte[512];
			int len;
			while((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			String content = new String(baos.toByteArray(), "UTF-8");
			Map<String,String> activityMap = JsonUtil.fromJson(content, HashMap.class);
			
			System.out.println("map->" + activityMap);*/
			
			
			//
			
//			Map<String,String> map = new HashMap<>();
//			map.put("1", "10_限时英雄_3;11_巅峰强者_4");
//			map.put("2", "12_限时英雄_3;11_巅峰强者_4");
//			map.put("3", "13_限时英雄_3;11_巅峰强者_4");
//			System.out.println(JsonUtil.toJson(map));
			
//			serverVersion = content.split("#")[0];
//			downloadURL = content.split("#")[1];
//			Log.e("AAAAAAAAAAAAAAAAAAAAAA", "【服务器版本号：" + serverVersion + "】");
//			Log.e("AAAAAAAAAAAAAAAAAAAAAA", "【更新包地址：" + downloadURL + "】");
		
		
		
//		recursion();
	}
	
	/**
	 * 要求-计算出加上奖励经验后能达到的新等级和剩余经验
	 * @author mp
	 * @date 2014-12-3 上午11:21:25
	 * @throws Exception
	 * @Description
	 */
	public static void recursion () throws Exception{
		
		//加载资源
		SpringUtil.getSpringContext();
		DictData.dictData(0);
		
		//初始数据
		int currLevel = 10;
		int currExp = 100;
		int rewardExp = 1000;
		
		//我的递归计算方式
		System.out.println(calcFinalLevelAndExp(currExp + rewardExp, currLevel));
		
		//小胡的while-true计算方式
		System.out.println(xhMethod(currExp + rewardExp, currLevel));
	}
	
	/**
	 * 递归方法,计算最终等级和经验
	 * @author mp
	 * @date 2014-12-2 下午5:37:16
	 * @return
	 * @Description
	 */
	public static List<Integer> calcFinalLevelAndExp (int totalExp, int currLevel) {
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(currLevel + "");
		if (totalExp >= dictLevelProp.getFleetExp()) {
			currLevel = currLevel + 1;
			totalExp = totalExp - dictLevelProp.getFleetExp();
			return calcFinalLevelAndExp(totalExp, currLevel);
		} else {
			return ImmutableList.of(currLevel, totalExp);
		}
	}
	
	/**
	 * 小胡while-true方式
	 * @author mp
	 * @date 2014-12-2 下午5:43:28
	 * @param totalExp
	 * @param currLevel
	 * @return
	 * @Description
	 */
	public static List<Integer> xhMethod (int exp, int currLevel) {
		int level = currLevel;
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(level + "");
		if(exp >= dictLevelProp.getFleetExp()){
			exp = exp - dictLevelProp.getFleetExp();
			level = level + 1;
			while (true) {
				dictLevelProp = DictMap.dictLevelPropMap.get(level + "");
				if(exp >= dictLevelProp.getFleetExp()){
					exp = exp - dictLevelProp.getFleetExp();
					level = level + 1;
				}else{
					break;
				}
			}
		}
		return ImmutableList.of(level, exp);
	}
}
