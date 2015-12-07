package mmo.common.bean.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.goods.price.PriceGroup;
import mmo.common.bean.goods.price.PriceItem;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * 解析商店物品的价格
 * 
 * @author fanren
 * 
 */
public class ShopGoodsPriceXML {
	protected static final String TAG_ROOT     = "p";
	private static final String   TAG_GROUP    = "g";
	private static final String   TAG_MONEY    = "m";

	private static final String   ATT_ID       = "id";
	private static final String   ATT_GOODS_ID = "goodsid";
	private static final String   ATT_NORM     = "norm";
	private static final String   ATT_NOW      = "now";

	/**
	 * 通过配置文本获取商店物品价格列表
	 * 
	 * @param priceText
	 * @return
	 */
	public static List<PriceGroup> parseXMLText(String priceText) {
		List<PriceGroup> list = new ArrayList<PriceGroup>();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(new InputSource(new StringReader(priceText)));
			Element missions = doc.getRootElement();
			List<Element> groupList = missions.getChildren(TAG_GROUP);
			List<Element> itemList = null;
			int gSize = groupList.size();
			int iSize = 0;
			Element group = null;
			Element item = null;
			PriceGroup pg = null;
			PriceItem pi = null;
			int normId = 0;
			int norm = 0;
			int now = 0;
			String text = null;
			for (int gi = 0; gi < gSize; gi++) {
				pg = null;
				group = groupList.get(gi);
				itemList = group.getChildren(TAG_MONEY);
				iSize = itemList.size();
				if (iSize > 0) {
					text = group.getAttributeValue(ATT_ID);
					if (text != null && text.trim().length() > 0) {
						pg = new PriceGroup();
						pg.setId(Short.parseShort(text.trim()));
						for (int ii = 0; ii < iSize; ii++) {
							item = itemList.get(ii);
							text = item.getAttributeValue(ATT_GOODS_ID);
							if (text != null && text.trim().length() > 0) {
								normId = Integer.parseInt(text.trim());
							} else {
								break;
							}
							text = item.getAttributeValue(ATT_NORM);
							if (text != null && text.trim().length() > 0) {
								norm = Integer.parseInt(text.trim());
							}
							text = item.getAttributeValue(ATT_NOW);
							if (text != null && text.trim().length() > 0) {
								now = Integer.parseInt(text.trim());
							}
							pi = new PriceItem();
							pi.setGoodsId(normId);
							pi.setNormCount(norm);
							pi.setNowCount(now);
							pg.addPriceItem(pi);
						}
						if (pg.getItemCount() > 0) {
							list.add(pg);
						}
					}
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
