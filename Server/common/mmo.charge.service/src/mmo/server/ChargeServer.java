package mmo.server;

import mmo.common.module.service.charge.http.HttpServer4Login;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;

public class ChargeServer {
	static {
		ChargeApplication.init();
	}

	public static void main(String[] args) {
		NetAddress na = ProjectCofigs.getNetAddress("charges");
		if (na != null && na.getPort() > 0) {
			HttpServer4Login.startServer(na.getPort());
		}

		// na = ProjectCofigs.getNetAddress("testServer");
		// if (na != null && na.getPort() > 0) {
		// ServerService port4GameServer = new ServerService(na.getPort(),
		// ProPrivateServer_10000.P_10001_NET_CONFIRM,
		// GameServerApplication.getInstance(),
		// new ProtobufServerProtocolHandler());
		// port4GameServer.startServer();
		// }
	}

}
