package mmo.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;

import mmo.tools.log.LoggerError;

public class DBConnectUtil {
	public static Connection getConnect(String dbIp, int dbPort, String dbName, String dbUser, String dbPass) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://" + dbIp + ":" + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8", dbUser, dbPass);
		} catch (Exception e) {
			LoggerError.error("获取数据库连接异常", e);
		}
		return con;
	}
}
