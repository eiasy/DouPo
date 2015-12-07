package mmo.common.module.sdk.platform.version;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.config.update.ClientOperate;
import mmo.common.module.sdk.platform.PlatformManager;
import mmo.tools.util.StringUtil;

public class ChannelSub {
	private String              id;
	private String              name;
	private int                 no01;
	private int                 no02;
	private int                 no03;
	private Map<String, String> parameters = new HashMap<String, String>();

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

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getParameter(String key) {
		if (key == null) {
			return "";
		}
		return parameters.get(key);
	}

	public void setParameter(String key, String value) {
		if (key == null) {
			return;
		}
		parameters.put(key, value);
		if (PlatformManager.CODE_VERSION.equalsIgnoreCase(key)) {
			if (value != null) {
				String[] arr = StringUtil.splitString(value, '.');
				no01 = Integer.parseInt(arr[0]);
				no02 = Integer.parseInt(arr[1]);
				no03 = Integer.parseInt(arr[2]);
			}
		}
	}

	public byte validateVersion(String version, boolean isStrict) {
		if (version == null) {
			return ClientOperate.OPT_ERR_VERSION;
		}
		String[] arr = StringUtil.splitString(version, '.');
		if (arr.length < 3) {
			return ClientOperate.OPT_ERR_VERSION;
		}
		int n01 = Integer.parseInt(arr[0]);
		int n02 = Integer.parseInt(arr[1]);
		int n03 = Integer.parseInt(arr[2]);
		if (n01 < no01 || n02 < no02) {
			return ClientOperate.OPT_DOWN_CLIENT;
		}
		if (n01 < no01) {
			return ClientOperate.OPT_DOWN_CLIENT;
		} else if (n01 == no01) {
			if (n02 < no02) {
				return ClientOperate.OPT_DOWN_CLIENT;
			} else if (n02 == no02) {
				if (n03 < no03) {
					if (isStrict) {
						return ClientOperate.OPT_DOWN_CLIENT;
					} else {// 小版本更新，提示提升版本
						return ClientOperate.OPT_UP_VERSION;
					}
				} else if (n03 == no03) {
					return ClientOperate.OPT_OK;
				} else {
					return ClientOperate.OPT_HIGHT_VERSION;
				}
			} else {
				return ClientOperate.OPT_HIGHT_VERSION;
			}
		} else {
			return ClientOperate.OPT_HIGHT_VERSION;
		}
	}

	public void print() {
		System.out.println("			sub id=" + id + ", name=" + name);
		Set<String> keys = parameters.keySet();
		for (String k : keys) {
			System.out.println("			parameter k=" + k + ", v=" + parameters.get(k));
		}
	}
}
