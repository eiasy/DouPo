package mmo.common.module.sdk.platform.version;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.config.update.ClientOperate;
import mmo.common.module.sdk.platform.PlatformManager;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

public class PlatformRelease {
	private final static Object           NULL       = new Object();
	private int                           id;
	private String                        name;
	private boolean                       isStrict;
	private boolean                       openTestIp;
	private Map<String, String>           parameters = new HashMap<String, String>();
	private Map<String, Object>           testIps    = new HashMap<String, Object>();
	private Map<String, ChannelCooperate> channels   = new HashMap<String, ChannelCooperate>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStrict() {
		return isStrict;
	}

	public void setStrict(boolean isStrict) {
		this.isStrict = isStrict;
	}

	public boolean isOpenTestIp() {
		return openTestIp;
	}

	public void setOpenTestIp(boolean openTestIp) {
		this.openTestIp = openTestIp;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getParameter(String key) {
		return parameters.get(key);
	}

	public void setParameter(String key, String value) {
		if (key == null) {
			return;
		}
		this.parameters.put(key, value);
	}

	public Map<String, ChannelCooperate> getChannels() {
		return channels;
	}

	public ChannelCooperate getChannel(String channel) {
		return channels.get(channel);
	}

	public void addChannel(ChannelCooperate channel) {
		if (channel == null || channel.getId() == null) {
			return;
		}
		this.channels.put(channel.getId(), channel);
	}

	public void addTestIp(String ip) {
		if (ip != null) {
			testIps.put(ip, NULL);
		}
	}

	public boolean hashIp(String ip) {
		if (ip == null) {
			return false;
		}
		return testIps.get(StringUtil.formatIp(ip)) != null;
	}

	public void print() {
		System.out.println("	platform id=" + id + ", name=" + name + ", isStrict=" + isStrict + ", openTestIp=" + openTestIp);
		Set<String> keys = parameters.keySet();
		for (String k : keys) {
			System.out.println("	parameter k=" + k + ", v=" + parameters.get(k));
		}

		keys = channels.keySet();
		for (String k : keys) {
			System.out.println("	channel k=" + k);
			channels.get(k).print();
		}
		keys = testIps.keySet();
		for (String k : keys) {
			System.out.println("	ip=" + k);
		}
	}

	public JSONObject platformCheckCodeVersion(String codeVersion, String channel, String channelSub, String ip) {
		ChannelCooperate cc = channels.get(channel);
		JSONObject json = new JSONObject();
		if (cc == null) {
			json.put(PlatformManager.CODE, ClientOperate.OPT_ERR_VERSION);
			json.put(PlatformManager.MESSAGE, "请稍后再试");
		} else {
			json.put(PlatformManager.LOGIN_URL, getParameter(PlatformManager.LOGIN_URL));
			int result = cc.channelCheckCodeVersion(codeVersion, channelSub, isStrict);
			switch (result) {
				case ClientOperate.OPT_OK: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
					json.put(PlatformManager.MESSAGE, "OK");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
				case ClientOperate.OPT_DOWN_CLIENT: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_DOWN_CLIENT);
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "客户端版本提升，请下载新客户端！");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
				case ClientOperate.OPT_UP_VERSION: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_UP_VERSION);
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "有新版本客户端");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
				case ClientOperate.OPT_ERR_VERSION: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_ERR_VERSION);
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "ERROR");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
				case ClientOperate.OPT_HIGHT_VERSION: {
					json.put(PlatformManager.CODE, ClientOperate.OPT_OK);
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL_TEST));
					json.put(PlatformManager.MESSAGE, "OK");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
				default: {
					json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL));
					json.put(PlatformManager.MESSAGE, "ERROR");
					json.put(PlatformManager.DL_URL, cc.getParameter(channelSub, PlatformManager.DL_URL));
					json.put(PlatformManager.NOTICE_URL, cc.getParameter(channelSub, PlatformManager.NOTICE_URL));
					break;
				}
			}

			if (openTestIp && testIps.get(StringUtil.formatIp(ip)) != null) {
				json.put(PlatformManager.RES_URL, getParameter(PlatformManager.RES_URL_TEST));
			}
		}
		return json;
	}

	public boolean isTestplatform(String codeVersion, String channel, String channelSub, String ip) {
		ChannelCooperate cc = channels.get(channel);
		if (cc == null) {
			return false;
		} else {
			if (cc.channelCheckCodeVersion(codeVersion, channelSub, isStrict) == ClientOperate.OPT_HIGHT_VERSION) {
				return true;
			}

			if (openTestIp && testIps.get(StringUtil.formatIp(ip)) != null) {
				return true;
			}
		}
		return false;
	}
}
