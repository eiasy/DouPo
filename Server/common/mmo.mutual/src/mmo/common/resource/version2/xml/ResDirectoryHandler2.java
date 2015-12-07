package mmo.common.resource.version2.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import mmo.common.resource.version2.DataDirectory;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ResDirectoryHandler2 {
	protected final static String TAG_ROOT  = "root";
	protected final static String TAG_FILE  = "file";

	private final static String   ATT_NUM   = "num";
	private final static String   ATT_INDEX = "index";
	private final static String   ATT_NAME  = "name";
	private final static String   ATT_SIZE  = "size";
	private final static String   ATT_MD5   = "md5";

	public final static void parser(DataDirectory dataDirectory, String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);
			Element root = document.getRootElement();
			dataDirectory.setFileCount(Integer.parseInt(root.getAttributeValue(ATT_NUM)));

			List<Element> list = root.getChildren(TAG_FILE);
			int index = 0;
			String name = null;
			int size = 0;
			String md5 = null;
			for (Element e : list) {
				index = Integer.parseInt(e.getAttributeValue(ATT_INDEX));
				size = Integer.parseInt(e.getAttributeValue(ATT_SIZE));
				name = e.getAttributeValue(ATT_NAME);
				md5 = e.getAttributeValue(ATT_MD5);
				dataDirectory.addDataFile(index, size, name, md5);
			}
		} catch (Exception e) {
			LoggerError.error("解析文件报错：" + filePath, e);
		}
	}
}
