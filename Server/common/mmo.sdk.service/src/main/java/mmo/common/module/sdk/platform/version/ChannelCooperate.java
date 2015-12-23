package mmo.common.module.sdk.platform.version;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.config.update.ClientOperate;

/**
 * 
 * @author ŔîĚěĎ˛
 * 
 */
public class ChannelCooperate {
	private String                  id;
	private String                  name;
	private Map<String, ChannelSub> channelSubs = new HashMap<String, ChannelSub>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ChannelSub> getChannelSubs() {
		return channelSubs;
	}

	public ChannelSub getChannelSub(String id) {
		if (id == null) {
			return null;
		}
		return channelSubs.get(id);
	}

	public void addChannelSub(ChannelSub sub) {
		if (sub == null || sub.getId() == null) {
			return;
		}
		channelSubs.put(sub.getId(), sub);
	}

	public String getParameter(String channelSub, String key) {
		ChannelSub cs = channelSubs.get(channelSub);
		if (cs == null) {
			return "";
		}
		return cs.getParameter(key);
	}

	public void print() {
		System.out.println("		channel id=" + id + ", name=" + name);
		Set<String> keys = channelSubs.keySet();
		for (String k : keys) {
			System.out.println("		sub k=" + k);
			channelSubs.get(k).print();
		}
	}

	public int channelCheckCodeVersion(String codeVersion, String channelSub, boolean isStrict) {
		ChannelSub cs = channelSubs.get(channelSub);
		if (cs == null) {
			return ClientOperate.OPT_ERR_VERSION;
		}
		return cs.validateVersion(codeVersion, isStrict);
	}
}
