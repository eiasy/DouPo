package com.huayi.doupo.logic.handler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictHoldStarGradeReward;
import com.huayi.doupo.base.model.DictHoldStarRewardPos;
import com.huayi.doupo.base.model.DictHoldStarStep;
import com.huayi.doupo.base.model.DictHoldStarZodiac;
import com.huayi.doupo.base.model.InstPlayerHoldStar;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;

/**
 * 占星工具类
 * @author mp
 * @date 2015-12-7 上午11:20:41
 */
public class HoldStarUtil extends DALFactory{
	
	/**
	 * 刷新上方星星
	 * @author mp
	 * @date 2015-12-7 上午11:21:12
	 * @return
	 * @Description
	 */
	public static String refreshUpStars () {
		int size = DictList.dictHoldStarZodiacList.size();
		List<Integer> integerList = new ArrayList<>();
		Map<Integer, Integer> valiMap = new HashMap<>();
		int index = 0;
		for (;;) {
			index ++;
			int randomId = RandomUtil.getRangeInt(1, size);
			if (valiStars(integerList, randomId, valiMap) >= 4) {
				break;
			}
			if (index > 1000) {//防止死循环
				break;
			}
		}
		StringBuilder sb = new StringBuilder();//格式：位置_星座id_状态;  最后没有分号    状态: 0-未点亮 1-点亮
		int bs = 0;
		for (Integer integer : integerList) {
			bs++;
			sb.append(bs + "_").append(integer + "_").append("0").append(";");
		}
		return StringUtil.noContainLastString(sb.toString());
	}
	
