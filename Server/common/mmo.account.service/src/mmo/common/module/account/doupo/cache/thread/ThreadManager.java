package mmo.common.module.account.doupo.cache.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.pool.CThreadFactory;
import mmo.tools.thread.runnable.IDatabaseRunnable;
import mmo.tools.thread.runnable.IHttpRequest;

public class ThreadManager {
	private final static ExecutorService requestHttp =  Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP"));// new ThreadPoolExecutor(4, 4, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP"));

	// private final static ExecutorService loadAccountThreadPoll = new ThreadPoolExecutor(4, 4, 1000 * 60, TimeUnit.DAYS,
	// new LinkedBlockingQueue<Runnable>(), new CThreadFactory("LOAD-ACCOUNT"));

	public static void requestHttp(IHttpRequest request) {
		requestHttp.execute(request);
	}

	public final static void accessDatabase(IDatabaseRunnable run) {
		AccountDatabaseHeartbeat.getInstance().execute(run);
	}

	public final static void initThread() {
		ThreadObserver.getInstance().registerHeartbeat("ACCOUNT-VALIDATE", AccountValidateHeartbeat.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("ACCOUNT-DATABASE", AccountDatabaseHeartbeat.getInstance());
	}

}
