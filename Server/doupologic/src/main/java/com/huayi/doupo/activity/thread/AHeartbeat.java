package com.huayi.doupo.activity.thread;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 线程的心跳类型
 * 
 * @author 李天喜
 * 
 */
public abstract class AHeartbeat {
	/** 持续处理的最长时间 */
	public final static int EXECUTE_TIME = 30;
	/** 线程暂停时间 */
	public final static int SLEEP_TIME = 10;
	/**************************************************************************************************/
	private CThread host;
	/** 事件队列 */
	private ConcurrentLinkedQueue<CRunnable> eventQueue = new ConcurrentLinkedQueue<CRunnable>();
	/** 需要持续刷新的实体 */
	private ArrayList<IExecuteEntity> executeEntities = new ArrayList<IExecuteEntity>();
	/** 持续处理时间 */
	private long exeTime = 0;
	private CRunnable runEntity = null;
	private Object running;
	private long startTime = 0;
	private long runntime = 0;

	/** 补丁 */
	private CRunnable runPatch = null;

	public CRunnable getRunPatch() {
		return runPatch;
	}

	public void setRunPatch(CRunnable runPatch) {
		this.runPatch = runPatch;
	}

	public void setHost(CThread host) {
		this.host = host;
	}

	public final void run(long currTime) {
		if (host != Thread.currentThread()) {
			return;
		}
		try {
			exeTime = +currTime + EXECUTE_TIME;
			while ((runEntity = eventQueue.poll()) != null) {
				startTime = System.currentTimeMillis();
				running = runEntity;
				runEntity.run();
				runntime = System.currentTimeMillis() - startTime;
				if (runntime > 1000) {
					LogUtil.error(Thread.currentThread() + "超长时间运行[" + (runEntity != null ? runEntity.getClass().getName() : runEntity) + "], time=" + runntime);
				}
				running = null;
				if (System.currentTimeMillis() > exeTime) {
					break;
				}
			}
			execute(currTime);
			callback();

			runEntity = runPatch;
			if (runEntity != null) {
				running = runEntity;
				runEntity.run();
				runEntity = null;
				running = null;
			}
			exeTime = System.currentTimeMillis() - currTime;
			if (exeTime > 1000 * 5) {
				LogUtil.error(Thread.currentThread() + "执行时间超长：" + exeTime);
			}
		} catch (Exception e) {
			LogUtil.error(Thread.currentThread() + "事件抛出异常" + runEntity, e);
		}
	}

	public void callback() {
		// TODO Auto-generated method stub

	}

	private final void execute(long currTime) {
		int rSize = executeEntities.size();
		if (rSize > 0) {
			IExecuteEntity execute = null;
			for (int ri = 0; ri < rSize; ri++) {
				startTime = System.currentTimeMillis();
				execute = executeEntities.get(ri);
				running = execute;
				execute.run(currTime);
				runntime = System.currentTimeMillis() - startTime;
				if (runntime > 1000) {
					LogUtil.error(Thread.currentThread() + "超长时间运行[" + running.getClass().getName() + "], time=" + runntime);
				}
				running = null;
			}
		}
	}

	public final void addExecuteEntity(IExecuteEntity executeEntity) {
		if (executeEntity != null) {
			executeEntities.add(executeEntity);
		}
	}

	public String getRuningName() {
		Object running = this.running;
		if (running != null) {
			return "[" + running.getClass().getName() + "]";
		}
		return "[running = null]";
	}

	protected final void addEvent(CRunnable run) {
		eventQueue.offer(run);
		CThread hostTemp = host;
		if (hostTemp != null) {
			synchronized (hostTemp) {
				hostTemp.notify();
			}
		}
	}

	public CRunnable getRunEntity() {
		return runEntity;
	}
}
