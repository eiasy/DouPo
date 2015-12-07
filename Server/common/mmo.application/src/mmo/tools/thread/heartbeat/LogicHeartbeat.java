package mmo.tools.thread.heartbeat;

import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.runnable.ILogicRunnable;
import mmo.tools.thread.runnable.ParserPacket;

import org.apache.mina.core.buffer.IoBuffer;

public class LogicHeartbeat extends AHeartbeat {
	public void execute(ILogicRunnable run) {
		super.addEvent(run);
	}

	public void addPacketEvent(UserSession session, IoBuffer packet, ILogicHandler logic) {
		ParserPacket event = new ParserPacket();
		event.update(session, packet, logic);
		super.addEvent(event);
	}
}
