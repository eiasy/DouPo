package mmo.tools.thread;

import mmo.tools.log.LoggerError;
import mmo.tools.thread.heartbeat.AHeartbeat;

/**
 * 自定义线程
 * 
 * @author 李天喜
 * 
 */
public class CThread extends Thread {
	protected static final int LOG_OFFSET    = 1000 * 60 * 60;
	protected static final int OVERTIME      = 1000 * 60;
	/** 持续处理的最长时间 */
	protected final static int EXECUTE_TIME  = 30;
	/** 线程暂停时间 */
	protected final static int SLEEP_TIME    = 5;
	/***********************************************************************************/
	/** 线程是否正在运行 */
	protected boolean          running       = false;
	/** 记录日志间隔 */
	protected long             logTime       = 0;
	/** 线程心跳对象，定期进行刷新 */
	protected AHeartbeat       heartbeat;

	protected long             lastHeartbeat = System.currentTimeMillis();
	protected int              threadIndex;
	protected String           threadName;

	public CThread(String threadName, int threadIndex) {
		super("MMO-" + threadName + "-" + threadIndex);
		this.threadName = threadName;
		this.threadIndex = threadIndex;
	}

	public int getThreadIndex() {
		return threadIndex;
	}

	public String getThreadName() {
		return threadName;
	}

	public AHeartbeat getHeartbeat() {
		return heartbeat;
	}

	private String getRunningName() {
		AHeartbeat heartbeat = this.heartbeat;
		if (heartbeat == null) {
			return "[heartbeat = null]";
		}
		return heartbeat.getRuningName();
	}

	public void setHeartbeat(AHeartbeat heartbeat) {
		this.heartbeat = heartbeat;
		if (this.heartbeat != null) {
			this.heartbeat.setHost(this);
		}
	}

	/**
	 * 启动线程并初始化缓冲队列
	 */
	public synchronized void start() {
		if (!running) {
			running = true;
			super.start();
		}
	}

	public void toStop() {
		running = false;
	}

	public void run() {
		long currTime = System.currentTimeMillis();
		while (running) {
			try {
				currTime = System.currentTimeMillis();
				lastHeartbeat = currTime;
				if (heartbeat == null) {
					return;
				}
				heartbeat.run(currTime);
				if (currTime > logTime) {
					logTime = currTime + LOG_OFFSET;
					LoggerError.messageLog.warn(getName());
				}

			} catch (Exception e) {
				LoggerError.error(getName() + "-事件线程抛出异常", e);
			}
			try {
				synchronized (this) {
					wait(SLEEP_TIME);
				}
			} catch (InterruptedException e) {
				LoggerError.error(getName() + "-sleep抛出异常", e);
			}
		}
		LoggerError.messageLog.error(getName() + "线程退出");
		running = false;
	}

	public boolean isOvertime(long currTime) {
		boolean overtime = currTime - lastHeartbeat > OVERTIME;
		if (overtime) {
			LoggerError.error((currTime - lastHeartbeat) + " , " + OVERTIME + " --ThreadOvertime " + this + ", " + getRunningName());
		}
		return overtime;
	}

	public void isStarted() {
		if (running) {
			return;
		}
		running = true;
		super.start();
	}

}
