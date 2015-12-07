package mmo.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadObserverTest extends Thread {
	private static final int       EXECUTE_OFFSET  = 50;
	private final List<TestThread> threads         = new ArrayList<TestThread>();
	private long                   nextExecuteTime = System.currentTimeMillis();
	private Queue<TestThread>      overtimes       = new ConcurrentLinkedQueue<TestThread>();
	private Queue<TestThread>      newThreads      = new ConcurrentLinkedQueue<TestThread>();

	/**********************************************************************************/
	
	public void run() {
		long currTime = 0;
		int threadCount = 0;
		int threadIndex = 0;
		TestThread thread = null;
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
					System.out.println("超时：" + thread.getName());
					TestThread newThread = new TestThread(thread.getThreadName(), thread.getThreadIndex() + 1);

					newThread.setHeartbeat(thread.getHeartbeat());
					// thread.setHeartbeat(null);

					RunEntityTest entity = newThread.getHeartbeat().getRunEntity();
					if (entity != null) {
						entity.release();
					}

					newThread.start();
					threads.add(newThread);
					// deadThreads.add(thread);
					try {
						System.out.println("interrupt:" + thread);
						thread.stop();
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

	public void addObserverThread(TestThread thread) {
		newThreads.offer(thread);
	}

	public static void main(String[] args) {
		ThreadObserverTest observer = new ThreadObserverTest();
		observer.setName("ThreadObserver");
		observer.start();

		TestThread thread = new TestThread("aaaa", 1);
		HeartbeatTest heartbeat = new HeartbeatTest();
		heartbeat.execute(new RunEntityTest(heartbeat));
		thread.setHeartbeat(heartbeat);
		thread.start();
		observer.addObserverThread(thread);
		
		TestThread thread0 = new TestThread("bbbb", 1);
		HeartbeatTest heartbeat0 = new HeartbeatTest();
		heartbeat0.execute(new RunEntityTest(heartbeat0));
		thread0.setHeartbeat(heartbeat0);
		thread0.start();
		observer.addObserverThread(thread0);
	}
}
