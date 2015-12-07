package mmo.common.resource.version2;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import mmo.common.resource.version2.xml.ResPlatformHandler2;
import mmo.tools.util.ClassLoaderUtil;
import mmo.tools.util.FileUtil;

public class ResVersion2 {
	private final static String         SUB_CONFIG  = "update.xml";
	/** 资源ID */
	private int                         id;
	/** 资源更新历史配置文件存放目录 */
	private String                      cfgFile;
	/** 资源文件存放目录 */
	private String                      resDir;
	/** 资源最新版本 */
	private int                         version;
	/** 资源目录 */
	private Map<Integer, DataDirectory> directories = new HashMap<Integer, DataDirectory>();

	public ResVersion2(int id, String cfgFile, String resDir) {
		this.id = id;
		this.cfgFile = ClassLoaderUtil.getExtendResource(cfgFile);
		this.resDir = ClassLoaderUtil.getExtendResource(resDir);
		init(this.cfgFile, this.resDir);
	}

	public void release() {
		cfgFile = null;
	}

	protected void init(String cfgDir, String resDir) {
		ResPlatformHandler2.parser(this, this.cfgFile);
	}

	public int getId() {
		return id;
	}

	public String getCfgDir() {
		return cfgFile;
	}

	public String getResDir() {
		return resDir;
	}

	public int getVersion() {
		return version;
	}

	public void addDirectory(int version, String directory) {
		if (directory != null && !"".equals(directory)) {
			DataDirectory dd = new DataDirectory(version, this.resDir + FileUtil.FILE_SEPARATOR + directory, SUB_CONFIG);
			directories.put(version, dd);
		}
		if (version > this.version) {
			this.version = version;
		}
	}

	public DataDirectory getDataDirectory(int version) {
		return directories.get(version);
	}

	public DataDirectory getNextDataDirectory(int currVersion) {
		DataDirectory dataDirectory = null;
		if (currVersion < this.version) {
			for (int vi = currVersion + 1; vi <= version; vi++) {
				dataDirectory = directories.get(vi);
				if (dataDirectory != null) {
					return dataDirectory;
				}
			}
		}
		return null;
	}

	class JavaFileFilter implements FileFilter {
		private String keyword;

		public JavaFileFilter(String keyword) {
			this.keyword = keyword;
		}

		public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(keyword);
		}
	}

	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		root += "/vermanager/default/";
		System.out.println(root);
		new ResVersion2(1, root + "cfg/", root + "res/");
	}
}
