package mmo.common.bean.role;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class Broadcast implements Runnable {
	// protected static ConcurrentLinkedQueue<Broadcast> broadcastQueue = new ConcurrentLinkedQueue<Broadcast>();
	private Role     role;
	private IoBuffer buf;
	private boolean  self;
	private int      except;

	public static final Broadcast getBroadcast() {
		// Broadcast bc = broadcastQueue.poll();
		// if (bc == null) {
		// bc = new Broadcast();
		// }
		return new Broadcast();
	}

	Broadcast() {

	}

	public void reset(Role role, IoBuffer buf, boolean self) {
		this.role = role;
		this.buf = buf;
		this.self = self;
		this.except = 0;
	}

	public void reset(Role role, IoBuffer buf, boolean self, int except) {
		this.role = role;
		this.buf = buf;
		this.self = self;
		this.except = except;
	}

	public int getExcept() {
		return except;
	}

	@Override
	public void run() {
		try {
			if (except > 0) {
				List<Role> vr = role.getVisiableRoles();
				if (vr != null) {
					int size = vr.size();
					for (int i = 0; i < size; i++) {
						if (vr.get(i).getId() != except) {
							vr.get(i).sendData(buf.duplicateBuffer());
						}
					}
					if (self) {
						role.sendData(buf);
					} else {
						PacketBufferPool.freePacketBuffer(buf);
					}
				}
			} else {
				List<Role> vr = role.getVisiableRoles();
				if (vr != null) {
					int size = vr.size();
					for (int i = 0; i < size; i++) {
						vr.get(i).sendData(buf.duplicateBuffer());
					}
					if (self) {
						role.sendData(buf);
					} else {
						PacketBufferPool.freePacketBuffer(buf);
					}
				}
			}
		} finally {
			except = 0;
			role = null;
			buf = null;
			self = false;
			// broadcastQueue.offer(this);
		}
	}

}
