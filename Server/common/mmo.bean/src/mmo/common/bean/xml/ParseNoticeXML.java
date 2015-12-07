package mmo.common.bean.xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ParseNoticeXML {
	private static final String TAG_NOTICE  = "notice";
//	private static final String ATT_VERSION = "version";
	private static final String ATT_STATE   = "state";
	private static final String ATT_TEXT    = "text";
	private static final String ATT_TITLE    = "title";
	public static void main(String[] args) {
		//1 7 0 57
		String str = "E:/workspace/PROJECT/FanrenOnline/trunk/server/sources/normal/mmo.init.config/fanren/notice/notice.xml";
		parseNoticeXml(str);
	}

	/**
	 * 解析notice.xml文件
	 * 
	 * @param file
	 * @return
	 */
	public static void parseNoticeXml(String file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(file);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获取根节点
		Element notices = doc.getRootElement();
		// 获取子节点列表
		List<Element> noticeList = notices.getChildren(TAG_NOTICE);
		if (noticeList != null) {
			// 解析节点列表
			for (Iterator<Element> iter = noticeList.iterator(); iter.hasNext();) {
				Element notice = (Element) iter.next();
				// 先解析状态属性 1为关闭 0为开启
				int state = 1;				
				if (notice.getAttribute(ATT_STATE) != null && notice.getAttributeValue(ATT_STATE).trim().length() > 0) {
					state = Integer.parseInt(notice.getAttributeValue(ATT_STATE).trim());
				}
				if (state == 0) {
					String title = null;
					String text = null;
					if (notice.getAttribute(ATT_TITLE) != null && notice.getAttributeValue(ATT_TITLE).trim().length() > 0) {
						title = notice.getAttributeValue(ATT_TITLE).trim();
					}
					if (notice.getAttribute(ATT_TEXT) != null && notice.getAttributeValue(ATT_TEXT).trim().length() > 0) {
						text = notice.getAttributeValue(ATT_TEXT).trim();
					}
					break;
				}
			}
		}
	}
}
