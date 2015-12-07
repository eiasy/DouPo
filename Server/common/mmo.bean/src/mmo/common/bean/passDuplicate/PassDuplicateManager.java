package mmo.common.bean.passDuplicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.xml.PassDuplicateXML;
import mmo.tools.util.FileUtil;

public class PassDuplicateManager {

	/** 配置文件子目录 */
	private static final String                           SUB_DIR     = "passDuplicate";
	/** 配置文件 */
	private static final String                           CONFIG_FILE = "passDuplicate.xml";
	/** 唯一实例 */
	private static PassDuplicateManager                   instance    = new PassDuplicateManager();
	/** 副本对应的场景信息 */
	private static Map<Integer, List<PassDuplicateLayer>> layerMap    = new HashMap<Integer, List<PassDuplicateLayer>>();
	/** 配置文件 */
	private String                                        cfgFile     = null;

	private PassDuplicateManager() {

	}

	/**
	 * 获得管理器实例
	 * 
	 * @return 管理器实例
	 */
	public static final PassDuplicateManager getInstance() {
		if (instance == null) {
			instance = new PassDuplicateManager();
		}
		return instance;
	}

	/**
	 * 初始化VIP配置信息
	 * 
	 * @param cfgRoot
	 *            配置文件根目录
	 */
	public final void init(String cfgRoot) {
		StringBuilder sb = new StringBuilder();
		sb.append(cfgRoot).append(FileUtil.FILE_SEPARATOR).append(SUB_DIR).append(FileUtil.FILE_SEPARATOR).append(CONFIG_FILE);
		cfgFile = sb.toString();
		PassDuplicateXML.parseXML(this, cfgFile);
	}

	/**
	 * 增加一个副本ID与该副本图层信息的映射
	 * 
	 * @param id
	 *            副本ID
	 * @param list
	 *            副本场景信息
	 */
	public final void addPassDuplicateLayer(int id, List<PassDuplicateLayer> list) {
		if (layerMap.containsKey(id)) {
			return;
		}
		layerMap.put(id, list);
	}

	/**
	 * 返回该副本的所有图层ID
	 * 
	 * @param id
	 *            副本ID
	 * @return 副本的所有场景ID,从小到大排序
	 */
	public static final List<Integer> getDuplicateLayerIds(int id) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		if (pdls != null) {
			List<Integer> ids = new ArrayList<Integer>();
			int mapId = 0;
			for (PassDuplicateLayer pdl : pdls) {
				mapId = pdl.getMapId();
				if (ids != null && ids.size() > 0) {
					for (int i = 0, size = ids.size(); i < size; i++) {
						if (ids.get(i) > mapId) {
							ids.add(i, mapId);
							break;
						}
					}
				}
				ids.add(mapId);
			}
			return ids;
		}
		return null;
	}

	/**
	 * 返回当前图层的下一层图层ID
	 * 
	 * @param id
	 *            副本ID
	 * @param mapId
	 *            场景ID
	 * @return 下一层图层ID
	 */
	public static final int getNextMapId(int id, int mapId) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		if (pdls != null) {
			for (PassDuplicateLayer pdl : pdls) {
				if (pdl.getMapId() == mapId) {
					return pdl.getNextMapId();
				}
			}
		}
		return -1;
	}

	/**
	 * 返回TileX坐标
	 * 
	 * @param id
	 *            副本ID
	 * @param mapId
	 *            场景ID
	 * @return tilex坐标
	 */
	public static final int getTileX(int id, int mapId) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		if (pdls != null) {
			for (PassDuplicateLayer pdl : pdls) {
				if (pdl.getMapId() == mapId) {
					return pdl.getTileX();
				}
			}
		}
		return -1;
	}

	/**
	 * 返回TileY坐标
	 * 
	 * @param id
	 *            副本ID
	 * @param mapId
	 *            场景ID
	 * @return tiley坐标
	 */
	public static final int getTileY(int id, int mapId) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		if (pdls != null) {
			for (PassDuplicateLayer pdl : pdls) {
				if (pdl.getMapId() == mapId) {
					return pdl.getTileY();
				}
			}
		}
		return -1;
	}

	/**
	 * 返回该副本的最后一层图层编号
	 * 
	 * @param id
	 *            副本ID
	 * @return 最后一层图层编号
	 */
	public static final int getLastMap(int id) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		int maxId = 0;
		if (pdls != null) {
			int temp = 0;
			for (PassDuplicateLayer pdl : pdls) {
				temp = pdl.getMapId();
				if (temp > maxId) {
					maxId = temp;
				}
			}
		}
		return maxId;
	}

	/**
	 * 当前图层是副本的第几层 
	 */
	public static final byte getLayerIndex(int id, int mapId) {
		List<PassDuplicateLayer> pdls = layerMap.get(id);
		if (pdls != null) {
			for (PassDuplicateLayer pdl : pdls) {
				if (pdl.getMapId() == mapId) {
					return (byte) (pdl.getIndex() + 1);
				}
			}
		}
		return 0;
	}
}
