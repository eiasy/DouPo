package mmo.common.module.sdk.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.common.module.sdk.token.TokenHeartbeat;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.pool.CThreadFactory;
import mmo.tools.thread.runnable.IHttpRequest;

public class ThreadManager {
	private static ExecutorService requestHttp =  Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP"));//new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP"));

	private final static Map<String, ExecutorService> channelThread = new HashMap<String, ExecutorService>();
	private final static AtomicInteger threadCount = new AtomicInteger();

	public static void requestHttp(String channel, IHttpRequest request) {
		if (channel == null) {
			return;
		}
		ExecutorService tpe = channelThread.get(channel);
		if (tpe != null) {
			if (tpe.isShutdown()) {
				channelThread.remove(channel);
				tpe = null;
			}
		}
		if (tpe == null) {
			if (threadCount.get() < 30) {
				threadCount.incrementAndGet();
				LoggerError.warn("创建线程池|" + channel + "|" + threadCount.get());
				tpe = Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP-" + channel));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP-" + channel));
				channelThread.put(channel, tpe);
			} else {
				LoggerError.warn("线程池数量达到上限|" + channel + "|" + threadCount.get());
				tpe = requestHttp;
				if (tpe.isShutdown()) {
					requestHttp = Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP-" + channel));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP-" + channel));
					tpe = requestHttp;
				}
			}
		}
		tpe.execute(request);
	}

	public final static void initThread() {
		ThreadObserver.getInstance().registerHeartbeat("CHANNEL_TOKEN", TokenHeartbeat.getInstance());
	}
}
