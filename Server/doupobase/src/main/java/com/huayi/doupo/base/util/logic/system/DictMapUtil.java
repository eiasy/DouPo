package com.huayi.doupo.base.util.logic.system;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.base.ConvertUtil;

/**
 * 字典Map工具类
 * @author mp
 * @date 2014-8-16 上午9:03:13
 */
public class DictMapUtil {
	
	/**
	 * 获取系统配置表数值 Integer类型
	 * @author mp
	 * @date 2014-8-16 上午9:03:25
	 * @param id
	 * @return
	 * @Description
	 */
	public static int getSysConfigIntValue (int id) {
		return ConvertUtil.toInt(DictMap.dictSysConfigMap.get(id + "").getValue());
	}
	
	/**
	 * 获取系统配置表数值 Float类型
	 * @author mp
	 * @date 2014-8-16 上午9:08:52
	 * @param id
	 * @return
	 * @Description
	 */
	public static float getSysConfigFloatValue (int id) {
		return DictMap.dictSysConfigMap.get(id + "").getValue();
	}
}
