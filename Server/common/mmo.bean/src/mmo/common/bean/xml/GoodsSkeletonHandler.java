package mmo.common.bean.xml;

import java.util.Map;

import mmo.common.config.role.RoleSex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GoodsSkeletonHandler extends DefaultHandler {
	protected final static String TAG_SKELETONS   = "skeletons";
	private final static String   TAG_SKELETON    = "skeleton";
	private final static String   ATTRIBUTE_ID    = "id";
	private final static String   ATTRIBUTE_SEX   = "sex";
	private final static String   ATTRIBUTE_ani   = "ani";

	private Map<Byte, String>     skeletons       = null;
	private Map<Byte, String>     skeletonsFemale = null;

	public GoodsSkeletonHandler(Map<Byte, String> skeletons, Map<Byte, String> skeletonsFemale) {
		this.skeletons = skeletons;
		this.skeletonsFemale = skeletonsFemale;
	}

	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (TAG_SKELETON.equals(localName)) {
			byte id = 0;
			byte sex = 0;
			String ani = null;
			String attribute = attributes.getValue(ATTRIBUTE_ID);
			if (attribute != null) {
				id = Byte.parseByte(attribute);
			}
			attribute = attributes.getValue(ATTRIBUTE_ani);
			if (attribute != null) {
				ani = attribute;
			}
			attribute = attributes.getValue(ATTRIBUTE_SEX);
			if (attribute != null) {
				sex = Byte.parseByte(attribute);
			}
			switch (sex) {
				case RoleSex.FEMALE: {
					skeletonsFemale.put(id, ani);
					break;
				}
				case RoleSex.MALE: {
					skeletons.put(id, ani);
					break;
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
	}

}
