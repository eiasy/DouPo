package mmo.common.module.sdk.platform;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.config.update.ClientOperate;
import mmo.common.module.sdk.platform.version.PlatformRelease;
import net.sf.json.JSONObject;

public class PlatformManager {
	public final static String           CODE_VERSION = "code_version";
	public final static String           LOGIN_URL    = "login_url";
	public final static String           RES_URL      = "res_url";
	public final static String           RES_URL_TEST = "res_url_test";
	public final static String           DL_URL       = "dl_url";
	public final static String           NOTICE_URL   = "notice_url";
	public final static String           CODE         = "code";
	public final static String           MESSAGE      = "message";

	private final static PlatformManager instance     = new PlatformManager();

	public final static PlatformManager getInstance() {
		return instance;
	}

	private Map<Integer, PlatformRelease> platforms = new HashMap<Integer, PlatformRelease>();

	private PlatformManager() {

	}

	public void addPlatform(PlatformRelease platform) {
		if (platform == null || platform.getId() < 1) {
			return;
		}
		platforms.put(platform.getId(), platform);
	}

	public PlatformRelease getPlatform(int platformId) {
		return platforms.get(platformId);
	}

	public void print() {
		Set<Integer> keys = platforms.keySet();
		for (int k : keys) {
			System.out.println("platform=" + k);
			platforms.get(k).print();
		}
	}

	public JSONObject checkCodeVersion(int platform, String codeVersion, String channel, String channelSub, String ip) {
		PlatformRelease pr = platforms.get(platform);
		if (pr == null) {
			JSONObject json = new JSONObject();
			json.put(CODE, ClientOperate.OPT_ERR_VERSION);
			json.put(MESSAGE, "请下载新的客户端");
			return json;
		}
		return pr.platformCheckCodeVersion(codeVersion, channel, channelSub, ip);
	}

	public boolean isTest(int platform, String codeVersion, String channel, String channelSub, String ip) {
		PlatformRelease pr = platforms.get(platform);
		if (pr == null) {
			return false;
		}
		return pr.isTestplatform(codeVersion, channel, channelSub, ip);
	}
}
