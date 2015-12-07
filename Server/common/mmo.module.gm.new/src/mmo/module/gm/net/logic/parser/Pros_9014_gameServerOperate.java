package mmo.module.gm.net.logic.parser;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.common.protocol.command.ProGmServer_9000;
import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.progress.ProgressFrame;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.net.extension.logic.ASessionParser;
import mmo.tools.net.extension.session.UserSession;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class Pros_9014_gameServerOperate extends ASessionParser {
	public Pros_9014_gameServerOperate() {
		this.protocol = ProGmServer_9000.P_9014_GAME_SERVER_OPERATE;
	}

	@Override
	public void parse(UserSession session, IoBuffer packet) {
		int connectId = packet.getConnectId();
		boolean result = packet.getBoolean();
		String message = packet.getString();
		LoggerDevelop.gm(GmOperate.S_ADD_UPDATE_GAME_SERVER, GMNetManager.getGmUserId(),message);
		final int productId = packet.getInt();
		ProgressFrame.validateCode(connectId);
		if (result) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					if (MessageDialog.openQuestion(GMWindow.getInstance().getTopShell(), "询问操作", "操作已经完成，是否要重新加载服务器列表？")) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17002_APP_LIST);
						buf.putInt(productId);
						GMNetManager.sendData(buf, true);
					}
				}
			});
		} else {
			MyDialog.openInformation(message);
		}
	}
}
