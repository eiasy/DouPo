package com.huayi.doupo.base.util.logic.socket;

import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huayi.doupo.base.model.socket.XmlDomBean;



/**
 * 加载xml
 * @author mp
 * @date 2013-10-25 下午3:29:03
 */
public class RequestXmlUtil {
	
	@SuppressWarnings("unchecked")
	public static HashMap<Integer, XmlDomBean> loadXml(String xmlPath) {
		org.dom4j.Document doc = null;
		try {
			doc = new SAXReader().read(xmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = doc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		HashMap<Integer, XmlDomBean> map = new HashMap<Integer, XmlDomBean>();
		while (it.hasNext()) {
			Element e = it.next();
			XmlDomBean bean = new XmlDomBean();
			int id = Integer.valueOf(e.attributeValue("id"));
			bean.setId(id);
			bean.setClazz(e.attributeValue("class"));
			bean.setMethod(e.attributeValue("method"));
			bean.setDescription(e.attributeValue("description"));
			map.put(id, bean);
		}
		return map;
	}

}
