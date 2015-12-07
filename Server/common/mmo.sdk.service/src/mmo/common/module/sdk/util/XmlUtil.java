package mmo.common.module.sdk.util;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Xml工具类
 * @author mp
 * @date 2015-7-11 上午10:06:42
 */
public class XmlUtil {
	
	/**
	 * 获取子元素,仅限于单层模式：<IdentityInfo><AccountID>10003550803</AccountID><Username>13810535887</Username><DeviceID>123</DeviceID><verified>1</verified></IdentityInfo>
	 * @author mp
	 * @date 2015-7-11 上午10:05:45
	 * @param xml
	 * @param child
	 * @Description
	 */
	public static String getElementValue (String xml, String child) {
		try {
	    	ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(xml.getBytes());
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new InputStreamReader(tInputStringStream, "utf-8"));
			Element root = doc.getRootElement();
			Element childElement = root.getChild(child);
			return childElement.getValue();
		} catch (Exception e) {
			return "";
		}
	}
}
