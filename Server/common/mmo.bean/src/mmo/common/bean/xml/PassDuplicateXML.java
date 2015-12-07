package mmo.common.bean.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.passDuplicate.PassDuplicateLayer;
import mmo.common.bean.passDuplicate.PassDuplicateManager;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class PassDuplicateXML {
	// XML中的标签名
	protected static final String TAG_ROOT      = "passDuplicate";
	protected static final String TAG_DUPLICATE = "duplicate";
	protected static final String TAG_LAYER     = "layer";
	// XML中的属性名
	protected static final String ATT_ID        = "id";
	protected static final String ATT_NAME      = "name";
	protected static final String ATT_COST      = "cost";
	protected static final String ATT_INDEX     = "index";
	protected static final String ATT_MAP       = "map";
	protected static final String ATT_NEXTMAP   = "nextMap";
	protected static final String ATT_TITLEX    = "tilex";
	protected static final String ATT_TITLEY    = "tiley";

	public static void parseXML(PassDuplicateManager manager, String file) {
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
		Element duplicates = doc.getRootElement();
		List<Element> elementList = duplicates.getChildren(TAG_DUPLICATE);
		if (elementList != null) {
			String text = null;
			int id = 0;
			String name = null;
			for (Element duplicate : elementList) {
				List<PassDuplicateLayer> list = new ArrayList<PassDuplicateLayer>();
				text = duplicate.getAttributeValue(ATT_ID);
				if (text == null || text.trim().length() == 0) {
					LoggerError.error("passDuplicate配置文件ID未填写");
					continue;
				}
				id = Integer.parseInt(text.trim());

				text = duplicate.getAttributeValue(ATT_NAME);
				if (text == null || text.trim().length() == 0) {
					LoggerError.error("passDuplicate配置文件NAME未填写");
					continue;
				}
				name = text.trim();

				List<Element> layers = duplicate.getChildren(TAG_LAYER);
				if (layers != null) {
					int cost = 0;
					int index = 0;
					int mapId = 0;
					int nextMapId = 0;
					int tileX = 0;
					int tileY = 0;
					for (Element layer : layers) {
						text = layer.getAttributeValue(ATT_COST);
						if (text == null || text.trim().length() == 0) {
							LoggerError.error("passDuplicate配置文件COST未填写");
							continue;
						}
						cost = Integer.parseInt(text.trim());

						text = layer.getAttributeValue(ATT_INDEX);
						if (text == null || text.trim().length() == 0) {
							LoggerError.error("passDuplicate配置文件INDEX未填写");
							continue;
						}
						index = Integer.parseInt(text.trim());

						text = layer.getAttributeValue(ATT_MAP);
						if (text == null || text.trim().length() == 0) {
							LoggerError.error("passDuplicate配置文件MAP未填写");
							continue;
						}
						mapId = Integer.parseInt(text.trim());

						text = layer.getAttributeValue(ATT_NEXTMAP);
						if (text == null || text.trim().length() == 0) {
							nextMapId = 0;
						} else {
							nextMapId = Integer.parseInt(text.trim());
						}

						text = layer.getAttributeValue(ATT_TITLEX);
						if (text == null || text.trim().length() == 0) {
							tileX = 0;
						} else {
							tileX = Integer.parseInt(text.trim());
						}

						text = layer.getAttributeValue(ATT_TITLEY);
						if (text == null || text.trim().length() == 0) {
							tileY = 0;
						} else {
							tileY = Integer.parseInt(text.trim());
						}

						PassDuplicateLayer pdl = new PassDuplicateLayer();
						pdl.setId(id);
						pdl.setName(name);
//						pdl.setCost(cost);
						pdl.setIndex(index);
						pdl.setMapId(mapId);
						pdl.setNextMapId(nextMapId);
						pdl.setTileX(tileX);
						pdl.setTileY(tileY);
						list.add(pdl);
					}
				}
				manager.addPassDuplicateLayer(id, list);
			}
		}
	}
}
