package com.huayi.doupo.logic.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArenaNPC;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.NPCPlayerUtil;

public class NameUtil extends DALFactory{
	
	/**
	 * 读取文件,初始化名字
	 * @author mp
	 * @date 2015-1-22 下午2:44:46
	 * @Description
	 */
	public static void initName () {
		try {
			ParamConfig.nameFirstList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/name_first.txt"), "utf-8");
			ParamConfig.nameSecondList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/name_second.txt"), "utf-8");
			ParamConfig.nameThridList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/name_third.txt"), "utf-8");
			ParamConfig.openIds = FileUtil.readFileToSet(NameUtil.class.getResourceAsStream("/config/name/openIds.txt"), "utf-8");
			
			//统计删档收费所有充值信息 openId_thisrmb   #select CONCAT(openId ,'_', thisrmb) from Inst_Player_Recharge;
			List<String> s1RechargeList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/s1Recharge.txt"), "utf-8");
			List<String> s2RechargeList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/s2Recharge.txt"), "utf-8");
			List<String> s3RechargeList = FileUtil.readFileToList(NameUtil.class.getResourceAsStream("/config/name/s3Recharge.txt"), "utf-8");
			List<String> rechargeList = new ArrayList<String>();
			rechargeList.addAll(s1RechargeList);
			rechargeList.addAll(s2RechargeList);
			rechargeList.addAll(s3RechargeList);
			
			for (String rechargeStr : rechargeList) {
				String key = rechargeStr.substring(0, rechargeStr.lastIndexOf("_"));
				int value = Integer.valueOf(rechargeStr.substring(rechargeStr.lastIndexOf("_") + 1, rechargeStr.length()));
				if (ParamConfig.rechargeMap.containsKey(key)) {
					ParamConfig.rechargeMap.put(key, ParamConfig.rechargeMap.get(key) + value);
				} else {
					ParamConfig.rechargeMap.put(key, value);
				}
			}
		} catch (Exception e) {
			LogUtil.error("载入名字文件,openIds文件,充值文件异常", e);
		}
//		System.out.println(ParamConfig.rechargeMap.size());
//		System.out.println(ParamConfig.rechargeMap);
//		System.out.println(ParamConfig.rechargeMap.get("144104179@yijie"));
	}
	
	/**
	 * 获取随机名字
	 * @author mp
	 * @date 2015-1-22 下午3:57:44
	 * @return
	 * @Description
	 */
	public static String getRandomName () {
		
		String randomName = "";
		int index = 0;
		
		while (true) {
			index ++;
			String firstName = ParamConfig.nameFirstList.get(RandomUtil.getRangeInt(0, ParamConfig.nameFirstList.size() - 1)).trim();
			String secondName = ParamConfig.nameSecondList.get(RandomUtil.getRangeInt(0, ParamConfig.nameSecondList.size() - 1)).trim();
			String thirdName = ParamConfig.nameThridList.get(RandomUtil.getRangeInt(0, ParamConfig.nameThridList.size() - 1)).trim();
			
			if (firstName.length() >= 5) {
				randomName += firstName + thirdName;
			} else {
				randomName += firstName + secondName + thirdName;
			}
			
			if (randomName.length() > 8) {
				randomName = randomName.substring(0, 8);
			}
			
			//确保数据库中不存在此名字
			List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("name = '"+ randomName +"'", 0);
			
			//确保竞技场npc里没有此名字
			int isHave = 0;
			for(Entry<Integer, InstPlayerArenaNPC> entry : NPCPlayerUtil.instPlayerArenaNPCMap.entrySet()){
				InstPlayerArenaNPC instPlayerArenaNPC = entry.getValue();
				if (instPlayerArenaNPC.getName().equals(randomName)) {
					isHave ++ ;
					break;
				}
			}
			
			if (instPlayerList.size() <= 0 && isHave == 0) {
				break;
			}
			//为防止死循环,当寻找了100次的时候,不在往下寻找
			if (index >= 100) {
				break;
			}
		}
		return randomName;
	}
	
	public static void main(String[] args) throws Exception{
		
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(NameUtil.class.getResourceAsStream("/config/name/name_first.txt")));
		String str = "";
		while ((str = bufferedReader.readLine()) != null) {
			System.out.println(str);
		}
		
	}
	
}
