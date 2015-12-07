package mmo.test.thread;

public class TestThread extends Thread {
	private static final int OVERTIME        = 1000 * 10;
	private int              heartbeatOffset = 1000;
	private long             lastHeartbeat   = System.currentTimeMillis();
	private long             nextHeartbeat   = lastHeartbeat;
	private int              threadIndex;
	private String           threadName;
	private HeartbeatTest    heartbeat;

	public TestThread(String threadName, int threadIndex) {
		super(threadName + "-" + threadIndex);
		this.threadName = threadName;
		this.threadIndex = threadIndex;
	}

	public String getThreadName() {
		return threadName;
	}

	public int getThreadIndex() {
		return threadIndex;
	}

	public HeartbeatTest getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(HeartbeatTest heartbeat) {
		this.heartbeat = heartbeat;
		if (heartbeat != null) {
			this.heartbeat.setHost(this);
		}
	}

	public void start() {
		if (checkStart()) {
			super.start();
		}
	}

	private boolean checkStart() {
		if (heartbeat == null) {
			throw new NullPointerException("线程[" + getName() + "]未设置心跳。");
		}
		return true;
	}

	public void run() {
		long currTime = 0;
		while (true) {
			try {
				currTime = System.currentTimeMillis();
				if (currTime > nextHeartbeat) {
					nextHeartbeat = currTime + heartbeatOffset;
					lastHeartbeat = currTime;
					if (heartbeat == null) {
						return;
					}
					heartbeat.run(currTime);
				}
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isOvertime(long currTime) {
		return currTime - lastHeartbeat > OVERTIME;
	}
}
