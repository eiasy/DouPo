package mmo.common.config.update;

import mmo.tools.util.StringUtil;

public class PhoneType {
	private final static String DIR_RELEASE = "fanrenhdol/";
	private final static String DIR_TEST    = "test/";

	private int                 phoneType;
	private String              version;
	private String              url;
	private String              resUrl;
	private String              resReleaseUrl;
	private String              resTestUrl;
	private int                 no01;
	private int                 no02;
	private int                 no03;
	private String              addition    = "0";

	public void release() {
		version = null;
		url = null;
		addition = null;
	}

	public int getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
		if (version != null) {
			String[] arr = StringUtil.splitString(version, '.');
			no01 = Integer.parseInt(arr[0]);
			no02 = Integer.parseInt(arr[1]);
			no03 = Integer.parseInt(arr[2]);
			if (arr.length > 3) {
				addition = arr[3];
			} else {
				addition = "0";
			}
		}
	}

	public byte validateVersion(String version) {
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
					if (ClientUrl.isStrict) {
						return ClientOperate.OPT_DOWN_CLIENT;
					} else {// 小版本更新，提示提升版本
						return ClientOperate.OPT_UP_VERSION;// OPT_UP_VERSION
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		if (resUrl != null) {
			if (resUrl.endsWith("/")) {
				this.resUrl = resUrl;
			} else {
				this.resUrl = resUrl + "/";
			}
			this.resReleaseUrl = this.resUrl + DIR_RELEASE;
			this.resTestUrl = this.resUrl + DIR_TEST;
		}
	}

	public String getResReleaseUrl() {
		return resReleaseUrl;
	}

	public String getResTestUrl() {
		return resTestUrl;
	}

	@Override
	public String toString() {
		return "PhoneType [phoneType=" + phoneType + ", version=" + version + ", url=" + url + ", no01=" + no01 + ", no02=" + no02 + ", no03=" + no03
		        + ", addition=" + addition + "]";
	}

}
