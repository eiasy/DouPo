package mmo.test.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HeartbeatTest {
	/** 持续处理的最长时间 */
	public final static int                    EXECUTE_TIME = 30;
	/** 线程暂停时间 */
	public final static int                    SLEEP_TIME   = 10;
	/**************************************************************************************************/
	private TestThread                         host;
	/** 数据库操作事件队列 */
	protected ConcurrentLinkedQueue<RunEntityTest> eventQueue   = new ConcurrentLinkedQueue<RunEntityTest>();
	/** 持续处理时间 */
	private long                               exeTime      = 0;
	private RunEntityTest                          runEntity    = null;

	public void setHost(TestThread host) {
		this.host = host;
	}

	public void run(long currTime) {
		if (host != Thread.currentThread()) {
			return;
		}
		try {
			exeTime = +currTime + EXECUTE_TIME;
			while ((runEntity = eventQueue.poll()) != null) {
				runEntity.run();
				runEntity.release();
				if (System.currentTimeMillis() > exeTime) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	public void execute(RunEntityTest run) {
		eventQueue.offer(run);
	}

	public RunEntityTest getRunEntity() {
		return runEntity;
	}
}
