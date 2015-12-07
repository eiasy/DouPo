package mmo.common.config.update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ClientUrl {
	protected static final String              ROOT_LAUNCHER_CFG = "launchercfg";
	protected static final String              TAG_CHANNEL       = "channel";
	protected static final String              TAG_MACHINE_TYPE  = "manchinetype";

	protected static final String              ATT_NAME          = "name";
	protected static final String              ATT_URL           = "url";
	protected static final String              ATT_RESOURCE      = "resource";
	protected static final String              ATT_VALUE         = "value";
	protected static final String              ATT_VERSION       = "version";
	protected static final String              ATT_ACCOUNT_TYPE  = "accounttype";
	protected static final String              ATT_FEE_CALLBACK  = "feecallback";
	protected static final String              ATT_BELONGTO      = "belongto";
	protected static final String              ATT_CHANNEL_SUB   = "channelsub";
	protected static final String              ATT_strict        = "strict";
	protected static final String              ATT_toip          = "toip";
	protected static final String              ATT_toport        = "toport";

	protected static boolean                   isStrict          = true;
	protected static String                    toip              = "124.193.178.18";
	protected static int                       toport            = 6503;
	private static Map<Integer, ChannelClient> channels          = new HashMap<Integer, ChannelClient>();
	private static String                      filePath          = null;

	public final static void init(String filePath) {
		ClientUrl.filePath = filePath;
		SAXBuilder builder = new SAXBuilder();
		try {
			FileInputStream input = new FileInputStream(filePath);
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String text = root.getAttributeValue(ATT_strict);
			if (text != null) {
				isStrict = "true".equalsIgnoreCase(text.trim());
			}

			text = root.getAttributeValue(ATT_toip);
			if (text != null) {
				toip = text.trim();
			}

			text = root.getAttributeValue(ATT_toport);
			if (text != null) {
				toport = Integer.parseInt(text.trim());
			}

			List<Element> childrens = root.getChildren();
			for (Element child : childrens) {// 读取渠道号
				ChannelClient channelClient = new ChannelClient();
				int channelId = Integer.parseInt(child.getAttributeValue(ATT_VALUE));
				channelClient.setAccountType(child.getAttributeValue(ATT_ACCOUNT_TYPE).trim());
				channelClient.setFeeCallback(child.getAttributeValue(ATT_FEE_CALLBACK));
				channelClient.setBelongto(Integer.parseInt(child.getAttributeValue(ATT_BELONGTO)));
				channelClient.setChannelSub(child.getAttributeValue(ATT_CHANNEL_SUB));
				List<Element> eles = child.getChildren();
				if (eles.size() > 0) {// 如果存在子节点，继续读取
					for (Element ele : eles) {// 读取版本号、机型、url等信息
						PhoneType phoneType = new PhoneType();
						int type = Integer.parseInt(ele.getAttributeValue(ATT_VALUE));
						phoneType.setPhoneType(type);
						phoneType.setVersion(ele.getAttributeValue(ATT_VERSION));
						phoneType.setUrl(ele.getAttributeValue(ATT_URL));
						phoneType.setResUrl(ele.getAttributeValue(ATT_RESOURCE));
						channelClient.addPhoneType(type, phoneType);
					}
				}
				channels.put(channelId, channelClient);
			}
		} catch (FileNotFoundException e) {
			LoggerError.error("没有找到文件『" + filePath + "』", e);
		} catch (JDOMException e) {
			LoggerError.error(filePath, e);
		} catch (IOException e) {
			LoggerError.error("配置文件 『" + filePath + "』出错 ", e);
		}
	}

	public static void reload() {
		if (filePath == null) {
			return;
		}
		SAXBuilder builder = new SAXBuilder();
		try {
			FileInputStream input = new FileInputStream(filePath);
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String text = root.getAttributeValue(ATT_strict);
			if (text != null) {
				isStrict = "true".equalsIgnoreCase(text.trim());
			}

			text = root.getAttributeValue(ATT_toip);
			if (text != null) {
				toip = text.trim();
			}

			text = root.getAttributeValue(ATT_toport);
			if (text != null) {
				toport = Integer.parseInt(text.trim());
			}
			Map<Integer, ChannelClient> channels = new HashMap<Integer, ChannelClient>();
			List<Element> childrens = root.getChildren();
			for (Element child : childrens) {// 读取渠道号
				int channelId = Integer.parseInt(child.getAttributeValue(ATT_VALUE));
				ChannelClient channelClient = new ChannelClient();
				channelClient.setAccountType(child.getAttributeValue(ATT_ACCOUNT_TYPE).trim());
				channelClient.setFeeCallback(child.getAttributeValue(ATT_FEE_CALLBACK));
				channelClient.setBelongto(Integer.parseInt(child.getAttributeValue(ATT_BELONGTO)));
				channelClient.setChannelSub(child.getAttributeValue(ATT_CHANNEL_SUB));
				List<Element> eles = child.getChildren();
				if (eles.size() > 0) {// 如果存在子节点，继续读取
					for (Element ele : eles) {// 读取版本号、机型、url等信息
						int type = Integer.parseInt(ele.getAttributeValue(ATT_VALUE));
						PhoneType phoneType = new PhoneType();
						phoneType.setPhoneType(type);
						phoneType.setVersion(ele.getAttributeValue(ATT_VERSION));
						phoneType.setUrl(ele.getAttributeValue(ATT_URL));
						phoneType.setResUrl(ele.getAttributeValue(ATT_RESOURCE));
						channelClient.addPhoneType(type, phoneType);
					}
				}
				channels.put(channelId, channelClient);
			}
			Map<Integer, ChannelClient> old = ClientUrl.channels;
			ClientUrl.channels = channels;

			Collection<ChannelClient> values = old.values();
			for (ChannelClient cc : values) {
				cc.release();
			}
			old.clear();
			old = null;
		} catch (FileNotFoundException e) {
			LoggerError.error("没有找到文件『" + filePath + "』", e);
		} catch (JDOMException e) {
			LoggerError.error(filePath, e);
		} catch (IOException e) {
			LoggerError.error("配置文件 『" + filePath + "』出错 ", e);
		}
	}

	public static final PhoneType getPhoneType(int channelId, int phoneType) {
		return channels.get(channelId).getPhoneType(phoneType);
	}

	public static final ChannelClient getChannelClient(int channelId) {
		return channels.get(channelId);
	}

	public static String getToip() {
		return toip;
	}

	public static int getToport() {
		return toport;
	}
}
