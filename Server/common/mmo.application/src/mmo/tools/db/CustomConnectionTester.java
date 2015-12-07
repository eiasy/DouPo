/*
 * Distributed as part of c3p0 v.0.9.2.1
 * 
 * Copyright (C) 2013 Machinery For Change, Inc.
 * 
 * Author: Steve Waldman <swaldman@mchange.com>
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of EITHER:
 * 
 * 1) The GNU Lesser General Public License (LGPL), version 2.1, as published by the Free Software Foundation
 * 
 * OR
 * 
 * 2) The Eclipse Public License (EPL), version 1.0
 * 
 * You may choose which license to accept if you wish to redistribute or modify this work. You may offer derivatives of
 * this work under the license you have chosen, or you may provide the same choice of license which you have been
 * offered here.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * You should have received copies of both LGPL v2.1 and EPL v1.0 along with this software; see the files LICENSE-EPL
 * and LICENSE-LGPL. If not, the text of these licenses are currently available at
 * 
 * LGPL v2.1: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html EPL v1.0:
 * http://www.eclipse.org/org/documents/epl-v10.php
 */

package mmo.tools.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import mmo.tools.log.LoggerError;

import com.mchange.v1.db.sql.ResultSetUtils;
import com.mchange.v1.db.sql.StatementUtils;
import com.mchange.v2.c3p0.AbstractConnectionTester;

public class CustomConnectionTester extends AbstractConnectionTester {
	public CustomConnectionTester() {
	}

	private static final long serialVersionUID = 1L;

	final static int          HASH_CODE        = CustomConnectionTester.class.getName().hashCode();

	final static Set<String>  INVALID_DB_STATES;

	static {
		Set<String> temp = new HashSet<String>();
		temp.add("08001"); // SQL State "Unable to connect to data source"
		temp.add("08007"); // SQL State "Connection failure during transaction"

		INVALID_DB_STATES = Collections.unmodifiableSet(temp);
	}

	public int activeCheckConnection(Connection c, String query, Throwable[] rootCauseOutParamHolder) {

		if (query == null)
			return activeCheckConnectionNoQuery(c, rootCauseOutParamHolder);
		else {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = c.createStatement();
				rs = stmt.executeQuery(query);
				return CONNECTION_IS_OKAY;
			} catch (SQLException e) {
				LoggerError.error("Connection " + c + " failed Connection test with an Exception! [query=" + query + "]", e);

				if (rootCauseOutParamHolder != null)
					rootCauseOutParamHolder[0] = e;

				String state = e.getSQLState();
				if (INVALID_DB_STATES.contains(state)) {
					LoggerError.error("SQL State '" + state + "' of Exception which occurred during a Connection test (test with query '" + query
					        + "') implies that the database is invalid, " + "and the pool should refill itself with fresh Connections.", e);
					return DATABASE_IS_INVALID;
				} else
					return CONNECTION_IS_INVALID;
			} catch (Exception e) {
				LoggerError.error("Connection " + c + " failed Connection test with an Exception!", e);

				if (rootCauseOutParamHolder != null)
					rootCauseOutParamHolder[0] = e;

				return CONNECTION_IS_INVALID;
			} finally {
				ResultSetUtils.attemptClose(rs);
				StatementUtils.attemptClose(stmt);
			}
		}
	}

	public int statusOnException(Connection c, Throwable t, String query, Throwable[] rootCauseOutParamHolder) {
		LoggerError.error("Testing a Connection in response to an Exception:", t);

		try {
			if (t instanceof SQLException) {
				String state = ((SQLException) t).getSQLState();
				if (INVALID_DB_STATES.contains(state)) {
					LoggerError.error("SQL State '" + state + "' of Exception tested by statusOnException() implies that the database is invalid, "
					        + "and the pool should refill itself with fresh Connections.", t);
					return DATABASE_IS_INVALID;
				} else
					return activeCheckConnection(c, query, rootCauseOutParamHolder);
			} else {
				LoggerError.error("Connection test failed because test-provoking Throwable is an unexpected, non-SQLException.", t);
				if (rootCauseOutParamHolder != null)
					rootCauseOutParamHolder[0] = t;
				return CONNECTION_IS_INVALID;
			}
		} catch (Exception e) {
			LoggerError.error("Connection " + c + " failed Connection test with an Exception!", e);

			if (rootCauseOutParamHolder != null)
				rootCauseOutParamHolder[0] = e;

			return CONNECTION_IS_INVALID;
		} finally {
		}
	}

	static String queryInfo(String query) {
		return (query == null ? "[using default system-table query]" : "[query=" + query + "]");
	}

	private int activeCheckConnectionNoQuery(Connection c, Throwable[] rootCauseOutParamHolder) {
		ResultSet rs = null;
		try {
			rs = c.getMetaData().getTables(null, null, "PROBABLYNOT", new String[] { "TABLE" });
			return CONNECTION_IS_OKAY;
		} catch (SQLException e) {
			LoggerError.error("Connection " + c + " failed default system-table Connection test with an Exception!", e);

			if (rootCauseOutParamHolder != null)
				rootCauseOutParamHolder[0] = e;

			String state = e.getSQLState();
			if (INVALID_DB_STATES.contains(state)) {
				LoggerError
				        .error("SQL State '"
				                + state
				                + "' of Exception which occurred during a Connection test (fallback DatabaseMetaData test) implies that the database is invalid, "
				                + "and the pool should refill itself with fresh Connections.", e);
				return DATABASE_IS_INVALID;
			} else
				return CONNECTION_IS_INVALID;
		} catch (Exception e) {
			LoggerError.error("Connection " + c + " failed default system-table Connection test with an Exception!", e);

			if (rootCauseOutParamHolder != null)
				rootCauseOutParamHolder[0] = e;

			return CONNECTION_IS_INVALID;
		} finally {
			ResultSetUtils.attemptClose(rs);
		}
	}

	public boolean equals(Object o) {
		return (o != null && o.getClass() == CustomConnectionTester.class);
	}

	public int hashCode() {
		return HASH_CODE;
	}
}
