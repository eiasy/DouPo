package mmo.common.bean.xml;

import java.io.IOException;
import java.util.List;

import mmo.common.bean.vip.VipLevel;
import mmo.common.bean.vip.VipManager;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * 解析VIP配置文件
 * 
 * @author fanren
 * 
 */
public class VipConfigXML {
	// XML中的标签名
	protected static final String TAG_ROOT  = "vips";
	protected static final String TAG_VIP   = "vip";
	protected static final String TAG_LEVEL = "level";
	// XML中的属性名
	protected static final String ATT_INDEX = "index";
	protected static final String ATT_VALUE = "value";
	protected static final String ATT_MONEY = "money";
	protected static final String ATT_BOX   = "box";
	protected static final String ATT_NOTE  = "note";

	/**
	 * 解析VIP配置文件
	 * 
	 * @param vipManager
	 * @param file
	 */
	public static final void parseXML(VipManager vipManager, String file) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(file);
			// 获取根节点
			Element vips = doc.getRootElement();
			List<Element> elementList = vips.getChildren(TAG_VIP);
			if (elementList != null) {
				String text = null;
				short index = 0;
				short level = 0;
				int money = 0;
				int box = 0;
				String note = null;
				VipLevel vipLevel = null;
				for (Element vip : elementList) {
					List<Element> levelList = vip.getChildren(TAG_LEVEL);
					for (Element levelElement : levelList) {
						text = levelElement.getAttributeValue(ATT_INDEX);
						if (text == null || text.trim().length() == 0) {
							LoggerError.messageLog.error("VIP配置文件INDEX未填写");
							continue;
						}
						index = Short.parseShort(text.trim());

						text = levelElement.getAttributeValue(ATT_VALUE);
						if (text == null || text.trim().length() == 0) {
							LoggerError.messageLog.error("VIP配置文件VALUE未填写");
							continue;
						}
						level = Short.parseShort(text.trim());

						text = levelElement.getAttributeValue(ATT_MONEY);
						if (text == null || text.trim().length() == 0) {
							LoggerError.messageLog.error("VIP配置文件MONEY未填写");
							continue;
						}
						money = Integer.parseInt(text.trim());
						text = levelElement.getAttributeValue(ATT_BOX);
						if (text == null || text.trim().length() == 0) {
							LoggerError.messageLog.error("VIP配置文件BOX未填写");
							continue;
						}
						box = Integer.parseInt(text.trim());

						text = levelElement.getAttributeValue(ATT_NOTE);
						if (text == null || text.trim().length() == 0) {
							LoggerError.messageLog.error("VIP配置文件NOTE未填写");
							continue;
						}
						note = text;
						vipLevel = new VipLevel();
						vipLevel.setIndex(index);
						vipLevel.setBox(box);
						vipLevel.setLevel(level);
						vipLevel.setMoney(money);
						vipLevel.setNote(note);
						vipManager.addVipLevel(vipLevel);
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
