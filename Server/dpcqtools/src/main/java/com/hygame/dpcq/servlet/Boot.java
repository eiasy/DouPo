package com.hygame.dpcq.servlet;

import io.netty.channel.Channel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hygame.dpcq.config.Goods;
import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.tools.LogUtil;

public class Boot extends HttpServlet {
	
	/**
	 * 初始化物品
	 */
	public static List<Goods> goodList = new ArrayList<Goods>();
	
	public static Map<String, Channel> channelMap = new HashMap<String, Channel>();

	private static final long serialVersionUID = 8455453740633952540L;

	public void init() throws ServletException {
		try {
			
//	        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
//	        JoranConfigurator configurator = new JoranConfigurator();
//	        configurator.setContext(lc);
//	        lc.reset();
//	        try {
//	        	String root = System.getProperty("user.dir");
//	        	System.out.println(root);
//	        	if (root.contains("bin")) {
//	        		root = root.substring(0, root.length() - 4);
//				}
//	            configurator.doConfigure(root + "/webapps/dpcqtools/WEB-INF/classes/logback.xml");
//	       } catch (JoranException e) {
//	            e.printStackTrace();
//	       }
//	       StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
			
			LogUtil.info("--gm start--");
			
	       
	       //加载所有物品
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConn();
				stat = conn.createStatement();
				rs = stat.executeQuery("select * from `goods`");
				while (rs.next()) {
					Goods goods = new Goods();
					goods.setId(rs.getInt("id"));
					goods.setName(rs.getString("name"));
					goods.setTypeid(rs.getInt("typeid"));
					goodList.add(goods);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(conn, stat, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
