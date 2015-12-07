package com.huayi.doupo.logic.http;

import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;

public class HttpServer {
	public static void main(String[] args) {
		NetAddress na = ProjectCofigs.getNetAddress("sdk");
		if (na != null && na.getPort() > 0) {
			HttpServer4Login.startServer(na.getPort());
		}

	}
}
