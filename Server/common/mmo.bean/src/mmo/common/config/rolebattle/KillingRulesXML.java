package mmo.common.config.rolebattle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class KillingRulesXML {
	public static KillingRulesXML instance = null;
	public static int criticalvalue;//杀戮临界值，超过此值就是红名
	public static int lessperhour;//每小时减少的杀戮值
	private Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	
	public final static KillingRulesXML getInstance(){
		if(instance == null){
			instance = new KillingRulesXML();
		}
		return instance;
	}
	
	public void init(String filePath){
		try {
			FileInputStream input = new FileInputStream(filePath);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			criticalvalue = Integer.parseInt(root.getAttributeValue("criticalvalue"));//临界值，超过此值（10）即为红名
			lessperhour = Integer.parseInt(root.getAttributeValue("lessperhour"));//每小时减少杀戮值
			List<Element> children = root.getChildren();
			for(Element child : children){
				int level = Integer.parseInt(child.getAttributeValue("level"));//等级差
				int value = Integer.parseInt(child.getAttributeValue("killingvalue"));//杀戮值
				map.put(level, value);
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

	public Map<Integer, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Integer> map) {
		this.map = map;
	}
	
	
}
