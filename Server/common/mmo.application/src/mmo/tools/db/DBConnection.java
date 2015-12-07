package mmo.tools.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import mmo.tools.log.LoggerError;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class DBConnection {
	private static DataSource dataSource;
//	private static boolean    show_sql = false;

	/**
	 * 
	 * 初始化连接池
	 * 
	 * @param props
	 */
	private final static void initDataSource(Properties dbProperties) {
		try {
			if (dbProperties == null) {
				dbProperties = new Properties();
				dbProperties.load(new FileInputStream(System.getProperty("user.dir") + "\\dbdatasource.properties"));
			}
			Properties cp_props = new Properties();
			for (Object key : dbProperties.keySet()) {
				String skey = (String) key;
				if (skey.startsWith("jdbc.")) {
					String name = skey.substring(5);
					cp_props.put(name, dbProperties.getProperty(skey));
//					if ("show_sql".equalsIgnoreCase(name)) {
//						show_sql = "true".equalsIgnoreCase(dbProperties.getProperty(skey));
//					}
				}
			}

			dataSource = (DataSource) Class.forName(cp_props.getProperty("datasource")).newInstance();
			if (dataSource.getClass().getName().indexOf("c3p0") > 0) {
				// Disable JMX in C3P0
				System.setProperty("com.mchange.v2.c3p0.management.ManagementCoordinator", "com.mchange.v2.c3p0.management.NullManagementCoordinator");
			}

			BeanUtils.populate(dataSource, cp_props);
			Connection conn = getConnection();
			DatabaseMetaData mdm = conn.getMetaData();
			LoggerError.messageLog.warn("Connected to " + mdm.getDatabaseProductName() + " " + mdm.getDatabaseProductVersion());
			// closeConnection();
		} catch (Exception e) {
			LoggerError.error("初始化数据源", e);
		}
	}

	public final static void init(String file) {
		try {
			init(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			LoggerError.error(file + "--不存在", e1);
		}
	}

	public static void init(InputStream source) {
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(source);
			initDataSource(dbProperties);
		} catch (IOException e) {
			LoggerError.error("文件读取失败", e);
		}
	}

	/**
	 * 断开连接池
	 */
	public final static void closeDataSource() {
		try {
			dataSource.getClass().getMethod("close").invoke(dataSource);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			LoggerError.error("Unabled to destroy DataSource!!! ", e);
		}
	}

	public final static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();// conns.get();
			int testCount = 0;
			while (conn == null || conn.isClosed()) {
				System.out.println("conn.isClosed() ===  " + conn.isClosed());
				conn = dataSource.getConnection();
				if (testCount++ > 5) {
					return conn;
				}
			}
		} catch (SQLException e) {
			LoggerError.error("获取连接报错", e);
		}
		return conn;// (show_sql && !Proxy.isProxyClass(conn.getClass())) ? new _DebugConnection(conn).getConnection() :
		            // conn;
	}

	/**
	 * 将连接对象返回给指定名称的连接池
	 * 
	 * @param conn
	 *            连接对象
	 */
	public static final void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			LoggerError.error("释放数据库连接报错", e);
		}
	}

	/**
	 * 用于跟踪执行的SQL语句
	 * 
	 * @author Winter Lau
	 */
	private static class _DebugConnection implements InvocationHandler {
		private final static Logger log  = Logger.getLogger(_DebugConnection.class);
		private Connection          conn = null;

		public _DebugConnection(Connection conn) {
			this.conn = conn;
		}

		public Connection getConnection() {
			return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), this);
		}

		public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
			try {
				String method = m.getName();
				if ("prepareStatement".equals(method) || "createStatement".equals(method))
					log.info("[SQL] >>> " + args[0]);
				return m.invoke(conn, args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}
	}
}