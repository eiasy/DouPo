package mmo.common.resource.version;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mmo.tools.util.ClassLoaderUtil;

class ResVersionManager {
	private static ResVersionManager instance    = null;
	private static String            cfgXML      = null;
	private Map<Integer, ResVersion> resVersions = new HashMap<Integer, ResVersion>();

	public synchronized static void init(String cfgXML) {
		if (instance == null) {
			ResVersionManager.cfgXML = ClassLoaderUtil.getExtendResource(cfgXML);
			instance = new ResVersionManager();
		}
	}

	public static ResVersionManager getInstance() {
		return instance;
	}

	public static final void reload() {
		ResVersionManager old = instance;
		ResVersionManager rvm = new ResVersionManager();
		instance = rvm;
		old.release();
	}

	private void release() {
		if (resVersions != null) {
			Collection<ResVersion> values = resVersions.values();
			for (ResVersion rv : values) {
				rv.release();
			}
			resVersions.clear();
			resVersions = null;
		}
	}

	/**
	 * 资源版本配置文件
	 * 
	 * @param cfgXML
	 */
	private ResVersionManager() {
		try {
			JDomResManagerHandler.parser(this, ResVersionManager.cfgXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加一套新的资源
	 * 
	 * @param id
	 *            资源ID
	 * @param cfgDir
	 *            资源配置文件存放目录
	 * @param resDir
	 *            资源文件存放目录
	 */
	public void addResVersion(int id, String cfgDir, String resDir) {
		ResVersion resVersion = new ResVersion(id, cfgDir, resDir);
		resVersions.put(resVersion.getId(), resVersion);
	}

	public ResVersion getDefaultVersion() {
		return resVersions.get(1);
	}

	public ResVersion getResVersion(int clientVersion) {
		ResVersion rv = resVersions.get(clientVersion);
		if (rv == null) {
			return getDefaultVersion();
		}
		return rv;
	}

	public int getVersionFinal(int clientVersion) {
		ResVersion rv = resVersions.get(clientVersion);
		if (rv == null) {
			rv = getDefaultVersion();
		}
		return rv.getVersion();
	}
}
