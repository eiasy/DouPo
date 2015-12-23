package mmo.common.module.sdk.xml.platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import mmo.common.module.sdk.platform.PlatformManager;
import mmo.common.module.sdk.platform.version.ChannelSub;
import mmo.common.module.sdk.platform.version.ChannelCooperate;
import mmo.common.module.sdk.platform.version.PlatformRelease;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XmlPlatform {
	private final static String TAG_PLATFORM   = "platform";
	private final static String TAG_PARAMETERS = "parameters";
	private final static String TAG_PARAMETER  = "parameter";
	private final static String TAG_CHANNEL    = "channel";
	private final static String TAG_SUB        = "sub";
	private final static String TAG_TEST_IP    = "test-ip";
	private final static String TAG_IP         = "ip";
	private final static String TAG_LINKS      = "links";
	private final static String TAG_LINK       = "link";

	private final static String ATT_ID         = "id";
	private final static String ATT_NAME       = "name";
	private final static String ATT_KEY        = "key";
	private final static String ATT_VALUE      = "value";
	private final static String ATT_OPEN       = "open";
	private final static String ATT_STRICT     = "strict";

	public final static void init(String configFile) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = null;
			try {
				doc = builder.build(new InputStreamReader(new FileInputStream(new File(configFile)), "UTF-8"));
			} catch (Exception e) {
				LoggerError.error("解析文件报错#" + configFile, e);
			}
			Element root = doc.getRootElement();

			Element links = root.getChild(TAG_LINKS);
			if (links != null) {
				List<Element> linkList = links.getChildren(TAG_LINK);
				if (linkList != null) {
					for (Element ele : linkList) {
						init(ProjectCofigs.getFile(ele.getAttributeValue(ATT_KEY)));
					}
				}
			}

			List<Element> platformList = root.getChildren(TAG_PLATFORM);
			if (platformList != null) {
				PlatformRelease platform = null;
				int platformId = 0;
				for (Element ele : platformList) {
					try {
						platformId = Integer.parseInt(ele.getAttributeValue(ATT_ID));
						if (platformId > 0) {
							platform = new PlatformRelease();
							platform.setId(platformId);
							platform.setName(ele.getAttributeValue(ATT_NAME));
							Element params = ele.getChild(TAG_PARAMETERS);
							platform.setStrict("true".equalsIgnoreCase(ele.getAttributeValue(ATT_STRICT)));
							if (params != null) {
								List<Element> paramList = params.getChildren(TAG_PARAMETER);
								if (paramList != null) {
									Element pe = null;
									for (int pi = 0; pi < paramList.size(); pi++) {
										pe = paramList.get(pi);
										platform.setParameter(pe.getAttributeValue(ATT_KEY), pe.getAttributeValue(ATT_VALUE));
									}
								}
							}
							List<Element> channelList = ele.getChildren(TAG_CHANNEL);
							if (channelList != null) {
								ChannelCooperate channel = null;
								for (Element ce : channelList) {
									channel = new ChannelCooperate();
									channel.setId(ce.getAttributeValue(ATT_ID));
									channel.setName(ce.getAttributeValue(ATT_NAME));
									List<Element> subList = ce.getChildren(TAG_SUB);
									if (subList != null) {
										ChannelSub sub = null;
										for (Element se : subList) {
											sub = new ChannelSub();
											sub.setId(se.getAttributeValue(ATT_ID));
											sub.setName(se.getAttributeValue(ATT_NAME));
											params = se.getChild(TAG_PARAMETERS);
											if (params != null) {
												List<Element> paramList = params.getChildren(TAG_PARAMETER);
												if (paramList != null) {
													for (Element pe : paramList) {
														sub.setParameter(pe.getAttributeValue(ATT_KEY), pe.getAttributeValue(ATT_VALUE));
													}
												}
											}
											channel.addChannelSub(sub);
										}
									}
									platform.addChannel(channel);
								}
							}
						}

						// /////////////////////
						Element testIp = ele.getChild(TAG_TEST_IP);
						if (testIp != null) {
							platform.setOpenTestIp("true".equalsIgnoreCase(testIp.getAttributeValue(ATT_OPEN)));
							List<Element> ipList = testIp.getChildren(TAG_IP);
							if (ipList != null) {
								for (Element ie : ipList) {
									platform.addTestIp(ie.getText());
								}
							}
						}
						// ///////////////////////
						PlatformManager.getInstance().addPlatform(platform);
					} catch (Exception e) {
						LoggerError.error("解析文件报错#" + configFile);
					}
				}
			}

		} catch (Exception e) {
			LoggerError.error("解析文件报错#" + configFile);
		}
	}
}
