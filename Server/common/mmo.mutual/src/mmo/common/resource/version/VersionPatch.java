package mmo.common.resource.version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class VersionPatch {
	/** 版本号 */
	private int               version;
	/** 更新的文件 */
	private List<FileVersion> updateFiles = new ArrayList<FileVersion>();
	/** 被删除的文件 */
	private List<FileVersion> deleteFiles = new ArrayList<FileVersion>();
	/** 补丁文件 */
	private String            patchFile;

	public VersionPatch(String patchFile) {
		this.patchFile = patchFile;
		init(this.patchFile);
	}

	public void release() {
		if (updateFiles != null) {
			for (FileVersion fv : updateFiles) {
				fv.release();
			}
			updateFiles.clear();
			updateFiles = null;
		}
		
		if (deleteFiles != null) {
			for (FileVersion fv : deleteFiles) {
				fv.release();
			}
			deleteFiles.clear();
			deleteFiles = null;
		}
		patchFile = null;
	}

	private void init(String patchFile) {
		File resPatch = new File(patchFile);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(resPatch, "r");
			version = raf.readInt();
			short count = raf.readShort();
			for (int i = 0; i < count; i++) {
				String resName = raf.readUTF();
				int ver = raf.readInt();
				boolean client = raf.readBoolean();
				FileVersion fv = new FileVersion(ver, resName, client);
				updateFiles.add(fv);
			}
			count = raf.readShort();
			for (int i = 0; i < count; i++) {
				String resName = raf.readUTF();
				FileVersion fv = new FileVersion(0, resName, false);
				deleteFiles.add(fv);
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

	public int getVersion() {
		return version;
	}

	public List<FileVersion> getUpdateFiles() {
		return updateFiles;
	}

	public List<FileVersion> getDeleteFiles() {
		return deleteFiles;
	}

	public String getPatchFile() {
		return patchFile;
	}
}
