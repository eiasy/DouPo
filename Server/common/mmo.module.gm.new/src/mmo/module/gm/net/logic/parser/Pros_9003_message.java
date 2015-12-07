package mmo.module.gm.net.logic.parser;

import mmo.common.protocol.command.ProGmServer_9000;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.progress.ProgressFrame;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.net.extension.logic.ASessionParser;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;

public class Pros_9003_message extends ASessionParser {
	public Pros_9003_message() {
		this.protocol = ProGmServer_9000.P_9003_MESSAGE;
	}

	@Override
	public void parse(UserSession session, IoBuffer packet) {
		int connectId = packet.getConnectId();
		String message = packet.getString();

		ProgressFrame.validateCode(connectId);
		MyDialog.openInformation(message);
		LoggerDevelop.gm(GmOperate.S_MESSAGE_RESPONSE, GMNetManager.getGmUserId(), message);
	}
}
