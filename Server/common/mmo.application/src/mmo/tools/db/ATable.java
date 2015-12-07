package mmo.tools.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mmo.tools.db.DBConnection;

abstract public class ATable {
	protected final static String SYMBOL = "_";

	public ATable() {
	}

	protected void createTableRole(String scriptCreate) {
		if (scriptCreate == null) {
			return;
		}
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		try {
			boolean auto = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.addBatch(scriptCreate);
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(auto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
	}
}
