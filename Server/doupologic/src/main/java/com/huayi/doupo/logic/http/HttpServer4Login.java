package com.huayi.doupo.logic.http;

import java.io.IOException;

import mmo.http.httpserver.HttpServer;

public class HttpServer4Login {

	public final static void startServer(int port) {
		HttpServer server = new HttpServer();
		server.setEncoding("UTF-8");
		server.setHttpHandler(new HttpHandlerLogin());
		try {
			server.run(port);
		} catch (IOException e) {
			System.out.println("启动Http Server报错");
		}
	}
}