	/**
	 * 根据阶段,刷新下方星星
	 * @author mp
	 * @date 2015-12-7 下午1:18:28
	 * @param gradeId
	 * @return
	 * @Description
	 */
	public static String refreshDownStars (int gradeId, int step, String upstars) {
		
		//上方未点亮的星星列表
		List<Integer> noLightStarZodiacList = new ArrayList<>();
		for (String stars : upstars.split(";")) {
			int id = ConvertUtil.toInt(stars.split("_")[1]);
			int state = ConvertUtil.toInt(stars.split("_")[2]);
			if (state == 0) {
				noLightStarZodiacList.add(id);
			}
		}
		
		float per = 0.0f;
		for (DictHoldStarStep holdStarStep : DictList.dictHoldStarStepList) {
			if (holdStarStep.getHoldStarGradeId() == gradeId && holdStarStep.getStep() == step) {
				per = holdStarStep.getHitPer();
			}
		}
		
		List<Integer> integerList = new ArrayList<>();
		Map<Integer, Integer> valiMap = new HashMap<>();
		
		//命中,确定一个未点亮的星星加进去,其他随机
		float randomFloat = RandomUtil.getRandomFloat();
		if (randomFloat <= per) {
			int randomNoLightIndex = RandomUtil.getRandomInt(noLightStarZodiacList.size());
			int noLightStarId = noLightStarZodiacList.get(randomNoLightIndex);
			int size = DictList.dictHoldStarZodiacList.size();
			
			valiStars(integerList, noLightStarId, valiMap);//确定一个未点亮的星星加进去
			
			int index = 0;
			for (;;) {
				index ++;
				int randomId = RandomUtil.getRangeInt(1, size);
				if (valiStars(integerList, randomId, valiMap) >= 5) {
					break;
				}
				if (index > 1000) {//防止死循环
					break;
				}
			}
		} else {//未命中,从不包含上面未点亮的星星里边选5个
			List<DictHoldStarZodiac> noContainNoLightStarsList = new ArrayList<>();//不包含上方未点亮的所有星星
			for (DictHoldStarZodiac obj : DictList.dictHoldStarZodiacList) {
				if (!noLightStarZodiacList.contains(obj.getId())) {
					noContainNoLightStarsList.add(obj);
				}
			}
			int size = noContainNoLightStarsList.size();
			int index = 0;
			for (;;) {
				index ++;
				int randomIndex = RandomUtil.getRandomInt(size);
				int randomId = noContainNoLightStarsList.get(randomIndex).getId();
				if (valiStars(integerList, randomId, valiMap) >= 5) {
					break;
				}
				if (index > 1000) {//防止死循环
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();//格式：位置_星座id;
		int bs = 0;
		
		Collections.shuffle(integerList);//洗牌
		
		for (Integer integer : integerList) {
			bs++;
			sb.append(bs + "_").append(integer + "").append(";");
		}
		return StringUtil.noContainLastString(sb.toString());
	}
	
	/**
	 * 刷新奖励
	 * @author mp
	 * @date 2015-12-7 下午2:22:53
	 * @param gradeId
	 * @param refreshType 0-自然刷新 1-系统刷新
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static String refreshReward (int gradeId, int refreshType) {
		
		StringBuilder sb = new StringBuilder();
		
		for (DictHoldStarRewardPos obj : DictList.dictHoldStarRewardPosList) {
			int starNum = obj.getStarNum();
			List<DictHoldStarGradeReward> holdStarRewardNoStarNumList = (List<DictHoldStarGradeReward>)DictMapList.dictHoldStarGradeRewardMap.get(starNum);
			List<DictHoldStarGradeReward> holdStarGradeRewardList = new ArrayList<>();
			int randomBase = 0;
			for (DictHoldStarGradeReward holdStarGradeRewardObj : holdStarRewardNoStarNumList) {
				if (refreshType == 1) {
					if (holdStarGradeRewardObj.getHoldStarGradeId() == gradeId && holdStarGradeRewardObj.getIsSysRefresh() == refreshType) {
						holdStarGradeRewardList.add(holdStarGradeRewardObj);
						randomBase += holdStarGradeRewardObj.getWeight();
					}
				} else {
					if (holdStarGradeRewardObj.getHoldStarGradeId() == gradeId) {
						holdStarGradeRewardList.add(holdStarGradeRewardObj);
						randomBase += holdStarGradeRewardObj.getWeight();
					}
				}
			}
			
			//按权重随出一个
			int random = RandomUtil.getRangeInt(1, randomBase);
			int randomSum = 0;
			DictHoldStarGradeReward holdStarGradeReward = null;
			for (DictHoldStarGradeReward randomHoldStarGradeReward : holdStarGradeRewardList) {
				randomSum += randomHoldStarGradeReward.getWeight();
				if (random <= randomSum) {
					holdStarGradeReward = randomHoldStarGradeReward;
					break;
				}
			}
			//格式：  位置(根据位置找星数)_占星奖励Id(根据id找物品)_状态(1-已领取 0-可领取);
			sb.append(obj.getId() + "_").append(holdStarGradeReward.getId() + "_").append("0").append(";");
		}
		
		return StringUtil.noContainLastString(sb.toString());
	}
	
	/**
	 * 验证星数
	 * @author mp
	 * @date 2015-12-7 上午11:38:39
	 * @param id
	 * @return
	 * @Description
	 */
	private static int valiStars (List<Integer> integerList, int id, Map<Integer, Integer> valiMap) {
		if (integerList.contains(id)) {
			int value = valiMap.get(id);
			if (value < 2) {
				integerList.add(id);
				valiMap.put(id, (value + 1));
			}
		} else {
			integerList.add(id);
			valiMap.put(id, 1);
		}
		return integerList.size();
	}
	
	public static void main(String[] args) throws Exception{
		
		List<InstPlayerHoldStar> instPlayerHoldStarList = new ArrayList<>();
		System.out.println(instPlayerHoldStarList.size());
		
/*		SpringUtil.getSpringContext();
		DictData.loadDictData();
		
		for (int i = 0; i < 100; i++) {
			System.out.println(refreshUpStars());
		}*/
		
		/*
		for (int i = 0; i < 10000; i++) {
			List<Integer> integerList = new ArrayList<>();
			Map<Integer, Integer> valiMap = new HashMap<>();
			int index = 0;
			for (;;) {
				index++;
				int random = RandomUtil.getRangeInt(1, 20);
				if (valiStars(integerList, random, valiMap) >= 4) {
					break;
				}
			}
			if (index > 4) {
				System.out.println(index + "" + integerList);
			}
		}
	*/}
}
