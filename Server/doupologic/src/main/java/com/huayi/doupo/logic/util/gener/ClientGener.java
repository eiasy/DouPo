package com.huayi.doupo.logic.util.gener;

import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 客户端工具类生成
 * @author mp
 * @date 2014-10-1 上午9:49:16
 */
public class ClientGener {
	
	public static void main(String[] args){
		
		try {
			
			//生成实体bean
			GenerBeanTemPlate.generate();
			LogUtil.out("实体bean生成完成");
			
			//生成字典相关bean
//			DictData.dictData(1);
//			LogUtil.out("字典相关bean生成完成");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
