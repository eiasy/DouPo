package mmo.common.resource.version;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.resource.DataFile;
import mmo.common.resource.SceneInfo;
import mmo.tools.util.ClassLoaderUtil;

public class ResVersion {
	/** 资源ID */
	private int                      id;
	/** 资源更新历史配置文件存放目录 */
	private String                   cfgDir;
	/** 资源文件存放目录 */
	private String                   resDir;
	/** 资源文件数据管理器 */
	private ResDataManager           dataManager;
	/** 资源最新版本 */
	private int                      version;
	/** 文件最终版本号 */
	private Map<String, FileVersion> fileFinalVersion = new HashMap<String, FileVersion>();
	/** 补丁 */
	private List<VersionPatch>       patches          = new ArrayList<VersionPatch>();

	public ResVersion(int id, String cfgDir, String resDir) {
		this.id = id;
		this.cfgDir = ClassLoaderUtil.getExtendResource(cfgDir);
		this.resDir = ClassLoaderUtil.getExtendResource(resDir);
		init(this.cfgDir, this.resDir);
	}

	public void release() {
		if (fileFinalVersion != null) {
			Collection<FileVersion> values = fileFinalVersion.values();
			for (FileVersion fv : values) {
				fv.release();
			}
			fileFinalVersion.clear();
			fileFinalVersion = null;
		}

		if (patches != null) {
			for (VersionPatch vp : patches) {
				vp.release();
			}
			patches.clear();
			patches = null;
		}
		if (dataManager != null) {
			dataManager.release();
			dataManager = null;
		}
		cfgDir = null;
	}

	protected void init(String cfgDir, String resDir) {
		dataManager = new ResDataManager(resDir);
		generateVersion(cfgDir);
	}

	public int getId() {
		return id;
	}

	public String getCfgDir() {
		return cfgDir;
	}

	public String getResDir() {
		return resDir;
	}

	public ResDataManager getDataManager() {
		return dataManager;
	}

	public FileVersion getFileVersion(String fileName) {
		return fileFinalVersion.get(fileName);
	}

	public int getVersion() {
		return version;
	}

	public DataFile getDataFile(String fileName) {
		return dataManager.getDataFile(fileName);
	}

	public SceneInfo getScene(String sceneName) {
		return dataManager.getScene(sceneName);
	}

	public String getSceneResource(int index) {
		return dataManager.getSceneResource(index);
	}

	public Map<String, FileVersion> getFileFinalVersion() {
		return fileFinalVersion;
	}

	public List<VersionPatch> getPatches() {
		return patches;
	}

	public List<VersionPatch> getPatches(int version) {
		List<VersionPatch> list = new ArrayList<VersionPatch>();
		for (VersionPatch vp : patches) {
			if (vp.getVersion() > version) {
				list.add(vp);
			}
		}
		return list;
	}

	private void generateVersion(String cfgDir) {
		versionFinal(cfgDir + "gameres_HEAD.ini");
		File resourceRoot = new File(cfgDir);
		File[] files = resourceRoot.listFiles(new JavaFileFilter(".patch"));
		for (File file : files) {
			if (file.isFile()) {
				patches.add(new VersionPatch(file.getAbsolutePath()));
			}
		}
	}

	private void versionFinal(String versionFinal) {
		File resPatch = new File(versionFinal);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(resPatch, "r");
			version = raf.readInt();
			short count = raf.readShort();
			for (int i = 0; i < count; i++) {
				String resName = raf.readUTF();
				int ver = raf.readInt();
				boolean client = raf.readBoolean();
				int length = raf.readInt();
				fileFinalVersion.put(resName, new FileVersion(ver, resName, client, length));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
		new ResVersion(1, root + "cfg/", root + "res/");
	}
}
