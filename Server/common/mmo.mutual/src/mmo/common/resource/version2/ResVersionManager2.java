package mmo.common.resource.version2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mmo.common.resource.version2.xml.ResVersionHandler2;
import mmo.tools.util.ClassLoaderUtil;

public class ResVersionManager2 {
	private static ResVersionManager2 instance    = new ResVersionManager2();
	private static String             cfgXML      = null;
	private Map<Integer, ResVersion2> resVersions = new HashMap<Integer, ResVersion2>();

	public final static synchronized void init(String cfgXML) {
		ResVersionManager2.cfgXML = ClassLoaderUtil.getExtendResource(cfgXML);
		if (instance == null) {
			instance = new ResVersionManager2();
		}
		try {
			ResVersionHandler2.parser(instance, ResVersionManager2.cfgXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResVersionManager2 getInstance() {
		return instance;
	}

	public static final void reload() {
		ResVersionManager2 old = instance;
		ResVersionManager2 rvm = new ResVersionManager2();
		try {
			ResVersionHandler2.parser(rvm, ResVersionManager2.cfgXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		instance = rvm;
		old.release();
	}

	private void release() {
		if (resVersions != null) {
			Collection<ResVersion2> values = resVersions.values();
			for (ResVersion2 rv : values) {
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
	private ResVersionManager2() {
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
		ResVersion2 resVersion = new ResVersion2(id, cfgDir, resDir);
		resVersions.put(resVersion.getId(), resVersion);
	}

	public ResVersion2 getDefaultVersion() {
		return resVersions.get(1);
	}

	public ResVersion2 getResVersion(int clientVersion) {
		ResVersion2 rv = resVersions.get(clientVersion);
		if (rv == null) {
			return getDefaultVersion();
		}
		return rv;
	}

	public int getVersionFinal(int clientVersion) {
		ResVersion2 rv = resVersions.get(clientVersion);
		if (rv == null) {
			rv = getDefaultVersion();
		}
		return rv.getVersion();
	}
}
