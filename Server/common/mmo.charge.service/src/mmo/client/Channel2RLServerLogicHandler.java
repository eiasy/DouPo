package mmo.client;

import java.util.HashMap;
import java.util.Map;

import mmo.common.proto.ProtobufMessage;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.net.extension.logic.DefaultLogicHandler;
import mmo.tools.net.extension.logic.ISessionParser;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class Channel2RLServerLogicHandler extends DefaultLogicHandler {
	private static Map<Integer, ISessionParser> parsers = new HashMap<Integer, ISessionParser>();

	static {
	}

	private static void addParser(ISessionParser parser) {
		if (parser != null) {
			parsers.put(parser.getProtocol(), parser);
		}
	}

	public boolean handlerReceiveData(UserSession session, IoBuffer packet) {
		if (packet != null && packet.limit() >= 4) {
			try {
				byte[] data = new byte[packet.limit()];
				packet.get(data);
				ProtobufMessage.Msg message = ProtobufMessage.Msg.parseFrom(data);
				int protocol = message.getHeader();
				System.out.println("protocol="+protocol);
				System.out.println(message);
//				if (LoggerFilter.isLogInfo()) {
//					LoggerDevelop.loggerProtocol(session.getRole(), protocol);
//				}
				switch (protocol) {
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return false;
	}
}
