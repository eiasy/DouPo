package mmo.common.service.eventcenter.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.pool.CThreadFactory;
import mmo.tools.thread.runnable.CRunnable;

public class ThreadManager {
	private final static ExecutorService handleContext = new ThreadPoolExecutor(6, 6, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("database"));

	public final static void handleContext(CRunnable run) {
		handleContext.execute(run);
	}

	public final static void initThread() {
		ThreadObserver.getInstance().registerHeartbeat("EVENT-CHARGE", HeartbeatEventCharge.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("EVENT-ACCOUNT", HeartbeatEventAccount.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("EVENT-DEFAULT", HeartbeatEventDefault.getInstance());
	}
}
