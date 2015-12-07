package mmo.server.ptotobuf;

import java.util.HashMap;
import java.util.Map;

import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.net.extension.logic.DefaultLogicHandler;
import mmo.tools.net.extension.logic.ISessionParser;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class Port4GameServerLogicHandler extends DefaultLogicHandler {
	/** 日志管理类 */
	private static Map<Integer, ISessionParser> parsers = new HashMap<Integer, ISessionParser>();

	static {
	}

	private static void addParser(ISessionParser parser) {
		if (parser != null) {
			parsers.put(parser.getProtocol(), parser);
		}
	}

	public boolean handlerReceiveData(UserSession session, IoBuffer packet) {
		if (packet != null) {
			try {
				int protocol = packet.getInt();
				if (protocol != 15002) {
					if (LoggerFilter.isLogInfo()) {
						LoggerDevelop.loggerProtocol(session.getRole(),
								protocol);
					}
				}
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