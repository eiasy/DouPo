package mmo.common.resource.version;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class JDomResManagerHandler {
	protected final static String TAG_VERSIONS     = "versions";
	protected final static String TAG_VERSION      = "version";
	private final static String   ATTRIBUTE_ID     = "id";
	private final static String   ATTRIBUTE_CFGDIR = "cfgdir";
	private final static String   ATTRIBUTE_RESDIR = "resdir";

	public final static void parser(ResVersionManager versionManager, String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			List<Element> list = root.getChildren();
			int id = 0;
			String cfgDir = null;
			String resDir = null;
			for (Element e : list) {
				id = Integer.parseInt(e.getAttributeValue(ATTRIBUTE_ID));
				cfgDir = e.getAttributeValue(ATTRIBUTE_CFGDIR);
				resDir = e.getAttributeValue(ATTRIBUTE_RESDIR);
				versionManager.addResVersion(id, cfgDir, resDir);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
