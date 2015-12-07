package com.huayi.doupo.activity.thread;
/**
 * 需要持续刷新的实体
 * 
 * @author 李天喜
 * 
 */
public interface IExecuteEntity {
	void run(long currTime);
}
