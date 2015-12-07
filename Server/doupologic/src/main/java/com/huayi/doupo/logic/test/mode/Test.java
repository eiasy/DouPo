package com.huayi.doupo.logic.test.mode;

import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.util.logic.system.SpringUtil;

public class Test extends DALFactory{
	public static void main(String[] args) throws Exception{
		
		//启动工作线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadPoolUtils.startPool();
			}
		}).start();
		
		//加载资源
		SpringUtil.getSpringContext();
		
		//测试
		InstPlayer instPlayer = getInstPlayerDAL().getModel(67, 0);
		instPlayer.setOpenId("task400");
		QueueUtil.put(RunnableObj.instance(Operate.Update, instPlayer));
		TimeUnit.SECONDS.sleep(2);
		QueueUtil.put(RunnableObj.instance(Operate.Delete, instPlayer));
		
		//后修改的会直接影响前边传进去的对象
//		instPlayer.setOpenId("task5");
	}
}
