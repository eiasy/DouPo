package com.huayi.doupo.logic.core;

import java.util.List;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.SysQuartzJob;
import com.huayi.doupo.logic.util.QuartzUtil;

/**
 * 定时器服务类
 * @author mp
 * @date 2013-11-8 上午10:58:51
 */
public class QuartzServer extends DALFactory{

	public static void start() throws Exception{
		List<SysQuartzJob> sysQuartzJobList = getSysQuartzJobDAL().getList("", 0);
		for(SysQuartzJob job : sysQuartzJobList){
			QuartzUtil.addJob(job);
		}
	}
	
	public static void main(String[] args) {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
