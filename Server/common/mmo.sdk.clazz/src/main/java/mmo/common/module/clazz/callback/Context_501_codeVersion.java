package mmo.common.module.clazz.callback;

import java.net.SocketAddress;

import mmo.common.account.HttpCData;
import mmo.common.config.update.ClientOperate;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.sdk.platform.PlatformManager;
import mmo.common.module.sdk.platform.version.ChannelCooperate;
import mmo.common.module.sdk.platform.version.PlatformRelease;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class Context_501_codeVersion extends AContextHandle {

	public Context_501_codeVersion() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		String channel_id = request.getParameter(HttpParameter.Account.channel_id);
		int product_id = Integer.parseInt(request.getParameter(HttpParameter.Account.product_id));
		String code_version = request.getParameter(HttpParameter.Account.code_version);
		String imei = request.getParameter(HttpParameter.Account.imei);
		String phone_code = request.getParameter(HttpParameter.Account.phone_code);
		int screen_width = getIntRelax(request, HttpParameter.Account.screen_width);
		int screen_height = getIntRelax(request, HttpParameter.Account.screen_height);
		String device_os = request.getParameter(HttpParameter.Account.device_os);
		String os_version = request.getParameter(HttpParameter.Account.os_version);
		String udid = request.getParameter(HttpParameter.Account.udid);
		String mac = request.getParameter(HttpParameter.Account.mac);
		String ua = request.getParameter(HttpParameter.Account.ua);
		String serial_code = request.getParameter(HttpParameter.Account.serial_code);
		String channel_sub = request.getParameter(HttpParameter.Account.channel_sub);

		String remoteAddress = request.getHeaderValue(HttpCData.A20001.real_ip);
		if (remoteAddress == null || remoteAddress.length() < 6) {
			String addIp = "";
			SocketAddress sa = session.getRemoteAddress();
			if (sa != null) {
				addIp = sa.toString();
			}
			remoteAddress = StringUtil.formatIp(addIp);
		}
		JSONObject json = checkCodeVersion(product_id, code_version, channel_id, channel_sub, remoteAddress);
		LoggerAccount.codeVersion(channel_id, product_id, code_version, imei, phone_code, screen_width, screen_height, device_os, os_version, udid, mac, ua, serial_code, channel_sub, json.toString());
		return sendToClient(json.toString());
	}

	public final static JSONObject checkCodeVersion(int platform, String codeVersion, String channel, String channelSub, String ip) {
		PlatformRelease pr = PlatformManager.getInstance().getPlatform(platform);
		if (pr == null) {
			JSONObject json = new JSONObject();
			json.put(PlatformManager.CODE, ClientOperate.OPT_ERR_VERSION);
			json.put(PlatformManager.MESSAGE, "请下载新的客户端");
			return json;
		}
		return platformCheckCodeVersion(pr, codeVersion, channel, channelSub, ip);
	}

	public final static JSONObject platformCheckCodeVersion(PlatformRelease pr, String codeVersion, String channel, String channelSub, String ip) {
		ChannelCooperate cc = pr.getChannel(channel);
		JSONObject json = new JSONObject();
		if (cc == null) {
			json.put(PlatformManager.CODE, ClientOperate.OPT_ERR_VERSION);
			json.put(PlatformManager.MESSAGE, "请稍后再试");
		} else {
			json.put(PlatformManager.LOGIN_URL, pr.getParameter(PlatformManager.LOGIN_URL));
			int result = cc.channelCheckCodeVersion(codeVersion, channelSub, pr.isStrict());
			String noticUrl = cc.getParameter(channelSub, PlatformManager.NOTICE_URL);
			if (noticUrl == null || noticUrl.trim().length() < 1) {
				noticUrl = pr.getParameter(PlatformManager.NOTICE_URL);
			}
			switch (result) {
				case ClientOperate.OPT_OK: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
					json.put(PlatformManager.MESSAGE, "OK");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.NOTICE_URL, noticUrl);
					break;
				}
				case ClientOperate.OPT_DOWN_CLIENT: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_DOWN_CLIENT);
					json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "客户端版本提升，请下载新客户端！");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, noticUrl);
					break;
				}
				case ClientOperate.OPT_UP_VERSION: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_UP_VERSION);
					json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "有新版本客户端");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, noticUrl);
					break;
				}
				case ClientOperate.OPT_ERR_VERSION: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_ERR_VERSION);
					json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "ERROR");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, noticUrl);
					break;
				}
				case ClientOperate.OPT_HIGHT_VERSION: {
					if(pr.getId()==3){
						if(pr.isOpenTestIp() && pr.hashIp(ip)){
							json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
							json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL_TEST));
							json.put(PlatformManager.MESSAGE, "OK");
							json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
							json.put(PlatformManager.NOTICE_URL, noticUrl);
						}else{
							json.put(PlatformManager.LOGIN_URL, pr.getParameter(channel+"_login_url_pre"));
							json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
							json.put(PlatformManager.RES_URL, pr.getParameter(channel+"_res_url_pre"));
							json.put(PlatformManager.MESSAGE, "OK");
							json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
							json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, "notice_url_pre"));
						}
					}else{
						json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
						json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL_TEST));
						json.put(PlatformManager.MESSAGE, "OK");
						json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
						json.put(PlatformManager.NOTICE_URL, noticUrl);
					}
//					if (pr.getId() != 3 || (pr.isOpenTestIp() && pr.hashIp(ip))) {
//						json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
//						json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL_TEST));
//						json.put(PlatformManager.MESSAGE, "OK");
//						json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
//						json.put(PlatformManager.NOTICE_URL, noticUrl);
//					} else {
//						json.put(PlatformManager.LOGIN_URL, pr.getParameter("login_url_pre"));
//						json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
//						json.put(PlatformManager.RES_URL, pr.getParameter("res_url_pre"));
//						json.put(PlatformManager.MESSAGE, "OK");
//						json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
//						json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, "notice_url_pre"));
//					}
					break;
				}
				default: {
					json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "ERROR");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, noticUrl);
					break;
				}
			}

			if (pr.getId() != 3 && pr.isOpenTestIp() && pr.hashIp(ip)) {
				json.put(PlatformManager.RES_URL, pr.getParameter(PlatformManager.RES_URL_TEST));
			}
		}
		return json;
	}
}
