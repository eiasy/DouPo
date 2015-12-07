package mmo.common.bean.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.explain.Explain;
import mmo.common.bean.explain.ExplainManager;
import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class NpcExplainXML {
	// XML中的标签
	protected static final String TAG_NPC      = "npc";
	protected static final String TAG_FUNCTION = "function";

	// XML中的属性名
	protected static final String ATT_ID       = "id";
	protected static final String ATT_TITLE    = "title";
	protected static final String ATT_CONTENT  = "content";

	/**
	 * 解析说明配置文件
	 * 
	 * @param ExplainManager
	 *            说明管理器
	 * @param file
	 *            文件路径
	 */
	public static final void parseXML(ExplainManager explainManager, String file){
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
		Element npcs = doc.getRootElement();
		List<Element> elementList = npcs.getChildren(TAG_NPC);
		if(elementList != null){
			String text = null;
			int npcId = 0;
			for(Element npc : elementList){
				text = npc.getAttributeValue(ATT_ID);
				if(text == null || text.trim().length() == 0){
					LoggerError.messageLog.error("说明function配置文件未填写NPCID");
					continue;
				}
				npcId = Integer.parseInt(text.trim());
				if(npcId != 0){
					List<Explain> explainList = new ArrayList<Explain>();
					List<Element> functions = npc.getChildren(TAG_FUNCTION);
					if(functions != null){
						int id = 0;
						String title = null;
						String content = null;
						for(Element fc : functions){
							text = fc.getAttributeValue(ATT_ID);
							if(text == null || text.trim().length() == 0){
								LoggerError.messageLog.error("说明function配置文件未填写FUNCTIONID");
								continue;
							}
							id = Integer.parseInt(text.trim());
							
							text = fc.getAttributeValue(ATT_TITLE);
							if(text == null || text.trim().length() == 0){
								LoggerError.messageLog.error("说明function配置文件未填写TITLE");
								continue;
							}
							title = text;
							
							text = fc.getAttributeValue(ATT_CONTENT);
							if(text == null || text.trim().length() == 0){
								LoggerError.messageLog.error("说明function配置文件未填写CONTENT");
								continue;
							}
							content = text;
							
							Explain explain = new Explain();
							explain.setNpcId(npcId);
							explain.setId(id);
							explain.setTitle(title);
							explain.setContent(content);
							explainList.add(explain);
						}
					}
					explainManager.addExplain(npcId, explainList);
				}
			}
		}		
	}
}
