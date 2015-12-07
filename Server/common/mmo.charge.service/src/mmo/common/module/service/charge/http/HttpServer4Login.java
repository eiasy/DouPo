package mmo.common.module.service.charge.http;


import java.io.IOException;

import mmo.http.httpserver.HttpServer;
import mmo.tools.log.LoggerError;

public class HttpServer4Login {
	public final static void startServer(int port) {
		HttpServer server = new HttpServer();
		server.setEncoding("UTF-8");
		server.setHttpHandler(new HttpHandlerLogin());
		try {
			server.run(port);
		} catch (IOException e) {
			LoggerError.error("启动Http Server报错", e);
		}
	}
}
