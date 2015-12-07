package mmo.common.resource;

public class DataFile {
	private int    id;
	private String fileName;
	private String filePath;
	private byte[] data;
	private String MD5;
	private int    size;

	public void release() {
		fileName = null;
		filePath = null;
		data = null;
		MD5 = null;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public DataFile() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String file) {
		this.fileName = file;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
    public String toString() {
	    return "DataFile [id=" + id + ", fileName=" + fileName + "]";
    }

	public String getMD5() {
    	return MD5;
    }

	public void setMD5(String mD5) {
    	MD5 = mD5;
    }

	public int getSize() {
    	return size;
    }

	public void setSize(int size) {
    	this.size = size;
    }
}
