package mmo.module.cache.queue;

public class ServiceServer {
	private int    productId;
	private String productName;
	private int    appId;
	private String appName;
	private String mqName;
	private int    serverType;
	private long   timeRegister;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getMqName() {
		return mqName;
	}

	public void setMqName(String mqName) {
		this.mqName = mqName;
	}

	public int getServerType() {
		return serverType;
	}

	public void setServerType(int serverType) {
		this.serverType = serverType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public long getTimeRegister() {
		return timeRegister;
	}

	public void setTimeRegister(long timeRegister) {
		this.timeRegister = timeRegister;
	}

	public String serverInfo() {
		return "队列服务 [产品编号:" + productId + ", 产品名:" + productName + ", 应用编号:" + appId + ", 应用名:" + appName + ", 队列名:" + mqName + ", 服务器类型:"
		        + serverType + "]";
	}
}