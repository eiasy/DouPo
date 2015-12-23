package mmo.common.service.sdk.doupo.server;

import mmo.common.module.sdk.http.HttpServer4Login;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;

public class ChannelsServer {
	static {
		ChannelsApplication.init();
	}

	public static void main(String[] args) {
		NetAddress na = ProjectCofigs.getNetAddress("sdk");
		if (na != null && na.getPort() > 0) {
			HttpServer4Login.startServer(na.getPort());
		}
	}

}
