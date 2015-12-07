package mmo.tools.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLUtil {
	public final static boolean parseFile(String xmlFile, ContentHandler handler) {
		XMLReader xr;
		try {
			xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(handler);
			xr.parse(new InputSource(new InputStreamReader(new FileInputStream(xmlFile))));
		} catch (SAXException e) {
			System.out.println("不能解析文件：" + xmlFile);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public final static boolean parseFile(String xmlFile, String conding, ContentHandler handler) {
		XMLReader xr;
		try {
			xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(handler);
			InputSource source = new InputSource(new InputStreamReader(new FileInputStream(xmlFile), conding));
			source.setEncoding(conding);
			xr.parse(source);
		} catch (SAXException e) {
			System.out.println("不能解析文件：" + xmlFile);
			System.out.println(xmlFile);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public final static boolean parseString(String xmlString, ContentHandler handler) {
		XMLReader xr;
		try {
			xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(handler);
			xr.parse(new InputSource(new StringReader(xmlString)));
		} catch (SAXException e) {
			System.out.println("不能解析字符串：" + xmlString);
			System.out.println(xmlString);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
