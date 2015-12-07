package mmo.common.config.realmpoint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class GetRealmPoint {
	public static GetRealmPoint instance = null;
	private List<Realm_1> list = new LinkedList<Realm_1>();
	private static int limitLevel;
	private static int count;
	public final static GetRealmPoint getInstance(){
		if(instance == null){
			instance = new GetRealmPoint();
		}
		return instance;
	}
	
	public void init(String filePath){
		SAXBuilder builder = new SAXBuilder();
		try {
			FileInputStream fileInput = new FileInputStream(filePath);
			Document doc = builder.build(fileInput);
			Element root = doc.getRootElement();
			limitLevel = Integer.parseInt(root.getAttributeValue("limitlevel"));
			count = Integer.parseInt(root.getAttributeValue("count"));
			List<Element> children = root.getChildren();			
			for(Element child : children){
				Realm_1 realm = new Realm_1();
				String[] level = child.getAttributeValue("level").split(",");
				int startLevel = Integer.parseInt(level[0]);
				int endLevel = Integer.parseInt(level[1]);
				int realmPoint = Integer.parseInt(child.getAttributeValue("realmpoint"));
				realm.setRealmPoint(realmPoint);
				realm.setStartLevel(startLevel);
				realm.setEndLevel(endLevel);
//				realm.setLimitLevel(limitLevel);
//				realm.setCount(count);
				list.add(realm);
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

	public List<Realm_1> getList() {
		return list;
	}

	public static int getLimitLevel() {
		return limitLevel;
	}

	public static int getCount() {
		return count;
	}
	
}
