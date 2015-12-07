package mmo.common.bean.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TargetPlatfromHandler {
	protected final static String TAG_PLATFORMS = "platforms";
	private final static String   TAG_PLATFORM  = "platform";
	private final static String   ATT_VALUE     = "value";

	public static final List<Integer> parser(String filePath) {
		SAXBuilder builder = new SAXBuilder();
		List<Integer> platformList = new ArrayList<Integer>();
		try {
			Document document = builder.build(filePath);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			List<Element> list = root.getChildren(TAG_PLATFORM);
			int value = 0;
			for (Element platform : list) {
				String attribute = platform.getAttributeValue(ATT_VALUE);
				if (attribute != null && attribute.trim().length() > 0) {
					value = Integer.parseInt(attribute);
					platformList.add(value);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return platformList;
	}
}
