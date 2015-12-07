package mmo.common.bean.xml;

import java.util.List;

import mmo.common.bean.mission.extension.Action;
import mmo.common.bean.mission.extension.Dialog;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IntercourseHandler extends DefaultHandler {
	private final static String DIALOG  = "对话";
	private final static String CONTENT = "内容";
	private final static String ITEM    = "选项";
	private final static String NOTE    = "描述";
	private final static String CATE    = "类型";
	private final static String COMMAND = "指令";
	private final static String ICON    = "图片";

	private List<Dialog>        finishs = null;
	private Dialog              dialog  = null;
	private boolean             content;
	private StringBuilder        sb      = new StringBuilder();

	public IntercourseHandler(List<Dialog> list) {
		this.finishs = list;
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
		if (DIALOG.equals(localName)) {
			dialog = new Dialog();
			String temp = null;
			if ((temp = attributes.getValue(CATE)) != null) {
				dialog.setCate(Byte.parseByte(temp.trim()));
			}
		} else if (ITEM.equals(localName)) {
			Action action = new Action();
			String temp = null;
			if ((temp = attributes.getValue(NOTE)) != null) {
				action.setName(temp.trim());
			}
			if ((temp = attributes.getValue(CATE)) != null) {
				action.setCode(Byte.parseByte(temp.trim()));
			}
			if ((temp = attributes.getValue(COMMAND)) != null) {
				action.setCommand(temp.trim());
			}
			if ((temp = attributes.getValue(ICON)) != null) {
				action.setIcon(temp.trim());
			}
			if (dialog != null) {
				dialog.addAction(action);
			}
		} else if (CONTENT.equals(localName)) {
			content = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if (DIALOG.equals(localName)) {
			if (dialog != null) {
				this.finishs.add(dialog);
				dialog = null;
			}
		} else if (CONTENT.equals(localName)) {
			dialog.setDialog(sb.toString());
			sb.setLength(0);
			content = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (content && dialog != null) {
			sb.append(ch, start, length);
		}
	}
}
