package mmo.common.bean.xml;

import java.io.IOException;
import java.util.List;

import mmo.common.bean.skill.cool.CommonCool;
import mmo.common.bean.skill.cool.CommonCoolManager;
import mmo.tools.util.StringUtil;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ParseCommonCoolXML {
	// XML中的标签名
	private static final String TAG_COOL       = "common-cool";
	private static final String TAG_SKILL      = "skill";
	private static final String TAG_GOODS      = "goods";
	// XML中的属性名
	private static final String ATT_ID         = "id";
	private static final String ATT_PROFESSION = "profession";
	private static final String ATT_TIME       = "time";
	private static final String ATT_SKILLID    = "skillid";
	private static final String ATT_COOLID     = "coolid";
	private static final String ATT_GOODSID    = "goodsid";

	/**
	 * 解析cool-time.xml文件的common-cool节点
	 * 
	 * @param file
	 *            文件路径
	 * @return 解析结果
	 */
	public static final void parseCommonCool(String file) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(file);
			// 获取根节点
			String text = null;
			Element coolTime = doc.getRootElement();
			List<Element> elementList = coolTime.getChildren(TAG_COOL);
			if (elementList != null) {
				// 依次解析子节点属性
				byte[] profession = null;
				for (Element commonCool : elementList) {
					if (commonCool.getAttribute(ATT_ID) != null && commonCool.getAttributeValue(ATT_ID).trim().length() > 0) {
						int coolid = Integer.parseInt(commonCool.getAttributeValue(ATT_ID).trim());
						if (coolid != 0) {
							CommonCool cc = new CommonCool();
							cc.setId(coolid);
							text = commonCool.getAttributeValue(ATT_PROFESSION);
							if (text != null && text.trim().length() > 0) {
								profession = StringUtil.string2ByteArray(text.trim(), ',');
							}
							text = commonCool.getAttributeValue(ATT_TIME);
							if (text != null && text.trim().length() > 0) {
								int time = Integer.parseInt(text.trim());
								cc.setCoolTime(time);
							}
							CommonCoolManager.addCommonCool(profession, cc);
						}
					}
				}
			}
			elementList = coolTime.getChildren(TAG_SKILL);
			if (elementList != null) {
				// 依次解析子节点属性
				int skillId = 0;
				int coolId = 0;
				for (Element skillCool : elementList) {
					text = skillCool.getAttributeValue(ATT_SKILLID);
					if (text != null && text.trim().length() > 0) {
						skillId = Integer.parseInt(text.trim());
					}
					text = skillCool.getAttributeValue(ATT_COOLID);
					if (text != null && text.trim().length() > 0) {
						coolId = Integer.parseInt(text.trim());
					}
					if (skillId > 0 && coolId > 0) {
						CommonCoolManager.addSkillCool(coolId, skillId);
					}
				}
			}

			elementList = coolTime.getChildren(TAG_GOODS);
			if (elementList != null) {
				// 依次解析子节点属性
				int skillId = 0;
				int coolId = 0;
				for (Element skillCool : elementList) {
					text = skillCool.getAttributeValue(ATT_GOODSID);
					if (text != null && text.trim().length() > 0) {
						skillId = Integer.parseInt(text.trim());
					}
					text = skillCool.getAttributeValue(ATT_COOLID);
					if (text != null && text.trim().length() > 0) {
						coolId = Integer.parseInt(text.trim());
					}
					if (skillId > 0 && coolId > 0) {
						CommonCoolManager.addGoodsCool(coolId, skillId);
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
