package mmo.common.resource.version2.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import mmo.common.resource.ResManager;
import mmo.common.resource.version2.ResVersionManager2;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ResVersionHandler2 {
	protected final static String TAG_VERSIONS      = "versions";
	protected final static String TAG_VERSION       = "version";
	private final static String   ATTRIBUTE_ID      = "id";
	private final static String   ATTRIBUTE_CFGDIR  = "cfgdir";
	private final static String   ATTRIBUTE_RESDIR  = "resdir";
	private final static String   ATTRIBUTE_VERSION = "version";
	private final static String   ATTRIBUTE_SCENE   = "scenedir";

	public final static void parser(ResVersionManager2 resManager, String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			ResManager.setSceneDir(root.getAttributeValue(ATTRIBUTE_SCENE));
			Element platform = null;
			List<Element> list = null;
			String platformName = "windows";
			if (ApplicationConfig.getInstance().isWin()) {
				platform = root.getChild(platformName);
			} else {
				platformName = "linux";
				platform = root.getChild(platformName);
			}
			if (platform == null) {
				LoggerError.error(platformName + "平台资源配置错误，配置文件：" + filePath);
				return;
			}
			list = platform.getChildren();
			int id = 0;
			int version = 0;
			String cfgDir = null;
			String resDir = null;

			for (Element e : list) {
				if (e.getName().equals(TAG_VERSION)) {
					version = Integer.parseInt(e.getAttributeValue(ATTRIBUTE_VERSION));
					id = Integer.parseInt(e.getAttributeValue(ATTRIBUTE_ID));
					cfgDir = e.getAttributeValue(ATTRIBUTE_CFGDIR);
					resDir = e.getAttributeValue(ATTRIBUTE_RESDIR);
					switch (version) {
					// case 0: {
					// CopyOfResVersionManager.getInstance().addResVersion(id, cfgDir, resDir);
					// break;
					// }
						case 1: {
							resManager.addResVersion(id, cfgDir, resDir);
							break;
						}
					}
				}
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
