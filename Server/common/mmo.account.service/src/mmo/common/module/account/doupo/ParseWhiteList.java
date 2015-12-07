package mmo.common.module.account.doupo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import mmo.common.module.account.doupo.cache.account.bean.WhiteList;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ParseWhiteList {
	public final static String TAG_ROOT    = "lists";
	public final static String TAG_CHANNEL = "channel";
	public final static String TAG_ID      = "id";
	public final static String TAG_IP      = "ip";

	public final static String ATT_VALUE   = "value";

	public final static void init(String configFile) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new InputStreamReader(new FileInputStream(new File(configFile)), "UTF-8"));
		} catch (Exception e) {
			LoggerError.error("file:" + configFile, e);
		}
		Element root = doc.getRootElement();
		WhiteList wl = new WhiteList();
		List<Element> logList = root.getChildren(TAG_CHANNEL);
		if (logList != null) {
			String name = null;
			for (Element ele : logList) {
				name = ele.getAttributeValue(ATT_VALUE);
				if (name != null && name.trim().length() > 0) {
					List<Element> idList = ele.getChildren(TAG_ID);
					for (Element ide : idList) {
						wl.addChannelId(name, ide.getAttributeValue(ATT_VALUE));
					}
				}
			}
		}
		Element eleIP = root.getChild(TAG_IP);
		if (eleIP != null) {
			wl.addWhiteIp(eleIP.getAttributeValue(ATT_VALUE));
		}
		AccountCache.getInstance().setWhiteList(wl);

	}
}
