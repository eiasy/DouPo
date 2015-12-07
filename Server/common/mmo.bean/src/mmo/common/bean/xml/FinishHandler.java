package mmo.common.bean.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FinishHandler extends DefaultHandler {
	private final static String TAG_FINISH         = "finish";
	private final static String ATTRIBUTE_FLAG     = "flag";
	private final static String ATTRIBUTE_ID       = "id";
	private final static String ATTRIBUTE_COUNT    = "count";
	private final static String ATTRIBUTE_PROGRESS = "progress";
	private final static String ATTRIBUTE_SCENE    = "scene";
	private final static String ATTRIBUTE_TILEX    = "tilex";
	private final static String ATTRIBUTE_TILEY    = "tiley";
	private final static String ATTRIBUTE_NOTE     = "note";
	private final static String ATTRIBUTE_DIALOG   = "dialog";
	private final static String ATTRIBUTE_INDEX    = "index";
	private final static String ATT_WAY            = "way";
	private final static String ATT_WAYID          = "wayid";
	private static final String ATTRIBUTE_QUALITY  = "quality";
	private static final String ATT_KEYWORD        = "keyword";
	private static final String ATT_ADDITION       = "addition";
//	private Map<Byte, Finish>   finishs            = null;

//	public FinishHandler(Map<Byte, Finish> list) {
//		this.finishs = list;
//	}

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
		if (TAG_FINISH.equals(localName)) {
			int flag = Integer.parseInt(attributes.getValue(ATTRIBUTE_FLAG).trim());
			int id = 0;
			int count = 0;
			int progress = 0;
			int scene = 0;
			byte index = 0;
			byte way = 0;
			int wayid = 0;
			byte quality = -1;
			String temp = null;
			byte addition=0;
			if ((temp = attributes.getValue(ATTRIBUTE_ID)) != null) {
				id = Integer.parseInt(temp.trim());
			}
			if ((temp = attributes.getValue(ATTRIBUTE_COUNT)) != null) {
				count = Integer.parseInt(temp.trim());
			}
			if ((temp = attributes.getValue(ATTRIBUTE_PROGRESS)) != null) {
				progress = Integer.parseInt(temp.trim());
			}
			if ((temp = attributes.getValue(ATT_WAY)) != null) {
				way = Byte.parseByte(temp.trim());
			}
			if ((temp = attributes.getValue(ATT_WAYID)) != null) {
				wayid = Integer.parseInt(temp.trim());
			}
//			Finish fd = new Finish(flag, id, count, progress);
//			if ((temp = attributes.getValue(ATTRIBUTE_INDEX)) != null) {
//				index = Byte.parseByte(temp.trim());
//			}
//			fd.setIndex(index);
//			if ((temp = attributes.getValue(ATTRIBUTE_QUALITY)) != null) {
//				quality = Byte.parseByte(temp.trim());
//			}
//			fd.setQuality(quality);
//			
//			if ((temp = attributes.getValue(ATT_ADDITION)) != null) {
//				addition = Byte.parseByte(temp.trim());
//			}
//			fd.setAddition(addition);
//			
//			if ((temp = attributes.getValue(ATTRIBUTE_SCENE)) != null) {
//				scene = Integer.parseInt(temp.trim());
//			}
//			if ((temp = attributes.getValue(ATTRIBUTE_TILEX)) != null) {
//				fd.setTileX(Short.parseShort(temp.trim()));
//			}
//			if ((temp = attributes.getValue(ATTRIBUTE_TILEY)) != null) {
//				fd.setTileY(Short.parseShort(temp.trim()));
//			}
//			if ((temp = attributes.getValue(ATTRIBUTE_DIALOG)) != null) {
//				fd.setDialog(temp);
//			}
//			if ((temp = attributes.getValue(ATTRIBUTE_NOTE)) != null) {
//				fd.setNote(temp);
//			}
//			if ((temp = attributes.getValue(ATT_KEYWORD)) != null) {
//				fd.setKeyword(temp);
//			}
//			fd.setScene(scene);
//			fd.setFinishWay(way);
//			fd.setWayId(wayid);
//			this.finishs.put(fd.getIndex(), fd);

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
