package mmo.tools.util;

import java.util.Properties;

public class PropertiesExt extends Properties {
    private static final long serialVersionUID = -4105785275116749954L;
	public static final String RSSET_USER_DIR = "${user.dir}";

	public String getProperty(String key) {
		String value = super.getProperty(key);
		if (value != null) {
			return value.replace(RSSET_USER_DIR, FileUtil.ROOT_DIR);
		}
		return value;
	}
}
