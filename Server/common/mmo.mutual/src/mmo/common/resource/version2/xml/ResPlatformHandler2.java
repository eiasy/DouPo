package mmo.common.resource.version2.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import mmo.common.resource.version2.ResVersion2;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ResPlatformHandler2 {
	protected final static String TAG_ROOT    = "update";
	protected final static String TAG_VERSION = "version";

	private final static String   ATT_NUM     = "num";
	private final static String   ATT_DIR     = "dir";

	public final static void parser(ResVersion2 resVersion, String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);
			Element root = document.getRootElement();
			List<Element> list = root.getChildren(TAG_VERSION);

			for (Element e : list) {
				resVersion.addDirectory(Integer.parseInt(e.getAttributeValue(ATT_NUM)), e.getAttributeValue(ATT_DIR));
			}
		} catch (Exception e) {
			LoggerError.error("解析文件报错：" + filePath, e);
		}
	}
}
