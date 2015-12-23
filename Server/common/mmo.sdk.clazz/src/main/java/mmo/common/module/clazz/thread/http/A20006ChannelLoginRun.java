package mmo.common.module.clazz.thread.http;

import java.net.SocketAddress;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.config.update.ClientOperate;
import mmo.common.module.sdk.platform.PlatformManager;
import mmo.common.module.sdk.platform.version.ChannelCooperate;
import mmo.common.module.sdk.platform.version.PlatformRelease;
import mmo.common.module.sdk.server.ServerManager;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

public class A20006ChannelLoginRun extends RequestHttpRun {
	private IoSession session;

	public A20006ChannelLoginRun(IoSession session, Map<String, String> parameter) {
		super(parameter);
		this.session = session;
	}

	@Override
	public void run() {
		NetAddress address = ProjectCofigs.getNetAddress(HttpCData.ACCOUNT_URL_NAME);
		if (address == null) {
			LoggerError.error("未设置账号中心地址");
			return;
		}
		try {
			String json = request(address.getUrl() + HttpCData.ContextsAccount.C_20006);
			JSONObject message = new JSONObject();
			JSONObject result = JSONObject.fromObject(json);
			boolean isSub = "anysdk".equalsIgnoreCase(parameter.get(HttpCData.A20001.channelId));
			message.put(HttpCData.AccountDoupo.message, result.get(HttpCData.AccountDoupo.message));
			message.put(HttpCData.AccountDoupo.result, result.get(HttpCData.A20001.status));
			if (result.getInt("status") == 0) {
				int platform = Integer.parseInt(parameter.get(HttpCData.A20001.productId));
				message.put(HttpCData.AccountDoupo.userId, result.get(HttpCData.A20001.userid));
				message.put(HttpCData.AccountDoupo.username, result.get(HttpCData.A20001.username));
				String chargeNotify = null;
				PlatformRelease pr = PlatformManager.getInstance().getPlatform(platform);
				if (pr != null) {
					ChannelCooperate cc = pr.getChannel(parameter.get(HttpCData.A20001.channelId));
					if (cc != null) {
						chargeNotify = cc.getParameter(parameter.get(HttpCData.A20001.channelSub), "charge_notify");
					}
				}
				if (chargeNotify != null && chargeNotify.startsWith("http")) {
					message.put(HttpCData.A20001.notifyUri, chargeNotify);
				} else {
					message.put(HttpCData.A20001.notifyUri, result.getString(HttpCData.A20001.notifyUri));
				}

				message.put(HttpCData.AccountDoupo.securityCode, result.getString(HttpCData.A20001.securityCode));
				message.put(HttpCData.AccountDoupo.accountId, result.getInt(HttpCData.AccountDoupo.accountId));
				message.put(HttpCData.AccountDoupo.loginCount, result.getString(HttpCData.AccountDoupo.loginCount));
				String addIp = "";
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					addIp = sa.toString();
				}
				boolean isTest = isTest(platform, parameter.get(HttpCData.A20001.clientCode), parameter.get(HttpCData.A20001.channelId), parameter.get(HttpCData.A20001.channelSub), addIp);
				message.put(HttpCData.AccountDoupo.servers_all, ServerManager.getInstance().getServerAll(platform, isTest, isSub));
				message.put(HttpCData.AccountDoupo.servers_suggest, ServerManager.getInstance().getServerSuggest(platform, isTest, isSub));
				String customData = parameter.get(HttpCData.A20001.customData);
				if (customData != null) {
					message.put(HttpCData.A20001.customData, customData);
				}
				int[] history = StringUtil.string2IntArray(result.getString(HttpCData.AccountDoupo.lastEnter), ':');
				if (history != null && history.length > 0) {
					message.put(HttpCData.AccountDoupo.servers_history, ServerManager.getInstance().getServerHistory(platform, history, isSub));
				}
			}
			if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
				LoggerError.error("login result#" + message.toString());
			}
			if (session != null) {
				HttpResponseMessage response = new HttpResponseMessage();
				response.setContentType("text/plain;charset=utf-8");
				response.appendBody(message.toString());
				session.write(response).addListener(IoFutureListener.CLOSE);
			}
		} catch (Exception e) {
			LoggerError.error("渠道用户账号登录信息出错", e);
		}
	}

	public boolean isTest(int platform, String codeVersion, String channel, String channelSub, String ip) {
		PlatformRelease pr = PlatformManager.getInstance().getPlatform(platform);
		if (pr == null) {
			return false;
		}
		return isTestplatform(pr, codeVersion, channel, channelSub, ip);
	}

	public boolean isTestplatform(PlatformRelease pr, String codeVersion, String channel, String channelSub, String ip) {
		ChannelCooperate cc = pr.getChannel(channel);
		if (cc == null) {
			return false;
		} else {
			if (cc.channelCheckCodeVersion(codeVersion, channelSub, pr.isStrict()) == ClientOperate.OPT_HIGHT_VERSION) {
				return true;
			}

			if (pr.isOpenTestIp() && pr.hashIp(ip)) {
				return true;
			}
		}
		return false;
	}

	public final static boolean platformCheckCodeVersion(PlatformRelease pr, String codeVersion, String channel, String channelSub, String ip) {
		ChannelCooperate cc = pr.getChannel(channel);
		int result = cc.channelCheckCodeVersion(codeVersion, channelSub, pr.isStrict());
		switch (result) {
			case ClientOperate.OPT_HIGHT_VERSION: {
				return true;
			}
			default: {
				return false;
			}
		}
	}
}
