package com.huayi.doupo.logic.handler.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictUnionLevelPriv;
import com.huayi.doupo.base.model.DictUnionStore;
import com.huayi.doupo.base.model.InstUnion;
import com.huayi.doupo.base.model.InstUnionApply;
import com.huayi.doupo.base.model.InstUnionBox;
import com.huayi.doupo.base.model.InstUnionDynamic;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUnionStore;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.logic.util.MessageUtil;


/**
 * 联盟工具
 * @author hzw
 * @date 2015-7-15下午7:39:09
 */
public class UnionUtil extends DALFactory{
	
	/**
	 * 添加公会
	 * @author hzw
	 * @date 2015-7-15下午7:33:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstUnion addInstUnion(int instPlayerId, String unionName, String unionManifesto) throws Exception{
		InstUnion instUnion = new InstUnion();
		instUnion.setName(unionName);
		instUnion.setExp(0);
		instUnion.setLevel(1);
		instUnion.setGradeTypeId(1);
		instUnion.setMaxMember(DictMap.dictUnionLevelPrivMap.get(1+"").getNum());
		instUnion.setCurrentMember(1);//联盟默认是1级？
		instUnion.setHeadInstPlayerId(instPlayerId);
		instUnion.setCreateTime(DateUtil.getCurrTime());
		instUnion.setUnionNotice("");
		instUnion.setUnionManifesto(unionManifesto);
		getInstUnionDAL().add(instUnion, 0);
		return instUnion;
	}
	
	/**
	 * 添加联盟成员列表
	 * @author hzw
	 * @date 2015-7-15下午8:17:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstUnionMember addInstUnionMember(int instUnionId, int instPlayerId , int grade) throws Exception{
		InstUnionMember instUnionMember = new InstUnionMember();
		instUnionMember.setCurrentOffer(0);
		instUnionMember.setInstUnionId(instUnionId);
		instUnionMember.setInstPlayerId(instPlayerId);
		instUnionMember.setUnionGradeId(grade);
		instUnionMember.setSumOffer(0);
		instUnionMember.setCurrentOffer(0);
		getInstUnionMemberDAL().add(instUnionMember, instPlayerId);
		return instUnionMember;
	}
	
	/**
	 * 添加联盟申请列表
	 * @author hzw
	 * @date 2015-7-17下午3:06:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstUnionApply addInstUnionApply(int instUnionId, int instPlayerId) throws Exception{
		InstUnionApply instUnionApply = new InstUnionApply();
		instUnionApply.setInstPlayerId(instPlayerId);
		instUnionApply.setInstUnionrId(instUnionId);
		getInstUnionApplyDAL().add(instUnionApply, instPlayerId);
		return instUnionApply;
	}
	
	 
	/**
	 * 返回此经验下联盟的等级及当前经验
	 * @author hzw
	 * @date 2015-7-20下午7:00:23
	 * @param level 当前等级
	 * @param exp  此经验（增加后的）
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Integer> calcLevel(int level, int exp) throws Exception{
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		List<DictUnionLevelPriv> unionLevelPrivList = DictList.dictUnionLevelPrivList;
		for(DictUnionLevelPriv dictUnionLevelPriv : unionLevelPrivList){
			if(dictUnionLevelPriv.getExp() == 0 && dictUnionLevelPriv.getId() == level ){
				exp = 0;
				break;
			}
			else if(dictUnionLevelPriv.getExp() <= exp && dictUnionLevelPriv.getId() == level){
				level = dictUnionLevelPriv.getId() + 1;
				exp = exp - dictUnionLevelPriv.getExp();
			}else if(dictUnionLevelPriv.getExp() > exp && dictUnionLevelPriv.getId() == level){
				break;
			}
		}
		
		retMap.put("level", level);
		retMap.put("exp", exp);
		return retMap;
	}
	
	/**
	 * 增加联盟动态
	 * @author hzw
	 * @date 2015-7-21下午4:07:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstUnionDynamic addInstUnionDynamic(int instUnionId, int instPlayerId, int unionBuildId) throws Exception{
		InstUnionDynamic instUnionDynamic = new InstUnionDynamic();
		instUnionDynamic.setInstPlayerId(instPlayerId);
		instUnionDynamic.setInstUnionId(instUnionId);
		instUnionDynamic.setUnionBuild(unionBuildId);
		getInstUnionDynamicDAL().add(instUnionDynamic, 0);
		return instUnionDynamic;
	}
	
	/**
	 * 增加联盟宝箱实例数据
	 * @author hzw
	 * @date 2015-7-25下午3:21:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstUnionBox addInstUnionBox(int instUnionId, int instPlayerId, int unionBoxId) throws Exception{
		InstUnionBox instUnionBox = new InstUnionBox();
		instUnionBox.setInstPlayerId(instPlayerId);
		instUnionBox.setInstUnionId(instUnionId);
		instUnionBox.setUnionBoxs(unionBoxId + "");
		getInstUnionBoxDAL().add(instUnionBox, 0);
		return instUnionBox;
	}

	/**
	 * 增加联盟商店实例数据
	 * @author hzw
	 * @date 2015-7-27上午10:25:09
	 * @param msgMap
	 * @param channelId
	 * @return 
	 * @throws Exception
	 * @Description
	 */
	public static void addInstUnionStore(int instUnionId, int instPlayerId, DictUnionStore dictUnionStore, int unionStoreId) throws Exception{
		int unionStoreType = dictUnionStore.getType();
		InstUnionStore instUnionStore = new InstUnionStore();
		instUnionStore.setInstPlayerId(instPlayerId);
		instUnionStore.setInstUnionId(instUnionId);
		switch (unionStoreType) {
		case 1:
			if(dictUnionStore.getSellType() == 2){
				instUnionStore.setUnionStoreOnes(unionStoreId + "_1");
			}else{
				instUnionStore.setUnionStores(unionStoreId + "_1");
			}
			break;
		case 2:
			instUnionStore.setUnionStoreTwos(unionStoreId + "_1");
			break;
		case 3:
			instUnionStore.setUnionStoreThrees(unionStoreId + "");
			break;
		}
		getInstUnionStoreDAL().add(instUnionStore, 0);
	}
	
