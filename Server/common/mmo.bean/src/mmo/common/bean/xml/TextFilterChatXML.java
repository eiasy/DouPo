package mmo.common.bean.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TextFilterChatXML {
	private static String  keyWords = "";
	private static Pattern pattern  = null;

	public final static void init(String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			List<Element> list = root.getChildren();
			String localName = null;
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Element attributes : list) {
				localName = attributes.getName();
				String value = null;
				if ("text".equalsIgnoreCase(localName)) {
					value = attributes.getAttributeValue("value");
					if ((value.trim()).length() != 0) {
						if (i == 0) {
							sb.append(value.trim().toUpperCase());
						} else {
							sb.append("|");
							sb.append(value.trim().toUpperCase());
						}
						i++;
					}
				}
			}
			keyWords = sb.toString();
			pattern = Pattern.compile(keyWords, Pattern.CASE_INSENSITIVE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String replace(String src, String str) {
		Matcher matcher = null;
		matcher = pattern.matcher(src);
		return matcher.replaceAll(str);
	}
}
