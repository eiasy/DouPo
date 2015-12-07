package mmo.common.resource.version.xml;

import mmo.common.resource.IResManager;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResManagerHandler extends DefaultHandler {
	protected final static String TAG_VERSIONS     = "versions";
	private final static String   TAG_VERSION      = "version";
	private final static String   ATTRIBUTE_ID     = "id";
	private final static String   ATTRIBUTE_CFGDIR = "cfgdir";
	private final static String   ATTRIBUTE_RESDIR = "resdir";
	private IResManager     versionManager;

	public ResManagerHandler(IResManager versionManager) {
		this.versionManager = versionManager;
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
		if (TAG_VERSION.equals(localName)) {
			int id = Integer.parseInt(attributes.getValue(ATTRIBUTE_ID));
			String cfgDir = attributes.getValue(ATTRIBUTE_CFGDIR);
			String resDir = attributes.getValue(ATTRIBUTE_RESDIR);
			versionManager.addResVersion(id, cfgDir, resDir);
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
