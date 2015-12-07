package mmo.common.bean.explain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.xml.NpcExplainXML;
import mmo.tools.util.FileUtil;

public class ExplainManager {
	/** 唯一实例 */
	private static ExplainManager              instance    = new ExplainManager();
	/** 配置文件子目录 */
	private static final String                SUB_DIR     = "npc";
	/** 配置文件 */
	private static final String                CONFIG_FILE = "function.xml";
	/** 功能说明映射表<key=说明编号,value=说明class> */
	private static Map<Integer, List<Explain>> explainMap  = new HashMap<Integer, List<Explain>>();

	/** 配置文件路径 */
	private String                             cfgFile     = null;

	private ExplainManager() {

	}

	/**
	 * 获取管理器实例
	 * 
	 * @return 管理器实例
	 */
	public static ExplainManager getInstance() {
		if (instance == null) {
			instance = new ExplainManager();
		}
		return instance;
	}

	/**
	 * 初始化说明内容配置信息
	 * 
	 * @param cfgRoot
	 *            文件路径
	 * 
	 */
	public final void init(String cfgRoot) {
		StringBuilder sb = new StringBuilder();
		sb.append(cfgRoot).append(FileUtil.FILE_SEPARATOR).append(SUB_DIR).append(FileUtil.FILE_SEPARATOR).append(CONFIG_FILE);
		cfgFile = sb.toString();
		if (explainMap != null) {
			explainMap.clear();
		} else {
			explainMap = new HashMap<Integer, List<Explain>>();
		}
		NpcExplainXML.parseXML(this, cfgFile);
	}

	/**
	 * 新增一个说明
	 * 
	 * @param npcId
	 *            NPC编号
	 * @param explainList
	 *            说明列表
	 */
	public void addExplain(int npcId, List<Explain> explainList) {
		if (explainMap != null && explainList != null) {
			if (!explainMap.containsKey(npcId)) {
				explainMap.put(npcId, explainList);
			}
		}
	}

	/**
	 * NPC是否有绑定说明
	 * 
	 * @param npcId
	 *            NPC编号
	 * @return true 有绑定说明 fase 为绑定说明
	 */
	public static final boolean isExplain(int npcId) {
		if (explainMap == null) {
			return false;
		}
		if (explainMap.containsKey(npcId)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取说明列表
	 * 
	 * @param npcId
	 *            NPC编号
	 * @return explainList 说明列表
	 */
	public static final List<Explain> getExpList(int npcId) {
		if (explainMap == null || !explainMap.containsKey(npcId)) {
			return null;
		}
		return explainMap.get(npcId);
	}

	/**
	 * 根据npcId和说明ID 获得说明
	 * 
	 * @param npcId
	 *            npc编号
	 * @param explainId
	 *            说明编号
	 * @return 说明
	 */
	public static final Explain getExplain(int npcId, int explainId) {
		List<Explain> explainList = getExpList(npcId);
		Explain explain = null;
		if (explainList != null && explainList.size() > 0) {
			for (int i = 0, len = explainList.size(); i < len; i++) {
				explain = explainList.get(i);
				if (explain.getId() == explainId) {
					return explain;
				}
			}
		}
		return null;
	}
}
