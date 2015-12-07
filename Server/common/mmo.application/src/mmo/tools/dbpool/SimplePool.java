package mmo.tools.dbpool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.tools.log.LoggerError;

/**
 * 数据库连接池类
 * 
 * @author 李天喜
 */
public class SimplePool extends Thread implements DBPool {
	protected final long                          timeDiff        = 60 * 60 * 1000;                             // 1小时60分钟，1分钟60秒，1秒1000毫秒
	private String                                configFile      = null;
	private String                                driverClasses;
	private String                                url;
	private String                                user;
	private String                                password;
	private int                                   maxConn;
	private String                                poolName;
	/** 一个连接超过此时间未使用过，则需要更新此连接----1小时 */
	private Driver                                driver;                                                       // 驱动
	private AtomicInteger                         workingCount    = new AtomicInteger(0);
	private ConcurrentLinkedQueue<ConnectionInfo> freeConnections = new ConcurrentLinkedQueue<ConnectionInfo>();

	/**
	 * 构造函数私有化，防止其他对象创建本类实例
	 * 
	 */
	public SimplePool() {
		super("Check Connection");
		loadDrivers();
		createPools();
		start();
	}

	/**
	 * 初始化连接池的配置信息
	 * 
	 * @param source
	 */
	public void init(InputStream source) {
		Properties props = new Properties();
		try {
			props.load(source);
			driverClasses = props.getProperty("drivers");

			Enumeration<?> propNames = props.propertyNames();
			while (propNames.hasMoreElements()) {
				String name = (String) propNames.nextElement();
				if (name.endsWith(".url")) {
					poolName = name.substring(0, name.indexOf("."));
					url = props.getProperty(poolName + ".url");
					if (url == null) {
						LoggerError.messageLog.warn("没有为连接池" + poolName + "指定URL");
						continue;
					}
					user = props.getProperty(poolName + ".user");
					password = props.getProperty(poolName + ".password");
					String maxconn = props.getProperty(poolName + ".maxconn", "0");

					try {
						maxConn = Integer.valueOf(maxconn).intValue();
					} catch (NumberFormatException e) {
						LoggerError.messageLog.warn("错误的最大连接数限制：" + maxconn + ".连接池" + poolName);
						maxConn = 0;
					}
				}
			}
		} catch (Exception e) {
			LoggerError.error("不能读取属性文件。请确保db.properties在CLASSPATH路径下", e);
			return;
		}
	}

