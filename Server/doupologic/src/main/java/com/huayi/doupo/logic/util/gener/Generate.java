package com.huayi.doupo.logic.util.gener;

import com.huayi.doupo.base.util.logic.system.LogUtil;


public class Generate {

	/**
	 * 生成工具类
	 * @author mp
	 * @version 1.0, 2013-6-20 上午11:23:09
	 * @param args
	 */
	public static void main(String[] args){
		
		try {
			
			//生成实体bean
			GenerBeanTemPlate.generate();
			LogUtil.out("实体bean生成完成");
			
			//生成Dao
//			GenerDaoTemPlate.generate();
			
			//生成字典相关bean
//			GenerDictTemplate.generate();
//			LogUtil.out("字典相关bean生成完成");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
