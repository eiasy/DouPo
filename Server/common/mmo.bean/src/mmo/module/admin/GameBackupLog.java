package mmo.module.admin;

import java.sql.Timestamp;

public class GameBackupLog {
	public final static String ID          = "id";
	public final static String USERID      = "userid";
	public final static String TABLENAMES  = "tablenames";
	public final static String DATAVERSION = "dataversion";
	public final static String ATIME       = "atime";
	public final static String NOTE        = "note";

	private int                id;
	private String             userid;
	private String             tableNames;
	private int                dataVersion;
	private Timestamp          atime;
	private String             note;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}

	public final String getTableNames() {
		return tableNames;
	}

	public final void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

	public final int getDataVersion() {
		return dataVersion;
	}

	public final void setDataVersion(int dataVersion) {
		this.dataVersion = dataVersion;
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
