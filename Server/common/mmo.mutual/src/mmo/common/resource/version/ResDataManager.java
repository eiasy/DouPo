package mmo.common.resource.version;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mmo.common.resource.DataFile;
import mmo.common.resource.SceneInfo;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;

public class ResDataManager {
	/** 资源文件数据 */
	private Map<String, DataFile>  dataFiles = new HashMap<String, DataFile>();
	/** 场景用到的所有文件 */
	private String[]               sceneResources;
	/** 场景引用的资源信息 */
	private Map<String, SceneInfo> sceneInfo = new HashMap<String, SceneInfo>();

	private String                 resourceDir;

	public ResDataManager(String resDir) {
		this.resourceDir = resDir;
		init(this.resourceDir, this.resourceDir + "scene.ini");
	}

	public ResDataManager() {

	}

	public void release() {
		if (dataFiles != null) {
			Collection<DataFile> values = dataFiles.values();
			for (DataFile df : values) {
				df.release();
			}
			dataFiles.clear();
			dataFiles = null;
		}

		if (sceneInfo != null) {
			Collection<SceneInfo> values = sceneInfo.values();
			for (SceneInfo si : values) {
				si.release();
			}
			sceneInfo.clear();
			sceneInfo = null;
		}
		if (sceneResources != null) {
			for (int si = 0; si < sceneResources.length; si++) {
				sceneResources[si] = null;
			}
			sceneResources = null;
		}
		resourceDir = null;
	}

	public void init(String root, String sceneInitFile) {
		resourceDir = root;
		RandomAccessFile raf = null;
		try {
			File resourceDirFile = new File(resourceDir);
			File[] files = resourceDirFile.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					DataFile df = new DataFile();
					df.setFileName(file.getName());
					df.setFilePath(file.getAbsolutePath());
					df.setData(FileUtil.getFileByteData(df.getFilePath()));
					dataFiles.put(df.getFileName(), df);
				}
			}
			File sceneIni = new File(sceneInitFile);

			raf = new RandomAccessFile(sceneIni, "r");
			int sceneResCount = raf.readShort();
			String[] resourceList = new String[sceneResCount];
			for (int i = 0; i < sceneResCount; i++) {
				resourceList[i] = raf.readUTF();
			}
			setSceneResources(resourceList);
			int sceneCount = raf.readShort();
			for (int i = 0; i < sceneCount; i++) {
				SceneInfo scene = new SceneInfo();
				scene.setSceneName(raf.readUTF());
				int rc = raf.readShort();
				short[] resIndex = new short[rc];
				for (int j = 0; j < rc; j++) {
					resIndex[j] = raf.readShort();
				}
				scene.setResourceIndex(resIndex);
				addScene(scene);
			}
		} catch (Exception e) {
			LoggerError.error("资源路径:" + resourceDir, e);
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

	public DataFile getDataFile(String fileName) {
		return dataFiles.get(fileName);
	}

	public static void main(String[] args) {
		String resDir = "E:/workspace/PROJECT/XiuXianOnline/develop/serverEngine/version_2012/org.common.game.res/vermanager/default/res/";
		ResDataManager grm = new ResDataManager();
		grm.init(resDir, resDir + "scene.ini");
	}

	public String[] getSceneResources() {
		return sceneResources;
	}

	public void setSceneResources(String[] sceneResources) {
		this.sceneResources = sceneResources;
	}

	public String getSceneResource(int index) {
		if (index < 0 || sceneResources == null || sceneResources.length <= index) {
			return null;
		}
		return sceneResources[index];
	}

	public SceneInfo getScene(String sceneName) {
		if (sceneName == null || sceneInfo == null) {
			return null;
		}
		return sceneInfo.get(sceneName);
	}

	public void addScene(SceneInfo scene) {
		if (scene == null) {
			return;
		}
		if (sceneInfo == null) {
			sceneInfo = new HashMap<String, SceneInfo>();
		}
		sceneInfo.put(scene.getSceneName(), scene);
	}
}
