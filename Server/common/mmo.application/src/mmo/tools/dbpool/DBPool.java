package mmo.tools.dbpool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface DBPool {
	public void init(String file);

	public void init(InputStream is);

	public void init(Properties properties);

	public Connection getConnection() throws SQLException;

	public void freeConnection(Connection connection);

	public void releaseConnection();
}
