package mmo.tools.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor {
	private static ExecutorService pool = new ThreadPoolExecutor(4, 4, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(),
	                                            new CThreadFactory("default"));

	public final static void execute(Runnable cmd) {
		if (pool == null) {
			pool = new ThreadPoolExecutor(4, 4, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("default"));// Executors.newFixedThreadPool(4);
		}
		pool.execute(cmd);
	}
}
