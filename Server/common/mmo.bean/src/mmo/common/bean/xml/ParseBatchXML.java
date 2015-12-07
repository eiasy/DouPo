package mmo.common.bean.xml;

import java.io.IOException;
import java.io.StringReader;

import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class ParseBatchXML {
	private final static String   TRUE      = "true";
	protected static final String TAG_BATCH = "batch";
	protected static final String ATT_V     = "v";

	/** 解析是否可以批量 */
	public static final boolean parseBatch(String file) {
		StringReader read = new StringReader(file);
		InputSource source = new InputSource(read);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(source);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc == null) {
			LoggerError.messageLog.error("BATCH解析出错了");
			return false;
		}
		Element datas = doc.getRootElement();
		if (datas != null) {
			String text = null;
			Element batch = datas.getChild(TAG_BATCH);
			if (batch != null) {
				text = batch.getAttributeValue(ATT_V);
				if (text == null || text.trim().length() == 0) {
					LoggerError.messageLog.error("DATA配置文件batch未填写");
				} else if (TRUE.equalsIgnoreCase(text)) {
					return true;
				}
			}
		}
		return false;
	}
}
