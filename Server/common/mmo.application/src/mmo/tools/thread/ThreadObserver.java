package mmo.tools.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.tools.log.LoggerError;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class ThreadObserver extends Thread {
	private final static ThreadObserver instance        = new ThreadObserver();

	private static final int            EXECUTE_OFFSET  = 50;
	private final List<CThread>         threads         = new ArrayList<CThread>();
	private long                        nextExecuteTime = System.currentTimeMillis();
	private Queue<CThread>              overtimes       = new ConcurrentLinkedQueue<CThread>();
	private Queue<CThread>              newThreads      = new ConcurrentLinkedQueue<CThread>();

	public static final ThreadObserver getInstance() {
		return instance;
	}

	static {
		instance.start();
		instance.setName("MMO-ThreadObserver");
	}

	public void run() {
		long currTime = 0;
		int threadCount = 0;
		int threadIndex = 0;
		CThread thread = null;
		while (true) {
			currTime = System.currentTimeMillis();
			if (currTime > nextExecuteTime) {
				nextExecuteTime = currTime + EXECUTE_OFFSET;
				threadCount = threads.size();
				for (threadIndex = 0; threadIndex < threadCount; threadIndex++) {
					thread = threads.get(threadIndex);
					if (thread.isOvertime(currTime)) {
						overtimes.offer(thread);
					}
				}
				while ((thread = overtimes.poll()) != null) {
					threads.remove(thread);
					CThread newThread = new CThread(thread.getThreadName(), thread.getThreadIndex() + 1);

					newThread.setHeartbeat(thread.getHeartbeat());
					thread.setHeartbeat(null);

					newThread.start();
					threads.add(newThread);
					try {
						LoggerError.error("停止线程--" + thread);
						thread.toStop();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				while ((thread = newThreads.poll()) != null) {
					threads.add(thread);
				}
			}

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void addObserverThread(CThread thread) {
		newThreads.offer(thread);
	}

	public void registerHeartbeat(String threadName, AHeartbeat heartbeat) {
		CThread thread = new CThread(threadName, 1);
		thread.setHeartbeat(heartbeat);
		thread.start();
		addObserverThread(thread);
	}
}
