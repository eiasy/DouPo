package mmo.module.admin;

import java.sql.Timestamp;

public final class GameGetbackLog {
	public static final String ID         = "ID";
	public static final String USERID     = "userid";
	public static final String TABLENAMES = "tablenames";
	public static final String ATIME      = "atime";
	public static final String NOTE       = "note";

	private int                id;
	private String             userId;
	private String             tableNames;
	private Timestamp          atime;
	private String             note;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getUserId() {
		return userId;
	}

	public final void setUserId(String userId) {
		this.userId = userId;
	}

	public final String getTableNames() {
		return tableNames;
	}

	public final void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

	public final Timestamp getAtime() {
		return atime;
	}

	public final void setAtime(Timestamp atime) {
		this.atime = atime;
	}

	public final String getNote() {
		return note;
	}

	public final void setNote(String note) {
		this.note = note;
	}
}
