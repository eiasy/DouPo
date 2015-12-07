package com.huayi.doupo.logic.handler.util;

import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictHoleConsume;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticThingType;


/**
 * 字典数据工具类
 * @author mp
 * @date 2014-7-2 下午2:03:14
 */
public class DictUtil  extends DALFactory{
	
	/**
	 * 根据次数获取打孔消耗
	 * @author mp
	 * @date 2014-7-2 下午2:04:07
	 * @param holeConsumeList
	 * @param times
	 * @return
	 * @Description
	 */
	public static DictHoleConsume getDictHoleConsume(List<DictHoleConsume> holeConsumeList, int times){
		DictHoleConsume holeConsume = null;
		for(DictHoleConsume obj : holeConsumeList){
			if(obj.getTimes() == times){
				holeConsume = obj;
				break;
			}
		}
		return holeConsume;
	}
	
	/**
	 * 获取下一级宝石对象
	 * @author mp
	 * @date 2014-7-2 下午4:58:23
	 * @param instPlayerThing
	 * @return
	 * @Description
	 */
	public static DictThing getNextLevelGemObj(InstPlayerThing instPlayerThing){
		DictThing currThing = DictMap.dictThingMap.get(instPlayerThing.getThingId() + "");
		final int thingType = currThing.getThingTypeId();
		final int fightPropId = currThing.getFightPropId();
		final int level = instPlayerThing.getLevel() + 1;
		Map<String,DictThing> filtedMap = Maps.filterValues(DictMap.dictThingMap,
				new Predicate<DictThing>() {
					public boolean apply(DictThing dictThing) {
						return dictThing.getThingTypeId() == thingType && dictThing.getFightPropId() == fightPropId && dictThing.getLevel() == level;
					}
				});
		return filtedMap.values().iterator().next();
	}
	
	
}
