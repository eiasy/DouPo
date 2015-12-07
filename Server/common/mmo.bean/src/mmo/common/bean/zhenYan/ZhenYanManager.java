package mmo.common.bean.zhenYan;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ZhenYanManager {
	/** 配置文件的元素 */
	protected static final String TAG_EXIT      = "exit";
	protected static final String ATT_ID        = "id";
	protected static final String ATT_MONSTERID = "monsterid";
	protected static final String ATT_NOTE      = "note";

	/** 唯一实例 */
	private static ZhenYanManager instance      = new ZhenYanManager();
	/** 阵眼与阵眼描述的映射 */
	private Map<Integer, String>  zhenyanToNote = new HashMap<Integer, String>();
	/** 传送口与阵眼的映射 */
	private Map<Integer, Integer> exitToZhenyan = new HashMap<Integer, Integer>();

	/** 获取实例对象 */
	public static ZhenYanManager getInstance() {
		if (instance == null) {
			instance = new ZhenYanManager();
		}
		return instance;
	}

	/**
	 * 初始化阵眼配置文件
	 * 
	 * @param file
	 *            文件路径
	 */
	public final void init(String file) {
		parseXML(file);
	}

	/**
	 * 判断是否是绑定了传送出口的阵眼
	 * 
	 * @param monsterId
	 *            阵眼ID
	 * @return true 是 false 否
	 */
	public boolean isExitMonster(int monsterId) {
		return zhenyanToNote.containsKey(monsterId);
	}

	/**
	 * 判断是否是阵法内的出口
	 * 
	 * @param exitId
	 *            出口ID
	 * @return true 是 false 否
	 */
	public boolean isZhenfaExit(int exitId) {
		return exitToZhenyan.containsKey(exitId);
	}

	/**
	 * 通过阵法ID获取阵眼ID
	 * 
	 * @param exitId
	 *            阵法ID
	 * @return
	 */
	public int getZhenyanId(int exitId) {
		return exitToZhenyan.get(exitId);
	}

	/**
	 * 获取阵眼对应的描述
	 * 
	 * @param monsterId
	 *            阵眼ID
	 * @return
	 */
	public String getNote(int monsterId) {
		return zhenyanToNote.get(monsterId);
	}

	/**
	 * 解析配置文件
	 * 
	 * @param file
	 *            文件路径
	 */
	public final void parseXML(String file) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(file);
			// 获取根节点
			Element zhenYanRoot = doc.getRootElement();
			List<Element> exits = zhenYanRoot.getChildren(TAG_EXIT);
			if (exits != null) {
				String text = null;
				int exitId = 0;
				int monsterId = 0;
				for (Element exit : exits) {
					text = exit.getAttributeValue(ATT_ID);
					if (text == null || text.trim().length() == 0) {
						LoggerError.messageLog.error("ZHENYAN配置文件ID未填写");
						continue;
					}
					exitId = Integer.parseInt(text.trim());

					text = exit.getAttributeValue(ATT_MONSTERID);
					if (text == null || text.trim().length() == 0) {
						LoggerError.messageLog.error("ZHENYAN配置文件MONSTERID未填写");
						continue;
					}
					monsterId = Integer.parseInt(text.trim());

					text = exit.getAttributeValue(ATT_NOTE);
					if (text == null || text.trim().length() == 0) {
						LoggerError.messageLog.error("ZHENYAN配置文件NOTE未填写");
						continue;
					}
					zhenyanToNote.put(monsterId, text.trim());
					exitToZhenyan.put(exitId, monsterId);
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
