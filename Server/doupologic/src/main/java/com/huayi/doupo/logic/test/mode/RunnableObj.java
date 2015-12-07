package com.huayi.doupo.logic.test.mode;

import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;

/**
 * 任务对象类
 * @author mp
 * @date 2015-1-12 上午10:03:00
 */
public class RunnableObj extends DALFactory implements Runnable{

	/**
	 * 操作类型
	 */
	private Operate operType;
	
	/**
	 * 操作对象 
	 */
	private Object operObj;
	
	
	public RunnableObj (Operate operate, Object object) {
		this.operType = operate;
		this.operObj = object;
	}
	
	/**
	 * 获取实例
	 * @author mp
	 * @date 2015-1-12 下午5:30:01
	 * @param operate
	 * @param object
	 * @return
	 * @Description
	 */
	public static RunnableObj instance (Operate operate, Object object) {
		return new RunnableObj(operate, object) ;
	}
	
	/**
	 * 内部run方法
	 * @author mp
	 * @date 2015-1-12 上午10:27:49
	 * @Description
	 */
	public void innerrun () {
		if (operObj instanceof InstPlayer) {
			try {
				if (operType == Operate.Update) {
					getInstPlayerDAL().update((InstPlayer)operObj, 0);
				} else if (operType == Operate.Insert) {
					getInstPlayerDAL().add((InstPlayer)operObj, 0);
				} else if (operType == Operate.Delete) {
					getInstPlayerDAL().deleteById(((InstPlayer)operObj).getId(), 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 线程任务
	 */
	@Override
	public void run() {
		try {
			System.out.println("threadId = " + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		innerrun();
	}

}
