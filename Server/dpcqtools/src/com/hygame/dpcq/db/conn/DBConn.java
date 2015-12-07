package com.hygame.dpcq.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.hygame.dpcq.tools.PropertyUtil;

/**
 * 数据库操作类
 * @author mp
 * @date 2015-4-22 上午9:11:15
 */
public class DBConn {	
	
	/**
	 * 关闭方法
	 * @author mp
	 * @date 2015-10-13 下午4:19:21
	 * @param conn
	 * @param stat
	 * @param rs
	 * @Description
	 */
	public static void close (Connection conn, Statement stat, ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----关闭连接方法出错----");
		}
	}
	
	public static Statement getStat() throws SQLException {
		Statement stat = null;
		try {
			 stat = getConn().createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stat;
	}
	
	public static Connection getConn () {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        String url = PropertyUtil.getValue("jdbc.url");
	        String name = PropertyUtil.getValue("jdbc.username");
	        String pass = PropertyUtil.getValue("jdbc.password");
	        conn = DriverManager.getConnection(url,name,pass);    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");
		}
		return conn;
	}
}
