package mmo.tools.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CThreadFactory implements ThreadFactory {

	static final AtomicInteger poolNumber   = new AtomicInteger(1);
	final ThreadGroup          group;
	final AtomicInteger        threadNumber = new AtomicInteger(1);
	final String               namePrefix;

	public CThreadFactory(String tag) {
		super();
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		namePrefix = "MMO-" + tag + "-" + poolNumber.getAndIncrement() + "-thread-";
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		if (t.isDaemon())
			t.setDaemon(false);
		if (t.getPriority() != Thread.NORM_PRIORITY)
			t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}

}
