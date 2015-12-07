package mmo.tools.config;

public class NetAddress {
	private String name;
	private String ip;
	private int    port;
	private String url;
	private String user;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		if (this.user != null) {
			this.user = this.user.trim();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		if (this.password != null) {
			this.password = this.password.trim();
		}
	}

	@Override
	public String toString() {
		return "NetAddress [name=" + name + ", ip=" + ip + ", port=" + port + ", url=" + url + "]";
	}
}
