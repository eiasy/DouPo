package mmo.module.gm.net.logic.parser;

import java.util.ArrayList;
import java.util.List;

import mmo.common.account.HttpCData;
import mmo.common.protocol.command.ProGmServer_9000;
import mmo.module.gm.bean.BeanAccount;
import mmo.module.gm.config.TabItemConfig;
import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.gui.progress.ProgressFrame;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.net.extension.logic.ASessionParser;
import mmo.tools.net.extension.session.UserSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;

public class Pros_9006_accountList extends ASessionParser {
	public Pros_9006_accountList() {
		this.protocol = ProGmServer_9000.P_9006_LOAD_ACCOUNTS;
	}

	@Override
	public void parse(UserSession session, IoBuffer packet) {
		int connectId = packet.getConnectId();

		List<BeanAccount> accounts = new ArrayList<BeanAccount>();
		StringBuilder sb = new StringBuilder();
		String end = "END_ACCOUNT";
		String tmp = null;
		while (!end.equals(tmp = packet.getString())) {
			sb.append(tmp);
		}
		JSONObject json = JSONObject.fromObject(sb.toString());
		JSONArray array = json.getJSONArray(HttpCData.A20001.cdata);
		for (int ai = 0; ai < array.size(); ai++) {
			JSONObject obj = array.getJSONObject(ai);
			accounts.add((BeanAccount) JSONObject.toBean(obj, BeanAccount.class));
		}
		LoggerDevelop.gm(GmOperate.S_ACCOUNT_LIST, GMNetManager.getGmUserId(), "");
		ProgressFrame.validateCode(connectId);
//		GMWindow.getInstance().gmSwitchTabItem(TabItemConfig.ITEM_16_ACCOUNT_USER_LIST, accounts);
	}
}
