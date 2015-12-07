package com.huayi.doupo.activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.activity.run.IActRunnable;
import com.huayi.doupo.activity.run.IDatabaseRunnable;
import com.huayi.doupo.activity.thread.CThreadFactory;
import com.huayi.doupo.activity.thread.ThreadObserver;

public class ThreadManager {
	private final static ExecutorService actThreadPoll = new ThreadPoolExecutor(1, 1, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("ACT-DB"));

	public final static void accessDatabase(IDatabaseRunnable run) {
		actThreadPoll.execute(run);
	}

	public final static void initThread() {
		ThreadObserver.getInstance().registerHeartbeat("ACT-LOGIC", HeartbeatActivyity.getInstance());
	}
	
	public final static void execute(IActRunnable run){
		HeartbeatActivyity.getInstance().execute(run);
	}

}