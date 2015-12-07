package mmo.common.resource.version;

public class FileVersion {
	/** 版本号 */
	private int     version;
	/** 名称 */
	private String  name;
	/** 是否永久保存在客户端 */
	private boolean client;
	/** 文件大小 */
	private int     length;

	public FileVersion(int version, String name, boolean client) {
		this(version, name, client, 0);
	}

	public FileVersion(int version, String name, boolean client, int length) {
		super();
		this.version = version;
		this.name = name;
		this.client = client;
		this.length = length;
	}

	public void release() {
		this.name = null;
	}

	public FileVersion(int version, String name) {
		this(version, name, false);
	}

	public FileVersion(String name) {
		this(0, name, false);
	}

	public int getVersion() {
		return version;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public boolean isClient() {
		return client;
	}

	@Override
	public String toString() {
		return "FileVersion [version=" + version + ", name=" + name + ", client=" + client + "]";
	}
}
