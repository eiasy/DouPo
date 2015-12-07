package mmo.test.thread;

public class RunEntityTest implements Runnable {
	private int       index;
	private HeartbeatTest heartbeat;

	public RunEntityTest(HeartbeatTest heartbeat) {
		this.heartbeat = heartbeat;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void release() {
		RunEntityTest entity = new RunEntityTest(heartbeat);
		entity.setIndex(index + 1);
		heartbeat.execute(entity);
		this.heartbeat = null;
		System.out.println("RELEASE:" + index + " -- " + Thread.currentThread());
	}

	public void run() {
		System.out.println("RUN:" + index + " -- " + Thread.currentThread());
		if (index % 5 == 0) {
			while (1==1) {
//				try {
//					if (!Thread.currentThread().isInterrupted()) {
//						Thread.sleep(20);
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
	}
}