	/**
	 * 更新联盟商店实例数据
	 * @author hzw
	 * @date 2015-7-29上午11:59:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static boolean updateInstUnionStore(InstUnionStore instUnionStore, DictUnionStore dictUnionStore, int unionStoreId, String channelId, HashMap<String, Object> msgMap) throws Exception{
		boolean flag = false;
		int unionStoreType = dictUnionStore.getType();
		switch (unionStoreType) {
		case 1:
			if(dictUnionStore.getSellType() == 2){
				String unionStoreOnes = instUnionStore.getUnionStoreOnes();
				if(unionStoreOnes == null || unionStoreOnes.equals("")){
					instUnionStore.setUnionStoreOnes(unionStoreId + "_1");
				}else{
					String unionStoreOnesTemp = ";" + unionStoreOnes + ";";
					if(unionStoreOnesTemp.contains(";" + unionStoreId + "_")){
						String unionStoreList[] = unionStoreOnes.split(";");
						int num = 1;
						for(String str : unionStoreList){
							int num2 = ConvertUtil.toInt(str.split("_")[1]);
							if(str.equals(unionStoreId + "_" + num2)){
								num = num2 + 1;
								break;
							}
						}
						if(dictUnionStore.getSellValue() <= num - 1){
							MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayBuy);
							flag = true;
							return flag;
						}
						unionStoreOnesTemp = unionStoreOnesTemp.replace(";" + unionStoreId + "_" + (num - 1) + ";", ";" + unionStoreId + "_" + (num) + ";");
						instUnionStore.setUnionStoreOnes(unionStoreOnesTemp.substring(1, unionStoreOnesTemp.length() - 1));
					}else{
						instUnionStore.setUnionStoreOnes(instUnionStore.getUnionStoreOnes() + ";" + unionStoreId + "_1");
					}
				}
			}else{
				String unionStores = instUnionStore.getUnionStores();
				if(unionStores == null || unionStores.equals("")){
					instUnionStore.setUnionStores(unionStoreId + "_1");
				}else{
					String unionStoresTemp = ";" + unionStores + ";";
					if(unionStoresTemp.contains(";" + unionStoreId + "_")){
						String unionStoreList[] = unionStores.split(";");
						int num = 1;
						for(String str : unionStoreList){
							int num2 = ConvertUtil.toInt(str.split("_")[1]);
							if(str.equals(unionStoreId + "_" + num2)){
								num = num2 + 1;
								break;
							}
						}
						if(dictUnionStore.getSellValue() <= num - 1){
							MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayBuy);
							flag = true;
							return flag;
						}
						unionStoresTemp = unionStoresTemp.replace(";" + unionStoreId + "_" + (num - 1) + ";", ";" + unionStoreId + "_" + (num) + ";");
						instUnionStore.setUnionStores(unionStoresTemp.substring(1, unionStoresTemp.length() - 1));
					}else{
						instUnionStore.setUnionStores(instUnionStore.getUnionStores() + ";" + unionStoreId + "_1");
					}
				}
			}
			break;
		case 2:
			String unionStoreTwos = instUnionStore.getUnionStoreTwos();
			if(unionStoreTwos == null || unionStoreTwos.equals("")){
				instUnionStore.setUnionStoreTwos(unionStoreId + "");
			}else{
				String unionStoreTwosTemp = ";" + unionStoreTwos + ";";
				if(unionStoreTwosTemp.contains(";" + unionStoreId + "_")){
					String unionStoreList[] = unionStoreTwos.split(";");
					int num = 1;
					for(String str : unionStoreList){
						int num2 = ConvertUtil.toInt(str.split("_")[1]);
						if(str.equals(unionStoreId + "_" + num2)){
							num = num2 + 1;
							break;
						}
					}
					if(dictUnionStore.getSellValue() <= num - 1){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayBuy);
						flag = true;
						return flag;
					}
					unionStoreTwosTemp = unionStoreTwosTemp.replace(";" + unionStoreId + "_" + (num - 1) + ";", ";" + unionStoreId + "_" + (num) + ";");
					instUnionStore.setUnionStoreTwos(unionStoreTwosTemp.substring(1, unionStoreTwosTemp.length() - 1));
				}else{
					instUnionStore.setUnionStoreTwos(instUnionStore.getUnionStoreTwos() + ";" + unionStoreId + "_1");
				}
			}
			break;
		case 3:
			String unionStoreThrees = instUnionStore.getUnionStoreThrees();
			if(unionStoreThrees == null || unionStoreThrees.equals("")){
				instUnionStore.setUnionStoreThrees(unionStoreId + "");
			}else{
				if(StringUtil.contains(unionStoreThrees, unionStoreId + "", ";")){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayBuy);
					flag = true;
					return flag;
				}
				instUnionStore.setUnionStoreThrees(unionStoreThrees + ";" + unionStoreId);
			}
			break;
		}
		getInstUnionStoreDAL().update(instUnionStore, 0);
		return flag;
	}
	
}
