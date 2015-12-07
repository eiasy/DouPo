package mmo.common.resource.version2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.resource.DataFile;
import mmo.common.resource.version2.xml.ResDirectoryHandler2;
import mmo.tools.util.FileUtil;

/***
 * 资源版本对应的目录及目录下面的数据
 * 
 * @author 李天喜
 * 
 */
public class DataDirectory {
	/** 版本编号 */
	private int                   version;
	/** 版本资源所在目录 */
	private String                directroy;
	/** 配置文件 */
	private String                cfgFile;
	/** 资源文件数量 */
	private int                   fileCount;
	/** 资源文件数量 */
	private Map<String, DataFile> files    = new HashMap<String, DataFile>();
	private List<DataFile>        fileList = new ArrayList<DataFile>();

	public DataDirectory(int version, String directory, String cfgFile) {
		this.version = version;
		this.directroy = directory;
		init(directory + FileUtil.FILE_SEPARATOR + cfgFile);
	}

	public void init(String cfgFile) {
		this.cfgFile = cfgFile;
		ResDirectoryHandler2.parser(this, this.cfgFile);
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public void addDataFile(int index, int size, String name, String md5) {
		DataFile dataFile = new DataFile();
		dataFile.setId(index);
		dataFile.setFileName(name);
		dataFile.setFilePath(directroy + FileUtil.FILE_SEPARATOR + name);
		dataFile.setMD5(md5);
		dataFile.setSize(size);
		dataFile.setData(FileUtil.getFileByteData(dataFile.getFilePath()));
		files.put(name, dataFile);
		fileList.add(dataFile);
	}

	public DataFile getDataFile(String fileName) {
		return files.get(fileName);
	}

	public List<DataFile> getFileList() {
		return fileList;
	}

	public int getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "DataDirectory [version=" + version + ", fileCount=" + fileCount + ", fileList=" + fileList + "]";
	}
}
