package mmo.common.bean.xml.skeleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import mmo.common.config.role.RoleSkeleton;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLSkeleton {
	public static final void init(String file) {
		parseXml(file);
	}

	protected static final String TAG_ROOT      = "SetManagement";
	protected static final String TAG_Package   = "Package";
	protected static final String TAG_List      = "List";
	protected static final String TAG_Animation = "Animation";

	protected static final String ATT_name      = "name";
	protected static final String ATT_property  = "property";
	protected static final String ATT_total     = "total";
	protected static final String ATT_file      = "file";

	private static void parseXml(String file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element notices = doc.getRootElement();
		List<Element> elePackages = notices.getChildren(TAG_Package);
		if (elePackages != null) {
			for (Element ele : elePackages) {
				List<Element> eleLists = ele.getChildren(TAG_List);
				if (eleLists != null) {
					Element eleList = null;
					for (byte skeleton = 0; skeleton < eleLists.size(); skeleton++) {
						eleList = eleLists.get(skeleton);
						if (eleList != null) {
							String skeletonName = eleList.getAttributeValue(ATT_name);
							if (skeletonName == null || skeletonName.trim().length() < 1) {
								continue;
							}
							RoleSkeleton.addSkeleton(skeletonName.trim(), skeleton);
//							List<Element> eleAnis = eleList.getChildren(TAG_Animation);
//							if (eleAnis != null) {
//								for (Element eleAni : eleAnis) {
//									String fileName = eleAni.getAttributeValue(ATT_file);
//									RoleSkeleton.addSkeleton(skeletonName, skeleton);
//								}
//							}
						}
					}
				}
			}
		}
	}
}
