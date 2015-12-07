package mmo.module.gm.net.logic.parser;

import java.util.Date;

import mmo.common.protocol.command.ProGmServer_9000;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.progress.ProgressFrame;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.net.extension.logic.ASessionParser;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.util.DateUtil;
import mmo.tools.util.FileUtil;

import org.apache.mina.core.buffer.IoBuffer;

public class Pros_9018_exchangeAdd extends ASessionParser {
	public Pros_9018_exchangeAdd() {
		this.protocol = ProGmServer_9000.P_9018_EXCHANGE_ADD;
	}

	@Override
	public void parse(UserSession session, IoBuffer packet) {
		int connectId = packet.getConnectId();
		String message = packet.getString();
		String note = packet.getString();
		boolean result = packet.getBoolean();
		if (result) {
			String codes = null;
			StringBuilder sb = new StringBuilder();
			while (!"end".equalsIgnoreCase(codes = packet.getString())) {
				sb.append(codes);
			}
			String file = FileUtil.FILE_SEPARATOR + note+"_" + DateUtil.formatDate(new Date()) + ".txt";
			file = file.replace(' ', '_');
			file = file.replace(':', '_');
			file = FileUtil.ROOT_DIR+FileUtil.FILE_SEPARATOR+"¶Ò»»Âë"+DateUtil.formatDate(new Date(),"yyyy-MM-dd-HH")+ file;
			FileUtil.writeDataToFile(file, sb.toString());
			message += '\n';
			message += "¶Ò»»ÂëÒÑ¾­Ð´Èë£º\n";
			message += file;
		} else {

		}
		LoggerDevelop.gm(GmOperate.S_GAME_CODE, GMNetManager.getGmUserId(), message);
		ProgressFrame.validateCode(connectId);
		MyDialog.openInformation(message);
	}
}