	/**
	 * 读取属性完成初始化
	 * 
	 */
	public void init(String config) {
		try {
			configFile = config;
			FileInputStream fis = new FileInputStream(configFile);
			init(fis);
		} catch (Exception e) {
			LoggerError.error("初始化属性文件时抛出异常", e);
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据库连接池的配置信息
	 */
	public void init() {
		init(System.getProperty("user.dir") + "/com/db/dbConfig.properties");
	}

	/**
	 * 定期检查连接的有效性
	 */
	public void run() {
		final long sleepTime = 2 * 1000; // 2秒
		final long refreshTime = 10 * 60 * 1000; // 10分钟运行一次。连接是否应该被更新
		long loop = 0;
		while (true) {

			if (loop % refreshTime == 0) {
				checkOverTime();
			}
			loop += sleepTime;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将连接对象返回给指定名称的连接池
	 * 
	 * @param conn
	 *            连接对象
	 */
	public final void freeConnection(Connection conn) {
		// 将指定连接加入向量末尾
		try {
			freeConnections.offer(new ConnectionInfo(conn));
			Connection conntest = ((ConnectionInfo) freeConnections.peek()).conn;
			if (conntest != null && conntest.isClosed()) {
				freeConnections.poll();
				LoggerError.messageLog.warn("连接池中的最后一个连接已经关闭");
			}
			workingCount.decrementAndGet();
		} catch (SQLException e) {
			LoggerError.error("释放数据库连接时抛出异常", e);
		}
	}

	/**
	 * 获得一个可用的（空闲）的连接，若果没有空闲连接，且已有连接数目小于最大连接数，则创建并返回新连接
	 * 
	 * @return Connection 可用的连接或null
	 */
	public final Connection getConnection() {
		ConnectionInfo connInfo = freeConnections.poll();
		Connection conn = connInfo.conn;
		connInfo.conn = null;

		if (conn != null) { // 如果闲置连接大于或等于最大连接数则返回一个可用连接
			try {
				if (conn.isClosed()) {
					conn = getConnection();// 递归调用自己，尝试获得一个可用连接
				} else {
					workingCount.incrementAndGet();
				}
			} catch (SQLException e) {
				// 递归调用自己，尝试获得一个可用连接
				conn = getConnection();
				LoggerError.error("从连接池删除一个无效连接时出错", e);
			}
		} else if (maxConn > 0) {// 如果闲置连接小于最大连接数，返回一个新连接
			if (workingCount.get() < maxConn) {
				conn = newConnection();
				try {
					if (conn.isClosed()) {
						conn = getConnection();// 递归调用自己，尝试获得一个可用连接
					} else {
						workingCount.incrementAndGet();
					}
				} catch (SQLException e) {
					// 递归调用自己，尝试获得一个可用连接
					conn = getConnection();
					LoggerError.error("从连接池删除一个无效连接时出错", e);
				}
			} else { // 如果闲置连接大于或等于最大连接数则返回一个可用连接
				try {
					wait();
				} catch (InterruptedException e) {
				}
				conn = getConnection();
			}
		} else {
			conn = newConnection();
		}

		return conn;

	}

	/**
	 * 获得一个可用连接，如果没有可用连接，且已有连接数小于最大连接数限制，则创建并返回新连接；否则在指定时间内等待其他线程释放连接
	 * 
	 * @param time
	 *            以毫秒记的等待时间
	 * @return Connection 可用连接或null
	 */
	public final Connection getConnection(long time) {

		long startTime = new Date().getTime();
		Connection conn;
		while ((conn = getConnection()) == null) {
			try {
				wait(time);
			} catch (InterruptedException e) {
				LoggerError.error("获取连接时抛出异常", e);
			}
			if ((new Date().getTime() - startTime) >= time) {
				// wait()返回的原因是超时
				return null;
			}
		}
		return conn;
	}

	public final void releaseConnection() {
		// 等待直到最后一个客户端程序调用
		if (workingCount.get() != 0) {
			return;
		}

		Iterator<ConnectionInfo> allConnections = freeConnections.iterator();
		while (allConnections.hasNext()) {
			Connection conn = ((ConnectionInfo) allConnections.next()).conn;
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				LoggerError.error("无法关闭连接池中的连接", e);
			}
		}
		freeConnections.clear();

		try {
			DriverManager.deregisterDriver(driver);
		} catch (SQLException e) {
			LoggerError.error("无法撤销下列JDBC驱动程序：" + driver.getClass().getName() + "的注册", e);
		}
	}

	/**
	 * 根据属性文件，创建指定的连接池
	 * 
	 * @param props
	 *            连接池属性
	 */
	private final void createPools() {
		initConnPool(maxConn / 2);
		LoggerError.messageLog.warn("创建连接池：" + poolName);
	}

	/**
	 * 装载和注册所有JDBC驱动程序
	 * 
	 * @param prop
	 *            属性
	 */
	private final void loadDrivers() {
		try {
			driver = (Driver) Class.forName(driverClasses).newInstance();
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
			LoggerError.error("无法注册JDBC驱动程序：" + driverClasses, e);
		}
	}

	/**
	 * 创建新的连接
	 */
	private final Connection newConnection() {
		Connection conn = null;
		try {
			if (user == null) {
				conn = DriverManager.getConnection(url);
			} else {
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (SQLException e) {
			LoggerError.error("无法创建下列URL的连接: " + url, e);
			return null;
		}
		return conn;
	}

	private final void initConnPool(int count) {
		for (int i = 0; i < count; i++) {
			freeConnections.offer(new ConnectionInfo(newConnection()));
		}
	}

	private final void checkOverTime() {
		Iterator<ConnectionInfo> connInfos = freeConnections.iterator();
		ConnectionInfo connInfo = null;
		while (connInfos.hasNext()) {
			connInfo = connInfos.next();
			if (connInfo != null) {
				connInfo.checkConnection();
			}
		}
	}

	private final class ConnectionInfo {
		protected Connection conn     = null;
		protected long       lastTime = System.currentTimeMillis();

		public ConnectionInfo(Connection conn) {
			this.conn = conn;
			lastTime = System.currentTimeMillis();
		}

		public final void checkConnection() {
			if (conn == null) {
				return;
			}
			try {
				if (System.currentTimeMillis() - lastTime > timeDiff) {
					conn.close();
					conn = newConnection();
					lastTime = System.currentTimeMillis();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init(Properties properties) {
		// TODO Auto-generated method stub

	}
}
