package mmo.module.gm.net.logic;

import java.util.HashMap;
import java.util.Map;

import mmo.common.protocol.command.ProGmServer_9000;
import mmo.module.gm.net.logic.parser.Pros_9003_message;
import mmo.module.gm.net.logic.parser.Pros_9006_accountList;
import mmo.module.gm.net.logic.parser.Pros_9008_roleDetail;
import mmo.module.gm.net.logic.parser.Pros_9014_gameServerOperate;
import mmo.module.gm.net.logic.parser.Pros_9018_exchangeAdd;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.logic.DefaultLogicHandler;
import mmo.tools.net.extension.logic.ISessionParser;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class GMHandler extends DefaultLogicHandler {
	private static Map<Integer, ISessionParser> packetParser = new HashMap<Integer, ISessionParser>();
	private static final ISessionParser         parser_9003  = new Pros_9003_message();
	private static final ISessionParser         parser_9006  = new Pros_9006_accountList();
	private static final ISessionParser         parser_9008  = new Pros_9008_roleDetail();
	private static final ISessionParser         parser_9014  = new Pros_9014_gameServerOperate();
	private static final ISessionParser         parser_9018  = new Pros_9018_exchangeAdd();
	static {
		init();
	}

	private static void init() {
		addParser(parser_9003);
		addParser(parser_9006);
		addParser(parser_9008);
		addParser(parser_9014);
		addParser(parser_9018);
	}

	public GMHandler() {

	}

	public boolean handlerReceiveData(UserSession session, IoBuffer packet) {
		if (packet != null) {
			try {
				int protocol = packet.getInt();
				LoggerError.warn("" + protocol);
				switch (protocol) {
					case ProGmServer_9000.P_9003_MESSAGE: {
						parser_9003.parse(session, packet);
						break;
					}
					case ProGmServer_9000.P_9006_LOAD_ACCOUNTS: {
						parser_9006.parse(session, packet);
						break;
					}
					case ProGmServer_9000.P_9008_ROLE_DETAIL: {
						parser_9008.parse(session, packet);
						break;
					}
					case ProGmServer_9000.P_9014_GAME_SERVER_OPERATE: {
						parser_9014.parse(session, packet);
						break;
					}
					case ProGmServer_9000.P_9018_EXCHANGE_ADD: {
						parser_9018.parse(session, packet);
						break;
					}
					default: {
						ISessionParser parse = packetParser.get(protocol);
						if (parse != null) {
							parse.parse(session, packet);
							return true;
						} else {
							System.out.println("Î´ÖªÐ­Òé" + protocol);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return false;
	}

	private static void addParser(ISessionParser parse) {
		if (parse == null) {
			return;
		}
		packetParser.put(parse.getProtocol(), parse);
	}
}
