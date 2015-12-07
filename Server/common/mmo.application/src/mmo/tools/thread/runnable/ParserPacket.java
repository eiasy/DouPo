package mmo.tools.thread.runnable;

import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

/**
 * 事件的执行者
 * 
 * @author 李天喜
 * 
 */
public class ParserPacket implements ILogicRunnable {
	private UserSession   session;
	private IoBuffer      packet;
	private ILogicHandler logic;

	public void update(UserSession session, IoBuffer packet, ILogicHandler logic) {
		this.session = session;
		this.packet = packet;
		this.logic = logic;
	}

	/**
	 * 处理相应的事件
	 */
	public void run() {
		parse();
		clear();
	}

	private void parse() {
		if (session != null && logic != null && packet != null) {
			logic.handlerReceiveData(session, packet);
		}
	}

	public void clear() {
		session = null;
		logic = null;
		if (packet != null) {
			PacketBufferPool.freePacketBuffer(packet);
			packet = null;
		}
	}
}
